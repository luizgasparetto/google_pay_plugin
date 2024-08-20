package com.luizgasparetto.pay.google_pay_plugin.plugins.payment.presenter

import android.app.Activity

import com.luizgasparetto.pay.google_pay_plugin.core.contracts.SimplifiedMethodChannel
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.presenter.handlers.IInitializePaymentHandler
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.presenter.handlers.IProcessPaymentHandler

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

private const val PAYMENT_PLUGIN_CHANNEL_NAME = "google_pay_channel"

private const val INITIALIZE_METHOD = "is_ready"
private const val PROCESS_PAYMENT_METHOD = "process_payment"

class PaymentPlugin(private val activity: Activity): SimplifiedMethodChannel(), KoinComponent {
    private val initializeHandler: IInitializePaymentHandler by inject()
    private val paymentHandler: IProcessPaymentHandler by inject()

    override val channelName: String get() = PAYMENT_PLUGIN_CHANNEL_NAME

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            INITIALIZE_METHOD -> initializeHandler.execute(call, activity, result)
            PROCESS_PAYMENT_METHOD -> paymentHandler.execute(call, activity, result)
        }
    }
}