package com.gumtree.advert;



import com.gumtree.advert.activity.MainActivity;
import com.gumtree.advert.adverting.AdvertingSubComponent;
import com.gumtree.advert.adverting.AdvertingModule;
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
public interface ApplicationComponent {

    void inject(MainActivity activity);

    AdvertingSubComponent plus(AdvertingModule module);


}