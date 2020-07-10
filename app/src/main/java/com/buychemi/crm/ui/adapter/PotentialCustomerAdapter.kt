package com.buychemi.crm.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.bean.SendLinkListEntity
import com.buychemi.crm.ui.activity.MyClientDetailsActivity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * 潜在客户
 * @Author 20342
 * @Date 2019/9/26 10:13
 */
class PotentialCustomerAdapter(context: Context, data: ArrayList<SendLinkListEntity>) : CommonAdapter<SendLinkListEntity>(context, data, R.layout.item_mylinkman_layout) {

    fun addDataNew(dataList: ArrayList<SendLinkListEntity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addDataAll(dataList: ArrayList<SendLinkListEntity>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: SendLinkListEntity, position: Int) {
//状态0成功1失败
        data?.customerName?.let { holder.setText(R.id.tv_name, it) }
        data?.position?.let { holder.setText(R.id.tv_positon, it) }
        data?.customerTel?.let { holder.setText(R.id.tv_phone, it) }
        data?.companyName?.let { holder.setText(R.id.tv_company, it) }
        when(data?.state){

            0->{
                holder.setText(R.id.tv_tag,"成功")

            }
            else->{
                holder.setText(R.id.tv_tag,"失败")

            }
        }
    }
}