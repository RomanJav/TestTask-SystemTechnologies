package by.tomal.exchangerates.ui.view

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import by.tomal.exchangerates.R
import by.tomal.exchangerates.data.service.ExchangeRateService
import by.tomal.exchangerates.presenter.entity.ExchangeRateSettingList.FILE_NAME
import by.tomal.exchangerates.presenter.entity.ExchangeRateSettingList.listCharCodeSettings
import by.tomal.exchangerates.presenter.entity.ExchangeRateViewList
import by.tomal.exchangerates.presenter.entity.ExchangeRateViewList.listToday
import by.tomal.exchangerates.presenter.entity.ExchangeRateViewList.listTomorrow
import by.tomal.exchangerates.ui.view.adapter.RateListAdapter
import by.tomal.exchangerates.ui.viewController.DataLoader
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


@TargetApi(Build.VERSION_CODES.O)
class ExchangeRateMainActivity : AppCompatActivity(), ExchangeRateService.Updater {

    private var adapter = RateListAdapter()
    private lateinit var menuItem: MenuItem

    companion object {
        private val SETTINGS_ACTIVITY = ExchangeRateSettingsActivivty::class.java
        private fun createIntent(context: Context, classActivity: Class<*>): Intent {
            return Intent(context, classActivity)
        }
    }

    //Создание меню Активити
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menuItem = menu!!.findItem(R.id.settings)
        return true
    }
    //При нажатии на шестеренку переход к странице настроек
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.settings)
            startActivity(createIntent(this, SETTINGS_ACTIVITY))
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarMain)
        initSettings()
        initRecyclerView()
        initAdapter()
        DataLoader.loadData(this)
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
    //Инициализация настроек отображения валют, если же файла настроек еще нет инициализирует файл по умолчанию
    private fun initSettings() {
        if (File(applicationContext.filesDir, FILE_NAME).exists()) {
            listCharCodeSettings = File(applicationContext.filesDir, FILE_NAME).readLines()
        } else {
            File(applicationContext.filesDir, FILE_NAME).createNewFile()
            File(applicationContext.filesDir, FILE_NAME).printWriter().use { out ->
                out.println("USD")
                out.println("EUR")
                out.println("RUB")
            }
        }
    }

    private fun initRecyclerView() {
        exchangeRateRecyclerView.setHasFixedSize(true)
        exchangeRateRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initAdapter() {
        exchangeRateRecyclerView.adapter = adapter
    }
    //Отображение даты при загрузке файлов
    private fun initDateBar() {
        if (!listToday.isNullOrEmpty())
            dateToday.text = listToday[0].dateToday
        if (!listTomorrow.isNullOrEmpty())
            dateTomorrow.text = listTomorrow[0].dateTomorrow
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

    fun unitRatesToToday() {
        if (!ExchangeRateViewList.listTomorrow.isNullOrEmpty()) {
            for (ratesToday in ExchangeRateViewList.listToday) {
                for (ratesTomorrow in ExchangeRateViewList.listTomorrow) {
                    if (ratesToday.numCode == ratesTomorrow.numCode) {
                        ratesToday.rateTomorrow = ratesTomorrow.rateTomorrow
                        ratesToday.dateTomorrow = ratesTomorrow.dateTomorrow
                    }
                }
            }
        }
    }
}