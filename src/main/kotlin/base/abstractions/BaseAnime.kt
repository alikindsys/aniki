package base.abstractions

import org.openqa.selenium.WebDriver

abstract class BaseAnime {
    abstract val Name : String
    abstract fun getEpisodes(anime : BaseAnime, driver : WebDriver) : List<BaseEpisode>
}