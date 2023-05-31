# BrowsrApp

## Description

Sample app shows list of Github organization

## Structure

- browsrlib/
    * repository/
        - This contains a service calls remote api for organization list:
           ```
           RemoteOrganizationService.getOrganizations(): OrganizationsResponse
           ```
- app/
    * Sample app displays list of organization and function store favorites

## Test
- To run/test app you may need to put your github token into browsrlib/NetworkFactory.kt