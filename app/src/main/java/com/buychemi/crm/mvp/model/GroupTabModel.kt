package com.buychemi.crm.mvp.model

import com.buychemi.crm.bean.GroupListEntity
import com.buychemi.crm.bean.UserBean
import com.buychemi.crm.dispatchDefault
import com.buychemi.crm.net.BaseResponse
import com.buychemi.crm.net.RetrofitManager
import com.buychemi.crm.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/10/18 10:49
 */
class GroupTabModel {

    /**
     * 我的分组
     */
    fun getMyGroupList(map:Map<String,String>): Observable<BaseResponse<ArrayList<GroupListEntity>?>> {


        return RetrofitManager.service.getMyGroupData(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
    /**
     * 我下属分组
     */
    fun getSubordinatelist(map:Map<String,String>): Observable<BaseResponse<ArrayList<GroupListEntity>?>> {


        return RetrofitManager.service.getMySubordinateGroupData(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 我和我下属的
     */
    fun getAllGroupData(map:Map<String,String>): Observable<BaseResponse<ArrayList<GroupListEntity>?>> {


        return RetrofitManager.service.getAllGroupData(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}