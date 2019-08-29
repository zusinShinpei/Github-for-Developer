package com.seok.gitfordeveloper.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CommitsDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(commits : Commits)

    @Update
    fun update(commits: Commits)

    @Query("SELECT * FROM commits ORDER BY data_count DESC LIMIT 1")
    fun maxCommit() : Commits

    @Query("SELECT * FROM commits")
    fun getAllCommits() : LiveData<List<Commits>>
}