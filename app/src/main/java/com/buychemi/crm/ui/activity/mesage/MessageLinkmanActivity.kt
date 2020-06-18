package com.buychemi.crm.ui.activity.mesage

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.EditorInfo
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.CompanyDetailsEntity
import com.buychemi.crm.bean.CustomerDetailsEntity
import com.buychemi.crm.bean.CustomerListEntity
import com.buychemi.crm.mvp.contract.CustomerContract
import com.buychemi.crm.mvp.presenter.CustomerPresenter
import com.buychemi.crm.showToast
import com.buychemi.crm.ui.adapter.MessageLinkmanAdapter
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_my_message_linkman.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 群发联系人
 * @Author 20342
 * @Date 2019/10/15 11:18
 */
class MessageLinkmanActivity : BaseActivity(), CustomerContract.View, View.OnClickListener {
    private var issearch = false
    override fun onHomeCustomer(data: ArrayList<CustomerListEntity>?, total: Int?) {

    }

    override fun onCompanlyDetails(data: CompanyDetailsEntity?) {

    }

    private var list = ArrayList<CustomerListEntity>()
    private var chosenum: Int = 0
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> finish()
            R.id.tv_btn -> {
                if (list != null && list.size > 0) {
                    var intent = Intent(this, MessageTemplateActivity::class.java)
                    intent.putExtra(Constants.KEYNAME, list)
                    intent.putExtra(Constants.KEYTYPE, "1")
                    startActivity(intent)
                } else {
                    showToast("请选择发送人员")
                }

            }
            R.id.tv_search -> {
                issearch = true
                page = 1
                setpushData()
            }
        }
    }

    private val mPresenter: CustomerPresenter by lazy { CustomerPresenter() }
    private var mAdapter: MessageLinkmanAdapter? = null
    private var mlist = ArrayList<CustomerListEntity>()
    private var map = HashMap<String, String>()
    private var page = 1
    private var mtotal = 1
    override fun layoutId(): Int {

        return R.layout.activity_my_message_linkman
    }

    override fun initData() {
    }

    override fun initView() {
        mPresenter.attachView(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "选择联系人"
        iv_left.setOnClickListener(this)
        tv_btn.setOnClickListener(this)
        tv_search.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        mAdapter = MessageLinkmanAdapter(this, mlist)
        mAdapter?.setOnItemListener(object : MessageLinkmanAdapter.BtnDataLinsenter {
            override fun btndata(str: CustomerListEntity, p: Int, b: Boolean) {

                if (b) {
                    chosenum += 1
                    list.add(str)

                } else {
                    chosenum -= 1
                    list.remove(str)
                }
                tv_num_bottom.text = "已选${chosenum}人"

            }
        })

        edit_query.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //setsendData()
                issearch = true
                page = 1
                setpushData()
            }
            false
        }
        recycler_view.adapter = mAdapter
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

    override fun start() {
        setpushData()
    }


    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onMyCustomerlist(data: ArrayList<CustomerListEntity>?, total: Int?) {
        if (total != null) {
            mtotal = total
        }
        if (data != null) {

            if (issearch){
                if (list!=null&&list.size>0){
                 mAdapter?.list=list
                    if (page == 1) {
                        mAdapter?.setDataNew(data)
                    } else {
                        mAdapter?.setDataAll(data)
                    }
                }else{
                    if (page == 1) {
                        mAdapter?.setDataNew(data)
                    } else {
                        mAdapter?.setDataAll(data)
                    }
                }
            }else{
                if (page == 1) {
                    mAdapter?.setDataNew(data)
                } else {
                    mAdapter?.setDataAll(data)
                }
            }

        } else {
            if (page == 1) {
                mAdapter?.cleardata()
            }
        }

    }

    override fun onCustomerDetails(data: CustomerDetailsEntity?) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    private fun setpushData() {
        //上传参数
        map.clear()
        map["pageSize"] = "10"
        map["pageNum"] = page.toString()
        if (issearch) {
            map["name"] = edit_query.text.toString().trim()
        }
        mPresenter.getMyCustomerlist(map)

    }
}