package com.buychemi.crm.mvp.contract

import com.buychemi.crm.base.IBaseView
import com.buychemi.crm.bean.GroupListEntity
import com.buychemi.crm.bean.UserBean

/**
 * @Author 20342
 * @Date 2019/10/15 16:18
 */
interface GrouptabContract {

  interface View:IBaseView{
      /**
       * 显示错误信息
       */
      fun showError(errorMsg:String,errorCode:Int)
      /**
       * 我的分组数据
       *
       */
      fun onMyGrouplist(data: ArrayList<GroupListEntity>?,total:Int?)
      fun onSubordinatelist(data: ArrayList<GroupListEntity>?,total:Int?)
      fun onAllGroupData(data: ArrayList<GroupListEntity>?,total:Int?)
  }
    interface Presenter{
        fun getMyGrouplist(map:Map<String,String>)
        fun getSubordinatelist(map:Map<String,String>)
        fun getAllGroupData(map:Map<String,String>)
    }
}