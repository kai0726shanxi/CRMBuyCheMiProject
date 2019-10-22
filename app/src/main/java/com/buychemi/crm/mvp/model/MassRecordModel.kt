package com.buychemi.crm.mvp.model

import com.buychemi.crm.bean.SendLinkListEntity
import com.buychemi.crm.bean.SendListEntity
import com.buychemi.crm.dispatchDefault
import com.buychemi.crm.net.BaseResponse
import com.buychemi.crm.net.RetrofitManager
import com.buychemi.crm.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/10/22 10:28
 */
class MassRecordModel {

    //群发消息记录列表
    fun getSendGroupList(map:Map<String,String?>): Observable<BaseResponse<ArrayList<SendListEntity>?>> {


        return RetrofitManager.service.getSendMessageList(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    //群发(Users)消息记录列表
    fun getSendLinkList(map:Map<String,String?>): Observable<BaseResponse<ArrayList<SendLinkListEntity>?>> {


        return RetrofitManager.service.getSendLinkList(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
}