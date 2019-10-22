package com.buychemi.crm.ui.adapter.report

import android.content.Context
import com.buychemi.crm.R
import com.buychemi.crm.bean.ReportCommentEntity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**报告详情的评论
 * @Author 20342
 * @Date 2019/10/16 15:38
 */
class ReportCommentAdapter(context: Context, data: ArrayList<ReportCommentEntity>) : CommonAdapter<ReportCommentEntity>(context, data, R.layout.item_report_comment) {

    fun setDataAll(categoryList: ArrayList<ReportCommentEntity>) {
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }
    fun setDataNew(categoryList: ArrayList<ReportCommentEntity>) {
        this.mData.clear()
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }
    override fun bindData(holder: ViewHolder, data: ReportCommentEntity, position: Int) {

    }
}