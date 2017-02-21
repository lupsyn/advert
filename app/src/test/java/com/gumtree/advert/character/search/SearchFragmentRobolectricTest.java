package com.gumtree.advert.character.search;

import com.gumtree.advert.BuildConfig;
import com.gumtree.advert.R;
import com.gumtree.advert.activity.MainActivity;
import com.gumtree.advert.adverting.AdvertingFragment;
import com.gumtree.advert.test.support.ShadowSnackbar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static com.gumtree.advert.test.support.Assert.assertProgressDialogIsShown;
import static com.gumtree.advert.test.support.Assert.assertSnackbarIsShown;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, shadows = {ShadowSnackbar.class})
public class SearchFragmentRobolectricTest {

    private MainActivity activity;
    private AdvertingFragment fragment;

    @Before
    public void setUp() throws Exception {
        // setup activity
        activity = Robolectric.setupActivity(MainActivity.class);
        assertNotNull(activity);

        // setup fragment
        fragment = (AdvertingFragment) activity.getSupportFragmentManager().findFragmentByTag(MainActivity.TAG_ADVERT_FRAGMENT);
        assertNotNull(fragment);

    }


    
}