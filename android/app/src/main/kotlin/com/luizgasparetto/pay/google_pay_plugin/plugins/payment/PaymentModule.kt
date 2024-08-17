package com.luizgasparetto.pay.google_pay_plugin.plugins.payment

import org.koin.dsl.module

import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.handlers.IVerifyInitializationPaymentHandler
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.handlers.VerifyInitializationPaymentPluginHandler

import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.utils.GetPaymentsClientUtil


val paymentModule = module {
    factory { GetPaymentsClientUtil() }
    factory<IVerifyInitializationPaymentHandler> { VerifyInitializationPaymentPluginHandler() }
}