package com.gumtree.advert.domain.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */
public class FakeInterceptor implements Interceptor {
    // FAKE RESPONSES.
    private final static String MACHINE_ONE = "{"
            + "                \"links\": [  \"http://ciccio.com\" ],"
            + "                \"id\": \"1234-1234-1234-1234\","
            + "                \"title\": \"Mercedes S320 CFI Long Wheel Base 2003\","
            + "                \"price\": \"3,200\","
            + "                \"currency\" : \"£\","
            + "                \"location\": \"Wraysbury, United Kingdom\","
            + "                \"date\": \"2017-02-01'T'12:12:12Z\"," //yyyy-MM-dd'T'HH:mm:ssZZZZZ
            + "                \"make\": \"Mercedes\","
            + "                \"transmission\" : \"automatic\","
            + "                \"model\" : \"S Class\","
            + "                \"year\" : \"2003\","
            + "                \"colour\" : \"black\","
            + "                \"enginesize\" : \"3222\","
            + "                \"bodytype\" : \"Saloon\","
            + "                \"sellertype\" : \"Private\","
            + "                \"mileage\" : \"196000\","
            + "                \"fueltype\" : \"Disel\","
            + "                \"longdescription\" : \"Side Airbags; Passenger Airbag; Bluetooth Telephone; CD Player; Alarm; Anti-Lock Brakes (ABS); Rear Spoiler; Leather Seats; Driver Airbag; Cruise Control; Power-assisted Steering (PAS); Central Locking; Rear seat belts; Immobiliser; Electronic Stability Program (ESP); Full Service History; CD Multichanger; V5 Registration Document; Safety Belt Pretensioners; Xenon headlights; Electric Windows; AM/FM Stereo; Electric Adjustable Seats; Climate Control; Navigation System; Alloy Wheels; Catalytic Converter \","
            + "                \"mobilenumber\" : \"1234-1234-1234\","
            + "                \"mail\" : \"test@test.com\""
            + "}";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;
        String responseString;
        // Get Request URI.


        // Get Query String.
         final String query = chain.request().url().query();
        // Parse the Query String.
        final String[] parsedQuery = query.split("=");
        if (parsedQuery[0].equalsIgnoreCase("id") && parsedQuery[1].equalsIgnoreCase("1234-1234-1234-1234")) {
            responseString = MACHINE_ONE;
        } else {
            responseString = "";
        }

        response = new Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                .addHeader("content-type", "application/json")
                .build();


        return response;
    }
}
