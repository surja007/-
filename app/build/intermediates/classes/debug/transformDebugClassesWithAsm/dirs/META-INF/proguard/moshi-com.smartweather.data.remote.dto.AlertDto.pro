-if class com.smartweather.data.remote.dto.AlertDto
-keepnames class com.smartweather.data.remote.dto.AlertDto
-if class com.smartweather.data.remote.dto.AlertDto
-keep class com.smartweather.data.remote.dto.AlertDtoJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
