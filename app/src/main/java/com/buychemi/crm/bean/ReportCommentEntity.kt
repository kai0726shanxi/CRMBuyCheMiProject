package com.buychemi.crm.bean

/**
 * Updated by 20342 on 2019-10-23
 */
data class ReportCommentEntity(var id: Int,
                               var auditId: Int,
                               var auditName: String?,
                               var reportId: Int,
                               var auditRemark: String?,
                               var createTime: String?,
                               var createId: Int,
                               var isDelete: Int)