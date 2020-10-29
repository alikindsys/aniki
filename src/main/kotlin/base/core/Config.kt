package base.core

import java.io.File

data class Config (val driver : Driver, var folder : File ) {
    constructor(driver: Driver, path : String) : this(driver, File(path))
}
data class Driver (var type : DriverType, var path : File) {
    constructor(type: DriverType, path: String) : this(type, File(path))
}
enum class DriverType {GECKO, CHROME}