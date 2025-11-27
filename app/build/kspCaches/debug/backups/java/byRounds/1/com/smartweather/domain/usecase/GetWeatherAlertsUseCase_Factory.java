package com.smartweather.domain.usecase;

import com.smartweather.domain.repository.WeatherRepository;
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
public final class GetWeatherAlertsUseCase_Factory implements Factory<GetWeatherAlertsUseCase> {
  private final Provider<WeatherRepository> repositoryProvider;

  public GetWeatherAlertsUseCase_Factory(Provider<WeatherRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetWeatherAlertsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetWeatherAlertsUseCase_Factory create(
      Provider<WeatherRepository> repositoryProvider) {
    return new GetWeatherAlertsUseCase_Factory(repositoryProvider);
  }

  public static GetWeatherAlertsUseCase newInstance(WeatherRepository repository) {
    return new GetWeatherAlertsUseCase(repository);
  }
}
