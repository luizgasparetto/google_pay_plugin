package com.luizgasparetto.pay.google_pay_plugin.plugins.payment.handlers

import android.app.Activity

import com.google.android.gms.wallet.IsReadyToPayRequest
import com.google.android.gms.wallet.PaymentsClient
import com.google.android.gms.wallet.Wallet
import com.google.android.gms.wallet.WalletConstants

import com.luizgasparetto.pay.google_pay_plugin.core.contracts.IPluginHandler
import com.luizgasparetto.pay.google_pay_plugin.core.errors.ArgumentPaymentPluginError
import com.luizgasparetto.pay.google_pay_plugin.core.errors.VerifyInitializePaymentPluginError
import com.luizgasparetto.pay.google_pay_plugin.core.extensions.error

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


interface IVerifyInitializationPaymentHandler : IPluginHandler


class VerifyInitializationPaymentPluginHandler: IVerifyInitializationPaymentHandler {
    override fun execute(call: MethodCall, activity: Activity, result: MethodChannel.Result) {
        val resultErrorDispatcher by lazy { result.error(ArgumentPaymentPluginError()) }
        val requestBody = call.arguments<String>() ?: return resultErrorDispatcher

        CoroutineScope(Dispatchers.Main).launch {
            val response = runCatching { isReadyToPay(requestBody, activity) }.getOrElse {
                return@launch result.error(VerifyInitializePaymentPluginError())
            }

            return@launch result.success(response)
        }
    }



    private suspend fun isReadyToPay(body: String, activity: Activity): Boolean {
        val isReadyToPayRequest = IsReadyToPayRequest.fromJson(body)
        val clients = getPaymentsClient(activity)

        return clients.isReadyToPay(isReadyToPayRequest).await()

    }

    private fun getPaymentsClient(activity: Activity): PaymentsClient {
        val builder = Wallet.WalletOptions.Builder()
        val environmentWallet = builder.setEnvironment(WalletConstants.ENVIRONMENT_TEST)
        val options = environmentWallet.build()

        return Wallet.getPaymentsClient(activity, options)
    }
}