package com.buychemi.crm.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.EditorInfo
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.CompanyListEntity
import com.buychemi.crm.bean.GroupListEntity
import com.buychemi.crm.bean.LinkTestEntity
import com.buychemi.crm.mvp.contract.NewCustomerContract
import com.buychemi.crm.mvp.presenter.NewCustomerPresenter
import com.buychemi.crm.showToast
import com.buychemi.crm.ui.adapter.SearchComplayAdapter
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_search_complay.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 关联客户搜索公司
 * @Author 20342
 * @Date 2019/9/23 15:41
 */
class SearchComplayActivity : BaseActivity(), NewCustomerContract.View, View.OnClickListener {
    override fun onMyGroupData(data: ArrayList<GroupListEntity>?, total: Int) {
    }

    private val mPresenter: NewCustomerPresenter by lazy { NewCustomerPresenter() }
    private var page = 1
    private var mTotalPage = 1
    private var issearch = false
    private var map = HashMap<String, String>()
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_btn -> {
                //startActivity(Intent(this@SearchComplayActivity, NewComplayActivity::class.java))
            }
            R.id.tv_search->{
                issearch = true
                page = 1
                setpushData()
            }
        }
    }

    private var mMyClientAdapter: SearchComplayAdapter? = null
    private val mlist = ArrayList<CompanyListEntity>()
    override fun layoutId(): Int {
        return R.layout.activity_search_complay
    }

    override fun initData() {
    }

    override fun initView() {
        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "搜索公司"
        iv_left.setOnClickListener(this)
        tv_search.setOnClickListener(this)
        tv_btn.setOnClickListener(this)
        mMyClientAdapter = SearchComplayAdapter(this, mlist)
        mMyClientAdapter?.setOnTitleItemClickListener { tag, i ->
            var intent = Intent(this, NewContactActivity::class.java)
            intent.putExtra(Constants.KEYNAME, tag.companyName)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        recycler_view.adapter = mMyClientAdapter
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
            if (page <= mTotalPage!!) {
                setpushData()
                refreshLayout.finishLoadMore()

            } else {
                refreshLayout.finishLoadMore(1000, true, true)

            }

        }

        edit_query.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                issearch = true
                page = 1
                setpushData()
            }
            false
        }
    }

    private fun setpushData() {
        //上传参数
        map.clear()
        map["pageSize"] = "10"
        map["pageNum"] = page.toString()
        if (issearch) {

            if (edit_query.text.toString().trim() == "") {
                showToast("请输入搜索内容")
            }
            map["companyName"] = edit_query.text.toString().trim()
        }
        mPresenter.getcompanyList(map)
    }

    override fun start() {

        setpushData()
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onNewAddCustomer(data: LinkTestEntity?) {
    }

    override fun oncompanyList(data: ArrayList<CompanyListEntity>?, total: Int) {
        if (total != null) {
            mTotalPage = total
        }
        if (data != null) {
            if (page == 1) {
                mMyClientAdapter?.addDataNew(data)
            } else {
                mMyClientAdapter?.addDataAll(data)
            }
        } else {
            if (page == 1) {
                mMyClientAdapter?.addDataNew(ArrayList())
                if (issearch) {
                    var intent = Intent(this, NewContactActivity::class.java)
                    intent.putExtra(Constants.KEYNAME, edit_query.text.toString())
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }

            }
        }
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

}