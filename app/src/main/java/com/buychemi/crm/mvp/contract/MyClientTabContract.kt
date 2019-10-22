package com.buychemi.crm.mvp.contract

import com.buychemi.crm.base.IBaseView
import com.buychemi.crm.bean.CustomerSearchEntity
import com.buychemi.crm.bean.FollowListEntity
import com.buychemi.crm.bean.LogListEntity
import com.buychemi.crm.bean.UserLogEntity

/**
 * @Author 20342
 * @Date 2019/10/21 9:32
 */
interface MyClientTabContract {
    interface View:IBaseView{
        fun showError(errorMsg:String,errorCode:Int)
        fun onFollowList(data:ArrayList<FollowListEntity>?,totalpage:Int?)
        fun onUserLogList(data: ArrayList<UserLogEntity>?,totalpage: Int?)
        fun onLinkLogList(data: ArrayList<LogListEntity>?,totalpage: Int?)
        fun onComplayLogList(data: ArrayList<LogListEntity>?,totalpage: Int?)
        fun onComPlanyCustomer(data: ArrayList<CustomerSearchEntity>?,totalpage: Int?)

    }
    interface Presenter{
        fun getFollowList(map: Map<String,String>)
        fun getUserLogList(map: Map<String, String>)
        fun getLinkLogList(map: Map<String, String>)
        fun getComplayLogList(map: Map<String, String>)
        fun onComPlanyCustomer(map: Map<String, String>)

    }
}