package com.chmichat.chat.ui.adapter

import android.content.Context
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 我的客户详情底部联系人
 * @Author 20342
 * @Date 2019/9/25 15:41
 */
class MyClientTabUserAdapter(context: Context, data:ArrayList<String>):CommonAdapter<String>(context,data, R.layout.item_related_user_layout) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {

    }
}