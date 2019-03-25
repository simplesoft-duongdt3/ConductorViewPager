package com.tinyapps.conductorviewpager

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.tinyapps.conductorviewpager.models.ViewPagerItem
import com.tinyapps.lib.conductorviewpager.ViewControllerPageFactory
import com.tinyapps.lib.conductorviewpager.endless.EndlessTabLayoutSetupHelper
import com.tinyapps.lib.conductorviewpager.endless.EndlessViewControllerPagerAdapter
import kotlinx.android.synthetic.main.demo_pager_endless.view.*

class DemoEndlessViewPagerController : Controller(null) {
    private val endlessTabLayoutSetupHelper = EndlessTabLayoutSetupHelper()
    private val itemsFull = listOf(
        ViewPagerItem(color = Color.RED, title = "Red"),
        ViewPagerItem(color = Color.GREEN, title = "Green"),
        ViewPagerItem(color = Color.YELLOW, title = "Yellow"),
        ViewPagerItem(color = Color.GRAY, title = "Gray"),
        ViewPagerItem(color = Color.CYAN, title = "Cyan")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.demo_pager_endless, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        setUpViewPager(view, itemsFull)
        initClickEvent(view)
    }

    private fun setUpViewPager(view: View, items: List<ViewPagerItem>) {
        val pageFactory = createItemFactory(items)
        val pagerAdapter = EndlessViewControllerPagerAdapter(this, pageFactory = pageFactory)
        view.view_pager.adapter = pagerAdapter
        endlessTabLayoutSetupHelper.setUpTabLayoutWithEndlessViewPager(
            viewPager = view.view_pager,
            tabLayout = view.tab_layout,
            items = items,
            viewControllerFactory = pageFactory
        )
    }

    private fun initClickEvent(view: View) {
        view.bt1Items.setOnClickListener {
            setUpViewPager(view, itemsFull.subList(0, 1))
        }
        view.bt2Items.setOnClickListener {
            setUpViewPager(view, itemsFull.subList(0, 2))
        }
        view.bt5Items.setOnClickListener {
            setUpViewPager(view, itemsFull)
        }
    }


    private fun createItemFactory(items: List<ViewPagerItem>): ViewControllerPageFactory<ViewPagerItem> {
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