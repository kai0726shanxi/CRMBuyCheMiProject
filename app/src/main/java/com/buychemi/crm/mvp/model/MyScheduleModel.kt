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
class MyScheduleModel {


    /**
     * 我的日程列表
     */
    fun getManagementList(map: Map<String, String>): Observable<BaseResponse<ArrayList<MyScheduleEntity>?>> {


        return RetrofitManager.service.getMyManagementList(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 我的日程日历
     */
    fun getCalendarList(map: Map<String, String>): Observable<BaseResponse<ArrayList<MyCalendarEntity>?>> {


        return RetrofitManager.service.getMyManagementCalender(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 我的日程详情
     */
    fun getmanagementDetails(map: Map<String, String>): Observable<BaseResponse<ManagementDetailsEntity?>> {


        return RetrofitManager.service.getmanagementDetails(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 我的日程完成
     */
    fun getMyscheduleOver(map: Map<String, String>): Observable<BaseResponse<String?>> {


        return RetrofitManager.service.getMyscheduleOver(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 我的日程删除
     */
    fun getMyscheduleDelete(map: Map<String, String>): Observable<BaseResponse<String?>> {


        return RetrofitManager.service.getMyscheduledelete(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}