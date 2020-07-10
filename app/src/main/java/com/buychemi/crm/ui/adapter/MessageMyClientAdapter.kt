package com.buychemi.crm.ui.adapter

import android.content.Context
import android.util.Log
import android.widget.CheckBox
import com.buychemi.crm.R
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter
import android.util.SparseBooleanArray
import com.buychemi.crm.bean.TestDataEntivity
import com.buychemi.crm.showToast

/**
 * 我的客户(群发消息)
 * @Author 20342
 * @Date 2019/9/23 15:51
 */
class MessageMyClientAdapter(context: Context, data: ArrayList<TestDataEntivity>) : CommonAdapter<TestDataEntivity>(context, data, R.layout.item_myclient_message_layout) {
    private val mCheckStates = SparseBooleanArray()

    private var mOnTitletemClick: BtnDataLinsenter? = null

    fun setOnItemListener(onclick: BtnDataLinsenter) {
        this.mOnTitletemClick = onclick
    }

    fun addDataNew(dataList: ArrayList<TestDataEntivity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()

    }

    fun addDataAll(dataList: ArrayList<TestDataEntivity>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: TestDataEntivity, position: Int) {

        var checkBox = holder.getView<CheckBox>(R.id.iv_check)

        checkBox.setOnCheckedChangeListener(null);

        checkBox.isChecked = data.isChoosed;

        // 先设置一次CheckBox的选中监听器，传入参数null,防止多次监听错乱
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            data.isChoosed = isChecked

            mOnTitletemClick?.btndata(data,position,isChecked)
        }
    }


    interface BtnDataLinsenter {
        fun btndata(str: TestDataEntivity, p: Int, b: Boolean)
    }

}