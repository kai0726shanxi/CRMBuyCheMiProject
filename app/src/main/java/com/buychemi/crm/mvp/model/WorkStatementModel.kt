package com.buychemi.crm.mvp.model

import com.buychemi.crm.bean.ReportCommentEntity
import com.buychemi.crm.bean.ReportListEntity
import com.buychemi.crm.bean.WorkStatementEntity
import com.buychemi.crm.dispatchDefault
import com.buychemi.crm.net.BaseResponse
import com.buychemi.crm.net.RetrofitManager
import com.buychemi.crm.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Author 20342
 * @Date 2019/10/21 11:52
 */
class WorkStatementModel {
    /**
     * 报告列表
     */
    fun getReportList(map: Map<String, String>): Observable<BaseResponse<ArrayList<ReportListEntity>?>> {
        return RetrofitManager.service.getReportList(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 报告详情
     */
    fun getReportDetails(map: Map<String, String>): Observable<BaseResponse<WorkStatementEntity?>> {
        return RetrofitManager.service.getReportDetails(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 报告详情评论列表
     */
    fun getReportCommentList(map: Map<String, String>): Observable<BaseResponse<ArrayList<ReportCommentEntity>?>> {
        return RetrofitManager.service.getReportCommentList(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }
    /**
     * 报告详情
     */
    fun getReportadd(map: Map<String, String>): Observable<BaseResponse<String?>> {
        return RetrofitManager.service.getreportadd(map)
                .dispatchDefault()
                .compose(SchedulerUtils.ioToMain())
    }


}