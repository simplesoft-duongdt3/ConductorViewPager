package com.tinyapps.lib.conductorviewpager

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction

abstract class  ViewControllerPageFactory<T>(private val items: List<T>) {
    abstract fun createPage(item: T) : Controller
    abstract fun getPageTitle(item: T) : CharSequence?

    fun getItemCount(): Int {
        return items.size
    }

    fun createPage(router: Router, position: Int) {
        if (!router.hasRootController()) {
            val item = findItemByPosition(position)
            if (item != null) {
                val page = createPage(item)
                router.setRoot(RouterTransaction.with(page))
            }
        }
    }

    fun getPageTitle(position: Int): CharSequence? {
        val item = findItemByPosition(position)
        return if (item != null) {
            getPageTitle(item)
        } else {
            null
        }
    }

    private fun findItemByPosition(position: Int): T? {
        return if (position >= 0 && position < items.size) {
            items[position]
        } else {
            null
        }
    }
}