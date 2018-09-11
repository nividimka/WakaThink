package com.example.nividimka.wakathink.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.widget.Toast
import com.example.nividimka.wakathink.R
import com.example.nividimka.wakathink.data.AuthHolder
import com.example.nividimka.wakathink.extentions.inTransaction
import com.example.nividimka.wakathink.ui.auth.AuthFragment
import com.example.nividimka.wakathink.ui.base.BaseFragment
import com.example.nividimka.wakathink.ui.main.MainFragment
import dagger.android.support.DaggerAppCompatActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var authHolder: AuthHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            if (authHolder.token == null)
                navigator.applyCommands(arrayOf(Replace(Screens.AUTH_FRAGMENT, null)))
            else
                navigator.applyCommands(arrayOf(Replace(Screens.MAIN_FRAGMENT, null)))
        }
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }


    private val navigator = object : SupportFragmentNavigator(supportFragmentManager, R.id.fragment_container) {
        override fun createFragment(screenKey: String, data: Any?): Fragment? {
            when (screenKey) {
                Screens.AUTH_FRAGMENT -> return AuthFragment()
                Screens.MAIN_FRAGMENT -> return MainFragment()
            }
            return null
        }

        override fun showSystemMessage(message: String) {
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }

        override fun exit() {
            finish()
        }

        override fun applyCommands(commands: Array<Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }


    override fun onBackPressed() {
        (supportFragmentManager?.findFragmentById(R.id.fragment_container) as BaseFragment).onBackPressed()
    }
}

