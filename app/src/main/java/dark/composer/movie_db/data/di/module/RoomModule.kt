//package uz.micro.star.ussd_clean_mvvm.di.module
//
//import android.app.Application
//import androidx.room.Room
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import uz.micro.star.ussd_clean_mvvm.common.utils.Constants
//import javax.inject.Singleton
//
///**
// * Created by Microstar on 05.06.2020.
// */
//@Module
//@InstallIn(SingletonComponent::class)
//object RoomModule {
//
//    @Provides
//    fun providesAppDatabase(application: Application) = Room.databaseBuilder(
//        application.applicationContext, AppDatabase::class.java, Constants.DATABASE_NAME
//    )
////        .allowMainThreadQueries()
////        .fallbackToDestructiveMigration()
////        .addMigrations(Migrations.isBonusTypeMigration)
//        .build()
//
//    @Provides
//    fun providesCardDao(appDatabase: AppDatabase) = appDatabase.getCardDao()
//
//}