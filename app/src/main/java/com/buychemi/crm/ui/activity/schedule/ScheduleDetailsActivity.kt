package com.buychemi.crm.ui.activity.schedule

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.ManagementDetailsEntity
import com.buychemi.crm.bean.MyCalendarEntity
import com.buychemi.crm.bean.MyScheduleEntity
import com.buychemi.crm.mvp.contract.MyScheduleContract
import com.buychemi.crm.mvp.presenter.MySchedulePresenter
import com.buychemi.crm.ui.adapter.schedule.ScheduleCommentAdapter
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_message_submit.*
import kotlinx.android.synthetic.main.activity_schedule_details.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 日程详情
 * @Author 20342
 * @Date 2019/10/16 16:15
 */
class ScheduleDetailsActivity : BaseActivity(),MyScheduleContract.View,View.OnClickListener {

     private val mPresenter:MySchedulePresenter by lazy { MySchedulePresenter() }
    private var mId:Int=0
    private var map=HashMap<String,String>()
    private var mlist= ArrayList<String>()
    private var mAdapter: ScheduleCommentAdapter?=null
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_left->{ finish() }
        }
    }

    override fun layoutId(): Int {
        return R.layout.activity_schedule_details
    }

    override fun initData() {
        mId=intent.getIntExtra(Constants.KEYGROUPID,0)
    }

    override fun initView() {
        mPresenter.attachView(this)
        recyclerView.isNestedScrollingEnabled=false
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "日程详情"
        iv_left.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        mAdapter= ScheduleCommentAdapter(this,mlist)
        recyclerView.adapter=mAdapter
        recyclerView.layoutManager=LinearLayoutManager(this)


    }

    override fun start() {
        map.clear()
        map["id"]=mId.toString()
        mPresenter.getmanagementDetails(map)
    }

    override fun showError(errorMsg: String, errorCode: Int) {
     ShowErrorMes(errorMsg,errorCode)
    }

    override fun onMyscheduleList(data: ArrayList<MyScheduleEntity>?, total: Int?) {
    }

    override fun onMyscheduleCalender(data: ArrayList<MyCalendarEntity>?) {
    }

    override fun ontmanagementDetails(data: ManagementDetailsEntity?) {

        tv_time.text="该日程于${data?.createTime}创建"

        tv_type.text=data?.title

        tv_start_time.text=data?.startTime
        tv_end_time.text=data?.endTime
        tv_describe.text=data?.description
        tv_name.text=data?.associationCustomerName
        tv_positon.text=data?.associationCustomerPosition
        tv_company.text=data?.associationCompanyName
        //提醒类型：0准时1提前5分钟2提前10分钟
        when(data?.remindType){
            0->{        et_content.text="提醒         准时"
            }
            1->{
                et_content.text="提醒         提前五分钟"
            }
            2->{
                et_content.text="提醒         提前十分钟"
            }

        }

    }

    override fun onMyscheduleOver(data: String?) {
    }

    override fun onMyscheduledelete(data: String?) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}