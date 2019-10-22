package com.buychemi.crm.mvp.model

import android.util.Log
import com.buychemi.crm.bean.LogListEntity
import com.buychemi.crm.bean.LogLookEntity
import com.buychemi.crm.bean.MyCalendarEntity
import com.buychemi.crm.dispatchDefault
import com.buychemi.crm.net.BaseResponse
import com.buychemi.crm.net.RetrofitManager
import com.buychemi.crm.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/10/15 16:20
 */
class LogLookModel {


    /**
     * 我的日志列表
     */
    fun getLogList(map:Map<String,String>): Observable<BaseResponse<ArrayList<LogLookEntity>?>> {


        return RetrofitManager.service.getMyLogList(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
    /**
     * 我的日志日历
     */
    fun getCalendarList(map:Map<String,String>): Observable<BaseResponse<ArrayList<MyCalendarEntity>?>> {


        return RetrofitManager.service.getMyLogCalender(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}