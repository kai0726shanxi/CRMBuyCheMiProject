package com.buychemi.crm.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.bean.ReportListEntity
import com.buychemi.crm.ui.activity.report.ReportDetailsActivity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * 工作报告
 * @Author 20342
 * @Date 2019/9/26 15:26
 */
class WorkStatementAdapter(context: Context,daya:ArrayList<ReportListEntity>):CommonAdapter<ReportListEntity>(context,daya, R.layout.item_workstatement_layout) {




    fun setDataAll(categoryList: ArrayList<ReportListEntity>) {
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }
    fun setDataNew(categoryList: ArrayList<ReportListEntity>) {
        this.mData.clear()
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }
    override fun bindData(holder: ViewHolder, data: ReportListEntity, position: Int) {
    holder.setOnItemClickListener(View.OnClickListener {
        var intent=Intent(mContext as Activity, ReportDetailsActivity::class.java)
            intent.putExtra(Constants.KEYTYPE,data?.id)
        mContext.startActivity(intent)

    })
        data?.title?.let { holder.setText(R.id.tv_time, it)
        }
        data?.userName?.let { holder.setText(R.id.tv_name, it) }
        data?.createTime?.let { holder.setText(R.id.tv_time_bottom, it) }
        //状态 0未批阅 1已批阅
        if (data?.state==1){
            holder.setText(R.id.tv_num,"已批阅")
        }else{
            holder.setText(R.id.tv_num,"未批阅")

        }


    }
}