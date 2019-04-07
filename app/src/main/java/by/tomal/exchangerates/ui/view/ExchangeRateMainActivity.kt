package by.tomal.exchangerates.ui.view

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import by.tomal.exchangerates.R
import by.tomal.exchangerates.data.service.ExchangeRateService
import by.tomal.exchangerates.presenter.entity.ExchangeRateViewList
import by.tomal.exchangerates.ui.view.adapter.RateListAdapter
import by.tomal.exchangerates.ui.viewController.loadData
import by.tomal.exchangerates.ui.viewController.unitRatesToToday
import kotlinx.android.synthetic.main.activity_main.*


class ExchangeRateMainActivity : AppCompatActivity(), ExchangeRateService.Updater {

    private var adapter = RateListAdapter()
    private lateinit var menuItem : MenuItem

    companion object {
        private val SETTINGS_ACTIVITY = ExchangeRateSettingsActivivty::class.java
        private fun createIntent(context: Context, classActivity: Class<*>): Intent {
            return Intent(context, classActivity)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menuItem = menu!!.findItem(R.id.settings)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.settings)
            startActivity(createIntent(this, SETTINGS_ACTIVITY))
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarMain)
        initRecyclerView()
        initAdapter()
        loadData(this)
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
    private fun initRecyclerView() {
        exchangeRateRecyclerView.setHasFixedSize(true)
        exchangeRateRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initAdapter() {
        exchangeRateRecyclerView.adapter = adapter
    }
    private fun initDateBar(){
        if(!ExchangeRateViewList.listToday.isNullOrEmpty())
            dateToday.text = ExchangeRateViewList.listToday[0].dateToday
        if(!ExchangeRateViewList.listTomorrow.isNullOrEmpty())
            dateTomorrow.text = ExchangeRateViewList.listTomorrow[0].dateTomorrow
    }

    override fun update() {
        unitRatesToToday()
        initDateBar()
        adapter.notifyDataSetChanged()
    }

    override fun error() {
        errorText.text = resources.getString(R.string.exchange_error)
        menuItem.isVisible = false
    }

}