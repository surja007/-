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
public final class GetCurrentWeatherUseCase_Factory implements Factory<GetCurrentWeatherUseCase> {
  private final Provider<WeatherRepository> repositoryProvider;

  public GetCurrentWeatherUseCase_Factory(Provider<WeatherRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetCurrentWeatherUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetCurrentWeatherUseCase_Factory create(
      Provider<WeatherRepository> repositoryProvider) {
    return new GetCurrentWeatherUseCase_Factory(repositoryProvider);
  }

  public static GetCurrentWeatherUseCase newInstance(WeatherRepository repository) {
    return new GetCurrentWeatherUseCase(repository);
  }
}
