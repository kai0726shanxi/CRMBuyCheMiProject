package com.buychemi.crm.mvp.model

import com.buychemi.crm.bean.BriefingEntity
import com.buychemi.crm.bean.UserLogEntity
import com.buychemi.crm.dispatchDefault
import com.buychemi.crm.net.BaseResponse
import com.buychemi.crm.net.RetrofitManager
import com.buychemi.crm.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/10/15 16:20
 */
class HomeModel {


    /**
     * 手机登录
     */
    fun getuserLogList(map:Map<String,String>): Observable<BaseResponse<ArrayList<UserLogEntity>?>> {


        return RetrofitManager.service.getUserLogList(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 简报
     */
    fun getBriefing(map:Map<String,String>): Observable<BaseResponse<BriefingEntity?>> {


        return RetrofitManager.service.getBriefing(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}