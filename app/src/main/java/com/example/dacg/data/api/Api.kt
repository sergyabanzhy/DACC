package com.example.dacg.data.api

import com.example.dacg.data.api.vehicle.VehicleResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET(".")
    fun getVehicle(
        @Query("p1Lat") p1Lat: String,
        @Query("p1Lon") p1Lon: String,
        @Query("p2Lat") p2Lat: String,
        @Query("p2Lon") p2Lon: String
    ): Observable<VehicleResponse>

    //This part can be configured in another way.
    companion object {

        val api: Api by lazy {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("https://fake-poi-api.mytaxi.com")
                .build()

            retrofit.create(Api::class.java)
        }
    }
}