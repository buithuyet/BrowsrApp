package com.thryve.browrslib.repository

import com.thryve.browrslib.common.NetworkFactory.getAppService
import com.thryve.browrslib.organizations.OrganizationsResponse

object RemoteOrganizationService {
    suspend fun getOrganizations(): OrganizationsResponse {
        return try {
            val response = getAppService().getOrganizations()
            if (response.isSuccessful) {
                val organizations = response.body() ?: listOf()
                OrganizationsResponse(
                    isSuccess = true,
                    errorMessage = "",
                    organizations = organizations
                )
            } else {
                OrganizationsResponse(
                    isSuccess = false,
                    errorMessage = response.errorBody().toString(),
                    organizations = listOf()
                )
            }
        } catch (exception: Exception) {
            OrganizationsResponse(
                isSuccess = false, errorMessage = exception.message ?: "",
                organizations = listOf()
            )
        }
    }
}