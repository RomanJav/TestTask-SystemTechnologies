package by.tomal.exchangerates.presenter.usecase

import android.content.Context


interface ExchangeRateUsecase {
    fun get(date: String, context: Context)
}