package com.buychemi.crm.mvp.model

import com.buychemi.crm.bean.SendSuccessEntity
import com.buychemi.crm.bean.TemplateDetails
import com.buychemi.crm.dispatchDefault
import com.buychemi.crm.net.BaseResponse
import com.buychemi.crm.net.RetrofitManager
import com.buychemi.crm.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/10/21 19:25
 */
class MassTextingModel {

    //群发消息
    fun getSendMessage(map:Map<String,String?>): Observable<BaseResponse<SendSuccessEntity?>> {


        return RetrofitManager.service.getSendMessage(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
    //模板详情
    fun getTempDetails(map:Map<String,String?>): Observable<BaseResponse<TemplateDetails?>> {


        return RetrofitManager.service.gettemplateDetails(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}