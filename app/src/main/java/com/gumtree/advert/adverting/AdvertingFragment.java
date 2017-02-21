package com.gumtree.advert.adverting;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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
import butterknife.OnClick;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;
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
    @BindView(R.id.item_long_description)
    TextView mItemLongDescr;
    @BindView(R.id.mapImageView)
    MapView mMapView;
    @BindView(R.id.item_open_map)
    ImageView mOpenMap;


    private GoogleMap map;
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
        mMapView.onCreate(savedInstanceState);
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
        mMapView.onResume();
        presenter.query("1234-1234-1234-1234");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.unbind();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    protected void injectDependencies(GumtreeApplication application) {
        application
                .getAdvertingSubComponent()
                .inject(this);
    }


    @Override
    public void showError(Throwable throwable) {
        Timber.e(throwable, "Error!");
    }

    @Override
    public void showServiceError(ApiResponseCodeException throwable) {

    }


    @Override
    public void populateView(Advert advert) {
        notifyAdvert.onNext(advert);
        fillField(advert);


    }

    @Override
    public void showInMap(Advert advert) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:<" + advert.getLat() + ">,<" + advert.getLng() + ">?q=<" + advert.getLat() + ">,<" + advert.getLng() + ">(" + advert.getTitle() + ")"));
        startActivity(intent);

    }

    @Override
    public void call(Advert advert) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", advert.getMobilenumber(), null)));
    }

    @Override
    public void sms(Advert advert) {
        try {
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("smsto:" + Uri.encode(advert.getMobilenumber())));
            smsIntent.putExtra("address", advert.getMobilenumber());
            smsIntent.putExtra("sms_body", "Hi, i'd kindly ask information about : " + advert.getTitle());

            PackageManager pm = getActivity().getPackageManager();
            List<ResolveInfo> resInfo = pm.queryIntentActivities(smsIntent, 0);

            for (int i = 0; i < resInfo.size(); i++) {
                ResolveInfo ri = resInfo.get(i);
                String packageName = ri.activityInfo.packageName;

                if (packageName.contains("sms")) {
                    //Log.d("TAG", packageName + " : " + ri.activityInfo.name);
                    smsIntent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                }
            }
            getActivity().startActivity(smsIntent);
        } catch (Exception e) {
            Timber.e(e, "Error!");
            // Handle Error
        }
    }

    @Override
    public void share(Advert advert) {
        ShareCompat.IntentBuilder
                .from(getActivity()) // getActivity() or activity field if within Fragment
                .setText(advert.getTitle() + " " + advert.getCurrency() + " " + advert.getPrice())
                .setType("text/plain") // most general text sharing MIME type
                .setChooserTitle(advert.getTitle())
                .startChooser();
    }

    @OnClick(R.id.item_open_map)
    void onCompassClick(View view) {
        presenter.onCompassClick();
    }

    public void onShareClick() {
        presenter.onShareClick();
    }

    public void onCallClick() {
        presenter.onCallClick();
    }

    public void onSmsClick() {
        presenter.onSmsClick();
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
        mItemLongDescr.setText(advert.getLongdescription());
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MapsInitializer.initialize(getContext());
                map = googleMap;
                setMapLocation(map, new NamedLocation(advert.getLocation(), new LatLng(advert.getLat(), advert.getLng())));
            }
        });
    }

    private static void setMapLocation(GoogleMap map, NamedLocation data) {
        // Add a marker for this item and set the camera
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(data.location, 18f));
        map.addMarker(new MarkerOptions().position(data.location));

        // Set the map type back to normal.
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    /**
     * Location represented by a position ({@link com.google.android.gms.maps.model.LatLng} and a
     * name ({@link java.lang.String}).
     */
    private static class NamedLocation {

        public final String name;

        public final LatLng location;

        NamedLocation(String name, LatLng location) {
            this.name = name;
            this.location = location;
        }
    }
}
