package com.buychemi.crm.ui.adapter

import android.content.Context
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.glide.GlideApp
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 *
 * 图片展示的布局
 * @Author 20342
 * @Date 2019/10/17 9:41
 */
class AddImageAdapter(context: Context, mlist: ArrayList<String>) : CommonAdapter<String>(context, mlist, R.layout.item_addimg_layout) {
    private var mOnTitletemClick: ((tag: String, i: Int) -> Unit)? = null

    fun setDataAll(categoryList: ArrayList<String>) {
        cleardata()
        this.mData.addAll(categoryList)
        notifyDataSetChanged()
    }

    fun setOnTitleItemClickListener(onTitleItemClickListener: (tag: String, i: Int) -> Unit) {
        this.mOnTitletemClick = onTitleItemClickListener
    }

    override fun bindData(holder: ViewHolder, data: String, position: Int) {

         if (data!="-1"){
             holder.setViewVisibility(R.id.iv_delete,View.VISIBLE)
             GlideApp.with(mContext)
                     .load(data)
                     .placeholder(R.mipmap.moren_icon)
                     .into(holder.getView(R.id.iv_img))

         }else{
             GlideApp.with(mContext)
                     .load(R.mipmap.add_ico_ic)
                     .into(holder.getView(R.id.iv_img))
             holder.setViewVisibility(R.id.iv_delete,View.GONE)


         }
        holder.setOnItemClickListener(View.OnClickListener {
            mOnTitletemClick?.invoke(data, position)
        })


    }
}