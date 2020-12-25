package org.wit.agridealership.controller

import mu.KotlinLogging
import org.wit.agridealership.models.AgridealershipJSONStore
import org.wit.agridealership.models.AgridealershipModel
import org.wit.agridealership.views.AddAgridealershipScreen
import org.wit.agridealership.views.ListAgridealershipScreen
import org.wit.agridealership.views.MenuScreen
import tornadofx.*

class AgridealershipUIController : Controller() {

    val agridealerships = AgridealershipJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Agridealership TornadoFX UI App" }
    }
    fun add(_Make : String, _Model : String, _Year : String, _Price : String){

        var aAgridealership = AgridealershipModel(Make = _Make, Model = _Model, Year = _Year, Price = _Price)
        agridealerships.create(aAgridealership)
        logger.info("Agridealership Added")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListAgridealershipScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        agridealerships.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddAgridealershipScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddAgridealershipScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListAgridealershipScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

}
