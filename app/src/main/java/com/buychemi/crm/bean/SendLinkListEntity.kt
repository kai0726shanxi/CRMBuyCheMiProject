package com.buychemi.crm.bean

import java.io.Serializable

/**
 * Updated by 20342 on 2019-10-22
 */
data class SendLinkListEntity(var id: Int,
                              var recordId: Int,
                              var state: Int,
                              var customerId: Int,
                              var remark: String?,
                              var customerName: String?,
                              var position: String?,
                              var companyName: String?,
                              var customerTel: String?):Serializable