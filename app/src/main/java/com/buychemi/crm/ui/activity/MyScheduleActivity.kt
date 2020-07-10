package com.buychemi.crm.ui.activity

import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.ManagementDetailsEntity
import com.buychemi.crm.bean.MyCalendarEntity
import com.buychemi.crm.bean.MyScheduleEntity
import com.buychemi.crm.mvp.contract.MyScheduleContract
import com.buychemi.crm.mvp.presenter.MySchedulePresenter
import com.buychemi.crm.showToast
import com.buychemi.crm.ui.activity.schedule.ScheduleDetailsActivity
import com.buychemi.crm.ui.adapter.MessageGroupTabAdapter
import com.buychemi.crm.ui.adapter.MyScheduleAdapter
import com.buychemi.crm.utils.StatusBarUtil
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import kotlinx.android.synthetic.main.activity_my_schedule.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 *
 * 我的日程
 * @Author 20342
 * @Date 2019/9/23 18:36
 */
class MyScheduleActivity : BaseActivity(), MyScheduleContract.View, CalendarView.OnCalendarSelectListener, CalendarView.OnYearChangeListener, View.OnClickListener {


    override fun ontmanagementDetails(data: ManagementDetailsEntity?) {

    }

    private var ismonth: Int? = 0
    private var mapcal = HashMap<String, String>()
    private var mapdata = HashMap<String, String>()
    private var map = java.util.HashMap<String, Calendar>()
    private var mapc=HashMap<String,String>()
    private val mPresenter: MySchedulePresenter by lazy { MySchedulePresenter() }
    private var page = 1
    private var mtotal = 1
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_right -> {
                startActivity(Intent(this, NewScheduleActivtity::class.java))


            }
        }
    }


    private var mMyScheduleAdapter: MyScheduleAdapter? = null
    private val mlist = ArrayList<MyScheduleEntity>()
    override fun layoutId(): Int {

        return R.layout.activity_my_schedule
    }

    override fun initData() {
    }

    override fun initView() {
        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "我的日程"
        tv_right.visibility = View.INVISIBLE
        tv_right.text = "新建"
        iv_left.setOnClickListener(this)
        tv_right.setOnClickListener(this)
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.home_bottom_down))
        mMyScheduleAdapter = MyScheduleAdapter(this, mlist)
        mMyScheduleAdapter?.setOnTitleItemClickListener { tag, i, click ->
            when (click) {
                1 -> {
                    //详情
                    var intent = Intent(this, ScheduleDetailsActivity::class.java)
                    intent.putExtra(Constants.KEYGROUPID, tag.id)
                    startActivity(intent)

                }
                2 -> {
                    //完成
                    mapc.clear()
                    mapc["id"]=tag.id.toString()
                    mPresenter.getMyscheduleOver(mapc)
                }
                3 -> {
                    //删除
                    mapc.clear()
                    mapc["id"]=tag.id.toString()
                    mPresenter.getMyscheduledelete(mapc)

                }
            }
        }
        recyclerView.adapter = mMyScheduleAdapter
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
        mapdata["thisDay"] = tv_showtime.text.toString()
        mPresenter.getMyscheduleCalender(mapdata)
    }

    private fun setCalendar() {
        mapcal["thisDay"] = tv_showtime.text.toString()
        mapcal["dateType"] = "1"
        mPresenter.getMyscheduleList(mapcal)
    }

    override fun start() {
        setCalendar()
        setpushData()
    }
    private fun getSchemeCalendar(year: Int, month: Int, day: Int, color: Int, text: String): Calendar {
        val calendar = Calendar()
        calendar.year = year
        calendar.month = month
        calendar.day = day
    //    calendar.schemeColor = color//如果单独标记颜色、则会使用这个颜色
        calendar.scheme = text
        calendar.addScheme(Calendar.Scheme())

        return calendar
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
        tv_showtime.text = calendar?.year.toString() + "-" + calendar?.month

    }

    override fun onYearChange(year: Int) {


    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onMyscheduleList(data: ArrayList<MyScheduleEntity>?, total: Int?) {
        if (total != null) {
            mtotal = total
        }
        if (data != null) {
            if (page == 1) {
                mMyScheduleAdapter?.setDataNew(data)
            } else {
                mMyScheduleAdapter?.setDataAll(data)
            }
        } else {
            if (page == 1) {
                mMyScheduleAdapter?.setDataNew(mlist)
            }
        }


    }

    override fun onMyscheduleCalender(data: ArrayList<MyCalendarEntity>?) {

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

    override fun onMyscheduleOver(data: String?) {
        showToast("已成功")
    }

    override fun onMyscheduledelete(data: String?) {
        showToast("删除成功")
        setCalendar()
        page=1
        setpushData()
    }
}