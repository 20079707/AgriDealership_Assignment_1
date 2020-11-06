package org.wit.agridealership.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.agridealership.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "agridealerships.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<AgridealershipModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class AgridealershipJSONStore : AgridealershipStore {

    var agridealerships = mutableListOf<AgridealershipModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<AgridealershipModel> {
        return agridealerships
    }

    override fun findOne(id: Long) : AgridealershipModel? {
        var foundAgridealership: AgridealershipModel? = agridealerships.find { p -> p.id == id }
        return foundAgridealership
    }

    override fun findMake(Make: String) : AgridealershipModel? {
            return agridealerships.find { p -> p.Make == Make }
    }

    override fun create(agridealership: AgridealershipModel) {
        agridealership.id = generateRandomId()
        agridealerships.add(agridealership)
        serialize()
    }

    override fun update(agridealership: AgridealershipModel) {
        var foundAgridealership = findOne(agridealership.id!!)
        if (foundAgridealership != null) {
            foundAgridealership.Make = agridealership.Make
            foundAgridealership.Model = agridealership.Model
            foundAgridealership.Year = agridealership.Year
            foundAgridealership.Price = agridealership.Price
        }
        serialize()
    }

    override fun delete(agridealership: AgridealershipModel) {
        agridealerships.remove(agridealership)
        serialize()
    }

    internal fun logAll() {
        agridealerships.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(agridealerships, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        agridealerships = Gson().fromJson(jsonString, listType)
    }
}
