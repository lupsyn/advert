package com.gumtree.advert.activity;


import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.WindowManager;

import com.gumtree.advert.ApplicationTestComponent;
import com.gumtree.advert.GumTreeTestApplication;
import com.gumtree.advert.GumtreeApplication;
import com.gumtree.advert.R;
import com.gumtree.advert.domain.client.GumTreeApi;
import com.gumtree.advert.domain.model.Advert;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;


/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String TEST_ADVERT_ID = "1234-1234-1234-1234";
    private static final String TEST_ADVERT_TITLE = "KITT the Knight Rider";
    private static final String TEST_ADVERT_PRICE = "100,000,000";
    private static final String TEST_ADVERT_NAME = "Enrico";
    private static final String TEST_ADVERT_POSTING = "2Years";
    private static final List TEST_ADVERT_LINK = new ArrayList();
    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(
            MainActivity.class,
            true,
            // false: do not launch the activity immediately
            false);

    @Inject
    GumTreeApi api;


    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();

        GumTreeTestApplication app = (GumTreeTestApplication) instrumentation.getTargetContext().getApplicationContext();
        ApplicationTestComponent component = (ApplicationTestComponent) GumtreeApplication.getComponent();
        // inject dagger
        component.inject(this);
        // Set up the stub we want to return in the mock

        Advert stub = new Advert();
        stub.setId(TEST_ADVERT_ID);
        stub.setTitle(TEST_ADVERT_TITLE);
        stub.setPrice(TEST_ADVERT_PRICE);
        stub.setName(TEST_ADVERT_NAME);
        stub.setPostingfor(TEST_ADVERT_POSTING);
        TEST_ADVERT_LINK.add("http://www.italiaparchi.it/public/img/supercar.jpg");
        stub.setLinks(TEST_ADVERT_LINK);
        // Set up the mock
        when(api.getAdvert(eq(TEST_ADVERT_ID)))
                .thenReturn(Observable.just(stub));
    }

    public void unlockScreen() {
        final MainActivity activity = mainActivity.getActivity();
        Runnable wakeUpDevice = () -> activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        activity.runOnUiThread(wakeUpDevice);
    }

    @Test
    public void shouldBeAbleToOpenMap() {
        // Launch the activity
        mainActivity.launchActivity(new Intent());
        // fix Travis locked device issue
        unlockScreen();
        // search for Test Character
        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.item_title), isDisplayed()));
        onView(withId(R.id.item_open_map)).check(matches(isClickable()));

        // Check view is loaded as we expect it to be
        onView(withId(R.id.item_contact_name)).check(matches(withText(TEST_ADVERT_NAME)));
        onView(withText(TEST_ADVERT_TITLE)).check(matches(withText(TEST_ADVERT_TITLE)));
        onView(withId(R.id.item_title)).check(matches(withText(TEST_ADVERT_TITLE)));

    }


}

