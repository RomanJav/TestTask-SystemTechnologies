package by.tomal.exchangerates.data.entity


import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
//Парсер для корня xml
@Root(strict = false, name = "DailyExRates")
class ExchangeRateResponse {
    @field:ElementList(name = "Currency", inline = true)
    var rateList= ArrayList<ExchangeRateItemResponse>()

    @field:Attribute(name = "Date")
    var date: String = ""
}