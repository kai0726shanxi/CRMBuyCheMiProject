package com.buychemi.crm.ui.activity.mesage

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.TestDataEntivity
import com.buychemi.crm.ui.adapter.MessageMyClientAdapter
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_my_message_client.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 我的客户(群发消息)
 * @Author 20342
 * @Date 2019/9/23 15:41
 */
class MessageMyClientActivity : BaseActivity(), View.OnClickListener {
    private var total: Int = 0
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_btn -> {
                var intent = Intent(this@MessageMyClientActivity, MessageTemplateActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private var mMyClientAdapter: MessageMyClientAdapter? = null
    private val mlist = ArrayList<TestDataEntivity>()
    override fun layoutId(): Int {
        return R.layout.activity_my_message_client
    }

    override fun initData() {
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "我的客户"
        tv_btn.setOnClickListener(this)
        iv_left.setOnClickListener(this)
        mlist.add(TestDataEntivity(0,"张三1",false))
        mlist.add(TestDataEntivity(1,"张三2",false))
        mlist.add(TestDataEntivity(2,"张三3",false))
        mlist.add(TestDataEntivity(3,"张三4",false))
        mlist.add(TestDataEntivity(4,"张三5",false))
        mlist.add(TestDataEntivity(5,"张三6",false))
        mlist.add(TestDataEntivity(6,"张三7",false))
        mlist.add(TestDataEntivity(7,"张三8",false))
        mlist.add(TestDataEntivity(8,"张三9",false))
        mlist.add(TestDataEntivity(9,"张三6",false))
        mlist.add(TestDataEntivity(10,"张三7",false))
        mlist.add(TestDataEntivity(11,"张三8",false))
        mlist.add(TestDataEntivity(12,"张三9",false))
        mlist.add(TestDataEntivity(13,"张三5",false))


        mMyClientAdapter = MessageMyClientAdapter(this, mlist)
        mMyClientAdapter?.setOnItemListener(object :MessageMyClientAdapter.BtnDataLinsenter{
            override fun btndata(str: TestDataEntivity, p: Int, b: Boolean) {
                if (str.isChoosed){
                    total+=1
                }else{
                    total-=1

                }
                tv_num_bottom.text="$total 人选中"

            }
        })

        recycler_view.adapter = mMyClientAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)

    }

    override fun start() {
    }
}