package com.buychemi.crm.mvp.model

import com.buychemi.crm.bean.CustomerSearchEntity
import com.buychemi.crm.bean.FollowListEntity
import com.buychemi.crm.bean.LogListEntity
import com.buychemi.crm.bean.UserLogEntity
import com.buychemi.crm.dispatchDefault
import com.buychemi.crm.net.BaseResponse
import com.buychemi.crm.net.RetrofitManager
import com.buychemi.crm.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/10/21 9:41
 */
class MyClientTabModel {
    fun getFollowList(map:Map<String,String?>): Observable<BaseResponse<ArrayList<FollowListEntity>?>> {


        return RetrofitManager.service.getFollowList(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
    fun getLinkLogList(map:Map<String,String?>): Observable<BaseResponse<ArrayList<LogListEntity>?>> {


        return RetrofitManager.service.getLinkLogList(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
    fun getComplayLogList(map:Map<String,String?>): Observable<BaseResponse<ArrayList<LogListEntity>?>> {


        return RetrofitManager.service.getComplayLogList(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
    fun getComPlanyCustomer(map:Map<String,String?>): Observable<BaseResponse<ArrayList<CustomerSearchEntity>?>> {


        return RetrofitManager.service.getComPlanyCustomer(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}