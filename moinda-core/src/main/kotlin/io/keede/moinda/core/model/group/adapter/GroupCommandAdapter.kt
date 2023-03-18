package io.keede.moinda.core.model.group.adapter

import io.keede.moinda.common.group.CreateGroup

/**
 * 외부에 노출시켜서 다른 모듈이 사용할 수 있도록 한다.
 * @author keede
 * Created on 2023-03-18
 */
interface GroupCommandAdapter {

    fun save(createGroup: CreateGroup) : Long

}