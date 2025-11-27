package com.smartweather.presentation.favorites;

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
public final class FavoritesViewModel_Factory implements Factory<FavoritesViewModel> {
  private final Provider<ManageFavoritesUseCase> manageFavoritesUseCaseProvider;

  public FavoritesViewModel_Factory(
      Provider<ManageFavoritesUseCase> manageFavoritesUseCaseProvider) {
    this.manageFavoritesUseCaseProvider = manageFavoritesUseCaseProvider;
  }

  @Override
  public FavoritesViewModel get() {
    return newInstance(manageFavoritesUseCaseProvider.get());
  }

  public static FavoritesViewModel_Factory create(
      Provider<ManageFavoritesUseCase> manageFavoritesUseCaseProvider) {
    return new FavoritesViewModel_Factory(manageFavoritesUseCaseProvider);
  }

  public static FavoritesViewModel newInstance(ManageFavoritesUseCase manageFavoritesUseCase) {
    return new FavoritesViewModel(manageFavoritesUseCase);
  }
}
