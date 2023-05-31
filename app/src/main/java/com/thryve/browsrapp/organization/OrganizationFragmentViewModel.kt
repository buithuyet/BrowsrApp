package com.thryve.browsrapp.organization

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thryve.browrslib.organizations.OrganizationsResponse
import com.thryve.browrslib.repository.RemoteOrganizationService
import com.thryve.browsrapp.database.Organization
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class OrganizationFragmentViewModel : ViewModel() {

    private val organizationsResults =
        MutableLiveData<Pair<OrganizationsResponse, List<Organization>>>()
    val organizationsResultsLiveData: LiveData<Pair<OrganizationsResponse, List<Organization>>> =
        organizationsResults

    fun getOrganizations(context: Context) {
        viewModelScope.launch {
            val favoriteOrganizations =
                OrganizationRepository(context).getAllOrganizations()?.first() ?: listOf()
            val remoteOrganizations = RemoteOrganizationService.getOrganizations()
            organizationsResults.value = remoteOrganizations to favoriteOrganizations
        }
    }

    fun insertOrUpdateFavorite(context: Context, item: OrganizationListItem) {
        val organization = Organization(
            id = item.id,
            name = item.name,
            avatarUrl = item.avatarUrl,
            isFavorite = item.isFavorite
        )
        OrganizationRepository(context).insertOrUpdateFavorite(organization)
    }
}