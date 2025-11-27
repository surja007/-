-if class com.smartweather.data.remote.dto.CurrentDto
-keepnames class com.smartweather.data.remote.dto.CurrentDto
-if class com.smartweather.data.remote.dto.CurrentDto
-keep class com.smartweather.data.remote.dto.CurrentDtoJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
