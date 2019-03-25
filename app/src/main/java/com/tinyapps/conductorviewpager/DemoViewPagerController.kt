package com.tinyapps.conductorviewpager

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.tinyapps.conductorviewpager.models.ViewPagerItem
import com.tinyapps.lib.conductorviewpager.ViewControllerPagerAdapter
import com.tinyapps.lib.conductorviewpager.ViewControllerPageFactory
import kotlinx.android.synthetic.main.demo_pager.view.*

class DemoViewPagerController : Controller(null) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.demo_pager, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        val pageFactory = createItemFactory()
        val pagerAdapter = ViewControllerPagerAdapter(this, pageFactory = pageFactory)
        pagerAdapter.notifyDataSetChanged()
        view.view_pager.adapter = pagerAdapter
        view.tab_layout.setupWithViewPager(view.view_pager)
    }

    private fun createItemFactory() : ViewControllerPageFactory<*> {
        val itemRed = ViewPagerItem(color = Color.RED, title = "Red")
        val itemGreen = ViewPagerItem(color = Color.GREEN, title = "Green")
        val itemYellow = ViewPagerItem(color = Color.YELLOW, title = "Yellow")
        val items = listOf(itemRed, itemGreen, itemYellow)

        return object : ViewControllerPageFactory<ViewPagerItem>(items) {
            override fun createPage(item: ViewPagerItem): Controller {
                val bundle = Bundle().apply {
                    putInt("color", item.color)
                }

                return DemoPageController(bundle = bundle)
            }

            override fun getPageTitle(item: ViewPagerItem): CharSequence? {
                return item.title
            }
        }
    }

}