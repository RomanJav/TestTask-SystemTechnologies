package by.tomal.exchangerates.data.net

import by.tomal.exchangerates.data.entity.ExchangeRateResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//Интерфейс получения данных с адреса используя Retrofit
interface GetData {
    @GET("Services/XmlExRates.aspx")
    fun getExchangeRates(
        @Query("ondate") date: String
    ): Call<ExchangeRateResponse>
}