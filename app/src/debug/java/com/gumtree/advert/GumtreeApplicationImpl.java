package com.gumtree.advert;


import timber.log.Timber;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */

public class GumtreeApplicationImpl extends GumtreeApplication {

    @Override
    public void initApplication() {

        // initialize Timber in debug version to log
        Timber.plant(new Timber.DebugTree() {
            @Override
            protected String createStackElementTag(StackTraceElement element) {
                // adding line number to logs
                return super.createStackElementTag(element) + ":" + element.getLineNumber();
            }
        });

    }
}
