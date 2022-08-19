package com.dirzaaulia.pokeapi.screens.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dirzaaulia.pokeapi.databinding.ActivityHomeBinding
import com.dirzaaulia.pokeapi.screens.home.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

  private lateinit var binding: ActivityHomeBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityHomeBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setup()
  }

  private fun setup() {
    setupPagerAdapter()
  }

  private fun setupPagerAdapter() {
    val tabNames = listOf("List", "Favorite")
    binding.viewPager.adapter = PagerAdapter(this)
    TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
      tab.text = tabNames[position]
    }.attach()
  }
}