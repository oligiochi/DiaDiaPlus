package diadia.config

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import diadia.util.getAbsolutePath
import java.io.File

data class ConfigBuilder(
    @SerializedName("borsa")
    val borsaConfig: BorsaConfig,
    @SerializedName("diaDia")
    val diaDiaConfig: DiaDiaConfig,
    @SerializedName("giocatore")
    val giocatoreConfig: GiocatoreConfig,
    @SerializedName("labirinto")
    val labirintoConfig: LabirintoConfig,
    @SerializedName("Stanza")
    val StanzaConfig: StanzaConfig
)
fun leggiDaFileJson(): ConfigBuilder? {
    val gson = Gson()
    return try {
        val file = File(getAbsolutePath("src/main/resources/Config/Config.json"))
        gson.fromJson(file.readText(), ConfigBuilder::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
