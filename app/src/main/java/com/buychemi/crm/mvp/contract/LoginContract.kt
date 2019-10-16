package com.buychemi.crm.mvp.contract

import com.buychemi.crm.base.IBaseView
import com.buychemi.crm.bean.UserBean

/**
 * @Author 20342
 * @Date 2019/10/15 16:18
 */
interface LoginContract {

  interface View:IBaseView{
      /**
       * 显示错误信息
       */
      fun showError(errorMsg:String,errorCode:Int)
      /**
       * 手机登录
       */
      fun onloginMember(data: UserBean?)
  }
    interface Presenter{
        fun getLoginMember(map:Map<String,String>)
    }
}