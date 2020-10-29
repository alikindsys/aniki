package base.core.cli

import kotlinx.cli.*

val Parser = ArgParser("aniki")
val input by Parser.option(ArgType.String, "input" , "i").multiple()
