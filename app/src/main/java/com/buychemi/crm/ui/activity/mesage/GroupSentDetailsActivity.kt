package com.buychemi.crm.ui.activity.mesage

import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.text.Html
import android.view.View
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.CustomerListEntity
import com.buychemi.crm.bean.SendListEntity
import com.buychemi.crm.bean.SendSuccessEntity
import com.buychemi.crm.bean.TemplateDetails
import com.buychemi.crm.mvp.contract.MassTextingContract
import com.buychemi.crm.mvp.presenter.MassTextingPresenter
import com.buychemi.crm.ui.activity.PotentialCustomerActivity
import com.buychemi.crm.ui.dialog.MessageDialog
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_groupsent_layout.*
import kotlinx.android.synthetic.main.title_bar_layout.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * 群组详情
 * @Author 20342
 * @Date 2019/9/25 9:25
 */
class GroupSentDetailsActivity : BaseActivity(), MassTextingContract.View, View.OnClickListener {


    private var mType: String? = ""//区分发布的还是详情
    private var mTag:String?=""//区分群组还是个人
    private var mid = 0
    private var mlist = ArrayList<CustomerListEntity>()
    private var mids=ArrayList<String>()
    private var strbuf: StringBuffer? = null
    private var pvTime: TimePickerView? = null
    private var map=HashMap<String,String>()
    private var mSendListEntity:SendListEntity?=null
    private val mPresenter:MassTextingPresenter by lazy { MassTextingPresenter() }
    override fun layoutId(): Int {
        return R.layout.activity_groupsent_layout
    }

    private val mDialog: MessageDialog by lazy { MessageDialog(this) }
    override fun initData() {
        mType = intent.getStringExtra(Constants.KEYTYPE)
        mid = intent.getIntExtra(Constants.KEYGROUPID, 0)
        mTag=intent.getStringExtra(Constants.KEYTAG)
        if (mType=="1"){
            //发送
            if (mTag=="1"){
                mlist = intent.getSerializableExtra(Constants.KEYNAME) as ArrayList<CustomerListEntity>

            }else if(mTag=="2"){
                mids=intent.getStringArrayListExtra(Constants.KEYUSERIDS)
            }
        }else if (mType=="2"){
           //详情
             mSendListEntity= intent.getSerializableExtra(Constants.KEYDTATA) as SendListEntity?

        }

    }

    override fun initView() {
        strbuf = StringBuffer()
        mPresenter.attachView(this)
        tv_cancel.setOnClickListener(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "群发确认"
        //   tv_right.visibility = View.VISIBLE
        if (mType=="1") {
            //  cl_edit.visibility = View.VISIBLE
            tv_look.text = "确定"
            tv_cancel.visibility = View.VISIBLE
            tv_two.text = "发送时间"
            tv_two_content.text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date())
            tv_two_content.setOnClickListener(this)
            if (mTag=="1"){

                if (mlist != null && mlist.size > 0) {
                    tv_one_content.text=mlist.size.toString()+"人"
                    for ((idex, data) in mlist.withIndex()){
                        if (idex==mlist.size-1){
                            strbuf?.append(data.id.toString())
                        }else{
                            strbuf?.append(data.id.toString()+",")

                        }
                    }
                }
            }else if (mTag=="2"){

                if (mids != null && mids.size > 0) {
                    tv_one_content.text=mids.size.toString()+"人"
                    for ((idex, data) in mids.withIndex()){
                        if (idex==mids.size-1){
                            strbuf?.append(data)
                        }else{
                            strbuf?.append("""$data,""")

                        }
                    }
                }



            }
        } else if (mType=="2"){
            //  cl_edit.visibility = View.GONE
            tv_cancel.visibility = View.GONE
            tv_two.text = "提交人"
            tv_look.text = "查看发送记录"
            tv_two_content.text=mSendListEntity?.sendName
            tv_one_content.text=mSendListEntity?.arrivalsNum.toString()+"/"+mSendListEntity?.sendNum.toString()+"人"

        }





        pvTime = TimePickerBuilder(this, OnTimeSelectListener { date, _ ->

            tv_two_content.text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(date)

        }).setDecorView(
                window.decorView.findViewById(android.R.id.content))
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setTitleSize(20)//标题文字大小
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setSubmitColor(ContextCompat.getColor(this, R.color.displaynomal))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(this, R.color.medynamic))//取消按钮文字颜色
                .setTextColorCenter(ContextCompat.getColor(this, R.color.displaynomal))
                .setType(booleanArrayOf(true, true, true, true, true, false))
                .setLabel("年", "月", "日", "时", "分", "").setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setTitleBgColor(Color.WHITE)
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build()
        tv_right.text = "创建"
        tv_right.setOnClickListener(this)
        iv_left.setOnClickListener(this)
        tv_look.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.home_bottom_down))
        mDialog.setOnClickListener(View.OnClickListener { v ->
            if (v.id == R.id.dialog_cancel) {
                mDialog.dismiss()
            }
            if (v.id == R.id.dialog_confirm) {
                //确认发送

                map.clear()
                map["ids"]=strbuf.toString()
                map["templateId"]=mid.toString()
                mPresenter.getsendmessage(map)



            }
        })
    }

    override fun start() {
        map.clear()
        map["id"]=mid.toString()
        mPresenter.gettempDetails(map)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_right -> {
            }

            R.id.tv_look -> {
                if (mType == "1") {

                    if (mTag=="1"){
                        if (mlist!=null){
                            mDialog.setcount("本次群发手机用户：${mlist.size}人")

                        }
                    }else{
                        if (mids!= null){
                            mDialog.setcount("本次群发手机用户：${mids.size}人")

                        }
                    }

                    mDialog.show()
                } else {
                    var intent = Intent(this, PotentialCustomerActivity::class.java)
                    if (mSendListEntity!=null){
                        intent.putExtra(Constants.KEYGROUPID, mSendListEntity?.id.toString())

                    }
                    intent.putExtra(Constants.KEYTYPE, "1")
                    startActivity(intent)
                }


            }
            R.id.tv_cancel->{
                finish()
            }
            R.id.tv_two_content -> {

            }
        }
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onsendMessage(data: SendSuccessEntity?) {

        var intent = Intent(this, MessageSubmintActivity::class.java)
        startActivity(intent)
        mDialog.dismiss()
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
    override fun ontempDetails(data: TemplateDetails?) {

        et_content.text=Html.fromHtml(data?.content)
       // tv_one_content.text=

    }

}