package com.luizgasparetto.pay.google_pay_plugin.core.contracts

import android.app.Activity
import android.content.Context
import io.flutter.plugin.common.MethodChannel

interface IPluginHandler {
    fun execute(context: Context, activity: Activity, result: MethodChannel.Result)
}
