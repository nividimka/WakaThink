package com.example.nividimka.wakathink.data.repository

import com.example.nividimka.wakathink.data.AuthHolder
import com.example.nividimka.wakathink.data.retrofit.WakatimeApi
import com.example.nividimka.wakathink.model.SchedulersProvider
import javax.inject.Inject

class AuthRepository @Inject constructor(
        private val authData: AuthHolder,
        private val api: WakatimeApi,
        private val schedulers: SchedulersProvider
) {

    val isSignedIn get() = !authData.token.isNullOrEmpty()

    fun requestOAuthToken(
            appId: String,
            appKey: String,
            code: String,
            redirectUri: String
    ) = api
            .auth(appId, appKey, code, redirectUri)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())

    fun saveAuthData(
            token: String
    ) {
        authData.token = token
    }

    fun clearAuthData() {
        authData.token = null
    }
}