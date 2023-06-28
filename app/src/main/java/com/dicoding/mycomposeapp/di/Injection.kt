package com.dicoding.mycomposeapp.di

import com.dicoding.mycomposeapp.data.MemberRepository

object Injection {
    fun provideRepository(): MemberRepository {
        return MemberRepository.getInstance()
    }
}