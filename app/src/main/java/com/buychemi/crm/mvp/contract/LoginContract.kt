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
      fun onFindpsw(data:String?)
      fun onSendCode(data:String?)
  }
    interface Presenter{
        fun getLoginMember(map:Map<String,String>)
        fun getFindPsw(map:Map<String,String>)
        fun getSendCode(map:Map<String,String>)
    }
}