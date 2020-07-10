package com.buychemi.crm.ui.activity.report

import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.ReportCommentEntity
import com.buychemi.crm.bean.ReportListEntity
import com.buychemi.crm.bean.WorkStatementEntity
import com.buychemi.crm.mvp.contract.WorkStatementContract
import com.buychemi.crm.mvp.presenter.WorkStatementPresenter
import com.buychemi.crm.ui.adapter.WorkStatementAdapter
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_workstatement.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 工作报告
 * @Author 20342
 * @Date 2019/9/26 15:17
 */
class WorkStatementActivity : BaseActivity(), WorkStatementContract.View, View.OnClickListener {
    override fun onreportadd(data: String?) {

    }


    private val mlist = ArrayList<ReportListEntity>()
    private var page = 1
    private var mtotalpage = 1
    private var map = HashMap<String, String>()
    private var mWorkStatementAdapter: WorkStatementAdapter? = null
    private val mPresenter: WorkStatementPresenter by lazy { WorkStatementPresenter() }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_right -> {
                startActivity(Intent(this, NewMemoActivity::class.java))
            }
        }
    }

    override fun layoutId(): Int {
        return R.layout.activity_workstatement
    }

    override fun initData() {
    }

    override fun initView() {
        mPresenter.attachView(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "我的报告"
        // tv_right.visibility=View.VISIBLE
        tv_right.text = "创建"
        iv_left.setOnClickListener(this)
        tv_right.setOnClickListener(this)
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.home_bottom_down))
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        mWorkStatementAdapter = WorkStatementAdapter(this, mlist)
        recycler_view.adapter = mWorkStatementAdapter
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
        mPresenter.getReportList(map)
    }


    override fun start() {
        setpushData()
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onReportList(data: ArrayList<ReportListEntity>?, total: Int?) {
        if (total != null) {
            mtotalpage = total
        }
        if (data != null) {
            if (page == 1) {
                mWorkStatementAdapter?.setDataNew(data)
            } else {
                mWorkStatementAdapter?.setDataAll(data)
            }
        } else {
            if (page == 1) {
                mWorkStatementAdapter?.cleardata()

            }
        }
    }

    override fun onRePortDetails(data: WorkStatementEntity?) {
    }

    override fun onReportComment(data: ArrayList<ReportCommentEntity>?, total: Int?) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}