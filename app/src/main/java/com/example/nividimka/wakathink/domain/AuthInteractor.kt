package com.example.nividimka.wakathink.domain

import android.util.Log
import com.example.nividimka.wakathink.data.repository.AuthRepository
import com.example.nividimka.wakathink.model.OAuthParams
import io.reactivex.Completable
import java.net.URI
import java.util.*
import javax.inject.Inject
import javax.inject.Named

/**
 * @author Konstantin Tskhovrebov (aka terrakok) on 23.04.17.
 */
class AuthInteractor(
        private val serverPath: String,
        private val authRepository: AuthRepository,
        private val hash: String,
        private val oauthParams: OAuthParams) {

    @Inject constructor(
            @Named("serverPath") serverPath: String,
            authRepository: AuthRepository,
            oauthParams: OAuthParams
    ) : this(
            serverPath,
            authRepository,
            UUID.randomUUID().toString(),
            oauthParams
    )

    val oauthUrl = "${serverPath}oauth/authorize?client_id=${oauthParams.appId}" +
            "&redirect_uri=${oauthParams.redirectUrl}&response_type=code&state=$hash&scope=${oauthParams.scope}"




    fun checkOAuthRedirect(url: String) = url.indexOf(oauthParams.redirectUrl) == 0

    fun isSignedIn() = authRepository.isSignedIn

    fun login(oauthRedirect: String) =
            Completable.defer {
                if (oauthRedirect.contains(hash)) {
                    authRepository
                            .requestOAuthToken(
                                    oauthParams.appId,
                                    oauthParams.appKey,
                                    getQueryParameterFromUri(oauthRedirect, PARAMETER_CODE),
                                    oauthParams.redirectUrl
                            )
                            .doOnSuccess {
                                Log.e("json", it.toString())
                                authRepository.saveAuthData(it.token)
                            }
                            .toCompletable()
                } else {
                    Completable.error(RuntimeException("Not valid oauth hash!"))
                }
            }


    fun logout() {
        authRepository.clearAuthData()
    }

    private fun getQueryParameterFromUri(url: String, queryName: String): String {
        val uri = URI(url)
        val query = uri.query
        val parameters = query.split("&")

        var code = ""
        for (parameter in parameters) {
            if (parameter.startsWith(queryName)) {
                code = parameter.substring(queryName.length + 1)
                break
            }
        }
        return code
    }

    companion object {
        private const val PARAMETER_CODE = "code"
    }
}