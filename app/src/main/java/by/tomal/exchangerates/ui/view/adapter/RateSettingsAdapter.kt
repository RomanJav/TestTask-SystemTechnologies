package by.tomal.exchangerates.ui.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import by.tomal.exchangerates.R
import by.tomal.exchangerates.presenter.entity.ExchangeRateViewList
import java.util.*



class RateSettingsAdapter : RecyclerView.Adapter<RateSettingsItemHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RateSettingsItemHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.exchange_rate_charcode_card_item, viewGroup, false)
        return RateSettingsItemHolder(view)
    }

    override fun getItemCount(): Int {
        return ExchangeRateViewList.listToday.size
    }

    override fun onBindViewHolder(holder: RateSettingsItemHolder, position: Int) {
        holder.bind(ExchangeRateViewList.listToday[position])
    }

    fun move(fromPos: Int, toPos: Int) {
        if (fromPos < toPos) {
            for (i in fromPos until toPos) {
                Collections.swap(ExchangeRateViewList.listToday, i, i + 1)
            }
        } else {
            for (i in fromPos downTo toPos + 1) {
                Collections.swap(ExchangeRateViewList.listToday, i, i - 1)
            }
        }
        notifyItemMoved(fromPos, toPos)
    }

}