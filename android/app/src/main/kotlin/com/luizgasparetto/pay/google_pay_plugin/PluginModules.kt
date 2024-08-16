package com.luizgasparetto.pay.google_pay_plugin

import org.koin.dsl.module

import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.handlers.IPaymentPluginHandler
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.handlers.IVerifyInitializationPaymentHandler
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.handlers.PaymentPluginHandler
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.handlers.VerifyInitializationPaymentPluginHandler

val paymentModule = module {
    single<IPaymentPluginHandler> { PaymentPluginHandler() }
    single<IVerifyInitializationPaymentHandler> { VerifyInitializationPaymentPluginHandler() }
}