package com.buychemi.crm.bean

import java.io.Serializable

/**
 * Updated by 20342 on 2019-10-22
 */
data class UserBean(var id: Int,
                    var name: String?,
                    var pwd: String?,
                    var telPhone: String?,
                    var email: String?,
                    var sex: Int,
                    var deptId: Int,
                    var roleId: Int,
                    var postId: Int,
                    var superiorPostId: Int,
                    var token: String?,
                    var isDisable: Int,
                    var createTime: String?,
                    var createId: String?,
                    var updateTime: String?,
                    var updateId: String?,
                    var isDelete: Int,
                    var lastLoginTime: String?,
                    var position: String?,
                    var companyName: String?,
                    var firstDeptId: String?):Serializable