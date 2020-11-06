package org.wit.agridealership.views

import org.wit.agridealership.controller.AgridealershipUIController
import org.wit.agridealership.models.AgridealershipModel
import tornadofx.*

class ListAgridealershipScreen : View("List Agridealerships") {

    val agridealershipUIController: AgridealershipUIController by inject()
    val tableContent = agridealershipUIController.agridealerships.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", AgridealershipModel::id)
            readonlyColumn("MAKE", AgridealershipModel::Make)
            readonlyColumn("MODEL", AgridealershipModel::Model)
            readonlyColumn("YEAR", AgridealershipModel::Year)
            readonlyColumn("PRICE", AgridealershipModel::Price)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    agridealershipUIController.closeList()
                }
            }
        }
    }
}
