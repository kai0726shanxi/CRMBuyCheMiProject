package com.buychemi.crm.ui.adapter

import android.content.Context
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.bean.CustomerListEntity
import com.buychemi.crm.bean.MyStaffEntity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * 我的联系人
 * @Author 20342
 * @Date 2019/9/23 14:58
 */
class MyAtaffAdapter(context: Context, data: ArrayList<MyStaffEntity>) : CommonAdapter<MyStaffEntity>(context, data, R.layout.item_mystaff_layout) {


    private var mOnTitletemClick: ((tag: MyStaffEntity, i: Int) -> Unit)? = null
    fun addDataNew(dataList: ArrayList<MyStaffEntity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addDataAll(dataList: ArrayList<MyStaffEntity>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    fun setOnTitleItemClickListener(onTitleItemClickListener: (tag: MyStaffEntity, i: Int) -> Unit) {
        this.mOnTitletemClick = onTitleItemClickListener
    }

    override fun bindData(holder: ViewHolder, data: MyStaffEntity, position: Int) {
//1:重点关注2:持续跟进3:放弃
        data?.name?.let { holder.setText(R.id.tv_name, it) }
        holder.setText(R.id.tv_phone, data?.subordinateCount.toString()+"人")
        holder.setOnItemClickListener(View.OnClickListener {

            mOnTitletemClick?.invoke(data, position)


        })
    }
}