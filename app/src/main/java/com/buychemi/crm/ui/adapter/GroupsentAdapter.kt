package com.buychemi.crm.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.ui.activity.mesage.GroupSentDetailsActivity
import com.buychemi.crm.ui.activity.mesage.MessageLinkmanActivity
import com.buychemi.crm.ui.activity.mesage.MessageMyClientActivity
import com.buychemi.crm.ui.activity.mesage.MessageMyGroupActivity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/9/24 18:45
 */
class GroupsentAdapter(context: Context, data: ArrayList<String>) : CommonAdapter<String>(context, data, R.layout.item_groupsent_layout) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        holder.setText(R.id.tv_name, data)

        holder.setOnItemClickListener(View.OnClickListener {
            when (position) {
                0 -> {
                    mContext.startActivity(Intent(mContext as Activity, MessageLinkmanActivity::class.java))

                }
                1 -> {
                    mContext.startActivity(Intent(mContext as Activity, MessageMyGroupActivity::class.java))

                }

                2 -> {
                    mContext.startActivity(Intent(mContext as Activity, MessageMyClientActivity::class.java))

                }


            }

        })


    }
}