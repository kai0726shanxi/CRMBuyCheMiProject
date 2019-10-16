package com.buychemi.crm.bean

import java.io.Serializable

/**
 * Created by 20342 on 2019-10-15
 */
data class UserBean(var id:Int,
                    var name:String?,
                    var telPhone:String?,
                    var email:String?,
                    var sex:Int,
                    var deptId:Int,
                    var roleId:Int,
                    var postId:Int,
                    var superiorPostId:Int,
                    var token:String?,
                    var isDisable:Int,
                    var createTime:String?):Serializable