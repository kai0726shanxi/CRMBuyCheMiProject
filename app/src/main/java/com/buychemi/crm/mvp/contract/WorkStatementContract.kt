package com.buychemi.crm.mvp.contract

import com.buychemi.crm.base.IBaseView
import com.buychemi.crm.bean.ReportCommentEntity
import com.buychemi.crm.bean.ReportListEntity
import com.buychemi.crm.bean.WorkStatementEntity

/**
 * 我的报告列表及详情
 * @Author 20342
 * @Date 2019/10/21 11:44
 */
interface WorkStatementContract {
    interface View : IBaseView {
        fun showError(errorMsg: String, errorCode: Int)
        fun onReportList(data: ArrayList<ReportListEntity>?, total: Int?)
        fun onRePortDetails(data: WorkStatementEntity?)
        fun onReportComment(data: ArrayList<ReportCommentEntity>?, total: Int?)
    }

    interface Presenter {
        fun getReportList(map: Map<String, String>)
        fun getRePortDetails(map: Map<String, String>)
        fun getRePortComment(map: Map<String, String>)
    }
}