package com.buychemi.crm.ui.adapter

import android.content.Context
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.bean.GroupListEntity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * 我的分组
 * @Author 20342
 * @Date 2019/9/23 14:58
 */
class MyGroupTabAdapter(context: Context, data: ArrayList<GroupListEntity>) : CommonAdapter<GroupListEntity>(context, data, R.layout.item_mygroup_layout) {
    private var mOnTitletemClick: ((tag: GroupListEntity, i: Int) -> Unit)? = null

    fun setOnTitleItemClickListener(onTitleItemClickListener: (tag: GroupListEntity, i: Int) -> Unit) {
        this.mOnTitletemClick = onTitleItemClickListener
    }

    fun addDataNew(dataList: ArrayList<GroupListEntity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addDataAll(dataList: ArrayList<GroupListEntity>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: GroupListEntity, position: Int) {
        data.groupName?.let { holder.setText(R.id.tv_name, it) }
        holder.setText(R.id.tv_num,data.customerNum.toString())
        holder.setOnItemClickListener(View.OnClickListener {

            mOnTitletemClick?.invoke(data, position)


        })
    }
}