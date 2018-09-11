package com.example.nividimka.wakathink.ui.auth

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.nividimka.wakathink.data.AuthHolder
import com.example.nividimka.wakathink.domain.AuthInteractor
import com.example.nividimka.wakathink.ui.Screens
import com.google.samples.apps.iosched.shared.result.Event
import ru.terrakok.cicerone.Router
import java.util.*
import javax.inject.Inject

class AuthViewModel @Inject constructor(val authInteractor: AuthInteractor, var router: Router) : ViewModel() {

    private val _loadWebViewUrl = MutableLiveData<Event<String>>()
    val loadWebViewUrl: LiveData<Event<String>> = _loadWebViewUrl

    init {

        _loadWebViewUrl.postValue(Event(authInteractor.oauthUrl))
    }


    fun onRedirected(url: String): Boolean {
        if (authInteractor.checkOAuthRedirect(url)) {
            Log.e("token", url)
            requestToken(url)
            return true
        } else {
            _loadWebViewUrl.postValue(Event(url))
            return false
        }
    }

    private fun requestToken(url: String) {
        authInteractor.login(url)
//                .doOnSubscribe { viewState.showProgress(true) }
//                .doAfterTerminate { viewState.showProgress(false) }
                .subscribe(
                        { router.navigateTo(Screens.MAIN_FRAGMENT) },
                        { Log.e("hello", "error", it) /*errorHandler.proceed(it, { viewState.showMessage(it) })*/ }
                )
    }

    fun onBackPressed() {
        router.exit()
    }

}