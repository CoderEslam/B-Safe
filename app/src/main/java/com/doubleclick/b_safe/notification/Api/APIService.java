package com.doubleclick.b_safe.notification.Api;


import com.doubleclick.b_safe.model.MyResponse;
import com.doubleclick.b_safe.model.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA9vhNrMQ:APA91bEbCbkuDv_VhOTRjYYVSNS449SU7_zGUlpN_AQj8iltoSjyt0xYqeYjb0eJU-BVFFoyfNm6BvNcpnnqGAxJIL-e6cYYZEnFBdxaj-HM2hmt60hkDGf-if6YxOd5oloZv46HovH3"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}

