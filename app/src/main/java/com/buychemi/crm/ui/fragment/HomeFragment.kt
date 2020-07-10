package com.buychemi.crm.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseFragment
import com.buychemi.crm.bean.BriefingEntity
import com.buychemi.crm.bean.UserLogEntity
import com.buychemi.crm.mvp.contract.HomeContract
import com.buychemi.crm.mvp.presenter.HomePresenter
import com.buychemi.crm.ui.activity.*
import com.buychemi.crm.ui.activity.mesage.GroupSentMessageActivity
import com.buychemi.crm.ui.activity.mesage.MassRecordActivity
import com.buychemi.crm.ui.activity.report.WorkStatementActivity
import com.buychemi.crm.ui.adapter.HomeFragmentHistoryAdapter
import com.buychemi.crm.ui.adapter.HomeFragmentTabAdapter
import com.buychemi.crm.ui.adapter.HomeFragmentUserAdapter
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_home_layout.*

/**首页
 * @Author 20342
 * @Date 2019/9/21 12:56
 */
class HomeFragment : BaseFragment(), HomeContract.View, View.OnClickListener {


    private var page = 1
    private var mtotal = 1
    private var map = HashMap<String, String>()
    private val mlist = arrayListOf("1", "2", "3", "4", "5", "6")
    private val muserlist = arrayListOf("1", "2","3")
    private var mHomeTabAdapter: HomeFragmentTabAdapter? = null
    private var mHomeuserAdapter: HomeFragmentUserAdapter? = null
    private var mHomeHistory: HomeFragmentHistoryAdapter? = null
    private val mPresenter: HomePresenter by lazy { HomePresenter() }

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
        mPresenter.attachView(this)
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, cl_top) }
        recycle_tab.isNestedScrollingEnabled = false
        recycle_history.isNestedScrollingEnabled = false
        recycler_user.isNestedScrollingEnabled = false
        mHomeTabAdapter = activity?.let { HomeFragmentTabAdapter(it, mlist) }
        mHomeuserAdapter = activity?.let { HomeFragmentUserAdapter(it, muserlist) }
        mHomeHistory = activity?.let { HomeFragmentHistoryAdapter(it, ArrayList()) }
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
                    startActivity(Intent(activity, WorkStatementActivity::class.java))

                    //  startActivity(Intent(activity, MyClientActivity::class.java))
                }
                3 -> {

                    startActivity(Intent(activity, MassRecordActivity::class.java))

                }
                4 -> {
                    startActivity(Intent(activity, LogBookActivity::class.java))


                }
                5 -> {
                    var intent = Intent(activity, MyCustomerActivity::class.java)
                    startActivity(intent)

                }

            }
        }
        mHomeuserAdapter?.setOnItemClickListener { tag, i ->
             when (i) {
                 0->{
                     var intent = Intent(activity, MyCustomerActivity::class.java)
                     startActivity(intent)
                 }

                 2 -> {
                     startActivity(Intent(activity, MyStaffActivity::class.java))

                 }
                 1-> {
                   //  startActivity(Intent(activity, MyStaffActivity::class.java))

                 }

             }

        }


        refreshLayout.setOnRefreshListener { refreshLayout ->
            //下拉刷新
            page = 1
            setpushData()
            refreshLayout.finishRefresh()
            mPresenter.getBriefing(HashMap())

        }
        refreshLayout.setOnLoadMoreListener { refreshLayout ->
            //加载更多
            page++
            if (page <= mtotal) {
                setpushData()
                refreshLayout.finishLoadMore()

            } else {
                refreshLayout.finishLoadMore(1000, true, true)

            }

        }

    }

    private fun setpushData() {
        map.clear()
        map["pageSize"] = "10"
        map["pageNum"] = page.toString()
        mPresenter.getUserLogList(map)
    }

    override fun lazyLoad() {

    }

    override fun onStart() {
        super.onStart()
        page =1
        setpushData()
        mPresenter.getBriefing(HashMap())
    }

    override fun onClick(v: View?) {
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onUserLogList(data: ArrayList<UserLogEntity>?, tital: Int?) {
        if (tital != null) {
            mtotal = tital
        }
        if (data != null) {
            if (page == 1) {
                mHomeHistory?.setDataNew(data)
            } else {
                mHomeHistory?.setDataAll(data)
            }
        } else {
            if (page == 1) {
                mHomeHistory?.cleardata()
            }
        }
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onBriefing(data: BriefingEntity?) {
        mHomeuserAdapter?.mBriefingEntity = data
        mHomeuserAdapter?.notifyDataSetChanged()
    }
}