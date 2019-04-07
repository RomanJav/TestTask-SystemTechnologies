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
fun loadData(context: Context) {
    val dateFormat = DateTimeFormatter.ofPattern("MM.dd.yyyy")
    val dateToday = LocalDate.now()
    val dateTomorrow = dateToday.plusDays(1)
    val data = ExchangeRateUsecaseDefault()

    GlobalScope.launch {
        data.get(dateToday.format(dateFormat), context)
        while(listToday.isNullOrEmpty())
            delay(2000)
        data.get(dateTomorrow.format(dateFormat), context)
        while(listTomorrow.isNullOrEmpty())
            delay(1000)
    }
}
