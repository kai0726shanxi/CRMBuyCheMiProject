package com.chmichat.chat.ui.adapter

import android.content.Context
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 *
 *底部消息
 * @Author 20342
 * @Date 2019/9/21 13:31
 */
class HomeFragmentHistoryAdapter(context: Context, list: ArrayList<String>):CommonAdapter<String>(context,list, R.layout.item_home_history_layout) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
    }
}