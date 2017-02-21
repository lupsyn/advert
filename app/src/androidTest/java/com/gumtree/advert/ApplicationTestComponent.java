package com.gumtree.advert;

import com.gumtree.advert.activity.MainActivityTest;
import com.gumtree.advert.domain.ApiModule;
import com.gumtree.advert.domain.ClientModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */

@Singleton
@Component(modules = {
        AndroidModule.class,
        ApplicationModule.class,
        ApiModule.class,
        ClientModule.class
})
public interface ApplicationTestComponent extends ApplicationComponent {

    void inject(MainActivityTest activity);

}