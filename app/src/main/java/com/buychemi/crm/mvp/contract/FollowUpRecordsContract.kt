package com.buychemi.crm.mvp.contract

import com.buychemi.crm.base.IBaseView
import com.buychemi.crm.bean.*

/**跟进记录
 * @Author 20342
 * @Date 2019/10/15 16:18
 */
interface FollowUpRecordsContract {

    interface View : IBaseView {
        /**
         * 显示错误信息
         */
        fun showError(errorMsg: String, errorCode: Int)

        /**
         * 跟进列表
         */
        fun onMyFollowList(data: ArrayList<FollowListEntity>?, total:Int?)
        /**
         * 跟进日历
         */
        fun onMyFollowCalender(data: ArrayList<MyCalendarEntity>?)
    }

    interface Presenter {
        fun getMyFollowList(map: Map<String, String>)
        fun getMyFollowCalender(map: Map<String, String>)
    }
}