import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;

public class ContenidoRespuestaCambio {

    private String result;
    private String documentation;

    @SerializedName("terms_of_use")
    private String termsOfUse;

    @SerializedName("time_last_update_unix")
    private long timeLastUpdateUnix;

    @SerializedName("time_last_update_utc")
    private String timeLastUpdateUtc;

    @SerializedName("time_next_update_unix")
    private long timeNextUpdateUnix;

    @SerializedName("time_next_update_utc")
    private String timeNextUpdateUtc;

    @SerializedName("base_code")
    private String baseCode;

    @SerializedName("target_code")
    private String targetCode;

    @SerializedName("conversion_rate")
    private double conversionRate;

    @Getter
    @SerializedName("conversion_result")
    private double conversionResult;

}