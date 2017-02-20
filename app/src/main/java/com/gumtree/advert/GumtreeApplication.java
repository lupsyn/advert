package com.gumtree.advert;

import android.app.Application;
import android.content.Context;

import com.gumtree.advert.adverting.AdvertingSubComponent;
import com.gumtree.advert.adverting.AdvertingModule;


/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */

public abstract class GumtreeApplication extends Application {

    private static ApplicationComponent component;
    private AdvertingSubComponent mAdvertingSubComponent;

    public static ApplicationComponent getComponent() {
        return component;
    }

    public static GumtreeApplication get(Context context) {
        return (GumtreeApplication) context.getApplicationContext();
    }

    public AdvertingSubComponent getAdvertingSubComponent() {
        if (null == mAdvertingSubComponent)
            createAdvertingSubComponent();

        return mAdvertingSubComponent;
    }

    public AdvertingSubComponent createAdvertingSubComponent() {
        mAdvertingSubComponent = component.plus(new AdvertingModule());
        return mAdvertingSubComponent;
    }

    public void releaseAdvertingSubComponent() {
        mAdvertingSubComponent = null;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        initApplication();

        component = createComponent();
    }

    public ApplicationComponent createComponent() {
        return DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();
    }

    public abstract void initApplication();

}
