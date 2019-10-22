package com.buychemi.crm.mvp.model

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
class FollowUpRecordsModel {


    /**
     * 我的列表
     */
    fun getFollowList(map:Map<String,String>): Observable<BaseResponse<ArrayList<FollowListEntity>?>> {


        return RetrofitManager.service.getMyFollowList(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
    /**
     * 我的日志日历
     */
    fun getCalendarList(map:Map<String,String>): Observable<BaseResponse<ArrayList<MyCalendarEntity>?>> {


        return RetrofitManager.service.getMyFollowCalender(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}