package org.wit.agridealership.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.agridealership.controller.AgridealershipUIController
import tornadofx.*
import kotlin.reflect.KClass

class AddAgridealershipScreen : View("Add Tractor") {
    val model = ViewModel()
    val _Make = model.bind { SimpleStringProperty() }
    val _Model = model.bind { SimpleStringProperty() }
    val _Year = model.bind { SimpleStringProperty() }
    val _Price = model.bind { SimpleStringProperty() }
    val agridealershipUIController: AgridealershipUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Make") {
                textfield(_Make).required()
            }
            field("Model") {
                textfield(_Model).required()
            }
            field("Year") {
                textfield(_Year).required()
            }
            field("Price") {
                textfield(_Price).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        agridealershipUIController.add(_Make.value,_Model.value,_Year.value,_Price.value)

                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        agridealershipUIController.closeAdd()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _Make.value = ""
        _Model.value = ""
        _Year.value = ""
        _Price.value = ""
        model.clearDecorators()
    }
}
