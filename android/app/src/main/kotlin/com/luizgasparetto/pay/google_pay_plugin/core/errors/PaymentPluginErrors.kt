package com.luizgasparetto.pay.google_pay_plugin.core.errors

open class PaymentPluginError (
    open val errorCode: String,
    open val errorMessage: String
)

class ArgumentPaymentPluginError: PaymentPluginError(
    errorCode = "ARGUMENT_ERROR",
    errorMessage = "No JSON argument provided"
)

class VerifyInitializePaymentPluginError: PaymentPluginError(
    errorCode = "VERIFY_INITIALIZE_ERROR",
    errorMessage = "Failed to verify initialization of plugin"
)