package com.buychemi.crm.ui.activity

import android.graphics.Color
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_show_content.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 显示关于内容
 * @Author 20342
 * @Date 2019/10/25 10:59
 */
class ShowContentActivity : BaseActivity(),View.OnClickListener {
    override fun onClick(v: View?) {
     when(v?.id){
         R.id.iv_left->{finish()}
     }
    }

    override fun layoutId(): Int {
        return R.layout.activity_show_content
    }

    override fun initData() {

    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.setOnClickListener(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "关于百料汇CRM"
        tv_content.text = "百料汇CRM系统是由上海百料汇信息科技有限公司研发的，专注于工作管理和团队协同的企业应用系统。主要为了帮助企业轻松任务协作与客户关系管理。\n主要功能点有：\n" +
                "1、基础信息管理\n" +
                "1.1组织结构管理\n" +
                "1.2角色管理\n" +
                "1.3权限管理\n" +
                "\n" +
                "2、客户关系管理\n" +
                "2.1公海客户\n" +
                "2.2灰色客户\n" +
                "2.3客户分组管理\n" +
                "2.4客户分类及定义\n" +
                "2.5客户信息记录及数据分析\n" +
                "\n" +
                "3、协作办公管理\n" +
                "3.1本人及下属信息\n" +
                "3.1.1日程管理\n" +
                "3.1.2工作日志\n" +
                "3.1.3工作报告\n" +
                "3.2信息群发管理\n" +
                "3.2.1短信模板管理\n" +
                "3.2.2短信发送管理及结果查询"
    }


    override fun start() {
    }
}