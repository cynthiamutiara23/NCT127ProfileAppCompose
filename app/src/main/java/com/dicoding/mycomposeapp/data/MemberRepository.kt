package com.dicoding.mycomposeapp.data

import com.dicoding.mycomposeapp.model.FakeMemberDataSource
import com.dicoding.mycomposeapp.model.Member
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MemberRepository {

    private val members = mutableListOf<Member>()

    init {
        if (members.isEmpty()) {
            FakeMemberDataSource.dummyMembers.forEach {
                members.add(it)
            }
        }
    }

    fun getAllMembers(): Flow<List<Member>> {
        return flowOf(members)
    }

    fun getMemberById(memberId: Long): Member {
        return members.first {
            it.id == memberId
        }
    }

    companion object {
        @Volatile
        private var instance: MemberRepository? = null

        fun getInstance(): MemberRepository =
            instance ?: synchronized(this) {
                MemberRepository().apply {
                    instance = this
                }
            }
    }
}