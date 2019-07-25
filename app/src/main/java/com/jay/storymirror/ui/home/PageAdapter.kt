package com.jay.storymirror.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jay.storymirror.ui.greatfirstreads.GreatFirstReadsFragment
import com.jay.storymirror.ui.paidstories.PaidStoriesFragment
import com.jay.storymirror.ui.recommendations.RecommendationsFragment

class PageAdapter internal constructor(fm: FragmentManager, private val numOfTabs: Int) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> RecommendationsFragment()
            1 -> PaidStoriesFragment()
            2 -> GreatFirstReadsFragment()
            else -> RecommendationsFragment()
        }
    }

    override fun getCount(): Int {
        return numOfTabs
    }
}