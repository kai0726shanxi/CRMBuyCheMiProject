package com.buychemi.crm.bean

/**
 * Updated by 20342 on 2019-10-22
 */
data class LogListEntity(var id: Int,
                         var associatedId: Int,
                         var associatedBusiness: Int,
                         var associatedBusinessName: String?,
                         var operatorId: Int,
                         var operatorTime: String?,
                         var operatorType: Int,
                         var operatorTypeName: String?,
                         var remark: String?,
                         var companyId: Int,
                         var operatorName: String?,
                         var companyName: String?)