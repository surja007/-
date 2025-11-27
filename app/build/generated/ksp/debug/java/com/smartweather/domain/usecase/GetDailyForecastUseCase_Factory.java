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
public final class GetDailyForecastUseCase_Factory implements Factory<GetDailyForecastUseCase> {
  private final Provider<WeatherRepository> repositoryProvider;

  public GetDailyForecastUseCase_Factory(Provider<WeatherRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetDailyForecastUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetDailyForecastUseCase_Factory create(
      Provider<WeatherRepository> repositoryProvider) {
    return new GetDailyForecastUseCase_Factory(repositoryProvider);
  }

  public static GetDailyForecastUseCase newInstance(WeatherRepository repository) {
    return new GetDailyForecastUseCase(repository);
  }
}
