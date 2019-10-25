package com.buychemi.crm.bean

import java.io.Serializable

/**
 * Created by 20342 on 2019-10-23
 */
data class TemplateDetails(var id:Int,
                           var name:String?,
                           var createName:String?,
                           var type:String?,
                           var aliSmsNo:String?,
                           var contentType:String?,
                           var content:String?):Serializable