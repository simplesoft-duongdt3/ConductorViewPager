package com.tinyapps.lib.conductorviewpager.endless

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.tinyapps.lib.conductorviewpager.ViewControllerPageFactory
import com.tinyapps.lib.conductorviewpager.ViewControllerViewPager

class EndlessTabLayoutSetupHelper() {
    private var tabChangeListener: TabLayout.BaseOnTabSelectedListener<TabLayout.Tab>? = null
    private var onViewPagerChangeListener: ViewPager.OnPageChangeListener? = null
    private var onAdapterChangeListener: ViewPager.OnAdapterChangeListener? = null

    fun <T> setUpTabLayoutWithEndlessViewPager(
        viewPager: ViewControllerViewPager,
        tabLayout: TabLayout,
        items: List<T>,
        viewControllerFactory: ViewControllerPageFactory<T>,
        isSmoothScrollWhenTabClick: Boolean = false
    ) {
        tabChangeListener?.let { listener ->
            tabLayout.removeOnTabSelectedListener(listener)
        }
        onViewPagerChangeListener?.let { listener ->
            viewPager.removeOnPageChangeListener(listener)
        }
        onAdapterChangeListener?.let { listener ->
            viewPager.removeOnAdapterChangeListener(listener)
        }

        tabLayout.removeAllTabs()
        items.forEach { item ->
            val tab = tabLayout.newTab()
            tab.text = viewControllerFactory.getPageTitle(item)
            tabLayout.addTab(tab)
        }

        tabChangeListener = object : TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                //nothing to do now
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //nothing to do now
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    val endlessTabLayoutPosition =
                        EndlessPagerHelper.getEndlessTabLayoutPosition(items.size, tab.position)
                    viewPager.setCurrentItem(endlessTabLayoutPosition, isSmoothScrollWhenTabClick)
                }
            }
        }

        tabChangeListener?.let { listener ->
            tabLayout.addOnTabSelectedListener(listener)
        }

        onViewPagerChangeListener = object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                //nothing to do now
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                //nothing to do now
            }

            override fun onPageSelected(position: Int) {
                val realTabLayoutPosition = EndlessPagerHelper.getRealTabLayoutPosition(items.size, position)
                if (realTabLayoutPosition >= 0 && realTabLayoutPosition <= tabLayout.tabCount) {
                    tabLayout.getTabAt(realTabLayoutPosition)?.select()
                }
            }
        }
        onViewPagerChangeListener?.let { listener ->
            viewPager.addOnPageChangeListener(listener)
        }

        onAdapterChangeListener =
            ViewPager.OnAdapterChangeListener { _, _, _ ->
                setUpTabLayoutWithEndlessViewPager(
                    viewPager = viewPager,
                    items = items,
                    tabLayout = tabLayout,
                    viewControllerFactory = viewControllerFactory
                )
            }

        onAdapterChangeListener?.let { listener ->
            viewPager.addOnAdapterChangeListener(listener)
        }

    }
}