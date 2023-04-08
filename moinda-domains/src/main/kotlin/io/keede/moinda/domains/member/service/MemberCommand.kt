package io.keede.moinda.domains.member.service

import io.keede.moinda.core.model.group.adapter.GroupQueryAdapter
import io.keede.moinda.core.model.member.adapter.MemberCommandAdapter
import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
import io.keede.moinda.domains.member.domain.Member
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author keede
 * Created on 2023-04-06
 */
@Service
@Transactional
internal class MemberCommand(
    private val memberCommandAdapter: MemberCommandAdapter,
    private val memberQueryAdapter: MemberQueryAdapter,
    private val groupQueryAdapter: GroupQueryAdapter
) : MemberCommandUseCase {

    override fun signup(command: MemberCommandUseCase.Command): Member {
        val entity = memberCommandAdapter.save(command.createMember)
        return Member(entity)
    }

    override fun participate(target: MemberCommandUseCase.Participate) {
        val groupJpaEntity = groupQueryAdapter.findById(target.groupId)
        val memberJpaEntity = memberQueryAdapter.findById(target.memberId)

        memberJpaEntity.participate(groupJpaEntity)
    }

}