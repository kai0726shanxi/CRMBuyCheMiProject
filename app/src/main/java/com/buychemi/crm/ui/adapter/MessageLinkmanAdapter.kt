package com.buychemi.crm.ui.adapter

import android.content.Context
import com.buychemi.crm.R
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/10/15 11:23
 */
class MessageLinkmanAdapter(context: Context,data:ArrayList<String>):CommonAdapter<String>(context,data, R.layout.item_messagelinkman_layout) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {

    }
}