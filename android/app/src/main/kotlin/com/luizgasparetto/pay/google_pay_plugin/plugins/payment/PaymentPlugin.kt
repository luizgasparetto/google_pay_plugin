package com.luizgasparetto.pay.google_pay_plugin.plugins.payment

import android.app.Activity

import com.luizgasparetto.pay.google_pay_plugin.core.contracts.SimplifiedMethodChannel
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.handlers.IVerifyInitializationPaymentHandler

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel


import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


private const val PAYMENT_PLUGIN_CHANNEL_NAME = "google_pay_channel"

private const val INITIALIZE_METHOD_NAME = "initialize"

class PaymentPlugin(private val activity: Activity): SimplifiedMethodChannel(), KoinComponent {
    private val initializeHandler: IVerifyInitializationPaymentHandler by inject()

    override val channelName: String get() = PAYMENT_PLUGIN_CHANNEL_NAME

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            INITIALIZE_METHOD_NAME -> initializeHandler.execute(call, activity, result)
        }
    }

}


