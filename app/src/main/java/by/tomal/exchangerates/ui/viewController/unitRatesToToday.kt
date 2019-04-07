package by.tomal.exchangerates.ui.viewController

import by.tomal.exchangerates.presenter.entity.ExchangeRateViewList

fun unitRatesToToday() {
    if (!ExchangeRateViewList.listTomorrow.isNullOrEmpty()) {
        for (ratesToday in ExchangeRateViewList.listToday) {
            for (ratesTomorrow in ExchangeRateViewList.listTomorrow) {
                if (ratesToday.numCode == ratesTomorrow.numCode) {
                    ratesToday.rateTomorrow = ratesTomorrow.rateTomorrow
                    ratesToday.dateTomorrow = ratesTomorrow.dateTomorrow
                }
            }
        }
    }
}