package com.chmichat.chat.ui.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 * 我的日程
 * @Author 20342
 * @Date 2019/9/24 9:44
 */
class MyScheduleAdapter(context: Context,data:ArrayList<String>):CommonAdapter<String>(context,data, R.layout.item_myschedule_layout) {
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        var isshow=false



        holder.getView<ImageView>(R.id.iv_next).setOnClickListener {
            if (!isshow){
                isshow=!isshow
                holder.setViewVisibility(R.id.cl_bottom,View.VISIBLE)
                holder.setImageResource(R.id.iv_next,R.mipmap.jiantouup)

            }else{
                isshow=!isshow
                holder.setViewVisibility(R.id.cl_bottom,View.GONE)
                holder.setImageResource(R.id.iv_next,R.mipmap.jiantoudown)

            }
        }
    }
}