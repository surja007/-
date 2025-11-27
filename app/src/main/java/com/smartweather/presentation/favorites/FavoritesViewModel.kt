package com.smartweather.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartweather.domain.model.FavoriteCity
import com.smartweather.domain.usecase.ManageFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val manageFavoritesUseCase: ManageFavoritesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<FavoritesUiState>(FavoritesUiState.Loading)
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        viewModelScope.launch {
            try {
                manageFavoritesUseCase.getAllFavorites().collect { favorites ->
                    val currentState = _uiState.value
                    val showSearch = if (currentState is FavoritesUiState.Success) {
                        currentState.showSearch
                    } else {
                        false
                    }
                    
                    _uiState.value = FavoritesUiState.Success(
                        favorites = favorites,
                        showSearch = showSearch
                    )
                }
            } catch (e: Exception) {
                _uiState.value = FavoritesUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun addFavorite(city: FavoriteCity) {
        viewModelScope.launch {
            manageFavoritesUseCase.addFavorite(city)
        }
    }

    fun removeFavorite(cityId: String) {
        viewModelScope.launch {
            manageFavoritesUseCase.removeFavorite(cityId)
        }
    }

    fun reorderFavorites(fromIndex: Int, toIndex: Int) {
        viewModelScope.launch {
            val currentState = _uiState.value
            if (currentState is FavoritesUiState.Success) {
                val reorderedList = currentState.favorites.toMutableList()
                val item = reorderedList.removeAt(fromIndex)
                reorderedList.add(toIndex, item)
                
                manageFavoritesUseCase.reorderFavorites(reorderedList)
            }
        }
    }

    fun toggleSearchMode() {
        val currentState = _uiState.value
        if (currentState is FavoritesUiState.Success) {
            _uiState.value = currentState.copy(showSearch = !currentState.showSearch)
            if (!currentState.showSearch) {
                _searchQuery.value = ""
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}

sealed class FavoritesUiState {
    object Loading : FavoritesUiState()
    data class Success(
        val favorites: List<FavoriteCity>,
        val showSearch: Boolean = false
    ) : FavoritesUiState()
    data class Error(val message: String) : FavoritesUiState()
}
