package com.buychemi.crm.ui.activity.mesage

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.EditorInfo
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.CustomerListEntity
import com.buychemi.crm.bean.FindGroupIdEntity
import com.buychemi.crm.bean.GroupListEntity
import com.buychemi.crm.bean.TemplateListEntity
import com.buychemi.crm.mvp.contract.TemplateContract
import com.buychemi.crm.mvp.presenter.TemplatePresenter
import com.buychemi.crm.showToast
import com.buychemi.crm.ui.adapter.MessageTemplateAdapter
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_my_message_client.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 群发消息模板
 * @Author 20342
 * @Date 2019/9/23 15:41
 */
class MessageTemplateActivity : BaseActivity(), TemplateContract.View, View.OnClickListener {

    private var map = HashMap<String, String>()
    private var mtotal: Int = 1
    private var page: Int = 1
    private var mType: String = ""
    private var mtempId = 0
    private var issearch=false
    private var strbuf: StringBuffer? = null
    private var mapgroup = HashMap<String, String>()
    private var mgroup = ArrayList<GroupListEntity>()
    private var list = ArrayList<CustomerListEntity>()
    private var mg = ArrayList<String>()
    private val mPresenter: TemplatePresenter by lazy { TemplatePresenter() }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_btn -> {
                if (mtempId!=0){
                    var intent = Intent(this, GroupSentDetailsActivity::class.java)
                    intent.putExtra(Constants.KEYGROUPID, mtempId)
                    intent.putExtra(Constants.KEYTYPE, "1")

                    if (mType == "1") {
                        intent.putExtra(Constants.KEYNAME, list)
                        intent.putExtra(Constants.KEYTAG, "1")


                    } else {
                        intent.putExtra(Constants.KEYUSERIDS, mg)
                        intent.putExtra(Constants.KEYTAG, "2")
                    }
                    startActivity(intent)
                }else{
                    showToast("请选择模板")
                }

            }
            R.id.tv_search->{
                issearch = true
                page = 1
                setpushData()
            }
        }
    }

    private var mMyClientAdapter: MessageTemplateAdapter? = null
    private val mlist = ArrayList<TemplateListEntity>()
    override fun layoutId(): Int {
        return R.layout.activity_my_message_client
    }

    override fun initData() {
        mType = intent.getStringExtra(Constants.KEYTYPE)
        if (mType == "1") {
            list = intent.getSerializableExtra(Constants.KEYNAME) as ArrayList<CustomerListEntity>

        } else {
            mgroup = intent.getSerializableExtra(Constants.KEYGROUPLIST) as ArrayList<GroupListEntity>

        }
    }

    override fun initView() {
        strbuf = StringBuffer()
        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        tv_search.setOnClickListener(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "选择模板"
        iv_left.setOnClickListener(this)
        tv_btn.setOnClickListener(this)
        if (mType == "1") {
            //发送给联系人
            if (list != null) {
                tv_num_bottom.text = "已选 ${list.size} 人"

            }
        }
        mMyClientAdapter = MessageTemplateAdapter(this, mlist)
        mMyClientAdapter?.setOnItemClickListener { tag, i ->
            mtempId = tag.id
            mMyClientAdapter?.mcheckPostion = i
            mMyClientAdapter?.notifyDataSetChanged()

        }
        recycler_view.adapter = mMyClientAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        refreshLayout.setOnRefreshListener { refreshLayout ->
            //下拉刷新
            page = 1
            setpushData()
            refreshLayout.finishRefresh()
        }
        refreshLayout.setOnLoadMoreListener { refreshLayout ->
            //加载更多
            page++
            if (page <= mtotal) {
                setpushData()
                refreshLayout.finishLoadMore()

            } else {
                refreshLayout.finishLoadMore(1000, true, true)

            }

        }
        edit_query.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //setsendData()
                issearch = true
                page = 1
                setpushData()

            }
            false
        }
    }

    private fun setpushData() {
        //上传参数
        map.clear()
        map["pageSize"] = "10"
        map["pageNum"] = page.toString()
        map["type"] = "1"
        if (issearch){
            map["name"]=edit_query.text.toString().trim()
        }
        mPresenter.getTemplateList(map)
    }

    override fun start() {

        setpushData()

        if (mType == "2") {
            if (mgroup != null) {

                for ((index, data) in mgroup.withIndex())
                    if (index == mgroup.size - 1) {
                        strbuf?.append(data.id.toString())
                    } else {
                        strbuf?.append(data.id.toString() + ",")

                    }

            }
            mapgroup.clear()
            mapgroup["groupIds"] = strbuf.toString()
            mPresenter.getGroupids(mapgroup)
        }


    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onTemplateList(data: ArrayList<TemplateListEntity>?, total: Int?) {
        if (total != null) {
            mtotal = total

        }
        if (data != null) {
            if (page == 1) {
                mMyClientAdapter?.setDataNew(data)
            } else {
                mMyClientAdapter?.setDataAll(data)
            }

        } else {
            if (page == 1) {
                mMyClientAdapter?.cleardata()
            }
        }

    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onGroupids(data: FindGroupIdEntity?) {
        if (data?.customerIds != null) {
            mg = data?.customerIds as ArrayList<String>

        }
        tv_num_bottom.text = "已选 ${data?.customerCount} 人"

    }
}