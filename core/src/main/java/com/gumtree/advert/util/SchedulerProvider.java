package com.gumtree.advert.util;

import rx.Scheduler;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */

public interface SchedulerProvider {

    Scheduler mainThread();

    Scheduler backgroundThread();

}
