package com.buychemi.crm.bean

import java.io.Serializable

/**
 * Created by 20342 on 2019-10-17
 * {
"id":385,
"documentName":null,
"note":null,
"url":null,
"storageAddr":null,
"size":null,
"type":null,
"associatedId":null,
"kind":null,
"fileFormat":null,
"createTime":null,
"createId":null,
"createName":null
}
 */
data class UpImagesEntity(var id:Int,var documentName:String?,var note:String?,
                          var url:String?,
                          var storageAddr:String?,
                          var size:String?,
                          var type:String?,
                          var associatedId:String?,
                          var kind:String?,
                          var fileFormat:String?,
                          var createTime:String?,
                          var createId:String?,
                          var createName:String?
                            ):Serializable