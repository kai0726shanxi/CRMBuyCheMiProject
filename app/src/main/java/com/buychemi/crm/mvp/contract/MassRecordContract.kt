package com.buychemi.crm.mvp.contract

import com.buychemi.crm.base.IBaseView
import com.buychemi.crm.bean.SendLinkListEntity
import com.buychemi.crm.bean.SendListEntity

/**
 * 发送群组记录
 * @Author 20342
 * @Date 2019/10/22 10:26
 */
interface MassRecordContract {

    interface View:IBaseView{
        fun showError(errorMsg:String,errorCode:Int)
        fun onSendGroupList(data:ArrayList<SendListEntity>?,total:Int?)
        fun ontSendLinkList(data:ArrayList<SendLinkListEntity>?,total:Int?)
    }
    interface Presenter{
         fun getSendFroupList(map: Map<String,String>)
         fun getSendLinkList(map: Map<String,String>)
    }
}