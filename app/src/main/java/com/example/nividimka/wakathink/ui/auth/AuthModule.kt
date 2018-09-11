package com.example.nividimka.wakathink.ui.auth

import android.arch.lifecycle.ViewModel
import com.example.nividimka.wakathink.di.ViewModelKey
import com.example.nividimka.wakathink.di.scope.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AuthModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    internal abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeAuthfragment(): AuthFragment
}