package by.tomal.exchangerates.presenter.usecase


import android.content.Context
import by.tomal.exchangerates.data.service.ExchangeRateService

class ExchangeRateUsecaseDefault : ExchangeRateUsecase {

    //Немного разграничил ответсветсвенность, для возможности обработки и других данных
    override fun get(date: String, context: Context){
        ExchangeRateService().requestData(date, context)
    }
}