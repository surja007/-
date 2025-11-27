-if class com.smartweather.data.remote.dto.WeatherConditionDto
-keepnames class com.smartweather.data.remote.dto.WeatherConditionDto
-if class com.smartweather.data.remote.dto.WeatherConditionDto
-keep class com.smartweather.data.remote.dto.WeatherConditionDtoJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
