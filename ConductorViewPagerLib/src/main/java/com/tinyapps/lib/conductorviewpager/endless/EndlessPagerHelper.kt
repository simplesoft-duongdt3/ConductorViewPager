package com.tinyapps.lib.conductorviewpager.endless

object EndlessPagerHelper {
    const val MIN_ITEM_NEED_ENDLESS = 2
    //add 1 fake page in left + 1 fake page right
    private const val NUM_FAKE_PAGE_FOR_ENDLESS = 2

    fun getEndlessCount(realCount: Int): Int {
        return if (realCount < MIN_ITEM_NEED_ENDLESS) {
            realCount
        } else {
            realCount + NUM_FAKE_PAGE_FOR_ENDLESS
        }
    }

    fun getEndlessPosition(realCount: Int, pagePosition: Int) : Int {
        return when {
            realCount < MIN_ITEM_NEED_ENDLESS -> pagePosition
            //left over first real item -> move back to last real item
            pagePosition == 0 -> realCount - 1
            //right over last real item -> move back to first real item
            pagePosition > realCount -> 0
            //anything else -> -1 fake page in left side
            else -> pagePosition - 1
        }
    }

    fun getRealPosition(itemCount: Int, endlessPosition: Int): Int {
        return when {
            itemCount <= MIN_ITEM_NEED_ENDLESS -> endlessPosition
            endlessPosition == 0 -> itemCount - 2
            endlessPosition == itemCount - 1 ->  1
            else -> endlessPosition
        }
    }

    fun isRealPage(realCount: Int, pagePosition: Int): Boolean {
        return when {
            realCount < MIN_ITEM_NEED_ENDLESS -> true
            pagePosition == 0 -> false
            pagePosition > realCount -> false
            else -> true
        }
    }

    fun getEndlessTabLayoutPosition(itemCount: Int, position: Int): Int {
        return when {
            itemCount < MIN_ITEM_NEED_ENDLESS -> position
            else -> position + 1
        }
    }

    fun getRealTabLayoutPosition(itemCount: Int, position: Int): Int {
        return when {
            itemCount < MIN_ITEM_NEED_ENDLESS -> position
            position > 0 && position < itemCount + NUM_FAKE_PAGE_FOR_ENDLESS  - 1 -> position - 1
            else -> -1
        }
    }
}