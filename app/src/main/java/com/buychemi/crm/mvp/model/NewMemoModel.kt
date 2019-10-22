package com.buychemi.crm.mvp.model

import com.buychemi.crm.bean.PostBodyEntity
import com.buychemi.crm.bean.UpImagesEntity
import com.buychemi.crm.dispatchDefault
import com.buychemi.crm.net.BaseResponse
import com.buychemi.crm.net.RetrofitManager
import com.buychemi.crm.rx.scheduler.SchedulerUtils
import io.reactivex.Observable
import okhttp3.RequestBody

/**
 * @Author 20342
 * @Date 2019/10/15 16:20
 */
class NewMemoModel {


    /**
     * 创建广告
     */
    fun getNewMemo(map: Map<String,Any>): Observable<BaseResponse<String?>> {
        return RetrofitManager.service.getNewMemo(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
    /**
     * 上传图片
     */
    fun getUpImages(body: RequestBody): Observable<BaseResponse<ArrayList<UpImagesEntity>?>> {


        return RetrofitManager.service.getUpImages(body)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}