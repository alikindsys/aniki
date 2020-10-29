package base.core.cli

import kotlinx.cli.*

val Parser = ArgParser("aniki")
val queueFile by Parser.option(ArgType.String, "queue" , "q")
