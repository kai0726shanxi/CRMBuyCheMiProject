package com.buychemi.crm.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.bean.SendListEntity
import com.buychemi.crm.ui.activity.mesage.GroupSentDetailsActivity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/9/26 11:10
 */
//群发消息
class MassRecordAdapter(context: Context, data: ArrayList<SendListEntity>) : CommonAdapter<SendListEntity>(context, data, R.layout.item_massrecord_layout) {

    fun setDataAll(categoryList: ArrayList<SendListEntity>) {
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    fun setDataNew(categoryList: ArrayList<SendListEntity>) {
        this.mData.clear()
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: SendListEntity, position: Int) {

        data.sendName?.let { holder.setText(R.id.tv_name, it) }
        //发送状态0待发送1已发送2发送失败
        when (data.state) {
            0 -> {
                holder.setText(R.id.tv_type, "待发送")
            }
            1 -> {
                holder.setText(R.id.tv_type, "已发送")
            }
            2 -> {
                holder.setText(R.id.tv_type, "发送失败")
            }
        }
        data?.createTime?.let { holder.setText(R.id.tv_time, it) }
        data?.title?.let { holder.setText(R.id.tv_bottom_time, it) }
        holder.setOnItemClickListener(View.OnClickListener {
            var intent = Intent(mContext as Activity, GroupSentDetailsActivity::class.java)
            intent.putExtra(Constants.KEYTYPE, "2")
            intent.putExtra(Constants.KEYGROUPID, data?.templateId)
            intent.putExtra(Constants.KEYDTATA,data)
            mContext.startActivity(intent)
        })
    }
}