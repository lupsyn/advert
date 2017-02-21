package com.gumtree.advert.adverting;

import com.gumtree.advert.base.BaseView;
import com.gumtree.advert.domain.model.Advert;

import java.util.List;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */
public interface AdvertingView extends BaseView {

    void showError(Throwable throwable);

    void showServiceError(ApiResponseCodeException throwable);

    void populateView(Advert advert);

    void showInMap(Advert advert);

    void call(Advert advert);

    void sms(Advert advert);

    void share(Advert advert);

}
