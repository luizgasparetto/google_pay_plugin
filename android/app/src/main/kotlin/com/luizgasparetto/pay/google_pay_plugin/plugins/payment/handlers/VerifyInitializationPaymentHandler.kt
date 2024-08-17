package com.luizgasparetto.pay.google_pay_plugin.plugins.payment.handlers

import android.app.Activity

import com.google.android.gms.wallet.IsReadyToPayRequest

import com.luizgasparetto.pay.google_pay_plugin.core.contracts.IPluginHandler
import com.luizgasparetto.pay.google_pay_plugin.core.errors.ArgumentPaymentPluginError
import com.luizgasparetto.pay.google_pay_plugin.core.errors.VerifyInitializePaymentPluginError
import com.luizgasparetto.pay.google_pay_plugin.core.extensions.error
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.utils.GetPaymentsClientUtil

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


interface IVerifyInitializationPaymentHandler : IPluginHandler

class VerifyInitializationPaymentPluginHandler: IVerifyInitializationPaymentHandler, KoinComponent {
    private val paymentsClient: GetPaymentsClientUtil by inject()

    override fun execute(call: MethodCall, activity: Activity, result: MethodChannel.Result) {
        val body = call.arguments<String>() ?: return result.error(ArgumentPaymentPluginError())

        CoroutineScope(Dispatchers.Main).launch {
            val response = runCatching { isReadyToPay(body, activity) }.getOrElse {
                return@launch result.error(VerifyInitializePaymentPluginError())
            }

            return@launch result.success(response)
        }
    }

    private suspend fun isReadyToPay(body: String, activity: Activity): Boolean {
        val isReadyToPayRequest = IsReadyToPayRequest.fromJson(body)
        val clients = paymentsClient.getPaymentsClient(activity)

        return clients.isReadyToPay(isReadyToPayRequest).await()
    }
}