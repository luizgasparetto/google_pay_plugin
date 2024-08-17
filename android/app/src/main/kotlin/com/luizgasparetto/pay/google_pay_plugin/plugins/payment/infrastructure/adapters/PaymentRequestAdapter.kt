package com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.adapters

import com.google.android.gms.wallet.CardRequirements
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters
import com.google.android.gms.wallet.TransactionInfo

class PaymentRequestAdapter(
    private val transactionInfo: TransactionInfo,
    private val cardRequirements: CardRequirements,
    private val methodTokenizationParameters: PaymentMethodTokenizationParameters
) {
    fun toJson(): String    {
        return """
            {
                "transactionInfo": ${getTransactionInfoJson()},
                "allowedPaymentMethods": [
                    {
                        "type": "CARD",
                        "parameters": ${getCardRequirementsJson()},
                        "tokenizationSpecification": ${getTokenizationSpecification()}
                    }
                ]
            }
        """.trimIndent()
    }

    private fun getTransactionInfoJson(): String {
        return """
            {
                "totalPriceStatus": ${transactionInfo.totalPriceStatus},
                "totalPrice": ${transactionInfo.totalPrice},
                "currencyCode": ${transactionInfo.currencyCode} 
            }
        """.trimIndent()
    }

    private fun getCardRequirementsJson(): String {
        val cards = cardRequirements.allowedCardNetworks
        val cardsJson = cards?.joinToString(separator = "\", \"", prefix = "\"", postfix = "\"")

        return """
            {
              "allowedCardNetworks": [$cardsJson]
            }
        """.trimIndent()
    }

    private fun getTokenizationSpecification(): String {
        return """
            {
              "type": "${methodTokenizationParameters.paymentMethodTokenizationType}",
              "parameters": {
                "publicKey": ${methodTokenizationParameters.parameters.getString("publicKey")}
              }
            }
        """.trimIndent()
    }
 }