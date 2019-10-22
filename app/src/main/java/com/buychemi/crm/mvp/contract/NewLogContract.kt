package com.buychemi.crm.mvp.contract

import com.buychemi.crm.base.IBaseView
import com.buychemi.crm.bean.PostBodyEntity
import com.buychemi.crm.bean.UpImagesEntity
import okhttp3.RequestBody

/**
 * @Author 20342
 * @Date 2019/10/17 11:39
 */
interface NewLogContract {

    interface View : IBaseView {
        /**
         * 显示错误信息
         */
        fun showError(errorMsg: String, errorCode: Int)

        /**
         * 创建报告
         */
        fun onNewLogdata(data: String?)

        fun onUpImages(data: ArrayList<UpImagesEntity>?)
    }

    interface Presenter {
        fun getNewLogdata(map:Map<String,Any?>)
        fun getUpImages(body: RequestBody)
    }
}