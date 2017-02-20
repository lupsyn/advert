package com.gumtree.advert.adverting;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gumtree.advert.GumtreeApplication;
import com.gumtree.advert.R;
import com.gumtree.advert.base.BaseFragment;
import com.gumtree.advert.domain.model.Advert;

import javax.inject.Inject;

import butterknife.ButterKnife;
import rx.subjects.PublishSubject;
import timber.log.Timber;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */
public class AdvertingFragment extends BaseFragment implements AdvertingView {

    @Inject
    Context context;
    @Inject
    AdvertingPresenter presenter;

    PublishSubject<Advert> notifyCharacter = PublishSubject.create();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AdvertingFragment() {
    }

    public static AdvertingFragment newInstance() {
        AdvertingFragment fragment = new AdvertingFragment();
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_advert, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.query("1234-1234-1234-1234");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        presenter.unbind();
    }



    @Override
    protected void injectDependencies(GumtreeApplication application) {
        application
                .getAdvertingSubComponent()
                .inject(this);
    }

    @Override
    public void showQueryNoResult() {

    }

    @Override
    public void showError(Throwable throwable) {
        Timber.e(throwable, "Error!");

        //showRetryMessage(throwable);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showServiceError(ApiResponseCodeException throwable) {

    }

    @Override
    public void onBack() {

    }
}
