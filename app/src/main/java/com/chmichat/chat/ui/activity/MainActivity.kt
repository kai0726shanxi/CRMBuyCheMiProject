package com.chmichat.chat.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import com.flyco.tablayout.listener.CustomTabEntity
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.showToast
import com.chmichat.chat.ui.adapter.AddDialogAdapter
import com.chmichat.chat.ui.dialog.HomeAddDialog
import com.chmichat.chat.ui.fragment.HomeFragment
import com.chmichat.chat.ui.fragment.MeSettingFragment
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


/**
 * @author Jake.Ho
 * created: 2017/10/25
 * desc:
 */


class MainActivity : BaseActivity() {
    private var iv: ImageView? = null
    private var mDialog: HomeAddDialog?=null
    private var mHomeFragment: HomeFragment? = null
    private var mMeSettingFragment: MeSettingFragment? = null


    //默认为0
    private var mIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        switchFragment(mIndex)

    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }


    //初始化底部菜单
    private fun initTab() {

        tab_layout!!.setOnItemSelectedListener { _, _, position ->
            when (position) {
                0 -> switchFragment(0)
                2 -> switchFragment(2)

            }

        }
        btn_add.setOnClickListener {

            mDialog?.show()
        }
    }

    /**
     * 切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 // 首页
            -> mHomeFragment?.let {
                transaction.show(it)
            } ?: HomeFragment.getInstance().let {
                mHomeFragment = it
                transaction.add(R.id.fl_container, it, "home")
            }

            2 //我的界面
            -> mMeSettingFragment?.let {

                transaction.show(it)
            } ?: MeSettingFragment.getInstance().let {
                mMeSettingFragment = it
                transaction.add(R.id.fl_container, it, "mine")
            }

            else -> {

            }
        }

        mIndex = position
        transaction.commitAllowingStateLoss()
    }

    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mMeSettingFragment?.let { transaction.hide(it) }
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (tab_layout != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        mDialog=HomeAddDialog(this)

    }

    override fun initData() {

    }

    override fun start() {

    }

    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast("再按一次退出程序")

            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun View.setHeight(height: Int) {
        val params = layoutParams
        params.height = height
        layoutParams = params
    }


    fun View.setWith(width: Int) {
        val params = layoutParams
        params.width = height
        layoutParams = params
    }

}
