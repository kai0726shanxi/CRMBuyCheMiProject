package com.chmichat.chat.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.ui.activity.*
import com.chmichat.chat.ui.adapter.HomeFragmentHistoryAdapter
import com.chmichat.chat.ui.adapter.HomeFragmentTabAdapter
import com.chmichat.chat.ui.adapter.HomeFragmentUserAdapter
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_home_layout.*

/**首页
 * @Author 20342
 * @Date 2019/9/21 12:56
 */
class HomeFragment : BaseFragment(), View.OnClickListener {


    private val mlist = arrayListOf("1", "2", "3", "4", "5", "6")
    private val muserlist = arrayListOf("1", "2", "3", "4")
    private var mHomeTabAdapter: HomeFragmentTabAdapter? = null
    private var mHomeuserAdapter: HomeFragmentUserAdapter? = null
    private var mHomeHistory: HomeFragmentHistoryAdapter? = null

    companion object {
        fun getInstance(): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home_layout

    override fun initView() {
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, cl_top) }
        recycle_tab.isNestedScrollingEnabled = false
        recycle_history.isNestedScrollingEnabled = false
        recycler_user.isNestedScrollingEnabled = false
        mHomeTabAdapter = activity?.let { HomeFragmentTabAdapter(it, mlist) }
        mHomeuserAdapter = activity?.let { HomeFragmentUserAdapter(it, muserlist) }
        mHomeHistory = activity?.let { HomeFragmentHistoryAdapter(it, mlist) }
        recycle_tab.adapter = mHomeTabAdapter
        recycle_tab.layoutManager = GridLayoutManager(activity, 3)
        recycler_user.adapter = mHomeuserAdapter
        recycler_user.layoutManager = GridLayoutManager(activity, 2)
        recycle_history.adapter = mHomeHistory
        recycle_history.layoutManager = LinearLayoutManager(activity)

        mHomeTabAdapter?.setOnItemClickListener { tag, i ->
            when (i) {
                0 -> {
                    startActivity(Intent(activity, MyScheduleActivity::class.java))

                }
                1 -> {
                    startActivity(Intent(activity, FollowUpRecordsActivity::class.java))

                }
                2 -> {

                    startActivity(Intent(activity, MyClientActivity::class.java))
                }
                3 -> {
                    startActivity(Intent(activity, GroupSentMessageActivity::class.java))

                }
                4 -> {
                    startActivity(Intent(activity, LogBookActivity::class.java))


                }
                5 -> {
                    var intent = Intent(activity, MyLinkManActivity::class.java)
                    startActivity(intent)

                }

            }
        }
        mHomeuserAdapter?.setOnItemClickListener { tag, i ->
            when (i) {
                0 -> {
                }
                1 -> {
                    startActivity(Intent(activity, PotentialCustomerActivity::class.java))
                }
                2 -> {

                }
                3 -> {
                    startActivity(Intent(activity, MassRecordActivity::class.java))

                }
            }

        }

    }

    override fun lazyLoad() {
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}