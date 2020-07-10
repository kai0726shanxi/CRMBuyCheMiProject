package com.buychemi.crm.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.bean.CompanyListEntity
import com.buychemi.crm.ui.activity.MyClientDetailsActivity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * 关联客户搜索公司
 * @Author 20342
 * @Date 2019/9/23 15:51
 */
class SearchComplayAdapter(context: Context, data:ArrayList<CompanyListEntity>):CommonAdapter<CompanyListEntity>(context,data, R.layout.item_myclient_layout) {

    private var mOnTitletemClick: ((tag: CompanyListEntity, i: Int) -> Unit)? = null
    fun addDataNew(dataList: ArrayList<CompanyListEntity>) {
        this.mData.clear()
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }
    fun addDataAll(dataList: ArrayList<CompanyListEntity>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    fun setOnTitleItemClickListener(onTitleItemClickListener: (tag: CompanyListEntity, i: Int) -> Unit) {
        this.mOnTitletemClick = onTitleItemClickListener
    }



    override fun bindData(holder: ViewHolder, data: CompanyListEntity, position: Int) {
        data?.companyName?.let { holder.setText(R.id.tv_name, it) }

        holder.setOnItemClickListener(View.OnClickListener {

            mOnTitletemClick?.invoke(data, position)


        })
    }
}