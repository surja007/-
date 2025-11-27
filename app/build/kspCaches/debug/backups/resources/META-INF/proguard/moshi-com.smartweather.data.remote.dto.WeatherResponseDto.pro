-if class com.smartweather.data.remote.dto.WeatherResponseDto
-keepnames class com.smartweather.data.remote.dto.WeatherResponseDto
-if class com.smartweather.data.remote.dto.WeatherResponseDto
-keep class com.smartweather.data.remote.dto.WeatherResponseDtoJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
