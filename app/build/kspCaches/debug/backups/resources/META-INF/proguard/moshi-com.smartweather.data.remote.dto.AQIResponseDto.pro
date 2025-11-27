-if class com.smartweather.data.remote.dto.AQIResponseDto
-keepnames class com.smartweather.data.remote.dto.AQIResponseDto
-if class com.smartweather.data.remote.dto.AQIResponseDto
-keep class com.smartweather.data.remote.dto.AQIResponseDtoJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
