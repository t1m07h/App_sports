package com.example.app_sports.Model

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.lang.Appendable

@Database(version = 1, entities = [UserData::class])
abstract class UserDb: RoomDatabase() {
	companion object {
		@Volatile
		private var INSTANCE: UserDb? = null

		fun get(application: Application): UserDb {
			val tempInstance = INSTANCE
			if (tempInstance != null) {
				return tempInstance
			}
			synchronized(this) {
				val instance = Room.databaseBuilder(application, UserDb::class.java, "user").build()
				INSTANCE = instance
				return instance
			}
		}
	}
	abstract fun get_user_dao(): UserDao
}