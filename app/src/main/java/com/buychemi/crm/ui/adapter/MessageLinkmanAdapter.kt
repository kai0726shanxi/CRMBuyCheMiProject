package com.buychemi.crm.ui.adapter

import android.content.Context
import android.widget.CheckBox
import com.buychemi.crm.R
import com.buychemi.crm.bean.CustomerListEntity
import com.buychemi.crm.bean.TestDataEntivity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/10/15 11:23
 */

class MessageLinkmanAdapter(context: Context, data: ArrayList<CustomerListEntity>) : CommonAdapter<CustomerListEntity>(context, data, R.layout.item_messagelinkman_layout) {
    private var mOnTitletemClick: BtnDataLinsenter? = null

    fun setOnItemListener(onclick: BtnDataLinsenter) {
        this.mOnTitletemClick = onclick
    }
     var list=ArrayList<CustomerListEntity>()
    fun setDataAll(categoryList: ArrayList<CustomerListEntity>) {
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    fun setDataNew(categoryList: ArrayList<CustomerListEntity>) {
        this.mData.clear()
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: CustomerListEntity, position: Int) {
        var checkBox = holder.getView<CheckBox>(R.id.iv_next)

        checkBox.setOnCheckedChangeListener(null)
         if (list!=null&&list.size>0){
         for (item in list){
             if (item.id==data.id){
                 data.isChoosed=true
             }


         }

         }
        checkBox.isChecked = data.isChoosed

        // 先设置一次CheckBox的选中监听器，传入参数null,防止多次监听错乱
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            data.isChoosed = isChecked

            mOnTitletemClick?.btndata(data, position, isChecked)
        }
        data?.name?.let { holder.setText(R.id.tv_name, it) }
        data?.position?.let { holder.setText(R.id.tv_positon, it) }
        data?.tel?.let { holder.setText(R.id.tv_phone, it) }
        data?.company?.companyName?.let { holder.setText(R.id.tv_company, it) }
        //客户分级1:重点关注2:持续跟进3:放弃
        when (data?.resource){
            1->{
                holder.setText(R.id.tv_tag, "平台")

            }
            else->{
                holder.setText(R.id.tv_tag, "非平台")

            }
        }

      /*  when (data?.customerRating) {
            1 -> {
                holder.setText(R.id.tv_tag, "重点关注")
            }
            2 -> {
                holder.setText(R.id.tv_tag, "持续跟进")
            }
            3 -> {
                holder.setText(R.id.tv_tag, "放弃")
            }

        }*/
    }

    interface BtnDataLinsenter {
        fun btndata(str: CustomerListEntity, p: Int, b: Boolean)
    }
}