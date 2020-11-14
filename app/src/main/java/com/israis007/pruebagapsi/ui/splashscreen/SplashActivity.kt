package com.israis007.pruebagapsi.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.israis007.pruebagapsi.R
import com.israis007.pruebagapsi.ui.market.ShopActivity
import kotlinx.android.synthetic.main.activity_main.*

class SplashActivity : AppCompatActivity() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        viewModel.startAnimation(splashscreen_lottie)
        viewModel.launchNext.observe(this, Observer {
            val value = it ?: return@Observer
            if (value){
                startActivity(Intent(this@SplashActivity, ShopActivity::class.java))
                finish()
            }
        })
    }
}