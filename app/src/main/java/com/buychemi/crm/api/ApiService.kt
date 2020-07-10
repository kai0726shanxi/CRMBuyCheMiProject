package com.buychemi.crm.api

import com.buychemi.crm.bean.*
import com.buychemi.crm.net.BaseResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * Created by 203442 2019/9/2
 * Api 接口
 */

interface ApiService {

    /**
     * 开关详情
     */
    @GET("bbs/user/push/switch/details")
    fun getSwitchDetails(): Observable<BaseResponse<NotificationManagerEntity>>

    /**
     *切换踩
     */
    @POST("bbs/user/push/switch/tread")
    fun getSwitchTread(@Query("action") id: Boolean): Observable<BaseResponse<Boolean>>

    /**
     *切换评论
     */
    @POST("bbs/user/push/switch/comments")
    fun getSwitchComments(@Query("action") id: Boolean): Observable<BaseResponse<Boolean>>

    /**
     * 登录
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("crm/user/login")
    fun getLogin(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<UserBean?>>

    /**
     * 上传工作报告
     * @param parmer
     * @return
     */
    @JvmSuppressWildcards
    @POST("work/report/add")
    fun getNewMemo(@Body body: Map<String,Any>):  Observable<BaseResponse<String?>>

    /**
     * 新增备忘录
     * @param parmer
     * @return
     */
    @JvmSuppressWildcards
    @POST("memorandum/add")
    fun getNewLog(@Body body: Map<String,Any?>):  Observable<BaseResponse<String?>>
    /**
     * 新增跟进
     * @param parmer
     * @return
     */
    @JvmSuppressWildcards
    @POST("customer/follow/up/add")
    fun getNewFollow(@Body body: Map<String,Any?>):  Observable<BaseResponse<String?>>
    /**
     * 批量上传图片
     * @param parmer
     * @return
     */
    @POST("upload/file/more")
    fun getUpImages(@Body body: RequestBody): Observable<BaseResponse<ArrayList<UpImagesEntity>?>>

    /**
     * 我的分组
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("group/myPage")
    fun getMyGroupData(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<ArrayList<GroupListEntity>?>>
    /**
     * 我和我下属的分组
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("group/myAllPage")
    fun getAllGroupData(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<ArrayList<GroupListEntity>?>>
    /**
     * 下属分组
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("group/subordinatePage")
    fun getMySubordinateGroupData(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<ArrayList<GroupListEntity>?>>
    /**
     * 我的联系人（）
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("customer/myGroupCustomerList")
    fun getMyCustomerData(@FieldMap parmer: Map<String, String>): Observable<BaseResponse<ArrayList<CustomerListEntity>?>>

    /**
     * 新建日程
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("schedule/management/add")
    fun getNewSchedule(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<String?>>
    /**
     * 增加审核记录（报告）
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("work/report/audit/add")
    fun getreportadd(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<String?>>

    /**
     * 跟进列表
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("customer/follow/up/page")
    fun getFollowList(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<FollowListEntity>?>>


    /**
     * 分页查询客户列表
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("customer/page")
    fun getComPlanyCustomer(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<CustomerSearchEntity>?>>
    /**
     * 我的客户列表
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("customer/myCustomerList")
    fun getmyCustomerList(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<CustomerListEntity>?>>
    /**
     * 根据下属查询我的客户列表
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("customer/customerListByUserId")
    fun getFindCustomerList(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<CustomerListEntity>?>>
    /**
     * 我的下属分页查询
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("user/mySubordinatePage")
    fun getmySubordinateList(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<MyStaffEntity>?>>

    /**
     * 用户日志列表
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("logging/pageByUser")
    fun getUserLogList(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<UserLogEntity>?>>
    /**
     * 用户详情
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("customer/findDetail")
    fun getCustomerDetails(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<CustomerDetailsEntity?>>

    /**
     * 公司详情
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("company/findDetail")
    fun getCompanlyDetails(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<CompanyDetailsEntity?>>
    /**
     *我的报告列表
     * @param parmer
     * @return
     */
    @FormUrlEncoded
  //  @POST("work/report/myPage")
   @POST("work/report/myAndSubordinatePage")
    fun getReportList(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<ReportListEntity>?>>

    /**
     *我的报告详情
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("work/report/findDetail")
    fun getReportDetails(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<WorkStatementEntity?>>
    /**
     *短信选择模板
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("template/page")
    fun getTemplateList(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<TemplateListEntity>?>>


     /**
     *我的报告详情（列表）
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("work/report/audit/page")
    fun getReportCommentList(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<ReportCommentEntity>?>>
     /**
     *发送短信
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("send/sms")
    fun getSendMessage(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<SendSuccessEntity?>>


    /**
     *群发消息，根据组的id获取用户的i
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("customer/findByGroupId")
    fun getfindByGroupId(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<FindGroupIdEntity?>>
    /**
     *发送记录列表
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("send/record/page")
    fun getSendMessageList(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<SendListEntity>?>>
    /**
     *发送到人的表记录
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("send/record/detail/page")
    fun getSendLinkList(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<SendLinkListEntity>?>>

    /**
     *查询客户日志列表
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("logging/pageByCustomer")
    fun getLinkLogList(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<LogListEntity>?>>
    /**
     *查询公司日志列表
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("logging/page")
    fun getComplayLogList(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<LogListEntity>?>>
    /**
     *我的日志列表
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("memorandum/getPage")
    fun getMyLogList(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<LogLookEntity>?>>
    /**
     *我的日志日历
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("memorandum/myCalendar")
    fun getMyLogCalender(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<MyCalendarEntity>?>>
    /**
     *我的跟进日历
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("customer/follow/up/myCalendar")
    fun getMyFollowCalender(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<MyCalendarEntity>?>>
    /**
     *我的日程日历
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("schedule/management/myCalendar")
    fun getMyManagementCalender(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<MyCalendarEntity>?>>

    /**
     *我的日程列表
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("schedule/management/getMyAndSubordinateListByDay")
    fun getMyManagementList(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<MyScheduleEntity>?>>
    /**
     *我的日程详情
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("schedule/management/findDetail")
    fun getmanagementDetails(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ManagementDetailsEntity?>>

    /**
     *我的跟进列表
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("customer/follow/up/page")
    fun getMyFollowList(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<FollowListEntity>?>>
    /**
     *完成日程
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("schedule/management/completed")
    fun getMyscheduleOver(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<String?>>
    /**
     *删除日程
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("schedule/management/delete")
    fun getMyscheduledelete(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<String?>>
    /**
     *短信模板详情
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("template/findOne")
    fun gettemplateDetails(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<TemplateDetails?>>
    /**
     *找回密码
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("user/forgetPsw")
    fun getFindPsw(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<String?>>
    /**
     *获取短信验证，
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("user/sendRegisterCode")
    fun getSendCode(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<String?>>
    /**
     *crm简报，
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("home/getBriefing")
    fun getBriefing(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<BriefingEntity?>>
    /**
     *跟进详情
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("customer/follow/up/findOne")
    fun getFollowDetails(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<FollowDetailsEntity?>>

 /**
     *日志详情
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("memorandum/findById")
    fun getLogDetails(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<LogDetailsEntity?>>
 /**
     *新增联系人
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("customer/contact")
    fun getNewAddCustomer(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<LinkTestEntity?>>
    /**
     *分页查询
     * @param parmer
     * @returnd
     */
    @FormUrlEncoded
    @POST("company/page")
    fun getcompanyList(@FieldMap parmer: Map<String, String?>): Observable<BaseResponse<ArrayList<CompanyListEntity>?>>


}