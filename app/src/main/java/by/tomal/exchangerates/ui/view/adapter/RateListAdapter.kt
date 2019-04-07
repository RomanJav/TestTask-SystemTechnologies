package by.tomal.exchangerates.ui.view.adapter


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import by.tomal.exchangerates.R
import by.tomal.exchangerates.presenter.entity.ExchangeRateViewList

class RateListAdapter : RecyclerView.Adapter<RateListSettingsHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RateListSettingsHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.exchange_rate_card_item, viewGroup, false)
        return RateListSettingsHolder(view)
    }

    override fun getItemCount(): Int {
        return ExchangeRateViewList.listToday.size
    }

    override fun onBindViewHolder(holder: RateListSettingsHolder, position: Int) {
        holder.bind(ExchangeRateViewList.listToday[position])
    }

}