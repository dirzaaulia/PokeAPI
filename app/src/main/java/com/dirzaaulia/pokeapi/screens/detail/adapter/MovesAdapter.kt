package com.dirzaaulia.pokeapi.screens.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dirzaaulia.pokeapi.databinding.ItemSimpleTextBinding
import com.dirzaaulia.pokeapi.domain.model.Moves
import com.dirzaaulia.pokeapi.util.capitalizeWords

class MovesAdapter: ListAdapter<Moves, MovesAdapter.ViewHolder>(MovesDiffCallback()) {

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

    fun bind(item: Moves) {
      binding.apply {
        text1.text = item.move
          ?.name
          ?.replace("-", " ")
          ?.capitalizeWords()
      }
    }
  }
}

class MovesDiffCallback: DiffUtil.ItemCallback<Moves>() {
  override fun areItemsTheSame(oldItem: Moves, newItem: Moves): Boolean {
    return oldItem.move?.name == newItem.move?.name
  }

  override fun areContentsTheSame(oldItem: Moves, newItem: Moves): Boolean {
    return oldItem == newItem
  }

}