package com.gumtree.advert.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gumtree.advert.ApplicationComponent;
import com.gumtree.advert.GumtreeApplication;


/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDependencies(GumtreeApplication.get(this), GumtreeApplication.getComponent());

        // can be used for general purpose in all Activities of Application
    }

    protected abstract void injectDependencies(GumtreeApplication application, ApplicationComponent component);

    @Override
    public void finish() {
        super.finish();

        releaseSubComponents(GumtreeApplication.get(this));
    }

    protected abstract void releaseSubComponents(GumtreeApplication application);

}
