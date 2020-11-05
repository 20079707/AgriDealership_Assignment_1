package org.wit.agridealership.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var lastId = 0L

internal fun getId(): Long
{
    return lastId++
}

class AgridealershipMemStore : AgridealershipStore {

    val agridealerships = ArrayList<AgridealershipModel>()

    override fun findAll(): List<AgridealershipModel> {
        return agridealerships
    }

    override fun findOne(id: Long
    ) : AgridealershipModel? {
        var foundAgridealership: AgridealershipModel? = agridealerships.find { p -> p.id == id }
        return foundAgridealership
    }

    override fun create(agridealership: AgridealershipModel) {
        agridealership.id = getId()
        agridealerships.add(agridealership)
        logAll()
    }

    override fun update(agridealership: AgridealershipModel) {
        var foundAgridealership = findOne(agridealership.id!!)
        if (foundAgridealership != null) {
            foundAgridealership.Make = agridealership.Make
            foundAgridealership.Model = agridealership.Model
            foundAgridealership.Year = agridealership.Year
            foundAgridealership.Price = agridealership.Price
        }
    }

    override fun delete(agridealership: AgridealershipModel) {
        agridealerships.remove(agridealership)
    }

    internal fun logAll() {
        agridealerships.forEach { logger.info("${it}") }
    }
}
