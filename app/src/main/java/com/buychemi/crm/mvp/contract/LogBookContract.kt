package com.buychemi.crm.mvp.contract

import com.buychemi.crm.base.IBaseView
import com.buychemi.crm.bean.LogListEntity
import com.buychemi.crm.bean.LogLookEntity
import com.buychemi.crm.bean.MyCalendarEntity
import com.buychemi.crm.bean.UserBean

/**工作日志
 * @Author 20342
 * @Date 2019/10/15 16:18
 */
interface LogBookContract {

    interface View : IBaseView {
        /**
         * 显示错误信息
         */
        fun showError(errorMsg: String, errorCode: Int)

        /**
         * 日志列表
         */
        fun onMyLogList(data: ArrayList<LogLookEntity>?, total:Int?)      /**
         * 日历
         */
        fun onMyLogCalender(data: ArrayList<MyCalendarEntity>?)
    }

    interface Presenter {
        fun getMyLogList(map: Map<String, String>)
        fun getMyLogCalender(map: Map<String, String>)
    }
}