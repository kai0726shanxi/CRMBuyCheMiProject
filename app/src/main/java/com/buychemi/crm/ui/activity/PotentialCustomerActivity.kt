package com.buychemi.crm.ui.activity

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.SendLinkListEntity
import com.buychemi.crm.bean.SendListEntity
import com.buychemi.crm.mvp.contract.MassRecordContract
import com.buychemi.crm.mvp.presenter.MassRecordPresenter
import com.buychemi.crm.ui.adapter.PotentialCustomerAdapter
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_potentialcustomer.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 潜在客户
 * @Author 20342
 * @Date 2019/9/26 10:08
 */
class PotentialCustomerActivity : BaseActivity(), MassRecordContract.View, View.OnClickListener {

    private var page = 1
    private var mtotal = 1
    private var map = HashMap<String, String>()
    private val mlist = ArrayList<SendLinkListEntity>()
    private var mPotentialCustomerAdapter: PotentialCustomerAdapter? = null
    private var mType: String? = "0"
    private val mPresenter: MassRecordPresenter by lazy { MassRecordPresenter() }
    private var mRecordId: String? = ""
    override fun layoutId(): Int {

        return R.layout.activity_potentialcustomer
    }

    override fun initData() {
        mType = intent.getStringExtra(Constants.KEYTYPE)
        mRecordId = intent.getStringExtra(Constants.KEYGROUPID)
    }

    override fun initView() {
        mPresenter.attachView(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        if (mType == "1") {
            tv_title.text = "发送记录"

        } else {
            tv_title.text = "潜在客户"

        }
        iv_left.setOnClickListener(this)
        mPotentialCustomerAdapter = PotentialCustomerAdapter(this, mlist)
        recycler_view.adapter = mPotentialCustomerAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        refreshLayout.setOnRefreshListener { refreshLayout ->
            //下拉刷新
            page = 1
            setpushData()
            refreshLayout.finishRefresh()
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
        //上传参数
        map.clear()
        map["pageSize"] = "10"
        map["pageNum"] = page.toString()
        if (mRecordId != null) {
            map["recordId"] = mRecordId!!
        }
        mPresenter.getSendLinkList(map)
        // mPresenter.getMyCustomerlist(map)
    }

    override fun start() {
        setpushData()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
        }
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onSendGroupList(data: ArrayList<SendListEntity>?, total: Int?) {
    }

    override fun ontSendLinkList(data: ArrayList<SendLinkListEntity>?, total: Int?) {
        if (data != null) {
            if (page == 1) {
                mPotentialCustomerAdapter?.addDataNew(data)
            } else {
                mPotentialCustomerAdapter?.addDataAll(data)
            }
        } else {
            if (page == 1) {
                mPotentialCustomerAdapter?.cleardata()

            }
        }
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}