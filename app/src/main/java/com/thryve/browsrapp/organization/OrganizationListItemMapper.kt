package com.thryve.browsrapp.organization

import com.thryve.browrslib.organizations.OrganizationDto
import com.thryve.browsrapp.database.Organization

object OrganizationListItemMapper {

    fun mapToListItems(remoteList: List<OrganizationDto>, favoriteList: List<Organization>) =
        remoteList.map { organization ->
            OrganizationListItem(
                id = organization.id,
                name = organization.login,
                avatarUrl = organization.avatarUrl,
                isFavorite = favoriteList.firstOrNull { it.id == organization.id }?.isFavorite
                    ?: false
            )
        }
}