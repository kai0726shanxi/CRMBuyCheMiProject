package com.buychemi.crm.ui.activity

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.ui.adapter.MyClientAdapter
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_my_client.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 我的客户
 * @Author 20342
 * @Date 2019/9/23 15:41
 */
class MyClientActivity : BaseActivity(), View.OnClickListener {
    private var mType: String? = ""
    private var mtag:String?=""
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
        }
    }

    private var mMyClientAdapter: MyClientAdapter? = null
    private val mlist = arrayListOf("1", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
    override fun layoutId(): Int {
        return R.layout.activity_my_client
    }

    override fun initData() {
        mType = intent.getStringExtra(Constants.KEYTYPE)
        mtag=intent.getStringExtra(Constants.KEYNAME)
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "我的客户"

        iv_left.setOnClickListener(this)
        mMyClientAdapter = MyClientAdapter(this, mlist)
        mMyClientAdapter?.setOnTitleItemClickListener { tag, i ->
            if (mType!="group"){
                var intent = Intent(this, MyClientDetailsActivity::class.java)
                intent.putExtra(Constants.KEYTYPE, "0")

                startActivity(intent)
            }else{
                var intent = Intent(this, MyLinkManActivity::class.java)
                if (mtag=="1"){
                    intent.putExtra(Constants.KEYNAME, "1")

                }
                startActivity(intent)
            }

        }
        recycler_view.adapter = mMyClientAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)

    }

    override fun start() {
    }
}