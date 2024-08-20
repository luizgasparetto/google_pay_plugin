package com.luizgasparetto.pay.google_pay_plugin.plugins.payment.presenter.handlers

import android.app.Activity

import com.google.android.gms.wallet.IsReadyToPayRequest

import com.luizgasparetto.pay.google_pay_plugin.core.contracts.IPluginHandler
import com.luizgasparetto.pay.google_pay_plugin.core.extensions.error

import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.builders.PaymentRequestBuilder
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.builders.PaymentsClientBuilder
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.errors.VerifyInitializePaymentPluginError

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

import org.koin.core.component.KoinComponent

interface IInitializePaymentHandler : IPluginHandler

class InitializePaymentHandler: IInitializePaymentHandler, KoinComponent {
    override fun execute(call: MethodCall, activity: Activity, result: MethodChannel.Result) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = runCatching { isReadyToPay(activity) }.getOrElse {
                return@launch result.error(VerifyInitializePaymentPluginError())
            }

            return@launch result.success(response)
        }
    }

    private suspend fun isReadyToPay(activity: Activity): Boolean {
        val paymentRequest = PaymentRequestBuilder.build()

        val isReadyToPayRequest = IsReadyToPayRequest.fromJson(paymentRequest.toJson())
        val clients = PaymentsClientBuilder.build(activity)

        return clients.isReadyToPay(isReadyToPayRequest).await()
    }
}