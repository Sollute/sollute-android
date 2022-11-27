package com.sollute.estoque_certo

import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.sollute.estoque_certo.activities.client.ClientActivity
import com.sollute.estoque_certo.activities.dashboard.DashboardActivity
import com.sollute.estoque_certo.activities.employee.EmployeeActivity
import com.sollute.estoque_certo.activities.extract.ExtractActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
import com.sollute.estoque_certo.activities.provider.ProviderActivity
import com.sollute.estoque_certo.activities.user.UserActivity

open class DrawerBaseActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    private var idEmpresa = 1

    override fun setContentView(view: View?) {
        drawerLayout = layoutInflater.inflate(R.layout.activity_drawer_base, null) as DrawerLayout
        val container: FrameLayout = drawerLayout.findViewById(R.id.activityContainer)
        container.addView(view)
        super.setContentView(drawerLayout)

        val toolbar: Toolbar = drawerLayout.findViewById(R.id.toolBar)
        setSupportActionBar(toolbar)

        val navigationView: NavigationView = drawerLayout.findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.menu_drawer_open,
            R.string.menu_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        idEmpresa = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)

        when (item.itemId) {
            R.id.nav_user -> {
                startActivity(Intent(this, UserActivity::class.java))
                overridePendingTransition(0, 0)
                return true
            }
            R.id.nav_products -> {
                startActivity(Intent(this, ProductActivity::class.java))
                overridePendingTransition(0, 0)
                return true
            }
            R.id.nav_dashboard -> {
                startActivity(Intent(this, DashboardActivity::class.java))
                overridePendingTransition(0, 0)
                return true
            }
            R.id.nav_client -> {
                startActivity(Intent(this, ClientActivity::class.java))
                overridePendingTransition(0, 0)
                return true
            }
            R.id.nav_provider -> {
                startActivity(Intent(this, ProviderActivity::class.java))
                overridePendingTransition(0, 0)
                return true
            }
            R.id.nav_employee -> {
                startActivity(Intent(this, EmployeeActivity::class.java))
                overridePendingTransition(0, 0)
                return true
            }
            R.id.nav_extract -> {
                startActivity(Intent(this, ExtractActivity::class.java))
                overridePendingTransition(0, 0)
                return true
            }
            R.id.nav_logout -> {
                return true
            }
        }
        return false
    }

    protected fun allocateActivityTitle(title: String) {
        if (supportActionBar != null) supportActionBar!!.title = title
    }
}
