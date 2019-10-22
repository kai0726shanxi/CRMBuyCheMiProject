package com.buychemi.crm.mvp.contract

import com.buychemi.crm.base.IBaseView
import com.buychemi.crm.bean.FindGroupIdEntity
import com.buychemi.crm.bean.TemplateListEntity

/**
 * @Author 20342
 * @Date 2019/10/21 17:33
 */
interface TemplateContract {
    interface View:IBaseView{
        fun showError(errorMsg:String,errorCode:Int)
        fun onTemplateList(data:ArrayList<TemplateListEntity>?,total:Int?)
        fun onGroupids(data: FindGroupIdEntity?)

    }
    interface Presenter{
        fun  getTemplateList(map: Map<String,String>)
        fun  getGroupids(map: Map<String, String>)
    }
}