package base.core.cli.subcommands

import base.core.ProviderRegistry
import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand

@ExperimentalCli
class ListProviders : Subcommand("list","Lists all sources.") {
    override fun execute() {
        println("All sources")
        println("")
        for (source in ProviderRegistry) {
            print("${source::class.simpleName} - ${source.ProviderUrl}")
        }
    }
}