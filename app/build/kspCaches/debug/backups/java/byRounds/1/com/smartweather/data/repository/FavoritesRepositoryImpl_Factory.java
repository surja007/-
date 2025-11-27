package com.smartweather.data.repository;

import com.smartweather.data.local.dao.FavoriteCityDao;
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
public final class FavoritesRepositoryImpl_Factory implements Factory<FavoritesRepositoryImpl> {
  private final Provider<FavoriteCityDao> daoProvider;

  public FavoritesRepositoryImpl_Factory(Provider<FavoriteCityDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public FavoritesRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static FavoritesRepositoryImpl_Factory create(Provider<FavoriteCityDao> daoProvider) {
    return new FavoritesRepositoryImpl_Factory(daoProvider);
  }

  public static FavoritesRepositoryImpl newInstance(FavoriteCityDao dao) {
    return new FavoritesRepositoryImpl(dao);
  }
}
