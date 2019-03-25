package com.tinyapps.conductorviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import kotlinx.android.synthetic.main.demo_page.view.*

class DemoPageController(bundle: Bundle) : Controller(bundle) {
    private val bgColor = bundle.getInt("color")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.demo_page, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        view.vgRoot.setBackgroundColor(bgColor)
    }


}