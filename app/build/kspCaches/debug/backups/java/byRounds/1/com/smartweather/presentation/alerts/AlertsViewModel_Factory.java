package com.smartweather.presentation.alerts;

import com.smartweather.domain.usecase.GetWeatherAlertsUseCase;
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
public final class AlertsViewModel_Factory implements Factory<AlertsViewModel> {
  private final Provider<GetWeatherAlertsUseCase> getWeatherAlertsUseCaseProvider;

  public AlertsViewModel_Factory(
      Provider<GetWeatherAlertsUseCase> getWeatherAlertsUseCaseProvider) {
    this.getWeatherAlertsUseCaseProvider = getWeatherAlertsUseCaseProvider;
  }

  @Override
  public AlertsViewModel get() {
    return newInstance(getWeatherAlertsUseCaseProvider.get());
  }

  public static AlertsViewModel_Factory create(
      Provider<GetWeatherAlertsUseCase> getWeatherAlertsUseCaseProvider) {
    return new AlertsViewModel_Factory(getWeatherAlertsUseCaseProvider);
  }

  public static AlertsViewModel newInstance(GetWeatherAlertsUseCase getWeatherAlertsUseCase) {
    return new AlertsViewModel(getWeatherAlertsUseCase);
  }
}
