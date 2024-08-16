package com.luizgasparetto.pay.google_pay_plugin

import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.PaymentPlugin

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity: FlutterActivity() {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        startKoin { androidContext(this@MainActivity); modules(paymentModule) }

        flutterEngine.plugins.add(PaymentPlugin(this))
    }
}
