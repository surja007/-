-if class com.smartweather.data.remote.dto.AQIDataDto
-keepnames class com.smartweather.data.remote.dto.AQIDataDto
-if class com.smartweather.data.remote.dto.AQIDataDto
-keep class com.smartweather.data.remote.dto.AQIDataDtoJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
