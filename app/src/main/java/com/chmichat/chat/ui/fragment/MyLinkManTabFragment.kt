package com.chmichat.chat.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.ui.adapter.MyLinkManTabAdapter
import kotlinx.android.synthetic.main.fragment_my_group_tab.*

/**
 * 我的联系人
 * @Author 20342
 * @Date 2019/9/23 14:20
 */
class MyLinkManTabFragment : BaseFragment() {
    private var mType: String? = "1"//1是我的，2是其他
    private var mlist= arrayListOf("","","","","","","","","","","","","","","","","","","","","","","","","","","")
    private var mMyLinkManTabAdapter: MyLinkManTabAdapter? = null


    companion object {
        fun getInstance(str: String?): MyLinkManTabFragment {
            val fragment = MyLinkManTabFragment()
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
        mMyLinkManTabAdapter=activity?.let { MyLinkManTabAdapter(it,mlist) }
        recycle_tab.adapter=mMyLinkManTabAdapter
        recycle_tab.layoutManager=LinearLayoutManager(activity)

    }

    override fun lazyLoad() {
    }
}