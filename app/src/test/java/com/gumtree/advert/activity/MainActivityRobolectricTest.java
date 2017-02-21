package com.gumtree.advert.activity;

import com.gumtree.advert.BuildConfig;
import com.gumtree.advert.R;
import com.gumtree.advert.test.support.ShadowSnackbar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static com.gumtree.advert.test.support.Assert.assertSnackbarIsShown;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;


/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, shadows = {ShadowSnackbar.class})
public class MainActivityRobolectricTest {

    private final static String TEST_TEXT = "This is a test text.";
    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity.class);

        assertNotNull(activity);
    }

    @Test
    public void testShowToastMessage() throws Exception {
        activity.showMessage(TEST_TEXT);

        assertThat(TEST_TEXT, equalTo(ShadowToast.getTextOfLatestToast()));
    }

}
