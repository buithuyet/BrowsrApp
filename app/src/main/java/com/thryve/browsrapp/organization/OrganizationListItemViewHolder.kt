package com.thryve.browsrapp.organization

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thryve.browsrapp.R
import com.thryve.browsrapp.databinding.OrganizationListItemBinding

class OrganizationListItemViewHolder(
    private val binding: OrganizationListItemBinding,
    private val onFavoriteClick: (OrganizationListItem) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: OrganizationListItem) = with(binding) {
        Glide.with(root).load(item.avatarUrl).into(avatar)
        name.text = item.name
        favorite.setImageResource(
            if (item.isFavorite) R.drawable.baseline_favorite_24
            else R.drawable.baseline_favorite_border_24
        )
        favorite.setOnClickListener {
            favorite.setImageResource(
                if (item.isFavorite) R.drawable.baseline_favorite_border_24
                else R.drawable.baseline_favorite_24
            )
            onFavoriteClick(
                OrganizationListItem(
                    id = item.id,
                    name = item.name,
                    avatarUrl = item.avatarUrl,
                    isFavorite = !item.isFavorite
                )
            )
        }
    }
}