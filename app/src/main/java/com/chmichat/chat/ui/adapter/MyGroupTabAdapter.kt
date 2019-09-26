package com.chmichat.chat.ui.adapter

import android.content.Context
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 我的分组
 * @Author 20342
 * @Date 2019/9/23 14:58
 */
class MyGroupTabAdapter(context: Context,data:ArrayList<String>):CommonAdapter<String>(context,data, R.layout.item_mygroup_layout) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {

    }
}