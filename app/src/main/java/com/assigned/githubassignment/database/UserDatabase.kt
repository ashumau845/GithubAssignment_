package com.assigned.githubassignment.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.assigned.githubassignment.model.UserModel


@Database(entities = [UserModel::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun UserDao(): UserDao

    companion object {

        // all threads will know the updated value of INSTANCE
        @Volatile
        private var INSTANCE: UserDatabase? = null

        // if one or more threads tried to call it, it will return only one value instances
        fun getDatabase(context: Context): UserDatabase {
            if (INSTANCE == null) {

                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java, "UserDB"
                    ).addCallback(callback).fallbackToDestructiveMigration().build()
                }

            }

            return INSTANCE!!
        }

        var callback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateAsyncTask(INSTANCE!!).execute()
            }
        }

        class PopulateAsyncTask (userDatabase: UserDatabase) :
            AsyncTask<Void?, Void?, Void?>() {
            private var userDao: UserDao? = null

            init {
                userDao = userDatabase.UserDao()
            }

            override fun doInBackground(vararg params: Void?): Void? {
                userDao?.deleteAll()
                return null
            }

        }

    }
}