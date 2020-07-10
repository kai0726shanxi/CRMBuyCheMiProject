package com.buychemi.crm.ui.adapter

import android.content.Context
import com.buychemi.crm.R
import com.buychemi.crm.bean.LogListEntity
import com.buychemi.crm.getTime
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * 我的客户详情底部相关信息
 * @Author 20342
 * @Date 2019/9/25 15:41
 */
class MyClientTabAdapter(context: Context, data:ArrayList<LogListEntity>):CommonAdapter<LogListEntity>(context,data, R.layout.item_related_layout) {

    fun setDataAll(categoryList: ArrayList<LogListEntity>) {
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    fun setDataNew(categoryList: ArrayList<LogListEntity>) {
        this.mData.clear()
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }


    override fun bindData(holder: ViewHolder, data: LogListEntity, position: Int) {
        data?.operatorTime?.let { holder.setText(R.id.tv_time, mContext.getTime(it)) }
        holder.setText(R.id.tv_content,data?.operatorName+data?.operatorTypeName+"了用户")
        data?.remark?.let { holder.setText(R.id.tv_content_time, it) }
    }
}