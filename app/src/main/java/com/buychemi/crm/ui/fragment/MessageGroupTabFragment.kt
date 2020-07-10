package com.buychemi.crm.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseFragment
import com.buychemi.crm.bean.GroupListEntity
import com.buychemi.crm.mvp.contract.GrouptabContract
import com.buychemi.crm.mvp.presenter.GroupTabPresenter
import com.buychemi.crm.ui.activity.mesage.MessageMyGroupActivity
import com.buychemi.crm.ui.activity.mesage.MessageTemplateActivity
import com.buychemi.crm.ui.adapter.MessageGroupTabAdapter
import com.buychemi.crm.ui.adapter.MessageLinkmanAdapter
import kotlinx.android.synthetic.main.fragment_message_group_tab.*

/**
 * @Author 20342
 * @Date 2019/9/23 14:20
 */
class MessageGroupTabFragment : BaseFragment(), GrouptabContract.View, View.OnClickListener {
    override fun onAllGroupData(data: ArrayList<GroupListEntity>?, total: Int?) {

    }

    private var page = 1
    private var mtotal = 1
    private var map = HashMap<String, String>()
    override fun onClick(v: View?) {

    }

    private var mType: String? = "1"//1是我的，2是下属
    private var mlist = ArrayList<GroupListEntity>()
    private var mMyGroupTabAdapter: MessageGroupTabAdapter? = null
    private val mPresenter: GroupTabPresenter by lazy { GroupTabPresenter() }
    private var messageGroup: MessageMyGroupActivity? = null

    companion object {
        fun getInstance(str: String?): MessageGroupTabFragment {
            val fragment = MessageGroupTabFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mType = str
            return fragment
        }
    }


    override fun getLayoutId(): Int {

        return R.layout.fragment_message_group_tab
    }

    override fun initView() {
        messageGroup = activity as MessageMyGroupActivity
        mPresenter.attachView(this)
        mMyGroupTabAdapter = activity?.let { MessageGroupTabAdapter(it, mlist) }
        mMyGroupTabAdapter?.setOnItemListener(object : MessageGroupTabAdapter.BtnDataLinsenter {
            override fun btndata(str: GroupListEntity, p: Int, b: Boolean) {
                messageGroup?.setListData(str, b)
            }
        })

        recycle_tab.adapter = mMyGroupTabAdapter
        recycle_tab.layoutManager = LinearLayoutManager(activity)
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
        map.clear()
        map["pageSize"] = "10"
        map["pageNum"] = page.toString()
        if (mType == "1") {
            mPresenter.getMyGrouplist(map)

        } else {
            mPresenter.getSubordinatelist(map)
        }
    }

    override fun lazyLoad() {
        setpushData()

    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onMyGrouplist(data: ArrayList<GroupListEntity>?, total: Int?) {
        if (total != null) {
            mtotal = total

        }
        if (data != null) {
            if (page == 1) {
                mMyGroupTabAdapter?.addDataNew(data)
            } else {
                mMyGroupTabAdapter?.addDataAll(data)
            }
        } else {
            if (page == 1) {
                mMyGroupTabAdapter?.cleardata()
            }
        }
    }

    override fun onSubordinatelist(data: ArrayList<GroupListEntity>?, total: Int?) {

        if (total != null) {
            mtotal = total

        }
        if (data != null) {
            if (page == 1) {
                mMyGroupTabAdapter?.addDataNew(data)
            } else {
                mMyGroupTabAdapter?.addDataAll(data)
            }
        } else {
            if (page == 1) {
                mMyGroupTabAdapter?.cleardata()
            }
        }
    }
        override fun showLoading() {
        }

        override fun dismissLoading() {
        }
    }