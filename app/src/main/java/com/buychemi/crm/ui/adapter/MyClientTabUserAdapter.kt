package com.buychemi.crm.ui.adapter

import android.content.Context
import com.buychemi.crm.R
import com.buychemi.crm.bean.CustomerSearchEntity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * 我的客户详情底部联系人
 * @Author 20342
 * @Date 2019/9/25 15:41
 */
class MyClientTabUserAdapter(context: Context, data: ArrayList<CustomerSearchEntity>) : CommonAdapter<CustomerSearchEntity>(context, data, R.layout.item_related_user_layout) {

    fun setDataAll(categoryList: ArrayList<CustomerSearchEntity>) {
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    fun setDataNew(categoryList: ArrayList<CustomerSearchEntity>) {
        this.mData.clear()
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: CustomerSearchEntity, position: Int) {
        data?.name?.let { holder.setText(R.id.tv_name, it) }
        data?.position?.let { holder.setText(R.id.tv_positon, it) }
        data?.tel?.let { holder.setText(R.id.tv_phone, it) }
    }
}