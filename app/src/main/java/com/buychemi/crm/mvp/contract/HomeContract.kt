package com.buychemi.crm.mvp.contract

import com.buychemi.crm.base.IBaseView
import com.buychemi.crm.bean.BriefingEntity
import com.buychemi.crm.bean.UserLogEntity

/**
 * @Author 20342
 * @Date 2019/10/15 16:18
 */
interface HomeContract {

  interface View:IBaseView{
      /**
       * 显示错误信息
       */
      fun showError(errorMsg:String,errorCode:Int)
      /**
       * 用户日志
       */
      fun onUserLogList(data: ArrayList<UserLogEntity>?,total:Int?)
      fun onBriefing(data: BriefingEntity?)
  }
    interface Presenter{
        fun getUserLogList(map:Map<String,String>)
        fun getBriefing(map:Map<String,String>)
    }
}