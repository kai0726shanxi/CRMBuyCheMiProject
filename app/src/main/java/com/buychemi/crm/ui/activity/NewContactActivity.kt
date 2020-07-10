package com.buychemi.crm.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.CompanyListEntity
import com.buychemi.crm.bean.GroupListEntity
import com.buychemi.crm.bean.LinkTestEntity
import com.buychemi.crm.mvp.contract.NewCustomerContract
import com.buychemi.crm.mvp.presenter.NewCustomerPresenter
import com.buychemi.crm.showToast
import com.buychemi.crm.ui.dialog.ChoseSexDialog
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_newcontact.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 新建联系人
 * @Author 20342
 * @Date 2019/9/26 14:35
 */
class NewContactActivity : BaseActivity(), NewCustomerContract.View, View.OnClickListener {
    override fun onMyGroupData(data: ArrayList<GroupListEntity>?, total: Int) {

    }

    private var map = HashMap<String, String>()
    private var mGroupListEntity:GroupListEntity?=null
    private val mdialog: ChoseSexDialog by lazy { ChoseSexDialog(this) }
    private val mPresenter:NewCustomerPresenter by lazy { NewCustomerPresenter() }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_right -> {
                setPushData()

            }

            R.id.tv_customer -> {
                var intent = Intent(this, SearchComplayActivity::class.java)
                startActivityForResult(intent, Constants.CHOSEFORUM)
            }
            R.id.tv_group->{
                //ChoseGroupActivity
                var intent = Intent(this, ChoseGroupActivity::class.java)
                startActivityForResult(intent, Constants.CHOSEGROUPID)
            }
            R.id.tv_sex -> mdialog.show()

            R.id.tv_country->{
                val intent = Intent()
                intent.setClass(this, CountryActivity::class.java)
                startActivityForResult(intent, CountryActivity.REQUESTCODE_FROM_MAIN_TO_PEPELU)
            }
        }
    }

    private fun setPushData() {

        if (et_name.text.toString() == "") {
            showToast("请输入姓名")
            return
        }
        if (et_phone.text.toString() == "") {
            showToast("请输入手机号")
            return
        }


        if (tv_customer.text.toString() == "") {
            showToast("请关联公司")
            return
        }
        if (mGroupListEntity==null) {
            showToast("请关联分组")
            return
        }


        map.clear()
        map["name"] = et_name.text.toString()
        if (tv_sex.text == "男") {
            map["sex"] = "1"

        } else if (tv_sex.text == "女") {
            map["sex"] = "2"

        }
        map["position"] = et_position.text.toString()
        map["tel"] = et_phone.text.toString()
        map["companyName"] = tv_customer.text.toString()
        map["email"] = et_Email.text.toString()
        map["groupId"] =mGroupListEntity?.id.toString()
        map["country"]=tv_country.text.toString().trim()
        mPresenter.getNewAddCustomer(map)

    }

    override fun layoutId(): Int {
        return R.layout.activity_newcontact
    }

    override fun initData() {
    }

    override fun initView() {
        mPresenter.attachView(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "新建客户"
        tv_right.visibility = View.VISIBLE
        tv_right.text = "创建"
        tv_customer.setOnClickListener(this)
        iv_left.setOnClickListener(this)
        tv_right.setOnClickListener(this)
        tv_sex.setOnClickListener(this)
        tv_group.setOnClickListener(this)
        tv_country.setOnClickListener(this)
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.home_bottom_down))
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        mdialog.setBtnDataLinsenter(object : ChoseSexDialog.BtnDataLinsenter {
            override fun btndata(str: String) {
                tv_sex.text = str
            }
        })

    }

    override fun start() {
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onNewAddCustomer(data: LinkTestEntity?) {

        showToast("创建成功")
        finish()
    }

    override fun oncompanyList(data: ArrayList<CompanyListEntity>?, total: Int) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                Constants.CHOSEFORUM -> {
                    tv_customer.text = data?.getStringExtra(Constants.KEYNAME)
                }
                Constants.CHOSEGROUPID->{

                    mGroupListEntity= data?.getSerializableExtra(Constants.KEYNAME) as GroupListEntity?
                    tv_group.text = mGroupListEntity?.groupName

                }

            }
        }else   if (requestCode == CountryActivity.REQUESTCODE_FROM_MAIN_TO_PEPELU) {
            try {
                if (data?.getStringExtra("countryName")!=null){
                 tv_country.text=data?.getStringExtra("countryName")
                }else{
                   // tv_country.text=""
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

    }

}