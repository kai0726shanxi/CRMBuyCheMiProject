package com.chmichat.chat.ui.adapter

import android.content.Context
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 *
 *中间部分
 * @Author 20342
 * @Date 2019/9/21 13:31
 */
class HomeFragmentUserAdapter(context: Context, list: ArrayList<String>) : CommonAdapter<String>(context, list, R.layout.item_home_user_layout) {

    private var mOnTitletemClick: ((tag: String,i:Int) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: (tag: String,i:Int) -> Unit) {
        this.mOnTitletemClick = onItemClickListener
    }
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        when (data) {
            "1" -> {
            holder.setImageResource(R.id.iv_head,R.mipmap.home_xinzeng)
            holder.setText(R.id.tv_name,"新增客户")
            }

            "2" -> {
                holder.setImageResource(R.id.iv_head,R.mipmap.home_qianzai)
                holder.setText(R.id.tv_name,"潜在客户")
            }

            "3" -> {
                holder.setImageResource(R.id.iv_head,R.mipmap.home_jilu)
                holder.setText(R.id.tv_name,"新增跟进记录")
            }

            "4" -> {
                holder.setImageResource(R.id.iv_head,R.mipmap.home_duanxin)
                holder.setText(R.id.tv_name,"群发短信")
            }




        }
        holder.setOnItemClickListener(View.OnClickListener { mOnTitletemClick?.invoke(data,position) }
        )
    }
}