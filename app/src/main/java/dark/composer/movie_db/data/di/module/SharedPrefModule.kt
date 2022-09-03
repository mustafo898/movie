//package dark.composer.movie_db.data.di.module
//
//import android.content.Context
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import uz.micro.star.base_clean_architecture.common.utils.SharedPref
//
//@Module
//@InstallIn(SingletonComponent::class)
//object SharedPrefModule {
//
//    @Provides
//    fun provideShared(@ApplicationContext context: Context) = SharedPref(context)
//
//}