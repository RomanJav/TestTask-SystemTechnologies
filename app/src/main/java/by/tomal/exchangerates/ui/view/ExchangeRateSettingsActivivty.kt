package by.tomal.exchangerates.ui.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import by.tomal.exchangerates.R
import by.tomal.exchangerates.presenter.entity.ExchangeRateSettingList.FILE_NAME
import by.tomal.exchangerates.presenter.entity.ExchangeRateViewList
import by.tomal.exchangerates.ui.view.adapter.RateSettingsAdapter
import kotlinx.android.synthetic.main.activity_settings.*
import java.io.File


class ExchangeRateSettingsActivivty : AppCompatActivity() {

    private var adapter = RateSettingsAdapter()
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    //Сохранение настроек
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.submit){
            File(applicationContext.filesDir, FILE_NAME).createNewFile()
            File(applicationContext.filesDir, FILE_NAME).printWriter().use { out ->
                for(rates in ExchangeRateViewList.listToday) {
                    out.println(rates.charCode)
                }
            }
            onBackPressed()
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbarSettings)
        initRecyclerView()
        initAdapter()
        initItemTouch()
    }

    private fun initRecyclerView() {
        exchangeRateSettingsRecyclerView.setHasFixedSize(true)
        exchangeRateSettingsRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initAdapter() {
        exchangeRateSettingsRecyclerView.adapter = adapter
    }

    //Перетаскивание элементов RecyclerView
    private fun initItemTouch() {
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,0
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                holder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPos = holder.adapterPosition
                val toPos = target.adapterPosition
                adapter.move(fromPos, toPos)
                return true
            }
            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
            }
        }
        itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(exchangeRateSettingsRecyclerView)
    }
}
