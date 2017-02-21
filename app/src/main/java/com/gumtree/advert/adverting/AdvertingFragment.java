package com.gumtree.advert.adverting;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gumtree.advert.GumtreeApplication;
import com.gumtree.advert.R;
import com.gumtree.advert.base.BaseFragment;
import com.gumtree.advert.domain.model.Advert;
import com.gumtree.advert.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;
import timber.log.Timber;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */
public class AdvertingFragment extends BaseFragment implements AdvertingView {

    @Inject
    Context context;
    @Inject
    AdvertingPresenter presenter;


    @BindView(R.id.item_title)
    TextView mItemTitle;
    @BindView(R.id.item_location)
    TextView mItemLocation;
    @BindView(R.id.item_date)
    TextView mItemDate;
    @BindView(R.id.item_make)
    TextView mItemMake;
    @BindView(R.id.item_transmission)
    TextView mItemTransmission;
    @BindView(R.id.item_model)
    TextView mItemModel;
    @BindView(R.id.item_year)
    TextView mItemYear;
    @BindView(R.id.item_colour)
    TextView mItemColour;
    @BindView(R.id.item_engine_size)
    TextView mItemEngineSize;
    @BindView(R.id.item_body_type)
    TextView mItemBodyType;
    @BindView(R.id.item_seller_type)
    TextView mItemSellerType;
    @BindView(R.id.item_mile_age)
    TextView mItemMileAge;
    @BindView(R.id.item_fuel_type)
    TextView mItemFuelType;
    @BindView(R.id.item_price)
    TextView mItemPrice;


    PublishSubject<Advert> notifyAdvert = PublishSubject.create();
    PublishSubject<String> notifyMessage = PublishSubject.create();


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AdvertingFragment() {
    }

    public static AdvertingFragment newInstance() {
        AdvertingFragment fragment = new AdvertingFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_advert, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        presenter.bind(this);
    }


    public Observable<Advert> advertObservable() {
        return notifyAdvert.asObservable();
    }

    public Observable<String> messageObservable() {
        return notifyMessage.asObservable();
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.query("1234-1234-1234-1234");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.unbind();
    }


    @Override
    protected void injectDependencies(GumtreeApplication application) {
        application
                .getAdvertingSubComponent()
                .inject(this);
    }

    @Override
    public void showQueryNoResult() {

    }

    @Override
    public void showError(Throwable throwable) {
        Timber.e(throwable, "Error!");

        //showRetryMessage(throwable);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showServiceError(ApiResponseCodeException throwable) {

    }

    @Override
    public void onBack() {

    }

    @Override
    public void disableScroll() {

    }

    @Override
    public void enableScroll() {

    }

    @Override
    public void populateView(Advert advert) {
        notifyAdvert.onNext(advert);
        fillField(advert);


    }

    @Override
    public void showMessage(String message) {
        notifyMessage.onNext(message);
    }

    private void fillField(Advert advert) {
        mItemTitle.setText(advert.getTitle());
        String price = advert.getCurrency() + " " + advert.getPrice();
        mItemPrice.setText(price);
        mItemLocation.setText(advert.getLocation());
        mItemMake.setText(advert.getMake());
        mItemDate.setText(DateUtil.formatHumanReadable(DateUtil.parseDate(advert.getDate())));
        mItemTransmission.setText(advert.getTransmission());
        mItemModel.setText(advert.getModel());
        mItemYear.setText(advert.getYear());
        mItemColour.setText(advert.getColour());
        mItemEngineSize.setText(advert.getEnginesize());
        mItemBodyType.setText(advert.getBodytype());
        mItemSellerType.setText(advert.getSellertype());
        mItemMileAge.setText(advert.getMileage());
        mItemFuelType.setText(advert.getFueltype());
    }
}
