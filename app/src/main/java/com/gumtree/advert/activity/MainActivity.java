package com.gumtree.advert.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gumtree.advert.ApplicationComponent;
import com.gumtree.advert.GumtreeApplication;
import com.gumtree.advert.R;
import com.gumtree.advert.adverting.AdvertingFragment;
import com.gumtree.advert.adverting.MainAdapter;
import com.gumtree.advert.base.BaseActivity;
import com.gumtree.advert.domain.model.Advert;
import com.gumtree.advert.utils.SnappyRecyclerView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    @BindView(R.id.toolbar_one)
    Toolbar toolbar;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;

    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.recycleview)
    SnappyRecyclerView mRecyclerView;

    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_favourite)
    ImageView toolbarFavourite;
    @BindView(R.id.toolbar_share)
    ImageView toolbarShare;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    public static final String TAG_ADVERT_FRAGMENT = "advert_fragment";
    private AdvertingFragment mAdvertingFragment;
    private MainAdapter mMainAdapter;
    CompositeSubscription subscriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // inject views using ButterKnife
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mMainAdapter = new MainAdapter(this, new ArrayList<>());
        mRecyclerView.setAdapter(mMainAdapter);

        Timber.d("Main Activity Created");
        setupToolbar();

        if (null == savedInstanceState) {
            mAdvertingFragment = AdvertingFragment.newInstance();
            attachFragments();
        } else {
            mAdvertingFragment = (AdvertingFragment) getSupportFragmentManager().findFragmentByTag(TAG_ADVERT_FRAGMENT);

        }
        mAppBarLayout.setExpanded(false, true);
        disableScrolling();

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (null == subscriptions || subscriptions.isUnsubscribed())
            subscriptions = new CompositeSubscription();

        subscriptions.addAll(
                mAdvertingFragment.messageObservable()
                        .subscribe(this::showMessage),
                mAdvertingFragment.advertObservable()
                        .subscribe(this::populateView)

        );
    }

    public interface Callback {
        void onLoad();
    }

    public void populateView(Advert advert) {
        mMainAdapter.clear();
        // ...the data has come back, add new items to your adapter...
        mMainAdapter.addAll(advert.getLinks(), new Callback() {
            @Override
            public void onLoad() {
                mAppBarLayout.setExpanded(true, true);
            }
        });
        // Now we call setRefreshing(false) to signal refresh has finished
        mMainAdapter.notifyDataSetChanged();


    }

    public void showMessage(String message) {
        Timber.d("Showing Message: %s", message);
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
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

    @OnClick(R.id.toolbar_back)
    void onToolbarBack(View view) {
        this.onBackPressed();
    }

    @OnClick(R.id.toolbar_share)
    void onToolbarShare(View view) {
    }

    @OnClick(R.id.toolbar_favourite)
    void onToolbarFavourite(View view) {
    }


    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    private void attachFragments() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.advert_fragment, mAdvertingFragment, TAG_ADVERT_FRAGMENT);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void collapseToolbar() {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
        if (behavior != null) {
            behavior.onNestedFling(mCoordinatorLayout, mAppBarLayout, null, 0, 10000, true);
        }
    }

    private void expandToolbar() {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
        if (behavior != null) {
            behavior.setTopAndBottomOffset(0);
            behavior.onNestedPreScroll(mCoordinatorLayout, mAppBarLayout, null, 0, 1, new int[2]);
        }
    }

    private void disableScrolling()
    {
        mRecyclerView.setNestedScrollingEnabled(false);
    }
}
