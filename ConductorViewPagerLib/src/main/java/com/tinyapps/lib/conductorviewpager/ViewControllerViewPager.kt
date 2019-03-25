package com.tinyapps.lib.conductorviewpager

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.tinyapps.lib.conductorviewpager.endless.EndlessPagerHelper
import com.tinyapps.lib.conductorviewpager.endless.EndlessViewControllerPagerAdapter

class ViewControllerViewPager : ViewPager {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)

    init {
        initEndless()
    }

    override fun setAdapter(adapter: PagerAdapter?) {
        super.setAdapter(adapter)
        adapter?.let { pagerAdapter ->
            if (isEndlessAdapter() && pagerAdapter.count > EndlessPagerHelper.MIN_ITEM_NEED_ENDLESS) {
                setCurrentItem(1, false)
            }
        }
    }

    private fun isEndlessAdapter(): Boolean {
        return adapter is EndlessViewControllerPagerAdapter
    }

    private fun initEndless() {
        addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    switchToEndlessPage()
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                //nothing to do now
            }

            override fun onPageSelected(position: Int) {
                //nothing to do now
            }
        })
    }

    private fun switchToEndlessPage() {
        if (isEndlessAdapter()) {
            adapter?.let { pagerAdapter ->
                val itemCount = pagerAdapter.count
                if (itemCount > EndlessPagerHelper.MIN_ITEM_NEED_ENDLESS) {
                    val endlessPosition = currentItem

                    val realPosition = EndlessPagerHelper.getRealPosition(
                        itemCount = itemCount,
                        endlessPosition = endlessPosition
                    )
                    if (realPosition != endlessPosition) {
                        setCurrentItem(realPosition, false)
                    }
                }
            }
        }
    }
}