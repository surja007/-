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
public final class GetHourlyForecastUseCase_Factory implements Factory<GetHourlyForecastUseCase> {
  private final Provider<WeatherRepository> repositoryProvider;

  public GetHourlyForecastUseCase_Factory(Provider<WeatherRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetHourlyForecastUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetHourlyForecastUseCase_Factory create(
      Provider<WeatherRepository> repositoryProvider) {
    return new GetHourlyForecastUseCase_Factory(repositoryProvider);
  }

  public static GetHourlyForecastUseCase newInstance(WeatherRepository repository) {
    return new GetHourlyForecastUseCase(repository);
  }
}
