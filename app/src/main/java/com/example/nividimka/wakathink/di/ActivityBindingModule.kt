package com.example.nividimka.wakathink.di

import com.example.nividimka.wakathink.ui.MainActivity
import com.example.nividimka.wakathink.di.scope.ActivityScoped
import com.example.nividimka.wakathink.ui.auth.AuthFragment
import com.example.nividimka.wakathink.ui.auth.AuthModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules =[AuthModule::class])
    internal abstract fun mainActivity(): MainActivity

}