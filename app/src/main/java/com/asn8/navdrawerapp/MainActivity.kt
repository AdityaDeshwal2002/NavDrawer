package com.asn8.navdrawerapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    private lateinit var textAcvityMain : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        textAcvityMain = findViewById(R.id.mainActivitytv)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        drawerLayout = findViewById(R.id.main)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setHomeButtonEnabled(true)
        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener {
            it.isChecked = true
            when (it.itemId) {
                R.id.home_view -> {
                    replaceFragment(HomeFragment(), it.title.toString())
                }

                R.id.message_view -> {
                    replaceFragment(MessageFragment(), it.title.toString())
                }

                R.id.settings_view -> {
                    replaceFragment(SettingFragment(), it.title.toString())
                }

                R.id.login -> {
                    replaceFragment(LoginFragment(), it.title.toString())
                }

            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentmanager = supportFragmentManager
        val fragTrans = fragmentmanager.beginTransaction()
        fragTrans.replace(R.id.NavigationDrawerFL, fragment)
        textAcvityMain.visibility = View.GONE
        fragTrans.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

}

