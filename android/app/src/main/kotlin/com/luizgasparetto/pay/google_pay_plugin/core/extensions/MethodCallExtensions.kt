package com.luizgasparetto.pay.google_pay_plugin.core.extensions

import io.flutter.plugin.common.MethodChannel

import com.luizgasparetto.pay.google_pay_plugin.core.errors.PaymentPluginError


fun MethodChannel.Result.error(error: PaymentPluginError) {
    this.error(error.errorCode, error.errorMessage, null);
}