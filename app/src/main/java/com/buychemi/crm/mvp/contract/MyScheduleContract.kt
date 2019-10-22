package com.buychemi.crm.mvp.contract

import com.buychemi.crm.base.IBaseView
import com.buychemi.crm.bean.*

/**工作日志
 * @Author 20342
 * @Date 2019/10/15 16:18
 */
interface MyScheduleContract {

    interface View : IBaseView {
        /**
         * 显示错误信息
         */
        fun showError(errorMsg: String, errorCode: Int)

        /**
         * 我的日程列表
         */
        fun onMyscheduleList(data: ArrayList<MyScheduleEntity>?, total:Int?)
        /**
         * 日历
         */
        fun onMyscheduleCalender(data: ArrayList<MyCalendarEntity>?)
        fun ontmanagementDetails(data: ManagementDetailsEntity?)
        fun onMyscheduleOver(data: String?)
        fun onMyscheduledelete(data: String?)

    }

    interface Presenter {
        fun getMyscheduleList(map: Map<String, String>)
        fun getMyscheduleCalender(map: Map<String, String>)
        fun getmanagementDetails(map: Map<String, String>)

        fun getMyscheduleOver(map: Map<String, String>)
        fun getMyscheduledelete(map: Map<String, String>)
    }
}