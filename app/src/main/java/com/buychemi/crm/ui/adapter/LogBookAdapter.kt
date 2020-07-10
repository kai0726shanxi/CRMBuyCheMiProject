package com.buychemi.crm.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.bean.LogDetailsEntity
import com.buychemi.crm.bean.LogLookEntity
import com.buychemi.crm.getTime
import com.buychemi.crm.ui.activity.log.LogDetailsActivity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * @Author 20342
 * @Date 2019/9/25 13:51
 */
class LogBookAdapter(context: Context, data: ArrayList<LogLookEntity>) : CommonAdapter<LogLookEntity>(context, data, R.layout.item_logbook_layout) {

    fun setDataAll(categoryList: ArrayList<LogLookEntity>) {
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    fun setDataNew(categoryList: ArrayList<LogLookEntity>) {
        this.mData.clear()
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: LogLookEntity, position: Int) {
        var mRecycle = holder.getView<RecyclerView>(R.id.recycle_img)
        var mlist = ArrayList<String>()
        if (data?.image != null && data?.image?.contains(",")!!) {
            mlist = data?.image?.split(",") as ArrayList<String>

        }
        mRecycle.isNestedScrollingEnabled = false
        if (mlist != null) {
            mRecycle.adapter = FollowUpRecordsItemAdapter(mContext as Activity, mlist)
            mRecycle.layoutManager = GridLayoutManager(mContext, 3)
        }
        data?.remarkDate?.let { holder.setText(R.id.tv_time, mContext.getTime(it)) }
        data?.description?.let { holder.setText(R.id.tv_content, it) }
        data?.checkInLocation?.let { holder.setText(R.id.tv_address, it) }

        holder.setOnItemClickListener(View.OnClickListener {
            val intent = Intent(mContext, LogDetailsActivity::class.java)
            intent.putExtra(Constants.KEYTYPE, "1")
            intent.putExtra(Constants.KEYGROUPID, data.id)
            mContext.startActivity(intent)

        })
    }
}