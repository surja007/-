package com.smartweather.data.repository;

import com.smartweather.data.local.dao.WeatherCacheDao;
import com.smartweather.data.remote.WeatherApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class WeatherRepositoryImpl_Factory implements Factory<WeatherRepositoryImpl> {
  private final Provider<WeatherApiService> apiServiceProvider;

  private final Provider<WeatherCacheDao> cacheDaoProvider;

  public WeatherRepositoryImpl_Factory(Provider<WeatherApiService> apiServiceProvider,
      Provider<WeatherCacheDao> cacheDaoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.cacheDaoProvider = cacheDaoProvider;
  }

  @Override
  public WeatherRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), cacheDaoProvider.get());
  }

  public static WeatherRepositoryImpl_Factory create(Provider<WeatherApiService> apiServiceProvider,
      Provider<WeatherCacheDao> cacheDaoProvider) {
    return new WeatherRepositoryImpl_Factory(apiServiceProvider, cacheDaoProvider);
  }

  public static WeatherRepositoryImpl newInstance(WeatherApiService apiService,
      WeatherCacheDao cacheDao) {
    return new WeatherRepositoryImpl(apiService, cacheDao);
  }
}
