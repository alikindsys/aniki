import base.core.Config
import base.core.Driver
import base.core.DriverType
import base.core.aniki
import base.core.cli.Parser
import base.core.cli.input
import base.core.cli.subcommands.ConfigSubcommand
import base.core.cli.subcommands.ListProviders
import com.google.gson.Gson
import kotlinx.cli.ExperimentalCli
import org.asynchttpclient.uri.Uri
import java.io.File
import kotlin.system.exitProcess

@ExperimentalCli
fun main (args : Array<String>) {
    aniki.saveCfg()
    Parser.subcommands(ConfigSubcommand(), ListProviders())
    Parser.parse(args)
    if(input.isEmpty()) {
        println("No links detected.")
        aniki.driver.quit()
        exitProcess(0)
    }else {
        println("Links detected : ${input.size}")
        for(link in input){
            try {
                val uri = Uri.create(link)
                if(!aniki.tryDownloadSeries(uri, aniki.driver)) {
                    println("========================================")
                    println("This website is not supported by aniki.")
                    println("If you want support to be added please create an issue on the original repository.")
                    println("Keep in mind that someone else might have done the same, so search before creating a new issue.")
                    println("========================================")
                    println("Website : ${uri.authority}")
                    println("Attempted to download : ${uri.path}")
                    println("Create an issue on : https://github.com/RORIdev/aniki/issues")
                    println("========================================")
                }
            } catch (e : Exception) {
                println("Invalid Link : $link")
            }
        }
    }
    aniki.driver.quit()
    exitProcess(0)
}