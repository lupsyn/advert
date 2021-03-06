package com.gumtree.advert.base;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */
public interface BasePresenter<T> {

    void bind(T view);

    void unbind();

}
