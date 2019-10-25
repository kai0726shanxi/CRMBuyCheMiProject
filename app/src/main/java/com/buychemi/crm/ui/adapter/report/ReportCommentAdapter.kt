package com.buychemi.crm.ui.adapter.report

import android.content.Context
import com.buychemi.crm.R
import com.buychemi.crm.bean.ReportCommentEntity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter
import java.sql.RowId

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
        data?.auditName?.let { holder.setText(R.id.tv_name, it) }
        data?.createTime?.let { holder.setText(R.id.tv_phone, it) }
        data?.auditRemark?.let { holder.setText(R.id.tv_content,it) }
    }
}