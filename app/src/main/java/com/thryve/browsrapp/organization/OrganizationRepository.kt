package com.thryve.browsrapp.organization

import android.content.Context
import com.thryve.browsrapp.database.AppDatabase
import com.thryve.browsrapp.database.Organization
import kotlinx.coroutines.flow.Flow

class OrganizationRepository(context: Context) {

    private val organizationDao = AppDatabase.getInstance(context)?.organizationDao()

    fun getAllOrganizations(): Flow<List<Organization>>? {
        return organizationDao?.getAllOrganizations()
    }

    fun insertOrUpdateFavorite(organization: Organization) {
        organizationDao?.insert(organization)
    }
}