package com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.adapters

import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.models.PriceInfoModel

object PriceInfoAdapter {
    fun fromJson(json: Map<String, Any>): PriceInfoModel {
        val price = json["totalPrice"] as Double
        val currencyCode = json["currencyCode"] as String

        return PriceInfoModel(price, currencyCode)
    }
}