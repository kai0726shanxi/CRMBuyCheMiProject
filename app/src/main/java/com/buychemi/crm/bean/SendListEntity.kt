package com.buychemi.crm.bean

import java.io.Serializable

/**
 * Updated by 20342 on 2019-10-22
 */
data class SendListEntity(var id: Int,
                          var templateId: Int,
                          var title: String?,
                          var content: String?,
                          var sendUserId: Int,
                          var sendName: String?,
                          var createTime: String?,
                          var state: Int,
                          var sendNum: Int,
                          var arrivalsNum: Int):Serializable