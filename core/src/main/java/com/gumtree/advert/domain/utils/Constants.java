package com.gumtree.advert.domain.utils;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */

public abstract class Constants {

    public static final String BASE_URL = "http://unknown";


    public static final int NETWORK_CONNECTION_TIMEOUT = 30; // 30 sec
    public static final long CACHE_SIZE = 10 * 1024 * 1024; // 10 MB
    public static final int CACHE_MAX_AGE = 2; // 2 min
    public static final int CACHE_MAX_STALE = 7; // 7 day

    public static final int CODE_OK = 200;

    public static final String PORTRAIT_XLARGE = "portrait_xlarge";
    public static final String STANDARD_XLARGE = "standard_xlarge";

}
