package com.buychemi.crm.mvp.contract

import com.buychemi.crm.base.IBaseView
import com.buychemi.crm.bean.PostBodyEntity
import com.buychemi.crm.bean.UpImagesEntity
import okhttp3.RequestBody

/**
 * @Author 20342
 * @Date 2019/10/17 11:39
 */
interface NewScheduleContract {

    interface View : IBaseView {
        /**
         * 显示错误信息
         */
        fun showError(errorMsg: String, errorCode: Int)

        /**
         * 创建进程
         */
        fun onNewSchedule(data: String?)

        fun onUpImages(data: ArrayList<UpImagesEntity>?)
    }

    interface Presenter {
        fun getNewSchedule(map:Map<String,String?>)
        fun getUpImages(body: RequestBody)
    }
}