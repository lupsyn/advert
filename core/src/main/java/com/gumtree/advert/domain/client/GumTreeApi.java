package com.gumtree.advert.domain.client;



import com.gumtree.advert.domain.model.Advert;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */

public interface GumTreeApi {
    String ID = "id";

    @GET("v1/adverts")
    Observable<Advert> getAdvert(@Query(ID) String id);
}
