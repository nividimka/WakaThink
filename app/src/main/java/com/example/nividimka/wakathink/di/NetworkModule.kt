package com.example.nividimka.wakathink.di

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import com.google.gson.Gson
import javax.inject.Singleton
import dagger.Provides
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import android.app.Application
import android.content.Context
import com.example.nividimka.wakathink.BuildConfig
import com.example.nividimka.wakathink.MainApplication
import com.example.nividimka.wakathink.data.retrofit.WakatimeApi
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import okhttp3.Cache
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Named


@Module
class NetworkModule {


    @Provides
    @Singleton
    internal fun provideHttpCache(context: Context): Cache {
        val cacheSize: Long = 10 * 1024 * 1024
        return Cache(context.cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(cache: Cache, context: Context): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(ChuckInterceptor(context))
        client.cache(cache)
        return client.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideApi(retrofit: Retrofit): WakatimeApi {
        return retrofit.create(WakatimeApi::class.java)
    }
}