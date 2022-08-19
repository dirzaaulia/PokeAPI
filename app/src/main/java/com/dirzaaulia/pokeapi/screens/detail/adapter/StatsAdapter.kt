package com.dirzaaulia.pokeapi.screens.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dirzaaulia.pokeapi.R
import com.dirzaaulia.pokeapi.databinding.ItemSimpleTextBinding
import com.dirzaaulia.pokeapi.domain.model.Stats
import com.dirzaaulia.pokeapi.util.capitalizeWords

class StatsAdapter: ListAdapter<Stats, StatsAdapter.ViewHolder>(StatsDiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      ItemSimpleTextBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
      )
    )
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = getItem(position)
    item?.let {
      holder.bind(it)
    }
  }

  inner class ViewHolder(
    private val binding: ItemSimpleTextBinding
  ): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Stats) {
      binding.apply {
        text1.text = root.context.getString(
          R.string.pokemon_stats_format,
          item.stat
            ?.name
            ?.replace("-", " ")
            ?.capitalizeWords(),
          item.baseStat,
          item.effort
        )
      }
    }
  }
}

class StatsDiffCallback: DiffUtil.ItemCallback<Stats>() {
  override fun areItemsTheSame(oldItem: Stats, newItem: Stats): Boolean {
    return oldItem.stat?.name == newItem.stat?.name
  }

  override fun areContentsTheSame(oldItem: Stats, newItem: Stats): Boolean {
    return oldItem == newItem
  }

}