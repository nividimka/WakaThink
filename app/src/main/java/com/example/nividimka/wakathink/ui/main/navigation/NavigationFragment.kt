package com.example.nividimka.wakathink.ui.main.navigation

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.nividimka.wakathink.R
import com.example.nividimka.wakathink.ui.MainActivity
import com.example.nividimka.wakathink.ui.base.BaseFragment

class NavigationFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_navigation
    private var mainActivity: MainActivity? = null

    private var userId: Long? = null

//    private val itemClickListener = { view: View ->
//        presenter.onMenuItemClick(view.tag as MenuItem)
//    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mainActivity = activity as MainActivity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        avatarImageView.setOnClickListener { userId?.let { presenter.onUserClick(it) } }
//
//        logoutIV.setOnClickListener {
//            ConfirmDialog
//                    .newInstants(
//                            msg = getString(R.string.logout_question),
//                            positive = getString(R.string.exit),
//                            tag = CONFIRM_LOGOUT_TAG
//                    )
//                    .show(childFragmentManager, CONFIRM_LOGOUT_TAG)
//        }
//
//        activityMI.tag = ACTIVITY
//        projectsMI.tag = PROJECTS
//        aboutMI.tag = ABOUT
//
//        activityMI.setOnClickListener(itemClickListener)
//        projectsMI.setOnClickListener(itemClickListener)
//        aboutMI.setOnClickListener(itemClickListener)
    }

//    selectMenuItem(item: MenuItem) {
//        (0 until navDrawerMenuContainer.childCount)
//                .map { navDrawerMenuContainer.getChildAt(it) }
//                .forEach { menuItem -> menuItem.tag?.let { menuItem.isSelected = it == item } }
//    }

//    fun onScreenChanged(item: MenuItem) {
//        presenter.onScreenChanged(item)
//    }

//    override fun dialogConfirm(tag: String) {
//        when (tag) {
//            CONFIRM_LOGOUT_TAG -> presenter.onLogoutClick()
//        }
//    }

    private companion object {
        private val CONFIRM_LOGOUT_TAG = "confirm_logout_tag"
    }
}