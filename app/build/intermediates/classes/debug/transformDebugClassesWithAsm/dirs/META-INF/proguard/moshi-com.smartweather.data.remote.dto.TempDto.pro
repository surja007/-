-if class com.smartweather.data.remote.dto.TempDto
-keepnames class com.smartweather.data.remote.dto.TempDto
-if class com.smartweather.data.remote.dto.TempDto
-keep class com.smartweather.data.remote.dto.TempDtoJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
