package org.wit.agridealership.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.agridealership.controller.AgridealershipUIController
import tornadofx.*

class MenuScreen : View("Agridealership Main Menu") {

    val agridealershipUIController: AgridealershipUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Tractor") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        agridealershipUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Tractors") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        agridealershipUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }


}
