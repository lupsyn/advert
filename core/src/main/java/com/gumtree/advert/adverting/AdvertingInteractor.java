package com.gumtree.advert.adverting;

import com.gumtree.advert.base.BaseInteractor;
import com.gumtree.advert.domain.model.Advert;

import rx.Observable;


/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */
public interface AdvertingInteractor extends BaseInteractor {
    Observable<Advert> loadAdvert(String id);
}
