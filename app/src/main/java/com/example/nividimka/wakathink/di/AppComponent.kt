package com.example.nividimka.wakathink.di

import com.example.nividimka.wakathink.MainApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            NavigationModule::class,
            ActivityBindingModule::class,
            ViewModelModule::class,
            NetworkModule::class
        ]
)
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MainApplication>()
}