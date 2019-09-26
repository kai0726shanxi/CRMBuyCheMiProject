package com.chmichat.chat.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.TextView
import com.chmichat.chat.R
import com.chmichat.chat.view.recyclerview.ViewHolder
import com.chmichat.chat.view.recyclerview.adapter.CommonAdapter

/**提醒时间
 * @Author 20342
 * @Date 2019/9/24 16:54
 */
class RemindTimeAdapter(context: Context,data:ArrayList<String>):CommonAdapter<String>(context,data, R.layout.item_remindtime_layout) {

    var mName:String="不提醒"
    private var mOnTitletemClick: ((tag: String,i:Int) -> Unit)? = null

    fun setOnItemClickListener(onTitleItemClickListener: (tag: String,i:Int) -> Unit) {
        this.mOnTitletemClick = onTitleItemClickListener
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        var drawable:Drawable=mContext.getDrawable(R.mipmap.chose_visible)
         var tvname=holder.getView<TextView>(R.id.tv_name)
        tvname.text=data

        if (data==mName){
            holder.setViewVisibility(R.id.iv_check,View.VISIBLE)
        }else{
            holder.setViewVisibility(R.id.iv_check,View.GONE)

        }

        holder.setOnItemClickListener(View.OnClickListener { mOnTitletemClick?.invoke(data,position) })

    }
}