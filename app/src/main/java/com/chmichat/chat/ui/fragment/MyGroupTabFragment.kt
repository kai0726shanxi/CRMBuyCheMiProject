package com.chmichat.chat.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.ui.adapter.MyGroupTabAdapter
import kotlinx.android.synthetic.main.fragment_my_group_tab.*

/**
 * @Author 20342
 * @Date 2019/9/23 14:20
 */
class MyGroupTabFragment : BaseFragment() {
    private var mType: String? = "1"//1是我的，2是下属
    private var mlist= arrayListOf("","","","","","","","","","","","","","","","","","","","","","","","","","","")
    private var mMyGroupTabAdapter: MyGroupTabAdapter? = null


    companion object {
        fun getInstance(str: String?): MyGroupTabFragment {
            val fragment = MyGroupTabFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mType = str
            return fragment
        }
    }


    override fun getLayoutId(): Int {

        return R.layout.fragment_my_group_tab
    }

    override fun initView() {
        mMyGroupTabAdapter=activity?.let { MyGroupTabAdapter(it,mlist) }
        recycle_tab.adapter=mMyGroupTabAdapter
        recycle_tab.layoutManager=LinearLayoutManager(activity)

    }

    override fun lazyLoad() {
    }
}