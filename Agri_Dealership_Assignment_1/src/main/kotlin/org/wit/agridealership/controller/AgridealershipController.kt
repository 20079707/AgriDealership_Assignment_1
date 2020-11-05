package org.wit.agridealership.controllers

import mu.KotlinLogging
import org.wit.agridealership.models.AgridealershipJSONStore
import org.wit.agridealership.models.AgridealershipModel
import org.wit.agridealership.views.AgridealershipView


class AgridealershipController {

    // val agridealerships = AgridealershipMemStore()
    val agridealerships = AgridealershipJSONStore()
    val agridealershipView = AgridealershipView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Agridealership Console App" }
        println("Agridealership Kotlin App Version 1.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> addTractor()
                2 -> updateTractor()
                3 -> listTractors()
                4 -> searchTractors()
                5 -> deleteTractor()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Agridealership Console App" }
    }

    fun menu() :Int { return agridealershipView.menu() }

    fun addTractor(){
        var aAgridealership = AgridealershipModel()

        if (agridealershipView.addAgridealershipData(aAgridealership))
            agridealerships.create(aAgridealership)
        else
            logger.info("Tractor Not Added")
    }

    fun listTractors() {
        agridealershipView.listAgridealerships(agridealerships)
    }

    fun updateTractor() {

        agridealershipView.listAgridealerships(agridealerships)
        var searchId = agridealershipView.getId()
        val aAgridealership = search(searchId)

        if(aAgridealership != null) {
            if(agridealershipView.updateAgridealershipData(aAgridealership)) {
                agridealerships.update(aAgridealership)
                agridealershipView.showAgridealership(aAgridealership)
                logger.info("Tractor Updated : [ $aAgridealership ]")
            }
            else
                logger.info("Tractor Not Updated")
        }
        else
            println("Tractor Not Updated...")
    }

    fun deleteTractor() {
        agridealershipView.listAgridealerships(agridealerships)
        var searchId = agridealershipView.getId()
        val aAgridealership = search(searchId)

        if(aAgridealership != null) {
            agridealerships.delete(aAgridealership)
            println("Tractor Deleted...")
            agridealershipView.listAgridealerships(agridealerships)
        }
        else
            println("Tractor Not Deleted...")
    }

    fun searchTractors() {
        val aAgridealership = search(agridealershipView.getId())!!
        agridealershipView.showAgridealership(aAgridealership)
    }


    fun search(id: Long) : AgridealershipModel? {
        var foundAgridealership = agridealerships.findOne(id)
        return foundAgridealership
    }

    fun dummyData() {
        agridealerships.create(AgridealershipModel(Make = "John Deere", Model = "6190R",  Year = "2014", Price = "70.000"
        ))
        agridealerships.create(AgridealershipModel(Make = "John Deere", Model = "6430",  Year = "2007", Price = "32.000"
        ))
        agridealerships.create(AgridealershipModel(Make = "Massey Ferguson", Model = "7618",  Year = "2015", Price = "52.500"
        ))
        agridealerships.create(AgridealershipModel(Make = "New Holland", Model = "T6080",  Year = "2011", Price = "36,000"
        ))
        agridealerships.create(AgridealershipModel(Make = "Ford", Model = "7810",  Year = "1989", Price = "14,900"
        ))
    }
}

