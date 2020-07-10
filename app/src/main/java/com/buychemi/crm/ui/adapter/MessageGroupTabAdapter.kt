package com.buychemi.crm.ui.adapter

import android.content.Context
import android.view.View
import android.widget.CheckBox
import com.buychemi.crm.R
import com.buychemi.crm.bean.CustomerListEntity
import com.buychemi.crm.bean.GroupListEntity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * 我的分组
 * @Author 20342
 * @Date 2019/9/23 14:58
 */
class MessageGroupTabAdapter(context: Context, data:ArrayList<GroupListEntity>):CommonAdapter<GroupListEntity>(context,data, R.layout.item_messagegroup_layout) {
    private var mOnTitletemClick: BtnDataLinsenter? = null

    fun setOnItemListener(onclick: BtnDataLinsenter) {
        this.mOnTitletemClick = onclick
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
        var checkBox = holder.getView<CheckBox>(R.id.iv_check)

        checkBox.setOnCheckedChangeListener(null)

        checkBox.isChecked = data.isChoosed

        // 先设置一次CheckBox的选中监听器，传入参数null,防止多次监听错乱
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            data.isChoosed = isChecked

            mOnTitletemClick?.btndata(data, position, isChecked)
        }
    }

    interface BtnDataLinsenter {
        fun btndata(str: GroupListEntity, p: Int, b: Boolean)
    }
}