package com.buychemi.crm.bean

import java.io.Serializable

/**
 * Updated by 20342 on 2019-10-22
 */
data class MyScheduleEntity(var id: Int,
                            var title: String?,
                            var startTime: String?,
                            var endTime: String?,
                            var groupId: Int,
                            var companyId: String?,
                            var customerId: Int,
                            var description: String?,
                            var state: Int,
                            var remindType: Int,
                            var remindTime: String?,
                            var createId: Int,
                            var createTime: String?,
                            var updateTime: String?,
                            var isDelete: Int,
                            var userName: String?,
                            var associationCompanyName: String?,
                            var associationCustomerName: String?,
                            var associationCustomerTel: String?,
                            var associationCustomerPosition: String?,
                            var markValue: String?,
                            var principalId: Int):Serializable