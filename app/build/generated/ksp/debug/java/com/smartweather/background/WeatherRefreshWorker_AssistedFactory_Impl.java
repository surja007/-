package com.smartweather.background;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class WeatherRefreshWorker_AssistedFactory_Impl implements WeatherRefreshWorker_AssistedFactory {
  private final WeatherRefreshWorker_Factory delegateFactory;

  WeatherRefreshWorker_AssistedFactory_Impl(WeatherRefreshWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public WeatherRefreshWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<WeatherRefreshWorker_AssistedFactory> create(
      WeatherRefreshWorker_Factory delegateFactory) {
    return InstanceFactory.create(new WeatherRefreshWorker_AssistedFactory_Impl(delegateFactory));
  }
}
