package diadia.config
import com.google.gson.annotations.SerializedName

data class StanzaConfig(
    @SerializedName("SogliaMagicaStandar")
    val sogliaMagicaStandar: Int
)