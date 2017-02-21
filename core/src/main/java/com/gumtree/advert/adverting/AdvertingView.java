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
public interface AdvertingView extends BaseView{

    void showQueryNoResult();

    void showError(Throwable throwable);

    void showProgress();

    void hideProgress();

    void showServiceError(ApiResponseCodeException throwable);

    void onBack();

    void disableScroll();

    void enableScroll();

//    void populateGallery(List<String> links);
//
    void populateView(Advert advert);

}
