package by.tomal.exchangerates.data.service


import android.util.Log
import by.tomal.exchangerates.data.entity.ExchangeRateItemResponse
import by.tomal.exchangerates.presenter.entity.ExchangeRateView
import by.tomal.exchangerates.presenter.entity.ExchangeRateViewList
import java.util.ArrayList


fun callback(exchangeRateViewDate: ArrayList<ExchangeRateItemResponse>, date: String) {
    val exchangeRateView = arrayListOf<ExchangeRateView>()

    if (!exchangeRateViewDate.isNullOrEmpty() && ExchangeRateViewList.listToday.isNullOrEmpty()) {
        for (rates in exchangeRateViewDate)
            exchangeRateView.add(
                ExchangeRateView(
                    rates.numCode,
                    rates.charCode,
                    rates.scale,
                    rates.name,
                    rates.rate,
                    "",
                    date,
                    ""
                )
            )
        ExchangeRateViewList.listToday = exchangeRateView
    } else if (!exchangeRateViewDate.isNullOrEmpty() && ExchangeRateViewList.listTomorrow.isNullOrEmpty()) {
        for (rates in exchangeRateViewDate)
            exchangeRateView.add(
                ExchangeRateView(
                    rates.numCode,
                    rates.charCode,
                    rates.scale,
                    rates.name,
                    "",
                    rates.rate,
                    "",
                    date
                )
            )
        ExchangeRateViewList.listTomorrow = exchangeRateView
    }

}
