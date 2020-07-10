package com.buychemi.crm.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseFragment
import com.buychemi.crm.bean.CompanyDetailsEntity
import com.buychemi.crm.bean.CustomerDetailsEntity
import com.buychemi.crm.bean.CustomerListEntity
import com.buychemi.crm.bean.MessageEvent
import com.buychemi.crm.mvp.contract.CustomerContract
import com.buychemi.crm.mvp.presenter.CustomerPresenter
import com.buychemi.crm.ui.activity.MyClientDetailsActivity
import com.buychemi.crm.ui.adapter.MyLinkManTabAdapter
import kotlinx.android.synthetic.main.fragment_my_group_tab.*
import org.greenrobot.eventbus.EventBus

/**
 * 我的联系人
 * @Author 20342
 * @Date 2019/9/23 14:20
 */
class MyLinkManTabFragment : BaseFragment(), CustomerContract.View {
    override fun onHomeCustomer(data: ArrayList<CustomerListEntity>?, total: Int?) {

    }


    override fun onCustomerDetails(data: CustomerDetailsEntity?) {

    }

    private var mType: String? = "1"//1是我的，2是其他
    private var mtag: String? = ""
    private var mlist = ArrayList<CustomerListEntity>()
    private var mMyLinkManTabAdapter: MyLinkManTabAdapter? = null
    private var page: Int = 1
    private var mTotalPage: Int? = 0
    private var mgroupid: Int? = 0
    private var map = HashMap<String, String>()
    private val mPresenter: CustomerPresenter by lazy { CustomerPresenter() }

    companion object {
        fun getInstance(str: String?, tag: String?, id: Int?): MyLinkManTabFragment {
            val fragment = MyLinkManTabFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mType = str
            fragment.mtag = tag
            fragment.mgroupid = id
            return fragment
        }
    }


    override fun getLayoutId(): Int {

        return R.layout.fragment_my_group_tab
    }

    override fun initView() {
        mPresenter.attachView(this)
        mMyLinkManTabAdapter = activity?.let { MyLinkManTabAdapter(it, mlist) }
        mMyLinkManTabAdapter?.setOnTitleItemClickListener { tag, i ->
            if (mtag == "1") {
                EventBus.getDefault().post(MessageEvent(tag.name,tag.id,tag.companyId,mgroupid))
                activity?.finish()

            } else {
                var intent = Intent(activity, MyClientDetailsActivity::class.java)
                intent.putExtra(Constants.KEYTYPE, "1")
                intent.putExtra(Constants.KEYCUSTOMER, tag.id)
                intent.putExtra(Constants.KEYCOMPLAY,tag.companyId)
                startActivity(intent)
            }

        }
        recycle_tab.adapter = mMyLinkManTabAdapter
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
            if (page <= mTotalPage!!) {
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
        map["groupId"] = mgroupid.toString()
        mPresenter.getMyCustomerlist(map)

    }

    override fun lazyLoad() {
        setpushData()
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onMyCustomerlist(data: ArrayList<CustomerListEntity>?, total: Int?) {
        mTotalPage = total
        if (data != null) {

            if (page == 1) {
                mMyLinkManTabAdapter?.addDataNew(data)
            } else {
                mMyLinkManTabAdapter?.addDataAll(data)

            }
        }
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
    override fun onCompanlyDetails(data: CompanyDetailsEntity?) {
    }

}