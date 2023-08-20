package com.novikov.blubb.hilt

import com.novikov.blubb.data.FirebaseAuthentificationRepositoryImpl
import com.novikov.blubb.data.UserRepositoryImpl
import com.novikov.blubb.domain.repositories.FirebaseAuthentificationRepository
import com.novikov.blubb.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideFirebaseAuthentificationRepository(): FirebaseAuthentificationRepository{
        return FirebaseAuthentificationRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository{
        return UserRepositoryImpl()
    }

}