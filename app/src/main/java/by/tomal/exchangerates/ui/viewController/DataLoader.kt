package by.tomal.exchangerates.ui.viewController

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import by.tomal.exchangerates.presenter.entity.ExchangeRateViewList.listToday
import by.tomal.exchangerates.presenter.entity.ExchangeRateViewList.listTomorrow
import by.tomal.exchangerates.presenter.usecase.ExchangeRateUsecaseDefault
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
object DataLoader {
    private val dateFormat = DateTimeFormatter.ofPattern("MM.dd.yyyy")
    private val dateToday = LocalDate.now()
    private val dateTomorrow = dateToday.plusDays(1)
    private val dateYesterday = dateToday.plusDays(-1)
    private val data = ExchangeRateUsecaseDefault()

    fun loadData(context: Context) {
        GlobalScope.launch {
            data.get(dateToday.format(dateFormat), context)
            while (listToday.isNullOrEmpty())
                delay(2000)
        }
    }

    fun loadDataTomorrow(context: Context) {
        GlobalScope.launch {
            data.get(dateTomorrow.format(dateFormat), context)
            while (listTomorrow.isNullOrEmpty())
                delay(1000)
        }
    }

    fun loadDataYesterday(context: Context) {
        GlobalScope.launch {
            data.get(dateYesterday.format(dateFormat), context)
            while (listTomorrow.isNullOrEmpty())
                delay(1000)
        }
    }
}