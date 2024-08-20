package com.luizgasparetto.pay.google_pay_plugin.core.contracts

import android.app.Activity

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

interface IPluginHandler {
    fun execute(call: MethodCall, activity: Activity, result: MethodChannel.Result)
}
