package com.gumtree.advert.adverting;

import com.gumtree.advert.domain.client.GumTreeApi;
import com.gumtree.advert.domain.model.Advert;
import com.gumtree.advert.util.SchedulerProvider;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.subjects.ReplaySubject;


/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */
@Adverting
public class AdvertingInteractorImpl implements AdvertingInteractor {
    private GumTreeApi mGumTreeApi;
    private SchedulerProvider mSchedulerProvider;
    private ReplaySubject<Advert> advertSubject;
    private Subscription advertSubscription;

    @Inject
    AdvertingInteractorImpl(GumTreeApi api, SchedulerProvider scheduler) {
        this.mGumTreeApi = api;
        this.mSchedulerProvider = scheduler;
    }

    @Override
    public Observable<Advert> loadAdvert(String id) {
        if (advertSubscription == null || advertSubscription.isUnsubscribed()) {
            advertSubject = ReplaySubject.create();

            advertSubscription = mGumTreeApi.getAdvert(id)
                    .subscribeOn(mSchedulerProvider.backgroundThread())
                    .subscribe(advertSubject);
        }

        return advertSubject.asObservable();
    }

    @Override
    public void unbind() {
        if (advertSubscription != null && !advertSubscription.isUnsubscribed())
            advertSubscription.unsubscribe();
    }
}
