package com.israis007.pruebagapsi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.israis007.pruebagapsi.R
import com.israis007.pruebagapsi.ui.ui.main.ShopFragment

class ShopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ShopFragment.newInstance())
                .commitNow()
        }
    }
}