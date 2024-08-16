package com.luizgasparetto.pay.google_pay_plugin.plugins.payment.handlers

import android.app.Activity
import android.content.Context
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.gms.wallet.IsReadyToPayRequest

import com.google.android.gms.wallet.PaymentsClient
import com.google.android.gms.wallet.Wallet
import com.google.android.gms.wallet.WalletConstants

import io.flutter.plugin.common.MethodChannel

import com.luizgasparetto.pay.google_pay_plugin.core.contracts.IPluginHandler
import kotlinx.coroutines.tasks.await

interface IPaymentPluginHandler: IPluginHandler

class PaymentPluginHandler: IPaymentPluginHandler {
    override fun execute(context: Context, activity: Activity, result: MethodChannel.Result) {
        TODO("Not yet implemented")
    }

    private suspend fun isReadyToPay(activity: Activity): Boolean {
        val isReadyToPayRequest = IsReadyToPayRequest.fromJson("{\"allowedPaymentMethods\":[{\"type\":\"CARD\",\"parameters\":{\"allowedAuthMethods\":[\"PAN_ONLY\",\"CRYPTOGRAM_3DS\"],\"allowedCardNetworks\":[\"AMEX\",\"DISCOVER\",\"MASTERCARD\",\"VISA\"]}}]}")

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
