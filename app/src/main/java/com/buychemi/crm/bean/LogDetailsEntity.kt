package com.buychemi.crm.bean

import java.io.Serializable

/**
 * Updated by 20342 on 2019-10-25
 */
data class LogDetailsEntity(var id: Int,
                            var description: String,
                            var checkInLocation: String,
                            var remarkDate: String,
                            var markValue: Int,
                            var createTime: String,
                            var updateTime: String,
                            var isDelete: Int,
                            var image: String,
                            var userId: Int):Serializable