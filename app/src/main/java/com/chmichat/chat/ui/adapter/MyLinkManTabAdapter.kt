package com.chmichat.chat.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.ui.activity.MyClientDetailsActivity
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 我的联系人
 * @Author 20342
 * @Date 2019/9/23 14:58
 */
class MyLinkManTabAdapter(context: Context, data:ArrayList<String>):CommonAdapter<String>(context,data, R.layout.item_mylinkman_layout) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        holder.setOnItemClickListener(View.OnClickListener {
            var intent= Intent(mContext, MyClientDetailsActivity::class.java)
               intent.putExtra(Constants.KEYTYPE,"1")
            mContext.startActivity(intent)

        })
    }
}