package com.luizgasparetto.pay.google_pay_plugin.plugins.payment

import android.app.Activity

import com.luizgasparetto.pay.google_pay_plugin.core.contracts.SimplifiedMethodChannel
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.handlers.IPaymentPluginHandler
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.handlers.IVerifyInitializationPaymentHandler

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel


import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


private const val PAYMENT_PLUGIN_CHANNEL_NAME = "google_pay_channel"

class PaymentPlugin(private val activity: Activity): SimplifiedMethodChannel(), KoinComponent {
    private val initializeHandler: IVerifyInitializationPaymentHandler by inject()
    private val handler: IPaymentPluginHandler by inject()

    override val channelName: String get() = PAYMENT_PLUGIN_CHANNEL_NAME

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "initialize" ->initializeHandler.execute(call, activity, result)
            "pay" -> handler.execute(call, activity, result);
        }
    }
}