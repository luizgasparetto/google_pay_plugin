package com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.builders

import android.app.Activity
import com.google.android.gms.wallet.PaymentsClient
import com.google.android.gms.wallet.Wallet
import com.google.android.gms.wallet.WalletConstants

object PaymentsClientBuilder {
    fun build(activity: Activity): PaymentsClient {
        val builder = Wallet.WalletOptions.Builder()
        val environmentWallet = builder.setEnvironment(WalletConstants.ENVIRONMENT_TEST)
        val options = environmentWallet.build()

        return Wallet.getPaymentsClient(activity, options)
    }
}