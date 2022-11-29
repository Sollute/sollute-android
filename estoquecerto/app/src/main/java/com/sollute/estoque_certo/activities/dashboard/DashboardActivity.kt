package com.sollute.estoque_certo.activities.dashboard;


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.sollute.estoque_certo.DrawerBaseActivity
import com.sollute.estoque_certo.R
import com.sollute.estoque_certo.databinding.ActivityDashboardBinding

class DashboardActivity : DrawerBaseActivity() {

    lateinit var barChart: BarChart
    lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }

        barChart = findViewById(R.id.bar_chart)

        val list1: ArrayList<BarEntry> = arrayListOf(BarEntry(100f, 150f))
        val list2: ArrayList<BarEntry> = arrayListOf(BarEntry(101f, 120f))
        val list3: ArrayList<BarEntry> = arrayListOf(BarEntry(102f, 100f))
        val list4: ArrayList<BarEntry> = arrayListOf(BarEntry(103f, 80f))
        val list5: ArrayList<BarEntry> = arrayListOf(BarEntry(104f, 80f))

        val barDataSet1 = BarDataSet(list1, "Produto 1")
        val barDataSet2 = BarDataSet(list2, "Produto 2")
        val barDataSet3 = BarDataSet(list3, "Produto 3")
        val barDataSet4 = BarDataSet(list4, "Produto 4")
        val barDataSet5 = BarDataSet(list5, "Produto 5")

        barDataSet1.setColors(ColorTemplate.rgb("#daa520"), 255)
        barDataSet1.valueTextColor = Color.BLACK
        barDataSet2.setColors(ColorTemplate.rgb("#c0c0c0"), 255)
        barDataSet2.valueTextColor = Color.BLACK
        barDataSet3.setColors(ColorTemplate.rgb("#cd7f32"), 255)
        barDataSet3.valueTextColor = Color.BLACK
        barDataSet4.setColors(ColorTemplate.rgb("#56c2de"), 255)
        barDataSet4.valueTextColor = Color.BLACK
        barDataSet5.setColors(ColorTemplate.rgb("#de7256"), 255)
        barDataSet5.valueTextColor = Color.BLACK

        val barData1 = BarData(barDataSet1, barDataSet2, barDataSet3, barDataSet4, barDataSet5)

        barChart.setFitBars(false)
        barChart.data = barData1
        barChart.animateY(2000)

    }


    inner class Formatter(
        private var mValues: ArrayList<String>
    ) : IndexAxisValueFormatter() {

        fun Formatter(values: ArrayList<String>) {
            this.mValues = values
        }

        override fun getFormattedValue(value: Float): String {
            return super.getFormattedValue(value)
        }

    }
}