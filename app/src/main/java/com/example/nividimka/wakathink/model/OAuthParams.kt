package com.example.nividimka.wakathink.model

/**
 * @author Konstantin Tskhovrebov (aka terrakok) on 25.09.17.
 */
data class OAuthParams(
        val appId: String,
        val appKey: String,
        val redirectUrl: String,
        val scope: String
)