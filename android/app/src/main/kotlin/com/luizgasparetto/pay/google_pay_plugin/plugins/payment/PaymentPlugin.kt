package com.luizgasparetto.pay.google_pay_plugin.plugins.payment

import android.app.Activity
import com.luizgasparetto.pay.google_pay_plugin.core.contracts.SimplifiedMethodChannel
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.handlers.IPaymentPluginHandler

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel


import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PaymentPlugin(private val activity: Activity): SimplifiedMethodChannel("google_pay_channel"), KoinComponent {
    private val handler: IPaymentPluginHandler by inject()

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "pay" -> handler.execute(context, activity, result);
        }
    }
}