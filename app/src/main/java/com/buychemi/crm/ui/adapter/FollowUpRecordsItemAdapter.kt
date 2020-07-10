package com.buychemi.crm.ui.adapter

import android.content.Context
import com.buychemi.crm.R
import com.buychemi.crm.glide.GlideApp
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 *
 * 跟进记录(图片的适配器)
 * @Author 20342
 * @Date 2019/9/25 10:07
 */
class FollowUpRecordsItemAdapter(context: Context, data:ArrayList<String>):CommonAdapter<String>(context,data, R.layout.item_followup_img_layout) {

    fun addDataNew(dataList: ArrayList<String>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
               GlideApp.with(mContext)
                       .load(data)
                       .error(R.mipmap.moren_icon)
                       .into(holder.getView(R.id.iv_head))

    }
}