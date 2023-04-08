package io.keede.moinda.domains.member.service

import io.keede.moinda.core.model.group.adapter.GroupQueryAdapter
import io.keede.moinda.core.model.member.adapter.MemberCommandAdapter
import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
import io.keede.moinda.domains.config.UseCaseTest
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author keede
 * Created on 2023-04-06
 */
@UseCaseTest
internal class MemberCommandTest {

    @MockK
    private lateinit var memberCommandAdapter: MemberCommandAdapter

    @MockK
    private lateinit var memberQueryAdapter: MemberQueryAdapter

    @MockK
    private lateinit var groupQueryAdapter: GroupQueryAdapter

    private lateinit var sut : MemberCommandUseCase

    @BeforeEach
    fun init() {
        this.sut = MemberCommand(
            this.memberCommandAdapter,
            this.memberQueryAdapter,
            this.groupQueryAdapter
        )
    }

    @Test
    fun 사용자_회원가입_성공() {

        val command = mockk<MemberCommandUseCase.Command>()

        // Model(createMember) 를 할당해서 사용시에 실패한다. 이유는?
        every { memberCommandAdapter.save(command.createMember) } returns mockk(relaxed = true)

        sut.signup(command)

        verify { memberCommandAdapter.save(command.createMember) }

    }

    @Test
    fun 사용자_그룹참여_성공() {
        val participate = mockk<MemberCommandUseCase.Participate>(relaxed = true)

        every { groupQueryAdapter.findById(participate.groupId) } returns mockk(relaxed = true)
        every { memberQueryAdapter.findById(participate.memberId) } returns mockk(relaxed = true)

        sut.participate(participate)

        verify(exactly = 1) { groupQueryAdapter.findById(participate.groupId) }
        verify(exactly = 1) { memberQueryAdapter.findById(participate.memberId) }
    }

}