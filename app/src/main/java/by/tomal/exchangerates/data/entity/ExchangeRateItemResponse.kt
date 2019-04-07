package by.tomal.exchangerates.data.entity

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

//Парсер для данных в Currency xml
@Root(strict = false, name = "Currency")
class ExchangeRateItemResponse {
    @field:Element(name = "NumCode")
    var numCode: String = ""

    @field:Element(name = "CharCode")
    var charCode: String = ""

    @field:Element(name = "Scale")
    var scale: String = ""

    @field:Element(name = "Name")
    var name: String = ""

    @field:Element(name = "Rate")
    var rate: String = ""

    @field:Attribute(name = "Id")
    var id: String = ""
}
