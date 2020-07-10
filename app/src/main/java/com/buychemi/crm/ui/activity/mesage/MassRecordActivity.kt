package com.buychemi.crm.ui.activity.mesage

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.SendLinkListEntity
import com.buychemi.crm.bean.SendListEntity
import com.buychemi.crm.mvp.contract.MassRecordContract
import com.buychemi.crm.mvp.presenter.MassRecordPresenter
import com.buychemi.crm.ui.adapter.MassRecordAdapter
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_massrecord.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 群发记录（群发信息）
 * @Author 20342
 * @Date 2019/9/26 10:44
 */
class MassRecordActivity : BaseActivity(), MassRecordContract.View, View.OnClickListener {
    override fun ontSendLinkList(data: ArrayList<SendLinkListEntity>?, total: Int?) {

    }

    private var page = 1
    private var mtotal = 1
    private val mlist = ArrayList<SendListEntity>()
    private var mMassRecordAdapter: MassRecordAdapter? = null
    private var map = HashMap<String, String>()
    private val mPresenter: MassRecordPresenter by lazy { MassRecordPresenter() }
    override fun layoutId(): Int {
        return R.layout.activity_massrecord
    }

    override fun initData() {
    }

    override fun initView() {
        mPresenter.attachView(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        iv_left.setOnClickListener(this)
        tv_title.text = "群发记录"
        StatusBarUtil.darkMode(this)
        mMassRecordAdapter = MassRecordAdapter(this, mlist)

        recyclerView.adapter = mMassRecordAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
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

        map["isMe"] = "1"
        mPresenter.getSendFroupList(map)
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
        if (total!=null){
            mtotal=total
        }
        if (data != null) {
            if (page == 1) {
                mMassRecordAdapter?.setDataNew(data)
            } else {
                mMassRecordAdapter?.setDataAll(data)
            }
        } else {
            if (page == 1) {
                mMassRecordAdapter?.cleardata()
            }
        }
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }


}