package com.assigned.githubassignment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.assigned.githubassignment.model.UserModel

@Dao
interface UserDao {
    @Insert
    fun insert(crewList: List<UserModel>)

    @Query("SELECT * FROM crew")
    fun getAllCrews(): LiveData<List<UserModel>>

    @Query("DELETE FROM crew")
    fun deleteAll()

}