package com.buychemi.crm.ui.adapter

import android.content.Context
import com.buychemi.crm.R
import com.buychemi.crm.bean.UserLogEntity
import com.buychemi.crm.getTime
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 *
 *底部消息
 * @Author 20342
 * @Date 2019/9/21 13:31
 */
class HomeFragmentHistoryAdapter(context: Context, list: ArrayList<UserLogEntity>) : CommonAdapter<UserLogEntity>(context, list, R.layout.item_home_history_layout) {


    fun setDataAll(categoryList: ArrayList<UserLogEntity>) {
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    fun setDataNew(categoryList: ArrayList<UserLogEntity>) {
        this.mData.clear()
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: UserLogEntity, position: Int) {
        data?.operatorTime?.let { holder.setText(R.id.tv_time, mContext.getTime(it)) }
        holder.setText(R.id.tv_content, data?.operatorName + data?.remark)

    }
}