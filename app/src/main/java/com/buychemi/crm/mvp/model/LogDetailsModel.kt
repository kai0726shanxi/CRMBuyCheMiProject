package com.buychemi.crm.mvp.model

import android.util.Log
import com.buychemi.crm.bean.*
import com.buychemi.crm.dispatchDefault
import com.buychemi.crm.net.BaseResponse
import com.buychemi.crm.net.RetrofitManager
import com.buychemi.crm.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/10/15 16:20
 */
class LogDetailsModel {


    /**
     * 我的日志详情
     */
    fun getLogdetaila(map: Map<String, String>): Observable<BaseResponse<LogDetailsEntity?>> {


        return RetrofitManager.service.getLogDetails(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 我的跟进详情
     */
    fun getFollowDetails(map: Map<String, String>): Observable<BaseResponse<FollowDetailsEntity?>> {


        return RetrofitManager.service.getFollowDetails(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }


}