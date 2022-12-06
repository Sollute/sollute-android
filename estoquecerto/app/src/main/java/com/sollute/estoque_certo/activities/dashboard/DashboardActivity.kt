package com.sollute.estoque_certo.activities.dashboard;

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.sollute.estoque_certo.R
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.activities.product.NewProductFirstActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
import com.sollute.estoque_certo.activities.sale.AddSaleActivity
import com.sollute.estoque_certo.activities.user.UserActivity
import com.sollute.estoque_certo.databinding.ActivityDashboardBinding
import com.sollute.estoque_certo.models.dashboard.ListTop
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.dashboard.Dashboard
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : DrawerBaseActivity() {

    lateinit var barChart: BarChart
    lateinit var binding: ActivityDashboardBinding
    private val httpClient: Dashboard = Rest.getInstance().create(Dashboard::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)
        getList(idEmpresa)

        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }

        binding.tvSell.setOnClickListener {
            startActivity(Intent(this, AddSaleActivity::class.java))
        }
        binding.tvUser.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }
        binding.tvProduct.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }

        barChart = findViewById(R.id.bar_chart)
    }

    private fun getList(idEmpresa: Int) {
        httpClient.listOrder(idEmpresa).enqueue(object : Callback<List<ListTop>> {
            override fun onFailure(call: Call<List<ListTop>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<ListTop>>, response: Response<List<ListTop>>) {
                if (response.body()!!.isEmpty()) {
                    val list1: ArrayList<BarEntry> = arrayListOf(BarEntry(100f, 150f))
                    val list2: ArrayList<BarEntry> = arrayListOf(BarEntry(101f, 120f))
                    val list3: ArrayList<BarEntry> = arrayListOf(BarEntry(102f, 100f))
                    val list4: ArrayList<BarEntry> = arrayListOf(BarEntry(103f, 80f))
                    val list5: ArrayList<BarEntry> = arrayListOf(BarEntry(104f, 80f))

                    val barDataSet1 = BarDataSet(list1, "Exemplo 1")
                    val barDataSet2 = BarDataSet(list2, "Exemplo 2")
                    val barDataSet3 = BarDataSet(list3, "Exemplo 3")
                    val barDataSet4 = BarDataSet(list4, "Exemplo 4")
                    val barDataSet5 = BarDataSet(list5, "Exemplo 5")

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

                    val barData1 = BarData(barDataSet1, barDataSet2, barDataSet3)

                    barChart.setFitBars(false)
                    barChart.data = barData1
                    barChart.animateY(2000)

                } else {
                    val list1: ArrayList<BarEntry> =
                        arrayListOf(BarEntry(100f, response.body()!![0].qtdVendidos.toFloat()))
                    val list2: ArrayList<BarEntry> =
                        arrayListOf(BarEntry(101f, response.body()!![1].qtdVendidos.toFloat()))
                    val list3: ArrayList<BarEntry> =
                        arrayListOf(BarEntry(102f, response.body()!![2].qtdVendidos.toFloat()))
                    val list4: ArrayList<BarEntry> =
                        arrayListOf(BarEntry(103f, response.body()!![3].qtdVendidos.toFloat()))
                    val list5: ArrayList<BarEntry> =
                        arrayListOf(BarEntry(104f, response.body()!![4].qtdVendidos.toFloat()))

                    val barDataSet1 = BarDataSet(list1, response.body()!![0].nome)
                    val barDataSet2 = BarDataSet(list2, response.body()!![1].nome)
                    val barDataSet3 = BarDataSet(list3, response.body()!![2].nome)
                    val barDataSet4 = BarDataSet(list4, response.body()!![3].nome)
                    val barDataSet5 = BarDataSet(list5, response.body()!![4].nome)

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

                    val barData1 = BarData(barDataSet1, barDataSet2, barDataSet3)

                    barChart.setFitBars(false)
                    barChart.data = barData1
                    barChart.animateY(3000)
                }
            }
        })
    }
}