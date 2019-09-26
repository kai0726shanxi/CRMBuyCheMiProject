package com.chmichat.chat.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.ui.activity.GroupSentDetailsActivity
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/9/26 11:10
 */
//群发消息
class MassRecordAdapter(context: Context, data: ArrayList<String>) : CommonAdapter<String>(context, data, R.layout.item_massrecord_layout) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
     holder.setOnItemClickListener( View.OnClickListener {
         mContext.startActivity(Intent(mContext as Activity, GroupSentDetailsActivity::class.java))
     })
    }
}