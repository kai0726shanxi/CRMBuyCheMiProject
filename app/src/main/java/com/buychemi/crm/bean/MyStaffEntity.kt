package com.buychemi.crm.bean

import java.io.Serializable

/**
 * Created by 20342 on 2019-10-24
 */
data class MyStaffEntity(var id: Int,
                         var name: String?,
                         var pwd: String?,
                         var telPhone: String?,
                         var email: String?,
                         var sex: Int,
                         var deptId: Int,
                         var roleId: Int,
                         var postId: Any?,
                         var superiorPostId: Int,
                         var token: String?,
                         var isDisable: Int,
                         var createTime: String?,
                         var createId: Any,
                         var updateTime: String?,
                         var updateId: Any?,
                         var isDelete: Int,
                         var lastLoginTime: String?,
                         var position: String?,
                         var companyName: Any?,
                         var firstDeptId: String?,
                         var subordinateCount: Int):Serializable