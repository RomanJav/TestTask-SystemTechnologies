package by.tomal.exchangerates.ui.view.adapter


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import by.tomal.exchangerates.R
import by.tomal.exchangerates.presenter.entity.ExchangeRateViewList.listToday

class RateListAdapter : RecyclerView.Adapter<RateListHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RateListHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.exchange_rate_card_item, viewGroup, false)
        return RateListHolder(view)
    }

    override fun getItemCount(): Int {
        return listToday.size
    }

    override fun onBindViewHolder(holder: RateListHolder, position: Int) {
        holder.bind(listToday[position])
    }

}