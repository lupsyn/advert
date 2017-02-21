package com.gumtree.advert.test.support;

import android.support.annotation.StringRes;

import org.robolectric.RuntimeEnvironment;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */

public class ResourceLocator {

    public static String getString(@StringRes int id) {
        return RuntimeEnvironment.application.getString(id);
    }

}
