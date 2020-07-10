package com.buychemi.crm.ui.activity.mesage

import android.content.Intent
import android.graphics.Color
import android.support.v4.app.Fragment
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.base.BaseFragmentAdapter
import com.buychemi.crm.bean.GroupListEntity
import com.buychemi.crm.mvp.contract.GrouptabContract
import com.buychemi.crm.showToast
import com.buychemi.crm.ui.fragment.MessageGroupTabFragment
import com.buychemi.crm.ui.fragment.MyGroupTabFragment
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_my_group.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 我的分组(群组消息)
 * @Author 20342
 * @Date 2019/9/23 14:15
 */
class MessageMyGroupActivity : BaseActivity(),  View.OnClickListener {

 private var list=ArrayList<GroupListEntity>()
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_btn ->{
                if (list!=null&&list.size>0){
                    var intent= Intent(this,MessageTemplateActivity::class.java)
                    intent.putExtra(Constants.KEYGROUPLIST,list)
                    intent.putExtra(Constants.KEYTYPE,"2")
                    startActivity(intent)
                }else{
                    showToast("请选择分组")
                }

            }
        }
    }

    private val mFragmentList = ArrayList<Fragment>()
    private var mTitles = arrayListOf("我的分组", "下属分组")
    private var mType: Int = 0

    override fun layoutId(): Int {
        return R.layout.activity_my_group
    }

    override fun initData() {
        mType = intent.getIntExtra(Constants.KEYTYPE, 0)
    }

    override fun initView() {
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "选择分组"
        iv_left.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        tv_btn.setOnClickListener(this)
        tv_btn.text="选择分组"
        tv_num_bottom.text="已选择0组"
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        mFragmentList.add(MessageGroupTabFragment.getInstance("1"))
        mFragmentList.add(MessageGroupTabFragment.getInstance("2"))
        mViewPager.adapter = BaseFragmentAdapter(supportFragmentManager, mFragmentList, mTitles)
        tab_layout.setViewPager(mViewPager)
        mViewPager.setCurrentItem(mType, true)
    }

    override fun start() {
    }

   fun setListData(data:GroupListEntity,b:Boolean){
       if (b){
           list.add(data)
       }else{
           list.remove(data)
       }

       tv_num_bottom.text="已选择${list.size}组"

   }

}