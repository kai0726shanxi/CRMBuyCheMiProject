package com.buychemi.crm.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.bean.FollowListEntity
import com.buychemi.crm.ui.activity.log.LogDetailsActivity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 *
 * 跟进记录
 * @Author 20342
 * @Date 2019/9/25 10:07
 */
class FollowUpRecordsAdapter(context: Context, data: ArrayList<FollowListEntity>) : CommonAdapter<FollowListEntity>(context, data, R.layout.item_followuprecords_layout) {

    fun addDataNew(dataList: ArrayList<FollowListEntity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addDataAll(dataList: ArrayList<FollowListEntity>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }
    override fun bindData(holder: ViewHolder, data: FollowListEntity, position: Int) {
        var mlist = ArrayList<String>()
        if (data?.imgs!=null){
            if (data?.imgs?.contains(",")){
                var  strarry=data?.imgs.split(",")
                mlist= strarry as ArrayList<String>
            }else{
                mlist.add(data?.imgs)
            }


        }


        var mRecycle = holder.getView<RecyclerView>(R.id.recyclerView_img)
        mRecycle.isNestedScrollingEnabled = false
        mRecycle.adapter = FollowUpRecordsItemAdapter(mContext as Activity, mlist)
        mRecycle.layoutManager = GridLayoutManager(mContext, 3)
        data?.userName?.let { holder.setText(R.id.tv_name, it) }
        data?.createTime?.let { holder.setText(R.id.tv_time, it) }
        //1到访2电话3微信4短信5邮件6QQ9其它
        when(data?.type){
            1->{
                holder.setText(R.id.tv_type,"跟进类型：到访")
            }
            2->{
                holder.setText(R.id.tv_type,"跟进类型：电话")

            }
            3->{
                holder.setText(R.id.tv_type,"跟进类型：微信")

            }
            4->{
                holder.setText(R.id.tv_type,"跟进类型：短信")

            }
            5->{
                holder.setText(R.id.tv_type,"跟进类型：邮件")

            }
            6->{
                holder.setText(R.id.tv_type,"跟进类型：QQ")

            }
            else->{
                holder.setText(R.id.tv_type,"跟进类型：其他")

            }


        }
        data?.detail?.let { holder.setText(R.id.tv_content, it) }
        data?.customerName?.let { holder.setText(R.id.tv_linkman, "联系人：$it") }
        data?.companyName?.let { holder.setText(R.id.tv_company, it) }
        data?.checkInAddress?.let { holder.setText(R.id.tv_address, it) }
        data?.followTime?.let { holder.setText(R.id.tv_follow_time, it) }


        holder.getView<View>(R.id.item_bg).setOnClickListener {

            val intent = Intent(mContext, LogDetailsActivity::class.java)
            intent.putExtra(Constants.KEYTYPE, "2")
            intent.putExtra(Constants.KEYGROUPID, data.id)
            mContext.startActivity(intent)
        }


    }
}