package com.buychemi.crm.mvp.model

import com.buychemi.crm.bean.FindGroupIdEntity
import com.buychemi.crm.bean.TemplateListEntity
import com.buychemi.crm.dispatchDefault
import com.buychemi.crm.net.BaseResponse
import com.buychemi.crm.net.RetrofitManager
import com.buychemi.crm.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/10/21 17:36
 */
class TemplateModel {
    /**
     *获取模板列表
     */
    fun getTemplatList(map: Map<String, String>): Observable<BaseResponse<ArrayList<TemplateListEntity>?>> {
        return RetrofitManager.service.getTemplateList(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())


    }
  /**
     *获取模板列表
     */
    fun getGroupids(map: Map<String, String>): Observable<BaseResponse<FindGroupIdEntity?>> {
        return RetrofitManager.service.getfindByGroupId(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())


    }

}