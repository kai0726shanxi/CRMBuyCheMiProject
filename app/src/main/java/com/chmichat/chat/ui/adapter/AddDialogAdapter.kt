package com.chmichat.chat.ui.adapter

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import com.chmichat.chat.R
import com.chmichat.chat.glide.GlideApp
import com.chmichat.chat.view.CircleImageView
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**
 *
 *顶部tab
 * @Author 20342
 * @Date 2019/9/21 13:31
 */
class AddDialogAdapter(context: Context, list: ArrayList<String>) : CommonAdapter<String>(context, list, R.layout.item_add_dialog) {
    private var mOnTitletemClick: ((tag: String,i:Int) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: (tag: String,i:Int) -> Unit) {
        this.mOnTitletemClick = onItemClickListener
    }

    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        var ivHead=holder.getView<ImageView>(R.id.iv_head)
        when (data) {
            "1" -> {

                ivHead.setColorFilter(ContextCompat.getColor(mContext,R.color.dialogbule))
                holder.setText(R.id.tv_name,"群发\n信息")
            }
            "2" -> {

                ivHead.setColorFilter(ContextCompat.getColor(mContext,R.color.dialoggreen))

                holder.setText(R.id.tv_name,"新建\n日程")
            }
            "3" -> {

                ivHead.setColorFilter(ContextCompat.getColor(mContext,R.color.dialogyellow))

                holder.setText(R.id.tv_name,"录入\n客户")
            }
            "4" -> {

                ivHead.setColorFilter(ContextCompat.getColor(mContext,R.color.dialogyellow))

                holder.setText(R.id.tv_name,"工作\n报告")
            }
            "5" -> {

                ivHead.setColorFilter(ContextCompat.getColor(mContext,R.color.dialogpurple))

                holder.setText(R.id.tv_name,"新建\n日志")
            }
            "6" -> {

                ivHead.setColorFilter(ContextCompat.getColor(mContext,R.color.dialogbule))
                holder.setText(R.id.tv_name,"新建\n跟进")
            }

        }


        holder.setOnItemClickListener(View.OnClickListener { mOnTitletemClick?.invoke(data,position) })


    }
}