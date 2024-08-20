package com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.errors

import com.luizgasparetto.pay.google_pay_plugin.core.errors.PaymentPluginError

class VerifyInitializePaymentPluginError: PaymentPluginError(
    errorCode = "VERIFY_INITIALIZE_ERROR",
    errorMessage = "Failed to verify initialization of plugin"
)

class ProcessPaymentPluginError: PaymentPluginError(
    errorCode = "PROCESS_PAYMENT_ERROR",
    errorMessage = "Failed to process payment of Google Pay"
)