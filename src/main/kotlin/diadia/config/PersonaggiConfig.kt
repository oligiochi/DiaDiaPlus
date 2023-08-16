package diadia.config

import com.google.gson.annotations.SerializedName
import diadia.config.tipiPersonaggiConfig.CaneConfig
import diadia.config.tipiPersonaggiConfig.MagoConfig
import diadia.config.tipiPersonaggiConfig.StregaConfig

data class PersonaggiConfig(
    @SerializedName("Mago")
    val magoConfig: MagoConfig,
    @SerializedName("Strega")
    val stregaConfig: StregaConfig,
    @SerializedName("Cane")
    val caneConfig: CaneConfig
)