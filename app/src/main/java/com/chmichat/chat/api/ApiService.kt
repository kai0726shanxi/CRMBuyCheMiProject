package com.chmichat.chat.api

import com.chmichat.chat.bean.*
import com.chmichat.chat.net.BaseResponse
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


}