package com.luizgasparetto.pay.google_pay_plugin.core.errors

open class PaymentPluginError (
    open val errorCode: String,
    open val errorMessage: String
)

