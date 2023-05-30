package com.thryve.browrslib

import com.thryve.browrslib.common.AppService
import com.thryve.browrslib.common.NetworkFactory
import com.thryve.browrslib.repository.RemoteOrganizationService
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Response

class RemoteOrganizationServiceTest {

    @Test
    fun `should return organizations successfully`() = runTest {
        val mockService = mockk<AppService> {
            coEvery { getOrganizations() } returns Response.success(listOf(mockk()))
        }
        mockkObject(NetworkFactory)
        every { NetworkFactory.getAppService() } returns mockService
        val organizationsResponse = RemoteOrganizationService.getOrganizations()
        assertTrue(organizationsResponse.isSuccess)
        assertEquals(1, organizationsResponse.organizations.size)
    }

    @Test
    fun `should return response error with empty list`() = runTest {
        val mockService = mockk<AppService> {
            coEvery { getOrganizations() } returns Response.error(400, mockk(relaxed = true))
        }
        mockkObject(NetworkFactory)
        every { NetworkFactory.getAppService() } returns mockService
        val organizationsResponse = RemoteOrganizationService.getOrganizations()
        assertFalse(organizationsResponse.isSuccess)
        assertEquals(0, organizationsResponse.organizations.size)
    }

    @Test
    fun `should return response with empty list caused by throw error`() = runTest {
        mockkObject(NetworkFactory)
        every { NetworkFactory.getAppService() }.throws(Exception())
        val organizationsResponse = RemoteOrganizationService.getOrganizations()
        assertFalse(organizationsResponse.isSuccess)
        assertEquals(0, organizationsResponse.organizations.size)
    }
}