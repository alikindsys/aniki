package base.abstractions

import org.asynchttpclient.uri.Uri
import java.io.File

abstract class BaseEpisode {
    abstract val ResourceUri : Uri
    abstract fun download(file : File)
}