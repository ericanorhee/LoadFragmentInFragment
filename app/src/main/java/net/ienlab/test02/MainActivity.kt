package net.ienlab.test02

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    MainFragment01.OnFragmentInteractionListener, MainFragment02.OnFragmentInteractionListener,
    MainFragment03.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        loadFragment(MainFragment01())
        navView.setOnNavigationItemSelectedListener(this)

        LocalBroadcastManager.getInstance(applicationContext).registerReceiver(object: BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                loadFragment(MainFragment03())
            }
        }, IntentFilter("PAGE3"))
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment = MainFragment01()
        when (item.itemId) {
            R.id.navigation_1 -> {
                fragment = MainFragment01()
            }
            R.id.navigation_2 -> {
                fragment = MainFragment02()
            }
        }
        return loadFragment(fragment)
    }

    fun loadFragment(fragment: Fragment?): Boolean {
        //switching fragment
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            return true
        }
        return false
    }

    override fun onFragmentInteraction(uri: Uri) {}

}
