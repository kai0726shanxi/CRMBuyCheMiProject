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
import com.buychemi.crm.bean.*
import com.buychemi.crm.mvp.contract.CustomerContract
import com.buychemi.crm.mvp.contract.MySubordinateContract
import com.buychemi.crm.mvp.contract.NewCustomerContract
import com.buychemi.crm.mvp.presenter.CustomerPresenter
import com.buychemi.crm.mvp.presenter.MySubordinatePresenter
import com.buychemi.crm.mvp.presenter.NewCustomerPresenter
import com.buychemi.crm.showToast
import com.buychemi.crm.ui.adapter.MyAtaffAdapter
import com.buychemi.crm.ui.adapter.MyGroupTabAdapter
import com.buychemi.crm.ui.adapter.MyLinkManTabAdapter
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_home_customer.*
import kotlinx.android.synthetic.main.title_bar_layout.*
import org.greenrobot.eventbus.EventBus

/**
 * 我的分组(创建时选择的分组)
 * @Author 20342
 * @Date 2019/10/23 16:24
 */
class ChoseGroupActivity : BaseActivity(), NewCustomerContract.View, View.OnClickListener {



    private var page = 1
    private var mTotalPage = 1
    private var mMyGroupTabAdapter: MyGroupTabAdapter? = null

    private var issearch = false
    private val mPresenter: NewCustomerPresenter by lazy { NewCustomerPresenter() }
    private var map = HashMap<String, String>()
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }

            R.id.tv_search -> {
                issearch = true
                page = 1
                setpushData()
            }
        }
    }

    override fun layoutId(): Int {
        return R.layout.activity_home_customer
    }

    override fun initData() {
    }

    override fun initView() {
        mPresenter.attachView(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        iv_left.setOnClickListener(this)
        tv_search.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        tv_title.text = "我的分组"
        edit_query.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //setsendData()
                issearch = true
                page = 1
                setpushData()
            }
            false
        }
        mMyGroupTabAdapter = MyGroupTabAdapter(this, ArrayList())

        mMyGroupTabAdapter?.setOnTitleItemClickListener { tag, i ->

            var intent = Intent(this, NewContactActivity::class.java)
            intent.putExtra(Constants.KEYNAME, tag)
            setResult(Activity.RESULT_OK, intent)
            finish()


        }
        recycle_view.adapter = mMyGroupTabAdapter
        recycle_view.layoutManager = LinearLayoutManager(this)
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
        if (issearch) {
            map["groupName"] = edit_query.text.toString().trim()
        }
        mPresenter.getMyGroupData(map)

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
    }

    override fun onMyGroupData(data: ArrayList<GroupListEntity>?, total: Int) {
        if (total != null) {
            mTotalPage = total
        }
        if (data != null) {
            if (page == 1) {
                mMyGroupTabAdapter?.addDataNew(data)
            } else {
                mMyGroupTabAdapter?.addDataAll(data)
            }
        } else {
            if (page == 1) {
                mMyGroupTabAdapter?.addDataNew(ArrayList())

            }
        }
    }
    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}