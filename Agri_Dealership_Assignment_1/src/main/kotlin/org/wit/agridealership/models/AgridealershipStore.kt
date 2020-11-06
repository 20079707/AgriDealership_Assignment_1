package org.wit.agridealership.models

interface AgridealershipStore {
    fun findAll(): List<AgridealershipModel>
    fun findOne(id: Long): AgridealershipModel?
    fun findMake(Make: String): AgridealershipModel?
    fun create(agridealership: AgridealershipModel)
    fun update(agridealership: AgridealershipModel)
    fun delete(agridealership: AgridealershipModel)
}
