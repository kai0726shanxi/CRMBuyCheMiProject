package com.buychemi.crm.bean

import java.io.Serializable

/**
 * Created by 20342 on 2019-10-25
 */
data class CompanyListEntity(var id:Int,
                             var companyName:String?,
                             var industryId:Int,
                             var type:Int,
                             var area:String?,
                             var address:String?,
                             var businessScale:String?,
                             var companyLicense:String?,
                             var createTime:String?,
                             var companyValue:String?):Serializable