package com.gumtree.advert.adverting;

import com.gumtree.advert.base.BasePresenter;
import com.gumtree.advert.domain.model.Advert;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */
interface AdvertingPresenter extends BasePresenter<AdvertingView> {

    void query(String id);

    void share(Advert advert);

    void addFavourite(Advert advert);

    void onBack();

    void onCompassClick();

    void onCallClick();

    void onShareClick();

    void onSmsClick();
}
