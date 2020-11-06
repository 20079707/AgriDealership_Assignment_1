package org.wit.agridealership.views

import com.github.mm.coloredconsole.colored
import org.wit.agridealership.models.AgridealershipJSONStore
import org.wit.agridealership.models.AgridealershipMemStore
import org.wit.agridealership.models.AgridealershipModel

class AgridealershipView {

    fun menu() : Int {

        var option: Int
        var input: String?

        colored {
            println("MAIN MENU".green.bold)
            println(" 1. Add Tractor".yellow.bold)
            println(" 2. Update Tractor".red.bold)
            println(" 3. List All Tractors".purple.bold)
            println(" 4. Search Tractors".blue.bold)
            println(" 5. Filter Tractors".cyan.bold)
            println(" 6. Delete Tractors".green.bold)
            println("-1. Exit".white.bold)
            println()
            print("Enter Option : ".black.bold.italic)
        }
            input = readLine()!!
            option = if (input.toIntOrNull() != null && !input.isEmpty())
                input.toInt()
            else
                -9
            return option

    }
    fun listAgridealerships(agridealerships : AgridealershipJSONStore) {
        println("List All Tractors")
        println()
        agridealerships.logAll()
        println()
    }

    fun showAgridealership(agridealership : AgridealershipModel) {
        if(agridealership != null)
            println("Tractor Details [ $agridealership ]")
        else
            println("Tractor Not Found...")
    }

    fun addAgridealershipData(agridealership : AgridealershipModel) : Boolean {

        println()
        print("Enter a Make : ")
        agridealership.Make = readLine()!!
        print("Enter a Model : ")
        agridealership.Model = readLine()!!
        print("Enter a Year : ")
        agridealership.Year = readLine()!!
        print("Enter a Price : ")
        agridealership.Price = readLine()!!

        return agridealership.Make.isNotEmpty() && agridealership.Model.isNotEmpty() && agridealership.Year.isNotEmpty() && agridealership.Price.isNotEmpty()
    }

    fun updateAgridealershipData(agridealership : AgridealershipModel) : Boolean {

        var tempMake: String?
        var tempModel: String?
        var tempYear: String?
        var tempPrice: String?

        if (agridealership != null) {
            print("Enter a new Make for [ " + agridealership.Make + " ] : ")
            tempMake = readLine()!!
            print("Enter a new Model for [ " + agridealership.Model + " ] : ")
            tempModel = readLine()!!
            print("Enter a new Year for [ " + agridealership.Year + " ] : ")
            tempYear = readLine()!!
            print("Enter a new Price for [ " + agridealership.Price + " ] : ")
            tempPrice = readLine()!!

            if (!tempMake.isNullOrEmpty() && !tempModel.isNullOrEmpty() && !tempYear.isNullOrEmpty() && !tempPrice.isNullOrEmpty()
            ) {
                agridealership.Make = tempMake
                agridealership.Model = tempModel
                agridealership.Year = tempYear
                agridealership.Price = tempPrice
                return true
            }
        }
        return false
    }

    fun getMake(): String? {
        print("Enter Make to Search Makes : ")
        val tractorMake : String? = readLine()!!// String to hold user input

        if (tractorMake != null)
            return tractorMake

        else
            -9

        return println("Tractor Not Found...").toString()

    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update/Delete : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9

        return searchId
    }
}
