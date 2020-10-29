package base.core

import com.google.gson.Gson
import org.asynchttpclient.uri.Uri
import org.openqa.selenium.WebDriver
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

        fun tryDownloadSeries(uri: Uri, driver: WebDriver) : Boolean {
            val provider = ProviderRegistry.firstOrNull{ it.ProviderUrl.authority == uri.authority } ?: return false
            val anime = provider.getAnime(uri, driver)
            println("[${provider::class.simpleName}] Detected anime : ${anime.Name}")
            println("[${provider::class.simpleName}] Getting episodes. Please wait.")
            val episodes = anime.getEpisodes(anime,driver)
            println("[${provider::class.simpleName}] Got ${episodes.size} episodes.")
            println("[${provider::class.simpleName}] Download of the series will begin shortly.")
            for((i,episode) in episodes.withIndex()) {
                val file = File(config.folder,"${anime.Name}/${(i+1)}.mp4")
                episode.download(file)
            }
            return true
        }
    }
}
