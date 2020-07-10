package com.buychemi.crm.ui.adapter

import android.content.Context
import com.buychemi.crm.R
import com.buychemi.crm.bean.UserDataEntity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * 我的客户详情底部基本信息
 * @Author 20342
 * @Date 2019/9/25 15:41
 */
class MyClientTabBasicAdapter(context: Context,data:ArrayList<UserDataEntity>):CommonAdapter<UserDataEntity>(context,data, R.layout.item_clienttabbasic) {
    override fun bindData(holder: ViewHolder, data: UserDataEntity, position: Int) {

        data?.title?.let { holder.setText(R.id.tv_name, it) }
        data?.name?.let { holder.setText(R.id.tv_name_content, it) }

    }
}