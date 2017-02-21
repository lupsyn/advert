package com.gumtree.advert;


import com.gumtree.advert.domain.ApiTestModule;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */

public class GumTreeTestApplication extends GumtreeApplicationImpl {

    @Override
    public ApplicationTestComponent createComponent() {
        return DaggerApplicationTestComponent
                .builder()
                .androidModule(new AndroidModule(this))
                // replace Api Module with Mock one
                .apiModule(new ApiTestModule())
                .build();
    }

}
