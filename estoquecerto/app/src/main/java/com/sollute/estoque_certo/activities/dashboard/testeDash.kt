package com.sollute.estoque_certo.activities.dashboard

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.sollute.estoque_certo.R

class testeDash : AppCompatActivity() {

    private var chart: AnyChartView? = null

    private val salary = listOf(200, 300, 400, 500)
    private val month = listOf("janeiro", "fevereiro", "março", "abril")

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.teste_dash)

        chart = findViewById(R.id.any_chart_view)

        configChartView()
    }

    private fun configChartView() {
        val pie: Pie = AnyChart.pie()

        val dataPieChart: MutableList<DataEntry> = mutableListOf()

        for (index in salary.indices) {
            dataPieChart.add(ValueDataEntry(month.elementAt(index), salary.elementAt(index)))
        }

        pie.data(dataPieChart)
        pie.title("Salario")
        chart!!.setChart(pie)
    }


}
