package com.smartweather.domain.usecase;

import com.smartweather.domain.repository.FavoritesRepository;
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
public final class ManageFavoritesUseCase_Factory implements Factory<ManageFavoritesUseCase> {
  private final Provider<FavoritesRepository> repositoryProvider;

  public ManageFavoritesUseCase_Factory(Provider<FavoritesRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ManageFavoritesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static ManageFavoritesUseCase_Factory create(
      Provider<FavoritesRepository> repositoryProvider) {
    return new ManageFavoritesUseCase_Factory(repositoryProvider);
  }

  public static ManageFavoritesUseCase newInstance(FavoritesRepository repository) {
    return new ManageFavoritesUseCase(repository);
  }
}
