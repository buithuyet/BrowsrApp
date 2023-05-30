package com.thryve.browrslib.common

import com.thryve.browrslib.organizations.OrganizationDto
import retrofit2.Response
import retrofit2.http.GET

interface AppService {
    @GET("/organizations")
    suspend fun getOrganizations(): Response<List<OrganizationDto>>
}