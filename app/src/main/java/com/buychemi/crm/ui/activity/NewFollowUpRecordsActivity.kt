package com.buychemi.crm.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.MessageEvent
import com.buychemi.crm.bean.UpImagesEntity
import com.buychemi.crm.hintKbTwo
import com.buychemi.crm.mvp.contract.NewFollowContract
import com.buychemi.crm.mvp.presenter.NewFollowPresenter
import com.buychemi.crm.showToast
import com.buychemi.crm.ui.adapter.AddImageAdapter
import com.buychemi.crm.ui.dialog.ChoseFollowTypeDialog
import com.buychemi.crm.utils.StatusBarUtil
import com.google.gson.Gson
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.tools.StringUtils
import kotlinx.android.synthetic.main.activity_new_followup_records.*
import kotlinx.android.synthetic.main.title_bar_layout.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.File
import java.text.SimpleDateFormat

/**
 * @Author 20342
 * @Date 2019/9/25 11:41
 */
class NewFollowUpRecordsActivity : BaseActivity(), NewFollowContract.View, View.OnClickListener {


    private var limit = 300
    //  用来记录输入字符的时候光标的位置
    private var cursor = 0
    // 用来标注输入某一内容之前的编辑框中的内容的长度
    private var before_length: Int = 0
    private var mtemp = ArrayList<UpImagesEntity>()
    private var mlist = ArrayList<String>()
    private var map = HashMap<String, Any?>()
    private var mAdapter: AddImageAdapter? = null
    private val mdialog: ChoseFollowTypeDialog by lazy { ChoseFollowTypeDialog(this) }
    private var pvTime: TimePickerView? = null
    private val mPresenter: NewFollowPresenter by lazy { NewFollowPresenter() }
    private var istime = 0
    private var isdata: String? = ""
    private var linkmanid: String = ""
    private var complayid: String = ""
    private var mtype = ""
    private var strbuf:StringBuffer?=null
    private var mstrlist = ArrayList<String?>()

    override fun layoutId(): Int {
        return R.layout.activity_new_followup_records
    }

    override fun initData() {

    }

    override fun initView() {
        mPresenter.attachView(this)
        strbuf= StringBuffer()
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "新建跟进"
        tv_right.text = "创建"
        tv_right.visibility = View.VISIBLE
        tv_right.setOnClickListener(this)
        iv_left.setOnClickListener(this)
        tv_time.setOnClickListener(this)
        tv_next_time.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        et_title.setOnClickListener(this)
        tv_customer.setOnClickListener(this)
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.home_bottom_down))
        edit_content.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val after_length = s!!.length
                if (after_length > limit) {
                    val d_value = after_length - limit
                    val d_num = after_length - before_length
                    val st = cursor + (d_num - d_value)
                    val en = cursor + d_num
                    val s_new = s.delete(st, en)
                    edit_content.setText(s_new.toString())
                    edit_content.setSelection(st)
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                before_length = s!!.length

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cursor = start
                tvshownum.text = "已输入 ${s!!.length}/" + limit

            }


        })
        mdialog.setBtnDataLinsenter(object : ChoseFollowTypeDialog.BtnDataLinsenter {

            override fun btndata(str: String) {

                mtype = str
                when (str) {
                    "1" -> et_title.text = "到访"
                    "2" -> et_title.text = "电话"
                    "3" -> et_title.text = "微信"
                    "4" -> et_title.text = "短信"
                    "5" -> et_title.text = "邮箱"
                    "6" -> et_title.text = "QQ"
                    "9" -> et_title.text = "其他"

                }
            }

        })

        mAdapter = AddImageAdapter(this, ArrayList())
        mAdapter?.setOnTitleItemClickListener { tag, i ->
            if (tag == "-1") {
                if (mlist.size < 10) {
                    openpicture()

                } else {
                    showToast("最多添加9张")
                }

            } else {
                mlist.removeAt(i)
                mAdapter?.setDataAll(mlist)

            }
        }
        recycle_img.adapter = mAdapter
        recycle_img.layoutManager = GridLayoutManager(this, 5)
        mlist.add("-1")
        mAdapter?.setDataAll(mlist)


        pvTime = TimePickerBuilder(this, OnTimeSelectListener { date, _ ->

            if (istime == 0) {
                tv_time.text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(date)

            } else {
                tv_next_time.text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(date)

            }
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

    }

    private fun openpicture() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or
                .maxSelectNum(10 - mlist.size)
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .forResult(PictureConfig.CHOOSE_REQUEST)
    }

    override fun start() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_right -> {

                if (mlist != null && mlist.size > 1) {
                    val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
                    for (item in mlist) {
                        if (item != "-1") {
                            val file = File(item)
                            builder.addFormDataPart("files", file.name, RequestBody.create(MediaType.parse("image/jpeg"), file))
                            builder.addFormDataPart("fileName", file.name)
                            builder.addFormDataPart("type", "5")
                            builder.addFormDataPart("kind", "2")
                        }

                    }
                    val requestBody = builder.build()
                    mPresenter.getUpImages(requestBody)
                } else {
                    pushdata(mtemp)

                }
            }
            R.id.et_title -> {
                hintKbTwo(this)
                mdialog.show()
            }
            R.id.tv_time -> {
                istime = 0
                hintKbTwo(this)
                pvTime?.show()
            }
            R.id.tv_next_time -> {
                istime = 1
                hintKbTwo(this)
                pvTime?.show()

            }

            R.id.tv_customer -> {
                var intent = Intent(this, MyGroupActivity::class.java)
                intent.putExtra(Constants.KEYTYPE, 0)
                intent.putExtra(Constants.KEYTAG, 1)
                startActivity(intent)
            }


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PictureConfig.CHOOSE_REQUEST -> {
                    // 图片、视频、音频选择结果回调
                    val selectList = PictureSelector.obtainMultipleResult(data)
                    /**
                     *   val file = File(imgagepath)
                    val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
                    builder.addFormDataPart("files", file.name, RequestBody.create(MediaType.parse("image/jpeg"), file))
                    val requestBody = builder.build()
                    mPresenter.getImageUrl(requestBody)
                     */

                    if (selectList != null) {
                        for (mitem in selectList) {
                            if (!mitem.compressPath.isNullOrEmpty()) {

                                mlist.add(mitem.compressPath)
                            } else {
                                showToast("添加失败，稍后重试")
                            }
                        }
                        mAdapter?.setDataAll(mlist)


                    }

                }

            }
        }
    }

    private fun pushdata(data: ArrayList<UpImagesEntity>?) {

        map.clear()

        if (mtype == "") {
            showToast("选择跟进类型")
            return
        }
    /*    if (linkmanid == "") {
            showToast("请关联客户")
            return
        }*/
        if (tv_time.text.toString() == "") {
            showToast("请选择跟进时间")
            return
        }
        if (edit_content.text.toString() == "") {
            showToast("请输入描述")
            return
        }

        map["customerId"] = linkmanid
        map["companyId"] = complayid
        map["followTime"] = tv_time.text.toString()
        map["detail"] = edit_content.text.toString()
        map["nextFollowTime"] = tv_next_time.text.toString()
        map["checkInAddress"] = et_address.text.toString()
        if (data != null && data.size > 0) {
            mstrlist.clear()
            for (  (item,da) in data.withIndex()) {
               // mstrlist.add(item.url)
                 if (item==data.size-1){
                     strbuf?.append(da.url)

                 }else{
                     strbuf?.append(da.url+",")

                 }
            }


            map["imgs"] = strbuf?.toString()
        }
        map["type"] = mtype


        mPresenter.getNewFollowdata(map)
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onNewFollow(data: String?) {

        showToast("创建成功")
        finish()
    }

    override fun onStart() {
        super.onStart()
        isdata = intent.getStringExtra(Constants.KEYNAME)
        if (isdata != null && isdata!!.contains("&&")) {
            var mdata = isdata?.split("&&")
            if (mdata != null) {
                tv_customer.text = mdata?.get(0).toString()
                linkmanid = mdata?.get(1).toString()
                complayid = mdata?.get(2).toString()

            }
        }
    }

    override fun onUpImages(data: ArrayList<UpImagesEntity>?) {
        pushdata(data)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun Event(messageEvent: MessageEvent) {
        tv_customer.text = messageEvent.name
        linkmanid = messageEvent.linkmanId.toString()
        complayid = messageEvent.ComplayId.toString()
    }


}