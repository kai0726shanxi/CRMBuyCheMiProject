package com.buychemi.crm.mvp.model

import com.buychemi.crm.bean.*
import com.buychemi.crm.dispatchDefault
import com.buychemi.crm.net.BaseResponse
import com.buychemi.crm.net.RetrofitManager
import com.buychemi.crm.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/10/18 10:49
 */
class NewCustomerModel {

    /**
     * 公司列表
     */
    fun getComplanyList(map:Map<String,String>): Observable<BaseResponse<ArrayList<CompanyListEntity>?>> {


        return RetrofitManager.service.getcompanyList(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    //新建联系人
    fun getNewAddCustomer(map:Map<String,String>): Observable<BaseResponse<LinkTestEntity?>> {


        return RetrofitManager.service.getNewAddCustomer(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    //我的分组
    fun getMyGroupData(map:Map<String,String>): Observable<BaseResponse<ArrayList<GroupListEntity>?>> {


        return RetrofitManager.service.getMyGroupData(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

}