package com.buychemi.crm.bean

import java.io.Serializable

/**
 * Updated by 20342 on 2019-10-25
 */
data class FollowDetailsEntity(var id: Int,
                               var customerId: Int,
                               var customerName: String?,
                               var companyId: Int,
                               var companyName: String?,
                               var userId: Int,
                               var userName: String?,
                               var followTime: String?,
                               var nextFollowTime: String?,
                               var detail: String?,
                               var type: Int,
                               var checkInAddress: String?,
                               var checkInTime: String?,
                               var annexId: String?,
                               var createId: Int,
                               var createTime: String?,
                               var updateTime: String?,
                               var updateId: String?,
                               var isDelete: Int,
                               var document: String?,
                               var principalId: Int,
                               var imgs: String?,
                               var dateType: String?,
                               var today: String?,
                               var isMe: String?):Serializable