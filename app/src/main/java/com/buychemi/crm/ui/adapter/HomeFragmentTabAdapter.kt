package com.buychemi.crm.ui.adapter

import android.content.Context
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.glide.GlideApp
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 *
 *顶部tab
 * @Author 20342
 * @Date 2019/9/21 13:31
 */
class HomeFragmentTabAdapter(context: Context, list: ArrayList<String>) : CommonAdapter<String>(context, list, R.layout.item_home_tab_layout) {

    private var mOnTitletemClick: ((tag: String,i:Int) -> Unit)? = null

    fun setOnItemClickListener(onTitleItemClickListener: (tag: String,i:Int) -> Unit) {
        this.mOnTitletemClick = onTitleItemClickListener
    }
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        when (data) {
            "1" -> {
             GlideApp.with(mContext)
                     .load(R.mipmap.home_richeng_ic)
                     .into(holder.getView(R.id.iv_head))
                holder.setText(R.id.tv_name,"我的日程")
            }
            "2" -> {
                GlideApp.with(mContext)
                        .load(R.mipmap.home_record_ic)
                        .into(holder.getView(R.id.iv_head))
                holder.setText(R.id.tv_name,"跟进记录")
            }
            "3" -> {
                GlideApp.with(mContext)
                        .load(R.mipmap.home_client_ic)
                        .into(holder.getView(R.id.iv_head))
                holder.setText(R.id.tv_name,"我的报告")
            }
            "4" -> {
                GlideApp.with(mContext)
                        .load(R.mipmap.home_message_ic)
                        .into(holder.getView(R.id.iv_head))
                holder.setText(R.id.tv_name,"群发消息")
            }
            "5" -> {
                GlideApp.with(mContext)
                        .load(R.mipmap.home_daily_ic)
                        .into(holder.getView(R.id.iv_head))
                holder.setText(R.id.tv_name,"我的备忘")
            }
            "6" -> {
                GlideApp.with(mContext)
                        .load(R.mipmap.home_contact_ic)
                        .into(holder.getView(R.id.iv_head))
                holder.setText(R.id.tv_name,"我的客户")
            }

        }
        holder.setOnItemClickListener(View.OnClickListener { mOnTitletemClick?.invoke(data,position) }
        )

    }
}