package com.thryve.browrslib.organizations

class OrganizationsResponse(
    val isSuccess: Boolean,
    val errorMessage: String,
    val organizations: List<OrganizationDto>
)