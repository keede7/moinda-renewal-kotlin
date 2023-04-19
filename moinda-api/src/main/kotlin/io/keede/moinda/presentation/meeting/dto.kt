package io.keede.moinda.presentation.meeting

import com.fasterxml.jackson.annotation.JsonFormat
import io.keede.moinda.common.meeting.CreateMeeting
import io.keede.moinda.domains.meeting.domain.Meeting
import io.keede.moinda.util.toFullPattern
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * @author keede
 * Created on 2023-04-19
 */
data class CreateMeetingDto(
    @field:NotBlank(message = "모임 이름을 입력하세요.")
    val name: String,
    @field:NotBlank(message = "우편 번호가 필요합니다.")
    val postCode: String,
    @field:NotBlank(message = "기본 주소가 필요합니다.")
    val primaryAddress: String,
    @field:NotBlank(message = "상세 주소가 필요합니다.")
    val detailAddress: String,
    val description: String?,
    @field:NotNull(message = "최대 인원 수를 입력하세요.")
    val capacity: Int,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", locale = "Asia/Seoul")
    val startAt: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", locale = "Asia/Seoul")
    val endAt: LocalDateTime,
)

data class MeetingResponseDto(
    val meetingId: Long,
    val name: String,
    val postCode: String,
    val primaryAddress: String,
    val detailAddress: String,
    val description: String?,
    val capacity: Int,
    val startAt: String,
    val endAt: String,
)

internal fun CreateMeetingDto.toDomain() = CreateMeeting(
    name,
    postCode,
    primaryAddress,
    detailAddress,
    description,
    capacity,
    startAt,
    endAt,
)

internal fun Meeting.toMeetingResponseDto() = MeetingResponseDto(
    meetingId,
    name,
    postCode,
    primaryAddress,
    detailAddress,
    description,
    capacity,
    startAt.toFullPattern(),
    endAt.toFullPattern(),
)