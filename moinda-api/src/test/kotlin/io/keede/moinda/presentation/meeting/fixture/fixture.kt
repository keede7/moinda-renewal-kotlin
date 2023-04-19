package io.keede.moinda.presentation.meeting.fixture

import io.keede.moinda.presentation.meeting.CreateMeetingDto
import java.time.LocalDateTime

internal fun ofCreateMeetingDto(
    name: String,
    postCode: String,
    primaryAddress: String,
    detailAddress: String,
    description: String?,
    capacity: Int,
    startAt: LocalDateTime,
    endAt: LocalDateTime
) = CreateMeetingDto(
    name,
    postCode,
    primaryAddress,
    detailAddress,
    description,
    capacity,
    startAt,
    endAt,
)