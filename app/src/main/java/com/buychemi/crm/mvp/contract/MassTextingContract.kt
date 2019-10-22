package com.buychemi.crm.mvp.contract

import com.buychemi.crm.base.IBaseView
import com.buychemi.crm.bean.SendSuccessEntity

/**
 * 群发消息界面
 * @Author 20342
 * @Date 2019/10/21 19:22
 */
interface MassTextingContract {
    interface View:IBaseView{
        fun showError(errorMsg:String,errorCode:Int)
        fun onsendMessage(data: SendSuccessEntity?)
    }
    interface Presenter{
        fun getsendmessage(map:Map<String,String>)
    }
}