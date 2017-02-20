package com.gumtree.advert.adverting;

import dagger.Subcomponent;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */
@Adverting
@Subcomponent(modules = {
        AdvertingModule.class
})
public interface AdvertingSubComponent {
    void inject(AdvertingFragment fragment);
}
