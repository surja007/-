package com.smartweather.di;

import com.smartweather.data.local.WeatherDatabase;
import com.smartweather.data.local.dao.WeatherCacheDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AppModule_ProvideWeatherCacheDaoFactory implements Factory<WeatherCacheDao> {
  private final Provider<WeatherDatabase> databaseProvider;

  public AppModule_ProvideWeatherCacheDaoFactory(Provider<WeatherDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public WeatherCacheDao get() {
    return provideWeatherCacheDao(databaseProvider.get());
  }

  public static AppModule_ProvideWeatherCacheDaoFactory create(
      Provider<WeatherDatabase> databaseProvider) {
    return new AppModule_ProvideWeatherCacheDaoFactory(databaseProvider);
  }

  public static WeatherCacheDao provideWeatherCacheDao(WeatherDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideWeatherCacheDao(database));
  }
}
