package com.buychemi.crm.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseFragment
import com.buychemi.crm.bean.GroupListEntity
import com.buychemi.crm.mvp.contract.GrouptabContract
import com.buychemi.crm.mvp.presenter.GroupTabPresenter
import com.buychemi.crm.ui.activity.MyLinkManActivity
import com.buychemi.crm.ui.adapter.MyGroupTabAdapter
import kotlinx.android.synthetic.main.fragment_my_group_tab.*

/**
 * @Author 20342
 * @Date 2019/9/23 14:20
 */
class MyGroupTabFragment : BaseFragment(), GrouptabContract.View {



    private var mType: String? = "1"//1是我的，2是下属
    private var mlist = ArrayList<GroupListEntity>()
    private var mMyGroupTabAdapter: MyGroupTabAdapter? = null
    private var page: Int = 1
    private var mTotalPage: Int? = 0
    private var map = HashMap<String, String>()
    private var isNew: Boolean = false
    private var mtag: String? = "0"//关联客户（1是新建跟进）
    private val mPresenter: GroupTabPresenter by lazy { GroupTabPresenter() }

    companion object {
        fun getInstance(str: String?, b: Boolean, tag: String): MyGroupTabFragment {
            val fragment = MyGroupTabFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mType = str
            fragment.isNew = b
            fragment.mtag = tag
            return fragment
        }
    }


    override fun getLayoutId(): Int {

        return R.layout.fragment_my_group_tab
    }

    override fun initView() {
        mPresenter.attachView(this)
        mMyGroupTabAdapter = activity?.let { MyGroupTabAdapter(it, mlist) }
        mMyGroupTabAdapter?.setOnTitleItemClickListener { tag, i ->

            var intent = Intent(activity, MyLinkManActivity::class.java)
            if (isNew) {
                intent.putExtra(Constants.KEYNAME, mtag)

            }
            intent.putExtra(Constants.KEYGROUPID, tag.id)
            startActivity(intent)
            activity?.finish()
        }
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
        if (mType=="1"){
            if (mtag=="1"){
                mPresenter.getAllGroupData(map)
            }else{
                mPresenter.getMyGrouplist(map)

            }

        }else{
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

        mTotalPage = total
        if (data != null) {

            if (page == 1) {
                mMyGroupTabAdapter?.addDataNew(data)
            } else {
                mMyGroupTabAdapter?.addDataAll(data)

            }
        }

    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onSubordinatelist(data: ArrayList<GroupListEntity>?, total: Int?) {

        mTotalPage = total
        if (data != null) {

            if (page == 1) {
                mMyGroupTabAdapter?.addDataNew(data)
            } else {
                mMyGroupTabAdapter?.addDataAll(data)

            }
        }
    }
    override fun onAllGroupData(data: ArrayList<GroupListEntity>?, total: Int?) {

        mTotalPage = total
        if (data != null) {

            if (page == 1) {
                mMyGroupTabAdapter?.addDataNew(data)
            } else {
                mMyGroupTabAdapter?.addDataAll(data)

            }
        }

    }
}