package com.gumtree.advert.adverting;

import dagger.Module;
import dagger.Provides;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */
@Module
public class AdvertingModule {

    @Provides
    @Adverting
    public AdvertingInteractor provideInteractor(AdvertingInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    @Adverting
    public AdvertingPresenter providePresenter(AdvertingPresenterImpl presenter) {
        return presenter;
    }

}
