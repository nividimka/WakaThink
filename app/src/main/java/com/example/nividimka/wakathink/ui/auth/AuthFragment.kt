package com.example.nividimka.wakathink.ui.auth

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.nividimka.wakathink.R
import com.example.nividimka.wakathink.ui.base.BaseFragment
import com.google.samples.apps.iosched.shared.result.EventObserver
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_auth.*
import java.util.*
import javax.inject.Inject

class AuthFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fragment_auth

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthViewModel::class.java)
        with(webView.settings) {
            javaScriptEnabled = true
//            userAgentString = "waka think user agent"
        }
        viewModel.loadWebViewUrl.observe(this, EventObserver {
            webView.loadUrl(it)
        })
        webView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//                showProgressDialog(true)
//                showEmptyView(false)
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
//                showProgressDialog(false)
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return overrideUrlLoading(view, url)
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                return overrideUrlLoading(view, request.url.toString())
            }

            private fun overrideUrlLoading(view: WebView, url: String): Boolean {
                return viewModel.onRedirected(url)
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
//                showEmptyView(true)
            }
        }
    }


    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack()
        else viewModel.onBackPressed()
    }

}