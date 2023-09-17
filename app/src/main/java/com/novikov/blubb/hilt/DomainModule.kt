package com.novikov.blubb.hilt

import androidx.transition.Visibility.Mode
import com.novikov.blubb.domain.repositories.FirebaseAuthentificationRepository
import com.novikov.blubb.domain.repositories.UserRepository
import com.novikov.blubb.domain.usecases.AuthentificationUseCase
import com.novikov.blubb.domain.usecases.CreateUserUseCase
import com.novikov.blubb.domain.usecases.GetUserUseCase
import com.novikov.blubb.domain.usecases.LogOutUseCase
import com.novikov.blubb.domain.usecases.SaveUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetUserUseCase(userRepository: UserRepository): GetUserUseCase{
        return GetUserUseCase(userRepository)
    }

    @Provides
    fun provideCreateUserUseCase(userRepository: UserRepository): CreateUserUseCase{
        return CreateUserUseCase(userRepository)
    }

    @Provides
    fun provideAuthentificationUseCase(firebaseAuthentificationRepository: FirebaseAuthentificationRepository): AuthentificationUseCase{
        return AuthentificationUseCase(firebaseAuthentificationRepository)
    }

    @Provides
    fun provideSaveUserUseCase(userRepository: UserRepository) : SaveUserUseCase{
        return SaveUserUseCase(userRepository)
    }

    @Provides
    fun provideLogOutUseCase(firebaseAuthentificationRepository: FirebaseAuthentificationRepository): LogOutUseCase{
        return LogOutUseCase(firebaseAuthentificationRepository)
    }
}