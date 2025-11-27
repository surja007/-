package com.smartweather.background;

import com.smartweather.data.location.LocationService;
import com.smartweather.domain.repository.WeatherRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class WeatherAlertReceiver_MembersInjector implements MembersInjector<WeatherAlertReceiver> {
  private final Provider<WeatherRepository> weatherRepositoryProvider;

  private final Provider<LocationService> locationServiceProvider;

  public WeatherAlertReceiver_MembersInjector(Provider<WeatherRepository> weatherRepositoryProvider,
      Provider<LocationService> locationServiceProvider) {
    this.weatherRepositoryProvider = weatherRepositoryProvider;
    this.locationServiceProvider = locationServiceProvider;
  }

  public static MembersInjector<WeatherAlertReceiver> create(
      Provider<WeatherRepository> weatherRepositoryProvider,
      Provider<LocationService> locationServiceProvider) {
    return new WeatherAlertReceiver_MembersInjector(weatherRepositoryProvider, locationServiceProvider);
  }

  @Override
  public void injectMembers(WeatherAlertReceiver instance) {
    injectWeatherRepository(instance, weatherRepositoryProvider.get());
    injectLocationService(instance, locationServiceProvider.get());
  }

  @InjectedFieldSignature("com.smartweather.background.WeatherAlertReceiver.weatherRepository")
  public static void injectWeatherRepository(WeatherAlertReceiver instance,
      WeatherRepository weatherRepository) {
    instance.weatherRepository = weatherRepository;
  }

  @InjectedFieldSignature("com.smartweather.background.WeatherAlertReceiver.locationService")
  public static void injectLocationService(WeatherAlertReceiver instance,
      LocationService locationService) {
    instance.locationService = locationService;
  }
}
