package com.smartweather.presentation.home;

import com.smartweather.data.location.LocationService;
import com.smartweather.domain.usecase.GetAQIUseCase;
import com.smartweather.domain.usecase.GetCurrentWeatherUseCase;
import com.smartweather.domain.usecase.GetDailyForecastUseCase;
import com.smartweather.domain.usecase.GetHourlyForecastUseCase;
import com.smartweather.domain.usecase.GetWeatherAlertsUseCase;
import com.smartweather.domain.usecase.ManageFavoritesUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<GetCurrentWeatherUseCase> getCurrentWeatherUseCaseProvider;

  private final Provider<GetAQIUseCase> getAQIUseCaseProvider;

  private final Provider<GetWeatherAlertsUseCase> getWeatherAlertsUseCaseProvider;

  private final Provider<ManageFavoritesUseCase> manageFavoritesUseCaseProvider;

  private final Provider<GetHourlyForecastUseCase> getHourlyForecastUseCaseProvider;

  private final Provider<GetDailyForecastUseCase> getDailyForecastUseCaseProvider;

  private final Provider<LocationService> locationServiceProvider;

  public HomeViewModel_Factory(Provider<GetCurrentWeatherUseCase> getCurrentWeatherUseCaseProvider,
      Provider<GetAQIUseCase> getAQIUseCaseProvider,
      Provider<GetWeatherAlertsUseCase> getWeatherAlertsUseCaseProvider,
      Provider<ManageFavoritesUseCase> manageFavoritesUseCaseProvider,
      Provider<GetHourlyForecastUseCase> getHourlyForecastUseCaseProvider,
      Provider<GetDailyForecastUseCase> getDailyForecastUseCaseProvider,
      Provider<LocationService> locationServiceProvider) {
    this.getCurrentWeatherUseCaseProvider = getCurrentWeatherUseCaseProvider;
    this.getAQIUseCaseProvider = getAQIUseCaseProvider;
    this.getWeatherAlertsUseCaseProvider = getWeatherAlertsUseCaseProvider;
    this.manageFavoritesUseCaseProvider = manageFavoritesUseCaseProvider;
    this.getHourlyForecastUseCaseProvider = getHourlyForecastUseCaseProvider;
    this.getDailyForecastUseCaseProvider = getDailyForecastUseCaseProvider;
    this.locationServiceProvider = locationServiceProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(getCurrentWeatherUseCaseProvider.get(), getAQIUseCaseProvider.get(), getWeatherAlertsUseCaseProvider.get(), manageFavoritesUseCaseProvider.get(), getHourlyForecastUseCaseProvider.get(), getDailyForecastUseCaseProvider.get(), locationServiceProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<GetCurrentWeatherUseCase> getCurrentWeatherUseCaseProvider,
      Provider<GetAQIUseCase> getAQIUseCaseProvider,
      Provider<GetWeatherAlertsUseCase> getWeatherAlertsUseCaseProvider,
      Provider<ManageFavoritesUseCase> manageFavoritesUseCaseProvider,
      Provider<GetHourlyForecastUseCase> getHourlyForecastUseCaseProvider,
      Provider<GetDailyForecastUseCase> getDailyForecastUseCaseProvider,
      Provider<LocationService> locationServiceProvider) {
    return new HomeViewModel_Factory(getCurrentWeatherUseCaseProvider, getAQIUseCaseProvider, getWeatherAlertsUseCaseProvider, manageFavoritesUseCaseProvider, getHourlyForecastUseCaseProvider, getDailyForecastUseCaseProvider, locationServiceProvider);
  }

  public static HomeViewModel newInstance(GetCurrentWeatherUseCase getCurrentWeatherUseCase,
      GetAQIUseCase getAQIUseCase, GetWeatherAlertsUseCase getWeatherAlertsUseCase,
      ManageFavoritesUseCase manageFavoritesUseCase,
      GetHourlyForecastUseCase getHourlyForecastUseCase,
      GetDailyForecastUseCase getDailyForecastUseCase, LocationService locationService) {
    return new HomeViewModel(getCurrentWeatherUseCase, getAQIUseCase, getWeatherAlertsUseCase, manageFavoritesUseCase, getHourlyForecastUseCase, getDailyForecastUseCase, locationService);
  }
}
