package base.core.cli.subcommands

import base.core.DriverType
import base.core.aniki
import kotlinx.cli.*
import org.openqa.selenium.InvalidArgumentException
import java.io.File

@ExperimentalCli
class ConfigSubcommand : Subcommand("config", "Shows/Edits the aniki configuration") {
    private val validDrivers = listOf("firefox","chrome","gecko")
    private val configOptions = mapOf(Pair("driver.path","The path of the driver") ,
        Pair("driver.type","The type of the driver [chrome/firefox]") ,
        Pair("folder","The folder anime will be downloaded to."))
    val listAll by option(ArgType.Boolean,"list-all","l",description = "Lists all available config settings").default(false)
    val edit by option(ArgType.Choice(configOptions.keys.toList(), { it }),
        "edit",
        "e",
        description = "Selects an option to be edited.")
        .default("folder")
    val value by option(ArgType.String, "value", "v")
    override fun execute() {
        if(listAll) {
            println("All available options : ")
            println("")
            configOptions.forEach { pair ->
                println("${pair.key} - ${pair.value}")
            }
            return
        }
        if(value.isNullOrBlank() && !edit.isBlank()) {
            println("Cannot edit without a value. Aborting")
            return
        }
        when (edit) {
            "driver.path" -> {
                val file = File(value)
                if (!file.exists()) {
                    println("The provided path is invalid. Aborting")
                } else {
                    aniki.config.driver.path = file
                    println("Set driver.path to ${file.canonicalPath}.")
                    aniki.saveCfg()
                }
            }
            "driver.type" -> {
                if(!validDrivers.contains(value?.toLowerCase()?.trim())) throw InvalidArgumentException("Invalid driver type.")
                when(value?.toLowerCase()?.trim()) {
                    "firefox" , "gecko" -> {
                        aniki.config.driver.type = DriverType.GECKO
                        println("Set driver.type to geckodriver.")
                        aniki.saveCfg()
                    }
                    "chrome" -> {
                        aniki.config.driver.type = DriverType.CHROME
                        println("Set driver.type to chromedriver.")
                        aniki.saveCfg()
                    }
                }
            }
            "folder" -> {
                val path = File(value)
                if(!path.exists()) {
                    println("Path provided didn't exist. aniki will now try to create it.")
                    try {
                        path.mkdirs()
                        println("Path created.")
                    } catch (e : FileSystemException) {
                        println("Could not create path.")
                    }
                }
                aniki.config.folder = path
                println("Set folder to ${path.canonicalPath}.")
                aniki.saveCfg()
            }
            else -> {
                println("Invalid option.")
            }
        }
    }
}