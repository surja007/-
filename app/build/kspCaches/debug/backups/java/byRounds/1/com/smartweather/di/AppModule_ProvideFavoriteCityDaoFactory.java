package com.smartweather.di;

import com.smartweather.data.local.WeatherDatabase;
import com.smartweather.data.local.dao.FavoriteCityDao;
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
public final class AppModule_ProvideFavoriteCityDaoFactory implements Factory<FavoriteCityDao> {
  private final Provider<WeatherDatabase> databaseProvider;

  public AppModule_ProvideFavoriteCityDaoFactory(Provider<WeatherDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public FavoriteCityDao get() {
    return provideFavoriteCityDao(databaseProvider.get());
  }

  public static AppModule_ProvideFavoriteCityDaoFactory create(
      Provider<WeatherDatabase> databaseProvider) {
    return new AppModule_ProvideFavoriteCityDaoFactory(databaseProvider);
  }

  public static FavoriteCityDao provideFavoriteCityDao(WeatherDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideFavoriteCityDao(database));
  }
}
