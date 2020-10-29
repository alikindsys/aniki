package base.core

import com.google.gson.Gson
import java.io.File

class aniki {
    companion object {
        val config : Config = getCfg()

        private fun getCfg(): Config {
            val file = File("./config.json")
            return if(!file.exists()) {
                Config(Driver(DriverType.CHROME, "./chromedriver"),"./Anime")
            } else {
                Gson().fromJson(file.readText(), Config::class.java)
            }
        }

        fun saveCfg() {
            val file = File("./config.json")
            file.writeText(Gson().toJson(config))
        }
    }
}
