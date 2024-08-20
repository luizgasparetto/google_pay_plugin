package com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.adapters

import com.google.android.gms.wallet.CardRequirements
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters
import com.google.android.gms.wallet.TransactionInfo

import org.json.JSONArray
import org.json.JSONObject

import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.constants.REQUEST_PARAMETER

class PaymentRequestAdapter(
    private val transactionInfo: TransactionInfo,
    private val cardRequirements: CardRequirements,
    private val methodTokenizationParameters: PaymentMethodTokenizationParameters
) {
    fun toJson(): String    {
        val transactionInfo = getTransactionInfoJson()
        val allowedPaymentMethods = getAllowedPaymentMethodsJson()

        return JSONObject().apply {
            put("transactionInfo", transactionInfo)
            put("allowedPaymentMethods", JSONArray().apply { put(allowedPaymentMethods) })
        }.toString().trimIndent()
    }

    private fun getTransactionInfoJson(): JSONObject {
        return JSONObject().apply {
            put("totalPriceStatus", transactionInfo.totalPriceStatus)
            put("totalPrice", transactionInfo.totalPrice)
            put("currencyCode", transactionInfo.currencyCode)
        }
    }

    private fun getAllowedPaymentMethodsJson(): JSONObject {
        val parameters = getCardRequirementsJson()
        val tokenization = getTokenizationSpecificationJson()

        return JSONObject().apply {
            put("type", "CARD")
            put("parameters", parameters)
            put("tokenizationSpecification", tokenization)
        }
    }

    private fun getCardRequirementsJson(): JSONObject {
        return JSONObject().apply {
            put("allowedCardNetworks", cardRequirements.allowedCardNetworks)
        }
    }

    private fun getTokenizationSpecificationJson(): JSONObject {
        return JSONObject().apply {
            put("type", methodTokenizationParameters.paymentMethodTokenizationType)
            put("parameters", JSONObject().apply {
                put("publicKey", methodTokenizationParameters.parameters.getString(REQUEST_PARAMETER))
            })
        }
    }
 }