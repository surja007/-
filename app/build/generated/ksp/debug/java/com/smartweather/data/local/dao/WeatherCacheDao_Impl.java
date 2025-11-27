package com.smartweather.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.smartweather.data.local.entity.WeatherCacheEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WeatherCacheDao_Impl implements WeatherCacheDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WeatherCacheEntity> __insertionAdapterOfWeatherCacheEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteExpiredCache;

  public WeatherCacheDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWeatherCacheEntity = new EntityInsertionAdapter<WeatherCacheEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `weather_cache` (`locationKey`,`timestamp`,`temp`,`feelsLike`,`humidity`,`windSpeed`,`windDeg`,`conditionId`,`conditionText`,`iconUrl`,`cityName`,`latitude`,`longitude`,`cachedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WeatherCacheEntity entity) {
        statement.bindString(1, entity.getLocationKey());
        statement.bindLong(2, entity.getTimestamp());
        statement.bindDouble(3, entity.getTemp());
        statement.bindDouble(4, entity.getFeelsLike());
        statement.bindLong(5, entity.getHumidity());
        statement.bindDouble(6, entity.getWindSpeed());
        statement.bindLong(7, entity.getWindDeg());
        statement.bindLong(8, entity.getConditionId());
        statement.bindString(9, entity.getConditionText());
        statement.bindString(10, entity.getIconUrl());
        statement.bindString(11, entity.getCityName());
        statement.bindDouble(12, entity.getLatitude());
        statement.bindDouble(13, entity.getLongitude());
        statement.bindLong(14, entity.getCachedAt());
      }
    };
    this.__preparedStmtOfDeleteExpiredCache = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM weather_cache WHERE cachedAt < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertWeatherCache(final WeatherCacheEntity cache,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWeatherCacheEntity.insert(cache);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteExpiredCache(final long expiryTime,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteExpiredCache.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, expiryTime);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteExpiredCache.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getWeatherCache(final String locationKey,
      final Continuation<? super WeatherCacheEntity> $completion) {
    final String _sql = "SELECT * FROM weather_cache WHERE locationKey = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, locationKey);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WeatherCacheEntity>() {
      @Override
      @Nullable
      public WeatherCacheEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLocationKey = CursorUtil.getColumnIndexOrThrow(_cursor, "locationKey");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfTemp = CursorUtil.getColumnIndexOrThrow(_cursor, "temp");
          final int _cursorIndexOfFeelsLike = CursorUtil.getColumnIndexOrThrow(_cursor, "feelsLike");
          final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
          final int _cursorIndexOfWindSpeed = CursorUtil.getColumnIndexOrThrow(_cursor, "windSpeed");
          final int _cursorIndexOfWindDeg = CursorUtil.getColumnIndexOrThrow(_cursor, "windDeg");
          final int _cursorIndexOfConditionId = CursorUtil.getColumnIndexOrThrow(_cursor, "conditionId");
          final int _cursorIndexOfConditionText = CursorUtil.getColumnIndexOrThrow(_cursor, "conditionText");
          final int _cursorIndexOfIconUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "iconUrl");
          final int _cursorIndexOfCityName = CursorUtil.getColumnIndexOrThrow(_cursor, "cityName");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final WeatherCacheEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpLocationKey;
            _tmpLocationKey = _cursor.getString(_cursorIndexOfLocationKey);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final double _tmpTemp;
            _tmpTemp = _cursor.getDouble(_cursorIndexOfTemp);
            final double _tmpFeelsLike;
            _tmpFeelsLike = _cursor.getDouble(_cursorIndexOfFeelsLike);
            final int _tmpHumidity;
            _tmpHumidity = _cursor.getInt(_cursorIndexOfHumidity);
            final double _tmpWindSpeed;
            _tmpWindSpeed = _cursor.getDouble(_cursorIndexOfWindSpeed);
            final int _tmpWindDeg;
            _tmpWindDeg = _cursor.getInt(_cursorIndexOfWindDeg);
            final int _tmpConditionId;
            _tmpConditionId = _cursor.getInt(_cursorIndexOfConditionId);
            final String _tmpConditionText;
            _tmpConditionText = _cursor.getString(_cursorIndexOfConditionText);
            final String _tmpIconUrl;
            _tmpIconUrl = _cursor.getString(_cursorIndexOfIconUrl);
            final String _tmpCityName;
            _tmpCityName = _cursor.getString(_cursorIndexOfCityName);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _result = new WeatherCacheEntity(_tmpLocationKey,_tmpTimestamp,_tmpTemp,_tmpFeelsLike,_tmpHumidity,_tmpWindSpeed,_tmpWindDeg,_tmpConditionId,_tmpConditionText,_tmpIconUrl,_tmpCityName,_tmpLatitude,_tmpLongitude,_tmpCachedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
