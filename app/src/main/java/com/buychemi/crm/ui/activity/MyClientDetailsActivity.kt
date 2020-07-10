package com.buychemi.crm.ui.activity

import android.content.Intent
import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.base.BaseFragmentAdapter
import com.buychemi.crm.bean.CompanyDetailsEntity
import com.buychemi.crm.bean.CustomerDetailsEntity
import com.buychemi.crm.bean.CustomerListEntity
import com.buychemi.crm.bean.UserDataEntity
import com.buychemi.crm.getTime4String
import com.buychemi.crm.mvp.contract.CustomerContract
import com.buychemi.crm.mvp.presenter.CustomerPresenter
import com.buychemi.crm.ui.fragment.MyClientTabFragment
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_myclientdetails.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 我的客户详情
 * @Author 20342
 * @Date 2019/9/23 17:07
 */
class MyClientDetailsActivity : BaseActivity(), CustomerContract.View, View.OnClickListener {
    override fun onHomeCustomer(data: ArrayList<CustomerListEntity>?, total: Int?) {

    }


    private var mlist = arrayListOf("跟进记录", "基本信息", "相关信息")
    private val mFragmentList = ArrayList<Fragment>()
    private var mType: String = "0"
    private var mcontent=ArrayList<UserDataEntity>()
    private val mPresenter:CustomerPresenter by lazy { CustomerPresenter() }
     private var map=HashMap<String,String>()
    private var mId=0
    private var mCompany=0
    override fun layoutId(): Int {
        return R.layout.activity_myclientdetails
    }

    override fun initData() {
        mType = intent.getStringExtra(Constants.KEYTYPE)
        mId=intent.getIntExtra(Constants.KEYCUSTOMER,0)
        mCompany=intent.getIntExtra(Constants.KEYCOMPLAY,0)
    }

    override fun initView() {
        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, appbar_layout)
        iv_left.setOnClickListener(this)
        tv_company.setOnClickListener (this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        if (mType == "0") {
            tv_title.text = "公司详情"
            tv_company.visibility = View.GONE
            tv4.visibility=View.GONE


        } else if (mType=="1") {
            tv_title.text = "客户详情"
            tv4.visibility=View.GONE
            tv_company.visibility = View.VISIBLE

        }



    }

    override fun start() {
        if (mType!="0"){
            map.clear()
            map["id"]=mId.toString()
            mPresenter.getCustomerDetails(map)
        }else{
            map.clear()
            map["id"]=mCompany.toString()
            mPresenter.getCompanlyDetails(map)
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_company->{
                var  intent=Intent(this,MyClientDetailsActivity::class.java)
                   intent.putExtra(Constants.KEYTYPE,"0")
                   intent.putExtra(Constants.KEYCOMPLAY,mCompany)
                   startActivity(intent)
            }
        }
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onMyCustomerlist(data: ArrayList<CustomerListEntity>?, total: Int?) {
    }

    override fun onCustomerDetails(data: CustomerDetailsEntity?) {
        //用户详情sex 1男2女
        tv_name.text=data?.name
        if(data?.position!=null){
            tv1.text="职务\n"+data?.position

        }else{
            tv1.text="职务"

        }
        if (data?.sex==2){
            tv2.text="性别\n女"


        }else{
            tv2.text="性别\n男"

        }
        if (data?.createTime!=null&&data?.createTime!=""){
            tv3.text="创建时间\n"+getTime4String(data?.createTime!!)

        }
        tv_company.text=data?.company?.companyName
        mcontent.clear()
        mcontent.add(UserDataEntity("姓名",data?.name))
        mcontent.add(UserDataEntity("公司名称",data?.company?.companyName))

        if (data?.sex==2){
            mcontent.add(UserDataEntity("性别","女"))

        }else{
            mcontent.add(UserDataEntity("性别","男"))

        }
        mcontent.add(UserDataEntity("职称",data?.position))
        mcontent.add(UserDataEntity("手机",data?.tel))
        mcontent.add(UserDataEntity("电子邮箱",data?.email))
        mcontent.add(UserDataEntity("邀请人",data?.invitePeople))
        mcontent.add(UserDataEntity("创建时间",data?.createTime))
        data?.id?.let { MyClientTabFragment.getInstance(mType, "0", it) }?.let { mFragmentList.add(it) }
        mFragmentList.add(MyClientTabFragment.getInstance(mType, "1",mcontent))
        data?.id?.let { MyClientTabFragment.getInstance(mType, "2", it) }?.let { mFragmentList.add(it) }
        mViewPager.adapter = BaseFragmentAdapter(supportFragmentManager, mFragmentList, mlist)
        tab_layout.setViewPager(mViewPager)

    }
    override fun onCompanlyDetails(data: CompanyDetailsEntity?) {
        //公司详情
        tv_name.text=data?.companyName
        when(data?.type){
            //公司类型1:采购商;2:供应商;
            1->{tv1.text="公司类型\n采购商"}
            2->{tv1.text="公司类型\n供应商"}
            else->{tv1.text="公司类型"}
        }
        when(data?.companyValue){
            //客户价值0:VIP公司1:普通公司
            0->{tv1.text="公司价值\nVIP公司"}
            1->{tv1.text="公司价值\n普通公司"}
            else->{
                "公司价值"
            }
        }
        if (data?.createTime!=null&&data?.createTime!=""){
            tv3.text="创建时间\n"+getTime4String(data?.createTime!!)

        }
        mcontent.clear()
        mcontent.add(UserDataEntity("公司名称",data?.companyName))

        if (data?.type==1){
            mcontent.add(UserDataEntity("公司类型","采购商"))

        }else{
            mcontent.add(UserDataEntity("公司类型","供应商"))

        }
        mcontent.add(UserDataEntity("公司行业",data?.industryName))

        when(data?.companyValue){
            //客户价值0:VIP公司1:普通公司
            0->{mcontent.add(UserDataEntity("公司价值","VIP公司"))}
            1->{mcontent.add(UserDataEntity("公司价值","普通公司"))}
            else->{mcontent.add(UserDataEntity("公司价值",""))}
        }
        mcontent.add(UserDataEntity("公司来源",data?.companySourceName))
        mcontent.add(UserDataEntity("所在地区",data?.area))
        mcontent.add(UserDataEntity("创建时间",data?.createTime))
        mcontent.add(UserDataEntity("主营产品",data?.mainProducts))
        mcontent.add(UserDataEntity("主营范围",data?.businessScope))
        data?.id?.let { MyClientTabFragment.getInstance(mType, "0", it) }?.let { mFragmentList.add(it) }
        mFragmentList.add(MyClientTabFragment.getInstance(mType, "1",mcontent))
        data?.id?.let { MyClientTabFragment.getInstance(mType, "2", it) }?.let { mFragmentList.add(it) }
        mViewPager.adapter = BaseFragmentAdapter(supportFragmentManager, mFragmentList, mlist)
        tab_layout.setViewPager(mViewPager)
    }
    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}