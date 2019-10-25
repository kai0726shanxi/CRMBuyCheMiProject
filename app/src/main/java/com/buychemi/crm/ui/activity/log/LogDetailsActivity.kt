package com.buychemi.crm.ui.activity.log

import android.graphics.Color
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.ReportCommentEntity
import com.buychemi.crm.bean.ReportListEntity
import com.buychemi.crm.bean.WorkStatementEntity
import com.buychemi.crm.mvp.contract.WorkStatementContract
import com.buychemi.crm.mvp.presenter.WorkStatementPresenter
import com.buychemi.crm.showToast
import com.buychemi.crm.ui.adapter.AddImageAdapter
import com.buychemi.crm.ui.adapter.ShowImageAdapter
import com.buychemi.crm.ui.adapter.report.ReportCommentAdapter
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_report_details.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 *日志和跟进详情
 * @Author 20342
 * @Date 2019/10/16 15:00
 */
class LogDetailsActivity : BaseActivity(),WorkStatementContract.View, View.OnClickListener {

    private var mapadd=HashMap<String,String>()
    private var mid:Int=0
    private var mlist = ArrayList<ReportCommentEntity>()
    private var mAdapter: ReportCommentAdapter? = null
    private var map=HashMap<String,String>()
    private var mimageAdapter:ShowImageAdapter?=null
    private var page=1
    private var mtotal=1
    private var mimglist=ArrayList<String>()
    private val mPresenter:WorkStatementPresenter by lazy { WorkStatementPresenter() }
    override fun layoutId(): Int {
        return R.layout.activity_report_details
    }

    override fun initData() {
        mid=intent.getIntExtra(Constants.KEYTYPE,0)
    }

    override fun initView() {
        mPresenter.attachView(this)
        recyclerView.isNestedScrollingEnabled=false
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "报告详情"
        iv_left.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
       // StatusBarUtil.setPaddingSmart(this, cl_bar)
        mAdapter = ReportCommentAdapter(this, mlist)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mimageAdapter= ShowImageAdapter(this,ArrayList())
        recycle_img.adapter=mimageAdapter
        recycle_img.layoutManager=LinearLayoutManager(this,LinearLayout.HORIZONTAL,false)

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
        et_send.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                setsendData()

            }
            false
        }

    }

    private fun setsendData() {
        //发送评论
        if (et_send.text.toString()==""){
            showToast("请输入内容")
            return
        }
        mapadd.clear()
        mapadd["reportId"]=mid.toString()
        mapadd["auditRemark"]=et_send.text.toString()
        mPresenter.getreportadd(mapadd)
    }

    private fun setpushData() {
        map.clear()
        map["pageSize"] = "10"
        map["pageNum"] = page.toString()
        map["reportId"]=mid.toString()
        mPresenter.getRePortComment(map)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
        }

    }

    override fun start() {
        map.clear()
        map["id"]=mid.toString()
        mPresenter.getRePortDetails(map)

    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg,errorCode)
    }

    override fun onReportList(data: ArrayList<ReportListEntity>?, total: Int?) {
    }

    override fun onRePortDetails(data: WorkStatementEntity?) {
        tv_time.text="该报告于${data?.createTime}提交"
        //报告类型0日报 1周报 2月报
        when(data?.type){
            0->{
                tv_type.text="日报"
            }
            1->{
                tv_type.text="周报"

            }
            2->{
                tv_type.text="月报"

            }



        }
        tv_name.text=data?.userName
        tv_positon.text=data?.position
        tv_phone.text=data?.tel
        et_content.text=data?.workSummary
        if (data?.img!=null&& data?.img?.size!! >0){
            mimglist.clear()
            for (index in data?.img!!){
                index.url?.let { mimglist.add(it) }
            }
            mimageAdapter?.setDataAll(mimglist)
        }

        if (data!=null){
            if (data?.isMe==1){
                et_send.visibility=View.GONE

            }else{
                et_send.visibility=View.VISIBLE
            }
            setpushData()

        }



    }

    override fun onReportComment(data: ArrayList<ReportCommentEntity>?, total: Int?) {
        if (total!=null){
            mtotal=total

        }
        if (data!=null){
            if (page==1){
                mAdapter?.setDataNew(data)
            }else{
                mAdapter?.setDataAll(data)
            }
        }else{
            if (page==1){
                mAdapter?.cleardata()
            }
        }

    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onreportadd(data: String?) {
        showToast("评论成功")
        et_send.setText("")
         page=1
        setpushData()
    }
}