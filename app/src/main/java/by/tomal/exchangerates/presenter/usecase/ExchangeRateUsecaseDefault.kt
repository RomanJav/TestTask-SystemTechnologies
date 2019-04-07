package by.tomal.exchangerates.presenter.usecase


import android.content.Context
import by.tomal.exchangerates.data.service.ExchangeRateService

class ExchangeRateUsecaseDefault : ExchangeRateUsecase {

    override fun get(date: String, context: Context){
        ExchangeRateService().requestData(date, context)
    }
}