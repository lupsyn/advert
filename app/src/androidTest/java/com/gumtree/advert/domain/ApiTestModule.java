package com.gumtree.advert.domain;


import com.gumtree.advert.domain.client.GumTreeApi;

import retrofit2.Retrofit;

import static org.mockito.Mockito.mock;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */

public class ApiTestModule extends ApiModule {

    @Override
    public GumTreeApi provideGumTreeApiService(Retrofit retrofit) {
        // replace real MarvelApi with Mock one
        return mock(GumTreeApi.class);
    }

}
