package com.thryve.browsrapp.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface OrganizationDao {

    @Transaction
    @Query("SELECT * FROM Organization")
    fun getAllOrganizations(): Flow<List<Organization>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(organization: Organization)
}
