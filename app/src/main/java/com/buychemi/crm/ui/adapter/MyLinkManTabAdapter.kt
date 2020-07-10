package com.buychemi.crm.ui.adapter

import android.content.Context
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.bean.CustomerListEntity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * 我的联系人
 * @Author 20342
 * @Date 2019/9/23 14:58
 */
class MyLinkManTabAdapter(context: Context, data: ArrayList<CustomerListEntity>) : CommonAdapter<CustomerListEntity>(context, data, R.layout.item_mylinkman_layout) {

    var isshow=true
    private var mOnTitletemClick: ((tag: CustomerListEntity, i: Int) -> Unit)? = null
    fun addDataNew(dataList: ArrayList<CustomerListEntity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addDataAll(dataList: ArrayList<CustomerListEntity>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    fun setOnTitleItemClickListener(onTitleItemClickListener: (tag: CustomerListEntity, i: Int) -> Unit) {
        this.mOnTitletemClick = onTitleItemClickListener
    }

    override fun bindData(holder: ViewHolder, data: CustomerListEntity, position: Int) {
//1:重点关注2:持续跟进3:放弃
        data.name?.let { holder.setText(R.id.tv_name, it) }
        data.position?.let { holder.setText(R.id.tv_positon, it) }
        data.tel?.let { holder.setText(R.id.tv_phone, it) }
        data.company.companyName?.let { holder.setText(R.id.tv_company, it) }
       if (isshow){
          holder.setViewVisibility(R.id.tv_tag,View.VISIBLE)
       }else{
           holder.setViewVisibility(R.id.tv_tag,View.INVISIBLE)

       }
        //resource 1:电商同步2:系统创建
        when (data.resource) {

            1 -> {
                holder.setText(R.id.tv_tag, "平台")
            }
            else-> {
                holder.setText(R.id.tv_tag, "非平台")

            }
        }
        holder.setOnItemClickListener(View.OnClickListener {

            mOnTitletemClick?.invoke(data, position)


        })
    }
}