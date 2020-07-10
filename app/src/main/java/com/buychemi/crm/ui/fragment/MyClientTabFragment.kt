package com.buychemi.crm.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseFragment
import com.buychemi.crm.bean.*
import com.buychemi.crm.mvp.contract.MyClientTabContract
import com.buychemi.crm.mvp.presenter.MyClientTabPresenter
import com.buychemi.crm.ui.adapter.FollowUpRecordsAdapter
import com.buychemi.crm.ui.adapter.MyClientTabAdapter
import com.buychemi.crm.ui.adapter.MyClientTabBasicAdapter
import com.buychemi.crm.ui.adapter.MyClientTabUserAdapter
import kotlinx.android.synthetic.main.fragment_myclient_tab.*

/**
 * 我的客户和我的联系人底部tab
 * @Author 20342
 * @Date 2019/9/25 15:34
 */
class MyClientTabFragment : BaseFragment(), MyClientTabContract.View {

     private var datalist=ArrayList<CustomerSearchEntity>()
    private var mType: String? = "0"//0和1区分客户和联系人
    private var mTag: String? = "0"//0,1,2 区分跟进，基本，相关
    private val mlist = ArrayList<LogListEntity>()
    private val mlistuser = ArrayList<CustomerSearchEntity>()
    private var muserList = ArrayList<UserDataEntity>()
    private var mMyClientTabBasicAdapter: MyClientTabBasicAdapter? = null//基本信息
    private var mFollowUpRecordsAdapter: FollowUpRecordsAdapter? = null//跟进记录
    private var mMyClientTabAdapter: MyClientTabAdapter? = null//相关信息
    private var mMyClientTabUserAdapter: MyClientTabUserAdapter? = null
    private var map = HashMap<String, String>()
    private var mapuser=HashMap<String,String>()
    private var linkmanId: Int = 0
    private var page: Int = 1
    private var mtotalpage: Int? = 1
    private val mPresenter: MyClientTabPresenter by lazy {
        MyClientTabPresenter()
    }

    companion object {
        fun getInstance(str: String?, strtag: String?, id: Int): MyClientTabFragment {
            val fragment = MyClientTabFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mType = str
            fragment.mTag = strtag
            fragment.linkmanId = id
            return fragment
        }

        fun getInstance(str: String?, strtag: String?, list: ArrayList<UserDataEntity>): MyClientTabFragment {
            val fragment = MyClientTabFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mType = str
            fragment.mTag = strtag
            fragment.muserList = list
            return fragment
        }
    }

    override fun getLayoutId(): Int {

        return R.layout.fragment_myclient_tab
    }

    override fun initView() {
        mPresenter.attachView(this)
        if (mType == "3") {
            tv_btn.visibility = View.VISIBLE

        } else {
            tv_btn.visibility = View.GONE
        }
        when (mTag) {
            "0" -> {
                tv_name.text = ""
                mFollowUpRecordsAdapter = activity?.let { FollowUpRecordsAdapter(it, ArrayList()) }
                recycle_tab.adapter = mFollowUpRecordsAdapter
                recycle_tab.layoutManager = LinearLayoutManager(activity)
            }

            "1" -> {
                tv_name.text = "基本信息"

                mMyClientTabBasicAdapter = activity?.let { MyClientTabBasicAdapter(it, muserList) }
                recycle_tab.adapter = mMyClientTabBasicAdapter
                recycle_tab.layoutManager = LinearLayoutManager(activity)
            }
            "2" -> {
                if (mType == "0") {
                    //客户
                    cl_top.visibility = View.VISIBLE
                    mMyClientTabUserAdapter = activity?.let { MyClientTabUserAdapter(it, mlistuser) }
                    recycle_linkman.adapter = mMyClientTabUserAdapter
                    recycle_linkman.layoutManager = LinearLayoutManager(activity)

                } else {
                    //联系人
                    tv_name.text = "操作信息"
                    cl_top.visibility = View.GONE


                }
                mMyClientTabAdapter = activity?.let { MyClientTabAdapter(it, mlist) }
                recycle_tab.adapter = mMyClientTabAdapter
                recycle_tab.layoutManager = LinearLayoutManager(activity)
            }


        }
        refreshLayout.setOnRefreshListener { refreshLayout ->
            //下拉刷新
            page = 1
            setpushData()
            refreshLayout.finishRefresh()
        }
        refreshLayout.setOnLoadMoreListener { refreshLayout ->
            //加载更多
            page++
            if (page <= mtotalpage!!) {
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

        if (mTag == "0") {
            if (mType == "0") {
                map["companyId"] = linkmanId.toString()

            } else {
                map["customerId"] = linkmanId.toString()

            }
            mPresenter.getFollowList(map)

        } else if (mTag == "2") {
            if (mType == "0") {
                map["companyId"] = linkmanId.toString()
                mPresenter.getComplayLogList(map)

                mapuser.clear()
                mapuser["companyId"]=linkmanId.toString()
                mPresenter.onComPlanyCustomer(mapuser)
            } else {
                map["customerId"] = linkmanId.toString()
                mPresenter.getLinkLogList(map)
            }


        }
    }

    override fun lazyLoad() {

    }

    override fun onStart() {
        super.onStart()
        page = 1
        setpushData()

    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onFollowList(data: ArrayList<FollowListEntity>?, totalpage: Int?) {
        mtotalpage = totalpage
        if (data != null) {
            if (page == 1) {
                mFollowUpRecordsAdapter?.addDataNew(data)

            } else {
                mFollowUpRecordsAdapter?.addDataAll(data)

            }
        }


    }

    override fun onUserLogList(data: ArrayList<UserLogEntity>?, totalpage: Int?) {
        mtotalpage = totalpage

    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onLinkLogList(data: ArrayList<LogListEntity>?, totalpage: Int?) {
//客户日志
        mtotalpage = totalpage
        if (data != null) {
            if (page == 1) {
                mMyClientTabAdapter?.setDataNew(data)
            } else {
                mMyClientTabAdapter?.setDataAll(data)
            }
        } else {
            if (page == 1) {
                mMyClientTabAdapter?.cleardata()
            }
        }

    }

    override fun onComplayLogList(data: ArrayList<LogListEntity>?, totalpage: Int?) {
//公司日志
        mtotalpage = totalpage

        if (data != null) {
            if (page == 1) {
                mMyClientTabAdapter?.setDataNew(data)
            } else {
                mMyClientTabAdapter?.setDataAll(data)
            }
        } else {
            if (page == 1) {
                mMyClientTabAdapter?.cleardata()
            }
        }

    }

    override fun onComPlanyCustomer(data: ArrayList<CustomerSearchEntity>?, totalpage: Int?) {
//联系人
        if (data!=null){
            if (data.size>3){
                datalist.clear()
                datalist.add(data[0])
                datalist.add(data[1])
                datalist.add(data[2])
                mMyClientTabUserAdapter?.setDataNew(datalist)

            }else{
                mMyClientTabUserAdapter?.setDataNew(data)

            }
        }

    }

}