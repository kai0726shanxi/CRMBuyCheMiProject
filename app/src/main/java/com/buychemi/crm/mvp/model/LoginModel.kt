package com.buychemi.crm.mvp.model

import com.buychemi.crm.bean.UserBean
import com.buychemi.crm.dispatchDefault
import com.buychemi.crm.net.BaseResponse
import com.buychemi.crm.net.RetrofitManager
import com.buychemi.crm.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/10/15 16:20
 */
class LoginModel {


    /**
     * 手机登录
     */
    fun getLoginMember(map:Map<String,String>): Observable<BaseResponse<UserBean?>> {


        return RetrofitManager.service.getLogin(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}