package by.tomal.exchangerates.data.service

import android.app.Activity
import android.content.Context
import android.util.Log
import by.tomal.exchangerates.data.entity.ExchangeRateItemResponse
import by.tomal.exchangerates.data.net.providerApi
import by.tomal.exchangerates.data.entity.ExchangeRateResponse
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExchangeRateService {

    private val api = providerApi()
    private lateinit var rateActivity : Activity
    interface Updater{
        fun update()
        fun error()
    }
    @Throws(Exception::class)
    fun requestData(date: String, context: Context){
        val exchangeRateResponse = api.getExchangeRates(date)
        rateActivity = context as Activity
        exchangeRateResponse.enqueue(object : Callback<ExchangeRateResponse> {
            override fun onResponse(call: Call<ExchangeRateResponse>, response: Response<ExchangeRateResponse>) {
                if (response.isSuccessful) {
                    val rateResponse = ArrayList(response.body()!!.rateList)
                    callback(rateResponse, date)
                    (rateActivity as Updater).update()
                }
            }

            override fun onFailure(call: Call<ExchangeRateResponse>, t: Throwable) {
                (rateActivity as Updater).error()
            }
        })
    }
}