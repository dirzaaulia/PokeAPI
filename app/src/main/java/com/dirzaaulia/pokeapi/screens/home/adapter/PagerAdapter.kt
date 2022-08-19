package com.dirzaaulia.pokeapi.screens.home.adapter

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dirzaaulia.pokeapi.screens.home.HomeActivity
import com.dirzaaulia.pokeapi.screens.home.tab.FavoriteFragment
import com.dirzaaulia.pokeapi.screens.home.tab.HomeFragment

class PagerAdapter(
  @NonNull activity: HomeActivity
): FragmentStateAdapter(activity) {

  override fun getItemCount(): Int {
    return mFragmentCount
  }

  override fun createFragment(position: Int): Fragment {
    return when (position) {
      0 -> HomeFragment()
      1 -> FavoriteFragment()
      else -> HomeFragment()
    }
  }

  companion object {
    private const val mFragmentCount = 2
  }
}