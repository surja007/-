-if class com.smartweather.data.remote.dto.HourlyDto
-keepnames class com.smartweather.data.remote.dto.HourlyDto
-if class com.smartweather.data.remote.dto.HourlyDto
-keep class com.smartweather.data.remote.dto.HourlyDtoJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
