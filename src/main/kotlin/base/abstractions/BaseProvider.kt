package base.abstractions

import org.asynchttpclient.uri.Uri
import org.openqa.selenium.WebDriver

abstract class BaseProvider {
    abstract val ProviderUrl : Uri
    abstract fun search(query: String, driver: WebDriver) : BaseAnime?
    abstract fun getAnime(link : Uri, driver: WebDriver) : BaseAnime
}