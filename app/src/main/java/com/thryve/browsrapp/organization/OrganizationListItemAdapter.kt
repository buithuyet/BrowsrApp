package com.thryve.browsrapp.organization

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thryve.browsrapp.databinding.OrganizationListItemBinding

class OrganizationListItemAdapter(
    private val onFavoriteClick: (OrganizationListItem) -> Unit
) : RecyclerView.Adapter<OrganizationListItemViewHolder>() {

    private val items: MutableList<OrganizationListItem> = mutableListOf()
    private var originalItems: MutableList<OrganizationListItem>? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrganizationListItemViewHolder {
        return OrganizationListItemViewHolder(
            OrganizationListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onFavoriteClick
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: OrganizationListItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateAll(newItems: List<OrganizationListItem>) {
        items.clear()
        items.addAll(newItems)
        if (originalItems == null) {
            originalItems = newItems.toMutableList()
        }
        notifyDataSetChanged()
    }


    fun updateFavoriteItem(organizationListItem: OrganizationListItem) {
        items.first { it.id == organizationListItem.id }.isFavorite =
            organizationListItem.isFavorite
    }

    fun searchItem(searchText: String) {
        if (searchText.isEmpty()) {
            originalItems?.let { updateAll(it) }
            return
        }
        val newItems = originalItems?.filter { it.name.contains(searchText) }
        newItems?.let { updateAll(it) }
    }
}