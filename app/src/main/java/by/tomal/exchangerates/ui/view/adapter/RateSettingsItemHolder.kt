package by.tomal.exchangerates.ui.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import by.tomal.exchangerates.presenter.entity.ExchangeRateView
import kotlinx.android.synthetic.main.exchange_rate_charcode_card_item.view.*

class RateSettingsItemHolder (view: View) : RecyclerView.ViewHolder(view) {
    fun bind(exchangeRateView: ExchangeRateView) {
        itemView.charCode.text = exchangeRateView.charCode
        itemView.scale.text = concatScaleToName(exchangeRateView.scale, exchangeRateView.name)
    }

    fun concatScaleToName(scale: String, name: String): String {
        return "$scale $name"
    }
}