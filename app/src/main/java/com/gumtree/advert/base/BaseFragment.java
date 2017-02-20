package com.gumtree.advert.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.gumtree.advert.GumtreeApplication;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */


public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        injectDependencies(GumtreeApplication.get(getContext()));

        // can be used for general purpose in all Fragments of Application
    }

    protected abstract void injectDependencies(GumtreeApplication application);

}