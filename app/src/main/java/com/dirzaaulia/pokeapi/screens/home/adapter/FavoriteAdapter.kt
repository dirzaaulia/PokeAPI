package com.dirzaaulia.pokeapi.screens.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dirzaaulia.pokeapi.R
import com.dirzaaulia.pokeapi.databinding.ItemHomeBinding
import com.dirzaaulia.pokeapi.domain.model.FavoritePokemon
import com.dirzaaulia.pokeapi.util.capitalizeWords
import com.dirzaaulia.pokeapi.util.loadNetworkImage

class FavoriteAdapter(
  private val listener: FavoriteAdapterListener
): PagingDataAdapter<FavoritePokemon, FavoriteAdapter.ViewHolder>(FavoritePokemonDiffCallback()) {

  interface FavoriteAdapterListener {
    fun onItemClicked(item: FavoritePokemon)
    fun onFavoriteClicked(item: FavoritePokemon)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = getItem(position)
    item?.let {
      holder.bind(item)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      ItemHomeBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
      )
    )
  }

  inner class ViewHolder(
    private val binding: ItemHomeBinding
  ): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FavoritePokemon) {
      binding.apply {
        root.setOnClickListener {
          listener.onItemClicked(item)
        }

        favorite.setOnClickListener {
          listener.onFavoriteClicked(item)
        }

        val url = root.context.getString(R.string.image_url_format, item.id)

        image.loadNetworkImage(
          context = root.context,
          url = url
        )

        name.text = item.name?.capitalizeWords()
      }
    }
  }
}

private class FavoritePokemonDiffCallback: DiffUtil.ItemCallback<FavoritePokemon>() {
  override fun areItemsTheSame(oldItem: FavoritePokemon, newItem: FavoritePokemon): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: FavoritePokemon, newItem: FavoritePokemon): Boolean {
    return oldItem == newItem
  }

}