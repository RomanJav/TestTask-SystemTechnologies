package by.tomal.exchangerates.presenter.entity

class ExchangeRateView (
    val numCode: String,
    val charCode: String,
    val scale: String,
    val name: String,
    var rateToday: String,
    var rateTomorrow: String,
    var dateToday: String,
    var dateTomorrow: String
)