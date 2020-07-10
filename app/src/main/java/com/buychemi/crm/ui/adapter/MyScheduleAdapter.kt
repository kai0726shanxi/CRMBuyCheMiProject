package com.buychemi.crm.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.buychemi.crm.R
import com.buychemi.crm.bean.MyScheduleEntity
import com.buychemi.crm.getTime
import com.buychemi.crm.ui.activity.schedule.ScheduleDetailsActivity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * 我的日程
 * @Author 20342
 * @Date 2019/9/24 9:44
 */
class MyScheduleAdapter(context: Context, data: ArrayList<MyScheduleEntity>) : CommonAdapter<MyScheduleEntity>(context, data, R.layout.item_myschedule_layout) {

    private var mOnTitletemClick: ((tag: MyScheduleEntity, i: Int,click:Int) -> Unit)? = null

    fun setOnTitleItemClickListener(onTitleItemClickListener: (tag: MyScheduleEntity, i: Int,click:Int) -> Unit) {
        this.mOnTitletemClick = onTitleItemClickListener
    }
    fun setDataAll(categoryList: ArrayList<MyScheduleEntity>) {
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    fun setDataNew(categoryList: ArrayList<MyScheduleEntity>) {
        this.mData.clear()
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: MyScheduleEntity, position: Int) {
        var isshow = false
        data?.remindTime?.let { holder.setText(R.id.tv_time, mContext.getTime(it)) }
        data.title?.let { holder.setText(R.id.tv_content, it) }
        data?.userName?.let { holder.setText(R.id.tv_creat, "创建人  $it") }
        if (data?.startTime != null && data?.endTime != null) {
            holder.setText(R.id.tv_content_time, mContext.getTime(data?.startTime!!) + "~" + mContext.getTime(data?.endTime!!))

        }
       /* holder.setOnItemClickListener(View.OnClickListener {
            var intent = Intent(mContext as Activity, ScheduleDetailsActivity::class.java)
            mContext.startActivity(intent)
        })*/

        holder.getView<ImageView>(R.id.iv_next).setOnClickListener {
            if (!isshow) {
                isshow = !isshow
                holder.setViewVisibility(R.id.cl_bottom, View.VISIBLE)
                holder.setImageResource(R.id.iv_next, R.mipmap.jiantouup)

            } else {
                isshow = !isshow
                holder.setViewVisibility(R.id.cl_bottom, View.GONE)
                holder.setImageResource(R.id.iv_next, R.mipmap.jiantoudown)

            }
        }
        holder.getView<TextView>(R.id.tv_two).setOnClickListener {
            mOnTitletemClick?.invoke(data,position,2)
        }
        holder.getView<TextView>(R.id.tv_four).setOnClickListener {
            mOnTitletemClick?.invoke(data,position,3)
        }
        holder.setOnItemClickListener(View.OnClickListener { mOnTitletemClick?.invoke(data,position,1) })


    }
}