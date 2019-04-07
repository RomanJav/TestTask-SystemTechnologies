package by.tomal.exchangerates.data.service

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.os.Build
import by.tomal.exchangerates.data.net.providerApi
import by.tomal.exchangerates.data.entity.ExchangeRateResponse
import by.tomal.exchangerates.ui.viewController.DataLoader.loadDataYesterday
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

//Сервис получения данных с запроса
@TargetApi(Build.VERSION_CODES.O)
class ExchangeRateService {

    private val api = providerApi()
    private lateinit var rateActivity: Activity
    val dateFormat = DateTimeFormatter.ofPattern("MM.dd.yyyy")
    val dateTomorrow = LocalDate.now().plusDays(1)

    //Интерфейс для обратного вызова в Активити
    interface Updater {
        fun update()
        fun error()
    }

    @Throws(Exception::class)
    fun requestData(date: String, context: Context) {
        val exchangeRateResponse = api.getExchangeRates(date)
        rateActivity = context as Activity
        exchangeRateResponse.enqueue(object : Callback<ExchangeRateResponse> {
            override fun onResponse(call: Call<ExchangeRateResponse>, response: Response<ExchangeRateResponse>) {
                if (response.isSuccessful) {
                    val rateResponse = ArrayList(response.body()!!.rateList)
                    callback(rateResponse, date) //Обработка и сохранение данных в глобальных листах
                    (rateActivity as Updater).update() //Вызов update в Main
                }
            }

            override fun onFailure(call: Call<ExchangeRateResponse>, t: Throwable) {
                if (dateTomorrow.format(dateFormat) == date) //Проверка, если ошибка с завтрашней датой проверить доступна ли вчерашняя
                    loadDataYesterday(context)
                else
                    (rateActivity as Updater).error() //Вызов error в Main
            }
        })
    }
}