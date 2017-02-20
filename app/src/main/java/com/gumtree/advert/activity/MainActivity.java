package com.gumtree.advert.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.gumtree.advert.ApplicationComponent;
import com.gumtree.advert.GumtreeApplication;
import com.gumtree.advert.R;
import com.gumtree.advert.adverting.AdvertingFragment;
import com.gumtree.advert.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */


public class MainActivity extends BaseActivity {
    @Inject
    Context context;

    // injecting views via ButterKnife
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static final String TAG_ADVERT_FRAGMENT = "advert_fragment";
    private AdvertingFragment mAdvertingFragment;

    CompositeSubscription subscriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.d("Main Activity Created");

        if (null == savedInstanceState) {
            mAdvertingFragment = AdvertingFragment.newInstance();

            attachFragments();
        } else {
            mAdvertingFragment = (AdvertingFragment) getSupportFragmentManager().findFragmentByTag(TAG_ADVERT_FRAGMENT);

        }



    }

    @Override
    protected void onPause() {
        super.onPause();

        subscriptions.unsubscribe();
    }


    @Override
    protected void injectDependencies(GumtreeApplication application, ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    protected void releaseSubComponents(GumtreeApplication application) {
        application.releaseAdvertingSubComponent();
    }


    private void setupToolbar() {
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.logo);
//        getSupportActionBar().setTitle(R.string.main_title);
    }

    private void attachFragments() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.advert_fragment, mAdvertingFragment, TAG_ADVERT_FRAGMENT);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
