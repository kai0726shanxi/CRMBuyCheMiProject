package com.buychemi.crm.bean

import java.io.Serializable

/**
 * Created by 20342 on 2019-10-18
 */
data class GroupListEntity(var id:Int,
                           var groupName:String?,
                           var firstLeader:String?,
                           var firstLeaderId:Int,
                           var customerNum:Int?=0,
                           var leaderNum:Int,
                           var dictClassId:Int,
                           var dictClassFirstId:Int,
                           var dictClassSecondId:Int,
                           var dictClassThirdId:Int,
                           var createTime:String?,
                           var isChoosed:Boolean):Serializable