package diadia.config


import com.google.gson.annotations.SerializedName

data class LabirintoConfig(
    @SerializedName("LabDef")
    val labDef: String
)