package com.buychemi.crm.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.LogListEntity
import com.buychemi.crm.bean.LogLookEntity
import com.buychemi.crm.bean.MyCalendarEntity
import com.buychemi.crm.mvp.contract.LogBookContract
import com.buychemi.crm.mvp.presenter.LogLookPresenter
import com.buychemi.crm.mvp.presenter.LoginPresenter
import com.buychemi.crm.showToast
import com.buychemi.crm.ui.adapter.LogBookAdapter
import com.buychemi.crm.utils.StatusBarUtil
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import kotlinx.android.synthetic.main.activity_log_book.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 工作日志
 * @Author 20342
 * @Date 2019/9/25 13:04
 */
class LogBookActivity : BaseActivity(), LogBookContract.View,
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener, View.OnClickListener {

    private var ismonth: Int? = 0
    private var mapcal = HashMap<String, String>()
    private var mapdata = HashMap<String, String>()
    private val map = java.util.HashMap<String, Calendar>()
    private var page = 1
    private var mtotal = 1
    private val mlist = arrayListOf("", "", "", "", "", "", "")
    private var mLogAdapter: LogBookAdapter? = null
    private val mPresenter: LogLookPresenter by lazy { LogLookPresenter() }
    override fun layoutId(): Int {
        return R.layout.activity_log_book
    }

    override fun initData() {
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        mPresenter.attachView(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_right.visibility = View.INVISIBLE
        tv_right.text = "新建"
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.home_bottom_down))
        tv_title.text = "我的备忘"
        iv_left.setOnClickListener(this)
        tv_title.setOnClickListener(this)
        tv_right.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        mLogAdapter = LogBookAdapter(this, ArrayList())
        ismonth = mCalendarView.curMonth//获取月份
        tv_showtime.text = mCalendarView.curYear.toString() + "-" + mCalendarView.curMonth.toString() + "-" + mCalendarView.curDay

        recyclerView.adapter = mLogAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        mCalendarView.setOnCalendarSelectListener(this)
        mCalendarView.setOnYearChangeListener(this)

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
    }

    override fun start() {

    }

    override fun onStart() {
        super.onStart()
        setCalendar()

        setpushData()
    }
    private fun setpushData() {
        mapdata.clear()
        mapdata["pageSize"] = "10"
        mapdata["pageNum"] = page.toString()
        mapdata["remarkDate"] = tv_showtime.text.toString()
        mPresenter.getMyLogList(mapdata)

    }

    private fun setCalendar() {
        mapcal["today"] = tv_showtime.text.toString()
        mapcal["dateType"] = "1"
        mPresenter.getMyLogCalender(mapcal)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> finish()
            R.id.tv_right -> {
                startActivity(Intent(this, NewLogActivity::class.java))
            }
        }
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onMyLogList(data: ArrayList<LogLookEntity>?, total: Int?) {
        if (total != null) {
            mtotal = total
        }


        if (data != null) {
            if (page == 1) {
                mLogAdapter?.setDataNew(data)
            } else {
                mLogAdapter?.setDataAll(data)
            }
        } else {
            if (page == 1) {
                mLogAdapter?.setDataNew(ArrayList())
            }
        }

    }

    override fun onMyLogCalender(data: ArrayList<MyCalendarEntity>?) {
        if (data != null) {
            map.clear()
            for (item in data) {
                if (item.isExist == "1") {
                    var ds = item.day.split("-")
                    try {
                        map[getSchemeCalendar(ds[0].toInt(), ds[1].toInt(), ds[2].toInt(), -0x196ec8, "事").toString()] = getSchemeCalendar(ds[0].toInt(), ds[1].toInt(), ds[2].toInt(), -0x196ec8, "事")

                    } catch (e: Exception) {

                    }
                }


            }
            mCalendarView.setSchemeDate(map)


        } else {

        }

    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    @SuppressLint("SetTextI18n")
    override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {

        tv_showtime.text = calendar?.year.toString() + "-" + calendar?.month.toString() + "-" + calendar?.day
        if (ismonth != calendar?.month) {
            ismonth = calendar?.month
            setCalendar()
        }
        if (isClick) {
            page = 1
            setpushData()
        }

    }

    override fun onCalendarOutOfRange(calendar: Calendar?) {
    }

    override fun onYearChange(year: Int) {

    }

    private fun getSchemeCalendar(year: Int, month: Int, day: Int, color: Int, text: String): Calendar {
        val calendar = Calendar()
        calendar.year = year
        calendar.month = month
        calendar.day = day
      //  calendar.schemeColor = color//如果单独标记颜色、则会使用这个颜色
        calendar.scheme = text
        calendar.addScheme(Calendar.Scheme())
        return calendar
    }

}