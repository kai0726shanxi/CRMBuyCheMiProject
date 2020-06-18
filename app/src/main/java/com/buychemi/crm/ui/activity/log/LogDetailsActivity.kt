package com.buychemi.crm.ui.activity.log

import android.graphics.Color
import android.support.v7.widget.GridLayoutManager

import android.view.View

import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.*
import com.buychemi.crm.mvp.contract.LogDetailsContract
import com.buychemi.crm.mvp.presenter.LogDetailsPresenter
import com.buychemi.crm.ui.adapter.FollowUpRecordsItemAdapter
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_log_details.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 *日志和跟进详情
 * @Author 20342
 * @Date 2019/10/16 15:00
 */
class LogDetailsActivity : BaseActivity(), LogDetailsContract.View, View.OnClickListener {
    //type 1日志，2进程

    private var mid: Int = 0
    private var mType: String = ""
    private var map = HashMap<String, String>()
    private var mAdapter: FollowUpRecordsItemAdapter? = null
    private var mlist = ArrayList<String>()
    private val mPresenter: LogDetailsPresenter by lazy { LogDetailsPresenter() }
    override fun layoutId(): Int {
        return R.layout.activity_log_details
    }

    override fun initData() {
        mid = intent.getIntExtra(Constants.KEYGROUPID, 0)
        mType = intent.getStringExtra(Constants.KEYTYPE)
    }

    override fun initView() {
        mPresenter.attachView(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        iv_left.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        if (mType == "1") {
            tv_title.text = "备忘详情"

            tv_start_time.visibility = View.GONE
            tv_end_time.visibility = View.GONE
        } else {
            tv_title.text = "跟进详情"

            tv_start_time.visibility = View.VISIBLE
            tv_end_time.visibility = View.VISIBLE
        }

        mAdapter = FollowUpRecordsItemAdapter(this, ArrayList())
        recycle_img.isNestedScrollingEnabled = false
        recycle_img.adapter = mAdapter
        recycle_img.layoutManager = GridLayoutManager(this, 3)

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
        }

    }

    override fun start() {
        map.clear()
        map["id"] = mid.toString()
        if (mType.equals("1")) {
            mPresenter.getLogDetails(map)
        } else {
            mPresenter.getFollowDetails(map)
        }
        // mPresenter.getRePortDetails(map)

    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }


    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onFollowDetails(data: FollowDetailsEntity?) {
        //跟进详情
        tv_time.text = "创建于${data?.createTime}"
        tv_type.text = " 签到位置  ${data?.checkInAddress}"
        tv_user.text = "关联客户"
        tv_start_time.text = "跟进时间 " + data?.followTime
        tv_end_time.text = "下次跟进 " + data?.nextFollowTime
        et_content.text = data?.detail
        if (data?.customerName!=null&&data?.customerName!=""){

            tv_name.text = data?.customerName
            tv_phone.text = data?.companyName
        }else{
            tv_name.text="无"
        }


        if (data?.imgs!=null){
            mlist.clear()
            if (data?.imgs!!.contains(",")){
                var  strarry=data?.imgs?.split(",")
                mlist= strarry as ArrayList<String>
            }else{
                mlist.add(data?.imgs!!)
            }
             mAdapter?.addDataNew(mlist)

        }


    }

    override fun onLogDetails(data: LogDetailsEntity?) {
        //日志详情

        tv_time.text = "创建于${data?.createTime}"
        tv_type.text = " 签到位置  ${data?.checkInLocation}"

        et_content.text = data?.description
        tv_user.text = "日期 ${data?.remarkDate}"
        if (data?.image!=null){
            mlist.clear()
            if (data?.image!!.contains(",")){
                var  strarry=data?.image?.split(",")
                mlist= strarry as ArrayList<String>
            }else{
                mlist.add(data?.image!!)
            }
            mAdapter?.addDataNew(mlist)

        }
    }

}