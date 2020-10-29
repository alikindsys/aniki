import base.core.Config
import base.core.Driver
import base.core.DriverType
import base.core.aniki
import base.core.cli.Parser
import base.core.cli.subcommands.ConfigSubcommand
import base.core.cli.subcommands.ListProviders
import com.google.gson.Gson
import kotlinx.cli.ExperimentalCli
import java.io.File

@ExperimentalCli
fun main (args : Array<String>) {
    aniki.saveCfg()
    Parser.subcommands(ConfigSubcommand(), ListProviders())
    Parser.parse(args)
}