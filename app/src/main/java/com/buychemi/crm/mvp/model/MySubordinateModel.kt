package com.buychemi.crm.mvp.model

import com.buychemi.crm.bean.CustomerListEntity
import com.buychemi.crm.bean.MyStaffEntity
import com.buychemi.crm.dispatchDefault
import com.buychemi.crm.net.BaseResponse
import com.buychemi.crm.net.RetrofitManager
import com.buychemi.crm.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/10/24 18:23
 */
class MySubordinateModel {
    /**
     * 我下属联系人列表
     */
    fun getMySubordinateList(map:Map<String,String>): Observable<BaseResponse<ArrayList<MyStaffEntity>?>> {


        return RetrofitManager.service.getmySubordinateList(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
    /**
     * 查询联系人列表
     */
    fun getFindCustomerList(map:Map<String,String>): Observable<BaseResponse<ArrayList<CustomerListEntity>?>> {


        return RetrofitManager.service.getFindCustomerList(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

}