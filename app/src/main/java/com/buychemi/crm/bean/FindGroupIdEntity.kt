package com.buychemi.crm.bean

import java.io.Serializable


/**
 * Updated by 20342 on 2019-10-22
 */
data class FindGroupIdEntity(var id: String,
                             var groupId: String,
                             var customerId: String,
                             var groupIds: String,
                             var customerCount: Int,
                             var customerIds: List<String>):Serializable