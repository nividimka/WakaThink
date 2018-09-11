package com.example.nividimka.wakathink.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nividimka.wakathink.R
import com.example.nividimka.wakathink.ui.base.BaseFragment
import com.example.nividimka.wakathink.ui.main.my_stats.MyStatsFragment
import com.example.nividimka.wakathink.ui.main.navigation.NavigationFragment

class MainFragment : Fragment() {

    private val currentFragment
        get() = fragmentManager!!.findFragmentById(R.id.mainContainer) as BaseFragment?

    private val drawerFragment
        get() = fragmentManager!!.findFragmentById(R.id.navDrawerContainer) as NavigationFragment?


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentManager!!.beginTransaction()
                .replace(R.id.mainContainer,MyStatsFragment())
                .replace(R.id.navDrawerContainer,NavigationFragment())
                .commit()
    }
}