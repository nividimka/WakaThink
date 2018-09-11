package com.example.nividimka.wakathink.di

import android.app.Application
import android.content.Context
import com.example.nividimka.wakathink.MainApplication
import com.example.nividimka.wakathink.data.AuthHolder
import com.example.nividimka.wakathink.data.storage.PreferenceStorage
import com.example.nividimka.wakathink.model.AppSchedulers
import com.example.nividimka.wakathink.model.OAuthParams
import com.example.nividimka.wakathink.model.SchedulersProvider
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AppModule {

    @Provides
    fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Named("serverPath")
    fun provideServerPath(): String {
        return "https://wakatime.com/"
    }

    @Provides
    fun oauthParams(): OAuthParams {
        return OAuthParams("Oxc0d1ca4CyWcqWiAVMm6iUn",
                "sec_Y9o6q3VTVUODxIlEDYi2a6O84mbNRATVMhWY7WMilj9IIFtLvBcAQzBdPxwlKpPDeT0YL7lMLyS0dTtC",
                "app://wakathink.com/",
                "email,read_stats")
    }

    @Provides
    fun provideAuthHolder(context: Context): AuthHolder {
        return PreferenceStorage(context)
    }

    @Provides
    fun schedulesProvider():SchedulersProvider{
        return AppSchedulers()
    }

}