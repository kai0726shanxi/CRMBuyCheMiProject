package com.chmichat.chat.ui.activity

import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.ui.adapter.MyScheduleAdapter
import com.chmichat.chat.utils.StatusBarUtil
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import kotlinx.android.synthetic.main.activity_my_schedule.*
import kotlinx.android.synthetic.main.title_bar_layout.*
import java.util.HashMap

/**
 *
 * 我的日程
 * @Author 20342
 * @Date 2019/9/23 18:36
 */
class MyScheduleActivity : BaseActivity(), CalendarView.OnCalendarSelectListener, CalendarView.OnYearChangeListener, View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left->{
                finish()
            }
            R.id.tv_right->{
                startActivity(Intent(this,NewScheduleActivtity::class.java))


            }
        }
    }


    private var mMyScheduleAdapter: MyScheduleAdapter? = null
    private val mlist = arrayListOf("", "", "", "", "", "", "", "", "")
    override fun layoutId(): Int {

        return R.layout.activity_my_schedule
    }

    override fun initData() {
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "我的日程"
        tv_right.visibility = View.VISIBLE
        tv_right.text = "新建"
        iv_left.setOnClickListener(this)
        tv_right.setOnClickListener(this)
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.home_bottom_down))
        mMyScheduleAdapter = MyScheduleAdapter(this, mlist)
        recyclerView.adapter = mMyScheduleAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun start() {
    }

    private fun getSchemeCalendar(year: Int, month: Int, day: Int, color: Int, text: String): Calendar {
        val calendar = Calendar()
        calendar.year = year
        calendar.month = month
        calendar.day = day
        calendar.schemeColor = color//如果单独标记颜色、则会使用这个颜色
        calendar.scheme = text
        calendar.addScheme(Calendar.Scheme())
        calendar.addScheme(-0xff7800, "假")
        calendar.addScheme(-0xff7800, "节")
        return calendar
    }

    override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {
        tv_showtime.text = calendar?.year.toString() + "-" + calendar?.month
    }

    override fun onCalendarOutOfRange(calendar: Calendar?) {
        tv_showtime.text = calendar?.year.toString() + "-" + calendar?.month

    }

    override fun onYearChange(year: Int) {


    }
}