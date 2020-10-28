package base.extensions

import org.openqa.selenium.WebDriver

/**
 * Works like get() but doesn't do anything in case the driver is already on that website.
 * @param url The website
 */
public fun WebDriver.safeGet(url: String) {
    if(this.currentUrl == url) return
    this.get(url)
}