package com.buychemi.crm.ui.activity

import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.FollowListEntity
import com.buychemi.crm.bean.MyCalendarEntity
import com.buychemi.crm.bean.MyFollowEntity
import com.buychemi.crm.mvp.contract.FollowUpRecordsContract
import com.buychemi.crm.mvp.presenter.FollowUpRecordsPresenter
import com.buychemi.crm.ui.adapter.FollowUpRecordsAdapter
import com.buychemi.crm.utils.StatusBarUtil
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import kotlinx.android.synthetic.main.activity_followuprecords.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 跟进记录
 * @Author 20342
 * @Date 2019/9/25 10:03
 */
class FollowUpRecordsActivity : BaseActivity(), FollowUpRecordsContract.View, CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener, View.OnClickListener {

    private var ismonth: Int? = 0
    private var mapcal = HashMap<String, String>()
    private var mapdata = HashMap<String, String>()
    private val map = java.util.HashMap<String, Calendar>()
    private var page = 1
    private var mtotal = 1
    private var mFollowUpRecordsAdapter: FollowUpRecordsAdapter? = null
    private val mlist = ArrayList<MyFollowEntity>()
    override fun layoutId(): Int {

        return R.layout.activity_followuprecords
    }

    override fun initData() {
    }

    private val mPresenter: FollowUpRecordsPresenter by lazy { FollowUpRecordsPresenter() }

    override fun initView() {
        mPresenter.attachView(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        iv_left.setOnClickListener(this)
        tv_right.setOnClickListener(this)
        tv_title.text = "跟进记录"
        tv_right.visibility = View.INVISIBLE
        tv_right.text = "新建"
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.home_bottom_down))
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        recyclerView.isNestedScrollingEnabled = false
        mFollowUpRecordsAdapter = FollowUpRecordsAdapter(this, ArrayList())
        recyclerView.adapter = mFollowUpRecordsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        ismonth = mCalendarView.curMonth//获取月份
        tv_showtime.text = mCalendarView.curYear.toString() + "-" + mCalendarView.curMonth.toString() + "-" + mCalendarView.curDay
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

    private fun setpushData() {
        mapdata.clear()
        mapdata["pageSize"] = "10"
        mapdata["pageNum"] = page.toString()
        mapdata["startTime"] = tv_showtime.text.toString() + " 00:00:00"
        mapdata["endTime"] = tv_showtime.text.toString() + " 23:59:59"
        mPresenter.getMyFollowList(mapdata)
    }

    private fun setCalendar() {
        mapcal["today"] = tv_showtime.text.toString()
        mapcal["dateType"] = "1"
        mPresenter.getMyFollowCalender(mapcal)
    }


    override fun start() {
        setCalendar()

        setpushData()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_right -> {
                startActivity(Intent(this, NewFollowUpRecordsActivity::class.java))
            }
        }
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onMyFollowList(data: ArrayList<FollowListEntity>?, total: Int?) {
        if (total != null) {
            mtotal = total

        }
        if (data != null) {
            if (page == 1) {
                mFollowUpRecordsAdapter?.addDataNew(data)
            } else {
                mFollowUpRecordsAdapter?.addDataAll(data)
            }
        } else {
            if (page == 1) {
                mFollowUpRecordsAdapter?.addDataNew(ArrayList())
            }
        }


    }

    override fun onMyFollowCalender(data: ArrayList<MyCalendarEntity>?) {
        if (data != null) {
            map.clear()
            for (item in data) {
                if (item.isExist == "1") {
                    var ds = item.day.split("-")
                    try {
                        map[getSchemeCalendar(ds[0].toInt(), ds[1].toInt(), ds[2].toInt(), 0x196ec8, "").toString()] = getSchemeCalendar(ds[0].toInt(), ds[1].toInt(), ds[2].toInt(), -0x196ec8, "事")

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
       // calendar.schemeColor = color//如果单独标记颜色、则会使用这个颜色
        calendar.scheme = text
        calendar.addScheme(Calendar.Scheme())

        return calendar
    }
}