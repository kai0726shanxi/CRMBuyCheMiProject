package com.buychemi.crm.bean

import java.io.Serializable

/**
 * Updated by 20342 on 2019-10-21
 */
data class WorkStatementEntity(var id: Int,
                               var userId: Int,
                               var userName: String?,
                               var type: Int,
                               var reportDate: String?,
                               var workPlan: String?,
                               var state: Int,
                               var workSummary: String?,
                               var title: String?,
                               var auditId: String?,
                               var createTime: String?,
                               var createId: Int,
                               var isDelete: Int,
                               var updateId: String?,
                               var updateTime: String?,
                               var docIds: String?,
                               var imgIds: String?,
                               var doc: List<Any>?,
                               var img: ArrayList<UpImagesEntity>?,
                               var auditVoList: List<Any>,
                               var position:String?,
                               var tel:String?,
                               var isMe:Int):Serializable