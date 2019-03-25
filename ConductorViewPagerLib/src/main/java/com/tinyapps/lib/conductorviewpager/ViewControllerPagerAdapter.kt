package com.tinyapps.lib.conductorviewpager

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.support.RouterPagerAdapter
import com.tinyapps.lib.conductorviewpager.ViewControllerPageFactory

open class ViewControllerPagerAdapter(host: Controller, private val pageFactory: ViewControllerPageFactory<*>) : RouterPagerAdapter(host) {

    override fun configureRouter(router: Router, position: Int) {
        pageFactory.createPage(router, position)
    }

    override fun getCount(): Int {
        return pageFactory.getItemCount()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pageFactory.getPageTitle(position)
    }
}
