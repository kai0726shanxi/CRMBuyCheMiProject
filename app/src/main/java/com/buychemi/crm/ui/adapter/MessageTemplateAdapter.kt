package com.buychemi.crm.ui.adapter

import android.content.Context
import android.content.Intent
import android.media.Image
import android.text.Html
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.bean.TemplateListEntity
import com.buychemi.crm.showToast
import com.buychemi.crm.ui.activity.MyClientDetailsActivity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 * 群发模板(群发消息)
 * @Author 20342
 * @Date 2019/9/23 15:51
 */
class MessageTemplateAdapter(context: Context, data: ArrayList<TemplateListEntity>) : CommonAdapter<TemplateListEntity>(context, data, R.layout.item_template_message_layout) {

    private var mOnTitletemClick: ((tag: TemplateListEntity, i: Int) -> Unit)? = null
     var mcheckPostion = -1

    fun setDataAll(categoryList: ArrayList<TemplateListEntity>) {
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    fun setDataNew(categoryList: ArrayList<TemplateListEntity>) {
        this.mData.clear()
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: (tag: TemplateListEntity, i: Int) -> Unit) {
        this.mOnTitletemClick = onItemClickListener
    }


    override fun bindData(holder: ViewHolder, data: TemplateListEntity, position: Int) {
         var tvcontent=holder.getView<TextView>(R.id.tv_content)

        // 先设置一次CheckBox的选中监听器，传入参数null,防止多次监听错乱

        var imageView = holder.getView<ImageView>(R.id.iv_check)
        if (mcheckPostion == position) {
            imageView.setBackgroundResource(R.mipmap.check_down)
        } else {
            imageView.setBackgroundResource(R.mipmap.check_nomal)

        }
        imageView.setOnClickListener {
            mOnTitletemClick?.invoke(data, position)

        }
        data?.name?.let { holder.setText(R.id.tv_name, it) }
        tvcontent.text = Html.fromHtml(data?.content)
    }
}