package com.luizgasparetto.pay.google_pay_plugin.plugins.payment

import org.koin.dsl.module

import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.presenter.handlers.IInitializePaymentHandler
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.presenter.handlers.IProcessPaymentHandler

import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.presenter.handlers.InitializePaymentHandler
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.presenter.handlers.ProcessPaymentHandler


val paymentModule = module {
    factory<IInitializePaymentHandler> { InitializePaymentHandler() }
    factory<IProcessPaymentHandler> { ProcessPaymentHandler() }
}