package com.buychemi.crm.ui.adapter

import android.content.Context
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * 我的客户
 * @Author 20342
 * @Date 2019/9/23 15:51
 */
class MyClientAdapter(context: Context,data:ArrayList<String>):CommonAdapter<String>(context,data, R.layout.item_myclient_layout) {


    private var mOnTitletemClick: ((tag: String,i:Int) -> Unit)? = null

    fun setOnTitleItemClickListener(onTitleItemClickListener: (tag: String,i:Int) -> Unit) {
        this.mOnTitletemClick = onTitleItemClickListener
    }

    override fun bindData(holder: ViewHolder, data: String, position: Int) {

        holder.setOnItemClickListener(View.OnClickListener {

            mOnTitletemClick?.invoke(data,position)

        })
    }
}