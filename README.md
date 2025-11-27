# Smart Weather Forecast + Alerts

A polished Android weather app built with Kotlin and Jetpack Compose, featuring real-time weather data, air quality monitoring, severe weather alerts, and Material You dynamic theming.

## Features

### Weather Data
- **Real-time Weather**: Current conditions with temperature, humidity, wind, feels-like
- **Hourly Forecast**: 48-hour forecast carousel with icons and precipitation probability
- **Daily Forecast**: 10-day forecast with expandable cards, sunrise/sunset times
- **Air Quality Index (AQI)**: Current AQI with pollutant details and health advice
- **Severe Weather Alerts**: Official alerts with notifications (FCM or offline AlarmManager)

### User Features
- **Favorite Cities**: Save, search, and quick-switch between favorite locations
- **Location Services**: Auto-detect current location with reverse geocoding
- **Settings**: Customize units, refresh interval, notifications, and theme
- **Offline Support**: Cached data for last-known conditions (30-min expiry)
- **Background Refresh**: Periodic updates via WorkManager

### Design
- **Material You**: Dynamic color theming based on device wallpaper
- **Beautiful Animations**: Weather-reactive gradients and smooth transitions
- **Responsive UI**: Optimized for all screen sizes
- **Accessibility**: TalkBack support and proper contrast ratios

## Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose with Material 3
- **Architecture**: Clean Architecture (Domain, Data, Presentation layers) 
- **DI**: Hilt/Dagger
- **Networking**: Retrofit + OkHttp + Moshi
- **Database**: Room
- **Background**: WorkManager
- **Push**: Firebase Cloud Messaging (FCM) or AlarmManager (offline alternative)
- **Location**: FusedLocationProviderClient
- **Images**: Coil
- **Animations**: Lottie Compose
- **Testing**: JUnit, MockK, Turbine, Compose UI tests

## Project Structure

```
app/
├── data/
│   ├── local/          # Room database, DAOs, entities
│   ├── remote/         # Retrofit API service, DTOs
│   └── repository/     # Repository implementations
├── domain/
│   ├── model/          # Domain models
│   ├── repository/     # Repository interfaces
│   └── usecase/        # Business logic use cases
├── presentation/
│   ├── home/           # Home screen UI and ViewModel
│   └── theme/          # Material 3 theming
├── di/                 # Hilt dependency injection modules
└── background/         # WorkManager and FCM services
```

## Notification Options

### Option 1: Offline Alerts (No Setup Required) ✅
**Already working!** The app uses:
- **AlarmManager**: Checks weather every 30 minutes
- **WorkManager**: Background refresh with network constraints
- **Local Notifications**: Shows alerts without any server

**Pros**: No Firebase setup, works offline, no dependencies  
**Cons**: Less instant (30-min intervals), uses battery for periodic checks

### Option 2: Firebase Cloud Messaging (Optional)
For instant push notifications:
1. Create Firebase project at https://console.firebase.google.com
2. Add Android app with package `com.smartweather`
3. Download `google-services.json` → place in `app/` folder

**Pros**: Instant alerts, battery efficient  
**Cons**: Requires Firebase setup, needs internet

**Default**: App uses offline alerts (Option 1) out of the box!

## Setup

1. **Clone the repository**

2. **Add API Keys**
   - Copy `local.properties.example` to `local.properties`
   - Add your API keys:
     ```properties
     WEATHER_API_KEY=your_openweathermap_key
     AQI_API_KEY=your_aqi_provider_key
     ```
   - Get API keys from:
     - [OpenWeatherMap](https://openweathermap.org/api) (One Call API 3.0)
     - [OpenWeatherMap Air Pollution API](https://openweathermap.org/api/air-pollution)

3. **Firebase Setup**
   - Create a Firebase project
   - Download `google-services.json` and place in `app/` directory
   - Enable Firebase Cloud Messaging

4. **Build and Run**
   ```bash
   ./gradlew assembleDebug
   ```

## API Configuration

The app uses OpenWeatherMap APIs:
- **One Call API 3.0**: Weather data (current, hourly, daily, alerts)
- **Air Pollution API**: AQI and pollutant data

## Permissions

- `ACCESS_FINE_LOCATION`: For current location weather
- `ACCESS_COARSE_LOCATION`: Fallback location permission
- `INTERNET`: Network access for API calls
- `POST_NOTIFICATIONS`: Push notifications for severe alerts

## Testing

Run unit tests:
```bash
./gradlew test
```

Run instrumented tests:
```bash
./gradlew connectedAndroidTest
```

## Architecture

The app follows Clean Architecture principles:

- **Presentation Layer**: Compose UI + ViewModels (MVVM pattern)
- **Domain Layer**: Use cases and business logic
- **Data Layer**: Repositories abstracting local (Room) and remote (Retrofit) data sources

## Key Features Implementation

### Material You Dynamic Colors
- Uses `dynamicDarkColorScheme` and `dynamicLightColorScheme` on Android 12+
- Fallback to static color schemes on older versions

### Weather Animations
- Dynamic gradients based on weather conditions
- Particle effects for rain, snow (ready for Lottie integration)
- Smooth transitions with Compose animations

### Background Refresh
- WorkManager periodic tasks (30-minute intervals)
- Network-aware constraints
- Exponential backoff on failures

### Caching Strategy
- 30-minute cache expiry for weather data
- Offline-first approach with fallback to cached data
- Automatic cache cleanup

### Push Notifications
- FCM integration for severe weather alerts
- Priority-based notifications (severity-dependent)
- Notification channels for Android O+

## Roadmap

### v1.0 MVP (✅ Completed)
- ✅ Home screen with current weather
- ✅ Favorites management
- ✅ Background refresh
- ✅ Material You theming

### v1.1 (✅ Completed)
- ✅ Hourly forecast carousel (48 hours)
- ✅ Daily forecast list (10 days)
- ✅ AQI display with health advice
- ✅ Alerts screen (active/past)
- ✅ Settings screen (units, notifications, theme)
- ✅ Location services integration
- ✅ Push notifications via FCM

### v1.2 (📋 Planned)
- 📋 Home screen widgets (small, medium, large)
- 📋 Lottie weather animations
- 📋 Particle effects (rain, snow)
- 📋 Advanced search with autocomplete
- 📋 Weather maps and radar
- 📋 Historical weather data

## Contributing

Contributions are welcome! Please follow the existing code style and architecture patterns.

## License

[Your License Here]

## Acknowledgments

- Weather data provided by OpenWeatherMap
- Icons and animations from various sources
- Built with ❤️ using Jetpack Compose
