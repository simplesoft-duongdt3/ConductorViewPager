package com.tinyapps.lib.conductorviewpager.endless

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.tinyapps.lib.conductorviewpager.ViewControllerPageFactory
import com.tinyapps.lib.conductorviewpager.ViewControllerPagerAdapter

class EndlessViewControllerPagerAdapter(host: Controller, pageFactory: ViewControllerPageFactory<*>) : ViewControllerPagerAdapter(host, pageFactory) {
    override fun getCount(): Int {
        val count = super.getCount()
        return EndlessPagerHelper.getEndlessCount(count)
    }

    override fun configureRouter(router: Router, position: Int) {
        val endlessPosition = getEndlessPosition(position)
        super.configureRouter(router, endlessPosition)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val realCount = super.getCount()
        val endlessPosition = getEndlessPosition(position)
        return if (EndlessPagerHelper.isRealPage(realCount, position)) {
            super.getPageTitle(endlessPosition)
        } else {
            null
        }
    }

    private fun getEndlessPosition(pagePosition: Int) : Int {
        val realCount = super.getCount()

        return EndlessPagerHelper.getEndlessPosition(
            realCount = realCount,
            pagePosition = pagePosition
        )
    }
}