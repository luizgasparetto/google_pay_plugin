package com.luizgasparetto.pay.google_pay_plugin.plugins.payment.presenter.handlers

import android.app.Activity

import com.luizgasparetto.pay.google_pay_plugin.core.contracts.IPluginHandler
import com.luizgasparetto.pay.google_pay_plugin.core.extensions.error

import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.adapters.PriceInfoAdapter
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.builders.PaymentRequestBuilder
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.builders.PaymentsClientBuilder
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.errors.ProcessPaymentPluginError
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.models.PriceInfoModel

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import org.koin.core.component.KoinComponent

interface IProcessPaymentHandler : IPluginHandler

class ProcessPaymentHandler : IProcessPaymentHandler, KoinComponent {
    override fun execute(call: MethodCall, activity: Activity, result: MethodChannel.Result) {
        val priceInfo = getPriceInfoArguments(call)

        val paymentsClient = PaymentsClientBuilder.build(activity)
        val paymentRequest = PaymentRequestBuilder.build(priceInfo)

        CoroutineScope(Dispatchers.Main).launch {
            val processResult = runCatching { paymentsClient.loadPaymentData(paymentRequest) }
            val error by lazy { ProcessPaymentPluginError() }

            processResult.onSuccess { result.success(Unit) }.onFailure { result.error(error) }

            return@launch
        }
    }

    private fun getPriceInfoArguments(call: MethodCall): PriceInfoModel {
        val requestMetadataJson = call.arguments<Map<String, Any>>() ?: emptyMap()
        return PriceInfoAdapter.fromJson(requestMetadataJson)
    }
}