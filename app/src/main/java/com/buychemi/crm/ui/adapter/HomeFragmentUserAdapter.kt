package com.buychemi.crm.ui.adapter

import android.content.Context
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.bean.BriefingEntity
import com.buychemi.crm.view.recyclerview.ViewHolder
import com.buychemi.crm.view.recyclerview.adapter.CommonAdapter

/**
 *
 *中间部分
 * @Author 20342
 * @Date 2019/9/21 13:31
 */
class HomeFragmentUserAdapter(context: Context, list: ArrayList<String>) : CommonAdapter<String>(context, list, R.layout.item_home_user_layout) {
   var mBriefingEntity: BriefingEntity?=null
    private var mOnTitletemClick: ((tag: String,i:Int) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: (tag: String,i:Int) -> Unit) {
        this.mOnTitletemClick = onItemClickListener
    }
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        when (data) {
            "1" -> {
            holder.setImageResource(R.id.iv_head,R.mipmap.home_xinzeng)
            holder.setText(R.id.tv_name,"我的客户")
            }

            "2" -> {
                holder.setImageResource(R.id.iv_head,R.mipmap.home_qianzai)
                holder.setText(R.id.tv_name,"公海客户")
            }

            "3" -> {
                holder.setImageResource(R.id.iv_head,R.mipmap.home_qianzai)
                holder.setText(R.id.tv_name,"我的下属")
            }

            "4" -> {
                holder.setImageResource(R.id.iv_head,R.mipmap.home_duanxin)
                holder.setText(R.id.tv_name,"群发短信")
            }



        }
        if (mBriefingEntity!=null){
            when(data){
                "1"->{
                    holder.setText(R.id.tv_num, mBriefingEntity?.todayCustomerNumber.toString()+"人")
                }
                "2"->{
                    holder.setText(R.id.tv_num, mBriefingEntity?.allocatedCustomerNumber.toString()+"人")

                }
                "3"->{

                    holder.setText(R.id.tv_num, mBriefingEntity?.subordinateNumber.toString()+"人")

                }

            }
        }
        holder.setOnItemClickListener(View.OnClickListener { mOnTitletemClick?.invoke(data,position) }
        )
    }
}