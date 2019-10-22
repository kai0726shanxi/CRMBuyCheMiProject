package com.buychemi.crm.bean

/**
 * Created by 20342 on 2019-10-17
 */
data class PostBodyEntity(var reportDate: String,
                          var workSummary: String,
                          var type: Int,
                          var img: ArrayList<UpImagesEntity>?)
