package com.tinyapps.conductorviewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.RouterTransaction
import kotlinx.android.synthetic.main.demo_activity.*

class DemoEndlessViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo_activity)
        val router = Conductor.attachRouter(this, vgRoot, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(DemoEndlessViewPagerController()))
        }
    }
}