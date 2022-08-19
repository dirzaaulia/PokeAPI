package com.dirzaaulia.pokeapi.screens.home.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dirzaaulia.pokeapi.R
import com.dirzaaulia.pokeapi.databinding.ItemHomeBinding
import com.dirzaaulia.pokeapi.domain.model.CommonStandardItem
import com.dirzaaulia.pokeapi.util.capitalizeWords
import com.dirzaaulia.pokeapi.util.loadNetworkImage

class HomeAdapter(
    private val listener: HomeAdapterListener
) : PagingDataAdapter<CommonStandardItem, HomeAdapter.ViewHolder>(HomeDiffCallback()) {

    interface HomeAdapterListener {
        fun onItemClicked(item: CommonStandardItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }


    inner class ViewHolder(
        private val binding: ItemHomeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CommonStandardItem) {
            binding.apply {
                root.setOnClickListener {
                    listener.onItemClicked(item)
                }

                favorite.isVisible = false

                val uri = Uri.parse(item.url)
                val id = uri.pathSegments[uri.pathSegments.size - 1]
                val url = root.context.getString(R.string.image_url_format, id)

                image.loadNetworkImage(
                    context = root.context,
                    url = url
                )

                name.text = item.name?.capitalizeWords()
            }
        }
    }
}

private class HomeDiffCallback : DiffUtil.ItemCallback<CommonStandardItem>() {
    override fun areItemsTheSame(oldItem: CommonStandardItem, newItem: CommonStandardItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CommonStandardItem, newItem: CommonStandardItem): Boolean {
        return oldItem == newItem
    }
}
