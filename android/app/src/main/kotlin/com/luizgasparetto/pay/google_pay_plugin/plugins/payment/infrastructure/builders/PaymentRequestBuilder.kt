package com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.builders

import com.google.android.gms.wallet.CardRequirements

import com.google.android.gms.wallet.PaymentDataRequest
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters

import com.google.android.gms.wallet.TransactionInfo
import com.google.android.gms.wallet.WalletConstants
import com.luizgasparetto.pay.google_pay_plugin.plugins.payment.infrastructure.adapters.PaymentRequestAdapter


private const val KEY_PARAMETER: String = "publicKey"

object PaymentRequestBuilder {
    fun build(price: String, currencyCode: String): PaymentDataRequest {
        val transactionInfo = buildTransactionInfo(price, currencyCode)
        val cardRequirements = buildCardRequirements()
        val tokenization = buildPaymentMethodTokenization()

        val adapter = PaymentRequestAdapter(transactionInfo, cardRequirements, tokenization)

        return PaymentDataRequest.fromJson(adapter.toJson())
    }

    private fun buildTransactionInfo(price: String, currencyCode: String): TransactionInfo {
        val builder = TransactionInfo.newBuilder()

        val priceStatusBuilder = builder.setTotalPriceStatus(WalletConstants.TOTAL_PRICE_STATUS_FINAL)
        val totalPriceBuilder = priceStatusBuilder.setTotalPrice(price)
        val currencyCodeBuilder = totalPriceBuilder.setCurrencyCode(currencyCode)

        return currencyCodeBuilder.build()
    }

    private fun buildCardRequirements(): CardRequirements {
        val builder = CardRequirements.newBuilder()

        val allowedCardNetworks = listOf(WalletConstants.CARD_NETWORK_VISA, WalletConstants.CARD_NETWORK_MASTERCARD)
        val allowedCardsBuilder = builder.addAllowedCardNetworks(allowedCardNetworks)

        return allowedCardsBuilder.build()
    }

    private fun buildPaymentMethodTokenization(): PaymentMethodTokenizationParameters {
        val builder = PaymentMethodTokenizationParameters.newBuilder()

        val tokenizationType = WalletConstants.PAYMENT_METHOD_TOKENIZATION_TYPE_PAYMENT_GATEWAY
        val tokenizationTypeBuilder = builder.setPaymentMethodTokenizationType(tokenizationType)

        val parameterBuilder = tokenizationTypeBuilder.addParameter(KEY_PARAMETER, "KEY_HERE")

        return parameterBuilder.build()
    }
}