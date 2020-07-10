package com.buychemi.crm.ui.activity.report

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.UpImagesEntity
import com.buychemi.crm.hintKbTwo
import com.buychemi.crm.mvp.contract.NewMemoContract
import com.buychemi.crm.mvp.presenter.NewMemoPresenter
import com.buychemi.crm.showToast
import com.buychemi.crm.ui.adapter.AddImageAdapter
import com.buychemi.crm.ui.dialog.ChoseTypeDialog
import com.buychemi.crm.utils.StatusBarUtil
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import kotlinx.android.synthetic.main.activity_new_memo.*
import kotlinx.android.synthetic.main.title_bar_layout.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * 新建报告
 * @Author 20342
 * @Date 2019/9/26 13:45
 */
class NewMemoActivity : BaseActivity(), NewMemoContract.View, View.OnClickListener {
    private var limit = 300
    //  用来记录输入字符的时候光标的位置
    private var cursor = 0
    // 用来标注输入某一内容之前的编辑框中的内容的长度
    private var before_length: Int = 0
    private var mtemp = ArrayList<UpImagesEntity>()
    private val mPresenter: NewMemoPresenter by lazy { NewMemoPresenter() }
    private var mlist = ArrayList<String>()
    private var map = HashMap<String, Any>()
    private var mAdapter: AddImageAdapter? = null
    private val mdialog: ChoseTypeDialog by lazy { ChoseTypeDialog(this) }
    private var pvTime: TimePickerView? = null
    private var strbuf:StringBuffer?=null

    override fun layoutId(): Int {
        return R.layout.activity_new_memo
    }

    override fun initData() {
    }

    override fun initView() {
        strbuf= StringBuffer()
        mPresenter.attachView(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "新建报告"
        tv_right.visibility = View.VISIBLE
        tv_right.text = "创建"
        tv_right.setOnClickListener(this)
        iv_left.setOnClickListener(this)
       // tv_time.setOnClickListener(this)
        tv_type.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)

        et_content.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val after_length = s!!.length
                if (after_length > limit) {
                    val d_value = after_length - limit
                    val d_num = after_length - before_length
                    val st = cursor + (d_num - d_value)
                    val en = cursor + d_num
                    val s_new = s.delete(st, en)
                    et_content.setText(s_new.toString())
                    et_content.setSelection(st)
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                before_length = s!!.length

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cursor = start
                tv_show_num.text = "已输入 ${s!!.length}/" + limit

            }


        })
        mdialog.setBtnDataLinsenter(object : ChoseTypeDialog.BtnDataLinsenter {
            override fun btndata(str: String) {
                tv_type.text = str
            }
        })

        tv_right.setTextColor(ContextCompat.getColor(this, R.color.home_bottom_down))
        mAdapter = AddImageAdapter(this, ArrayList())
        mAdapter?.setOnTitleItemClickListener { tag, i ->
            if (tag == "-1") {
                if (mlist.size < 4) {
                    openpicture()

                } else {
                    showToast("最多添加3张")
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

        tv_time.text=SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date())
        pvTime = TimePickerBuilder(this, OnTimeSelectListener { date, _ ->
            tv_time.text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(date)
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

    override fun start() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_right -> {

                if (mlist != null && mlist.size > 1) {
                    val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
                    for (item in mlist) {
                        if (item != "-1") {
                            val file = File(item)
                            builder.addFormDataPart("files", file.name, RequestBody.create(MediaType.parse("image/jpeg"), file))
                            builder.addFormDataPart("fileName", file.name)
                            builder.addFormDataPart("type", "2")
                            builder.addFormDataPart("kind", "2")
                        }

                    }
                    val requestBody = builder.build()
                    mPresenter.getUpImages(requestBody)
                } else {
                    pushdata(mtemp)

                }

            }
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_time -> {
                hintKbTwo(this)
                pvTime?.show()
            }
            R.id.tv_type -> {
                hintKbTwo(this)
                mdialog.show()
            }

        }
    }

    private fun pushdata(data: ArrayList<UpImagesEntity>) {
        var mtype = 0
        when (tv_type.text.toString()) {
            "日报" -> mtype = 0
            "周报" -> mtype = 1
            "月报" -> mtype = 2

        }
        map.clear()

        if (et_content.text.toString()==""){
            showToast("请输入内容")
            return
        }
        map["reportDate"] = tv_time.text.toString()
        map["workSummary"] = et_content.text.toString()
        map["type"] = mtype
        if (data!=null&&data.size>0){
            for (  (item,da) in data.withIndex()) {
                // mstrlist.add(item.url)
                if (item==data?.size-1){
                    strbuf?.append(da.id.toString())

                }else{
                    strbuf?.append(da.id.toString()+",")

                }
            }
            map["imgIds"]=strbuf.toString()
        }




        mPresenter.getNewMemodata(map)
    }

    private fun openpicture() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or
                .maxSelectNum(4 - mlist.size)
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .forResult(PictureConfig.CHOOSE_REQUEST)
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

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onNewMemodata(data: String?) {
        showToast("创建成功")
        finish()
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onUpImages(data: ArrayList<UpImagesEntity>?) {
        if (data!=null){
            pushdata(data)
        }


    }


}