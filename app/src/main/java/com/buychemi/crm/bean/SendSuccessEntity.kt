package com.buychemi.crm.bean

import java.io.Serializable

/**
 * Created by 20342 on 2019-10-22
 */
data class SendSuccessEntity(var failCount: Int,
                             var successCount: Int,
                             var customerCount: Int):Serializable