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
            + "                \"links\": [  "
            + "                \"http://orig06.deviantart.net/19fa/f/2015/193/5/2/southern_knights_k_i_t_t__schematic_by_valaryc-d910hnc.jpg\", "
            + "                \"https://s-media-cache-ak0.pinimg.com/564x/32/c5/82/32c5826e0314067849db25919e1717c6.jpg\","
            + "                \"http://www.marsicalive.it/wp-content/uploads/2014/02/supercar-kitt.jpg\","
            + "                \"https://s-media-cache-ak0.pinimg.com/originals/c5/9b/80/c59b8028115be51d7cdbc6321e545419.jpg\","
            + "                \"http://www.3ders.org/images2015/3d-printing-used-help-bring-knight-rider-kitt-car-replica-life-00002.jpg\","
            + "                \"http://static.blastingnews.com/media/photogallery/2014/7/6/300x132/la-mitica-kitt-del-telefilm-supercar_58808.jpg\","
            + "                \"http://www.ansa.it/webimages/foto_large/2014/4/15/542ba23e7d919ab5f7494a6361a4cf44.jpg\","
            + "                \"https://s-media-cache-ak0.pinimg.com/originals/1e/67/c1/1e67c18d5be9bcac0a7498839da11933.jpg\","
            + "                \"http://www.italiaparchi.it/public/img/supercar.jpg\""
            + "],"
            + "                \"id\": \"1234-1234-1234-1234\","
            + "                \"name\" : \"Michael Knight\","
            + "                \"postingfor\" : \"+ 5 years\","
            + "                \"title\": \"KITT the Knight Rider\","
            + "                \"price\": \"100,000,000\","
            + "                \"currency\" : \"£\","
            + "                \"location\": \"London tower, United Kingdom\","
            + "                \"date\": \"1982-02-01T12:12:12Z\"," //yyyy-MM-dd'T'HH:mm:ssZZZZZ
            + "                \"make\": \"Knight Industries Two Thousand\","
            + "                \"transmission\" : \"automatic\","
            + "                \"model\" : \"Deluxe\","
            + "                \"year\" : \"1982\","
            + "                \"colour\" : \"Black\","
            + "                \"enginesize\" : \"6000\","
            + "                \"bodytype\" : \"Saloon\","
            + "                \"sellertype\" : \"Private\","
            + "                \"mileage\" : \"10000\","
            + "                \"fueltype\" : \"Electric\","
            + "                \"longdescription\" : \"The character of KITT (Knight Industries Two Thousand) in the original Knight Rider series was physically embodied as a modified1982 Pontiac Trans Am with numerous special features such as Turbo Boost (which allowed quick bursts of speed or jumping over obstacles), the ability to drive 'himself', a front-mounted scanner bar that (among other things) allowed KITT to 'see' (and a nod to series creator, Glen A. Larson and his Battlestar Galactica's Cylons), and 'molecular bonded shell' body armor, portrayed to be invulnerable to diamond headed drills, small arms fire, the impact of thrown objects, and even high speed impact with cinder block wall. The armor could also resist close explosive blasts although a direct hit could cause severe damage. A refit in the 1985 season included the addition of Super Pursuit Mode and a convertible top. The car's voice was supplied by actor William Daniels.\","
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
