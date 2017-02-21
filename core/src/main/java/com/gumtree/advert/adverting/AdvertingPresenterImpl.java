package com.gumtree.advert.adverting;

import com.gumtree.advert.domain.model.Advert;
import com.gumtree.advert.util.SchedulerProvider;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */
public class AdvertingPresenterImpl implements AdvertingPresenter {
    @Inject
    AdvertingInteractor mAdvertingInteractor;

    private AdvertingView view;
    private Subscription subscription = Subscriptions.empty();
    private SchedulerProvider scheduler;

    @Inject
    public AdvertingPresenterImpl(SchedulerProvider scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void bind(AdvertingView view) {
        this.view = view;
    }


    @Override
    public void query(String id) {
        subscription = mAdvertingInteractor.loadAdvert(id)
                .observeOn(scheduler.mainThread())
                .subscribe(advert -> {
                            if (null != view) {
                            view.populateView(advert);
                            }
                        },
                        // handle exceptions
                        throwable -> {
                            if (null != view) {
                                view.hideProgress();

                            }
                        });
    }

    @Override
    public void share(Advert advert) {

    }

    @Override
    public void addFavourite(Advert advert) {

    }

    @Override
    public void onBack() {

    }


    @Override
    public void unbind() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();

        mAdvertingInteractor.unbind();

        view = null;
    }

}
