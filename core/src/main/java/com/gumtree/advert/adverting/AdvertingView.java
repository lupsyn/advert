package com.gumtree.advert.adverting;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */
public interface AdvertingView{

    void showQueryNoResult();

    void showError(Throwable throwable);

    void showProgress();

    void hideProgress();

    void showServiceError(ApiResponseCodeException throwable);

    void onBack();

}
