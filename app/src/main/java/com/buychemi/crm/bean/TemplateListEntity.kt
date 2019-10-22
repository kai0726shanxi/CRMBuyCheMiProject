package com.buychemi.crm.bean

import java.io.Serializable

/**
 * Updated by 20342 on 2019-10-21
 */
data class TemplateListEntity(var id: Int,
                              var createTime: String?,
                              var createUserId: Int,
                              var createName: String?,
                              var name: String?,
                              var type: Int,
                              var aliSmsNo: String?,
                              var contentType: String?,
                              var updateTime: String?,
                              var updateId: String?,
                              var isDelete: Int,
                              var content: String?):Serializable