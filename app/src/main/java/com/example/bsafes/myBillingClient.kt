package com.example.bsafes

import android.app.Activity
import android.util.Log
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.ConsumeResponseListener
import com.android.billingclient.api.PendingPurchasesParams
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MyBillingClient(private val activity:Activity) {
    private val tag = "MyBillingClient"
    private val purchasesList = MutableStateFlow<List<String>>(emptyList())
    private val purchasesUpdatedListener = PurchasesUpdatedListener { purchasesResult, purchases ->
        if(purchasesResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            Log.d(tag, "Purchases updated")
            for(purchase in purchases) {
                handlePurchase(purchase)
            }
        } else if (purchasesResult.responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
            Log.d(tag, "User canceled the purchase")
        } else {
            Log.d(tag, "Billing error: ${purchasesResult.responseCode}")
            Log.d(tag, "Billing error: ${purchasesResult.debugMessage}")
        }
    }
    private val pendingPurchasesParams: PendingPurchasesParams = PendingPurchasesParams.newBuilder()
        .enableOneTimeProducts()
        .build()
    private var billingClient: BillingClient = BillingClient.newBuilder(activity)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases(pendingPurchasesParams)
        .build()
    private fun handlePurchase(purchase: Purchase) {
        val consumeParams = ConsumeParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()
        val listener = ConsumeResponseListener{result, s->}
        billingClient.consumeAsync(consumeParams, listener)
    }
    fun setup() {
        Log.d(tag, "Billing client ready ${billingClient.isReady}")
        billingClient.startConnection(object: BillingClientStateListener {
            override fun onBillingSetupFinished(result: BillingResult) {
                if(result.responseCode == BillingClient.BillingResponseCode.OK){
                    Log.d(tag, "Billing setup finished")
                    this@MyBillingClient.queryPurchases()
                } else {
                    Log.d(tag, "Billing setup failed")
                    Log.d(tag, "error: ${result.debugMessage}")
                }
            }
            override fun onBillingServiceDisconnected() {
                Log.d(tag, "Billing service disconnected")
            }
        })
    }
    fun queryPurchases() {
        Log.d(tag, "Query purchases")
        val queryPurchaseParams = QueryPurchasesParams.newBuilder()
            .setProductType(BillingClient.ProductType.INAPP)
            .build()
        billingClient.queryPurchasesAsync(queryPurchaseParams) { result, purchases ->
            Log.d(tag, "Query purchases result: ${result.responseCode} ")
            Log.d(tag, "Query pruchases result: ${result.debugMessage}")
            when (result.responseCode) {
                BillingClient.BillingResponseCode.OK -> {
                    for(purchase in purchases) {
                        Log.d(tag, "Purchase: ${purchase.purchaseState}")
                        if(purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                            this@MyBillingClient.handlePurchase(purchase)
                            purchasesList.update {
                                val newList = it.toMutableList()
                                newList.add(purchase.products[0].toString())
                                newList
                            }
                        }
                        return@queryPurchasesAsync
                    }
                }
                BillingClient.BillingResponseCode.USER_CANCELED -> {
                    Log.d(tag, "User canceld the purchase")
                }
                else -> {
                    Log.d(tag, "Billing error: ${result.responseCode}")
                    Log.d(tag, "Billing error: ${result.debugMessage}")
                }
            }
        }

    }
    fun purchase(productId: String) {
        Log.d(tag, "Purchase prodcut:")
        val queryProductDetailsParams = QueryProductDetailsParams.newBuilder()
            .setProductList(
                listOf(
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(productId)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build()
                )
            )
            .build()
        billingClient.queryProductDetailsAsync(queryProductDetailsParams) { queryResult, productDetailsList ->
            if(queryResult.responseCode == BillingClient.BillingResponseCode.OK) {
                val productDetails = productDetailsList.firstOrNull{productDetails ->
                    productDetails.productId == productId
                }
                productDetails?.let{
                    val productDetailsParamList = listOf(
                        BillingFlowParams.ProductDetailsParams.newBuilder()
                            .setProductDetails(it)
                            .build()
                    )
                    val billingFlowParams = BillingFlowParams.newBuilder()
                        .setProductDetailsParamsList(productDetailsParamList)
                        .build()
                    billingClient.launchBillingFlow(activity, billingFlowParams)
                }
            }
        }
    }
}