package com.smartweather.background;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.smartweather.data.local.dao.FavoriteCityDao;
import com.smartweather.data.location.LocationService;
import com.smartweather.domain.repository.WeatherRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class WeatherRefreshWorker_Factory {
  private final Provider<WeatherRepository> weatherRepositoryProvider;

  private final Provider<FavoriteCityDao> favoriteCityDaoProvider;

  private final Provider<LocationService> locationServiceProvider;

  public WeatherRefreshWorker_Factory(Provider<WeatherRepository> weatherRepositoryProvider,
      Provider<FavoriteCityDao> favoriteCityDaoProvider,
      Provider<LocationService> locationServiceProvider) {
    this.weatherRepositoryProvider = weatherRepositoryProvider;
    this.favoriteCityDaoProvider = favoriteCityDaoProvider;
    this.locationServiceProvider = locationServiceProvider;
  }

  public WeatherRefreshWorker get(Context context, WorkerParameters params) {
    return newInstance(context, params, weatherRepositoryProvider.get(), favoriteCityDaoProvider.get(), locationServiceProvider.get());
  }

  public static WeatherRefreshWorker_Factory create(
      Provider<WeatherRepository> weatherRepositoryProvider,
      Provider<FavoriteCityDao> favoriteCityDaoProvider,
      Provider<LocationService> locationServiceProvider) {
    return new WeatherRefreshWorker_Factory(weatherRepositoryProvider, favoriteCityDaoProvider, locationServiceProvider);
  }

  public static WeatherRefreshWorker newInstance(Context context, WorkerParameters params,
      WeatherRepository weatherRepository, FavoriteCityDao favoriteCityDao,
      LocationService locationService) {
    return new WeatherRefreshWorker(context, params, weatherRepository, favoriteCityDao, locationService);
  }
}
