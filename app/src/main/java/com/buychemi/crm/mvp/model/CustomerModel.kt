package com.buychemi.crm.mvp.model

import com.buychemi.crm.bean.CompanyDetailsEntity
import com.buychemi.crm.bean.CustomerDetailsEntity
import com.buychemi.crm.bean.CustomerListEntity
import com.buychemi.crm.dispatchDefault
import com.buychemi.crm.net.BaseResponse
import com.buychemi.crm.net.RetrofitManager
import com.buychemi.crm.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/10/18 10:49
 */
class CustomerModel {

    /**
     * 我的联系人列表
     */
    fun getMyGroupList(map:Map<String,String>): Observable<BaseResponse<ArrayList<CustomerListEntity>?>> {


        return RetrofitManager.service.getMyCustomerData(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    //联系人详情
    fun getCustomerDetails(map:Map<String,String>): Observable<BaseResponse<CustomerDetailsEntity?>> {


        return RetrofitManager.service.getCustomerDetails(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
    //公司详情
    fun getCompanlyDetails(map:Map<String,String>): Observable<BaseResponse<CompanyDetailsEntity?>> {


        return RetrofitManager.service.getCompanlyDetails(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}