package com.example.project313.ui.map

import com.example.project313.data.model.Estate
import com.example.project313.data.model.MapPin
import com.example.project313.data.model.NavigationTab

enum class MapLayerType(val id: String, val displayName: String) {
    STREET("street", "Standard Map (OSM)"),
    TOPO("topo", "Topographic / Terrain"),
    SATELLITE("satellite", "Satellite Imagery")
}

data class MapExplorerUiState(
    val regionTitle: String = "Travis County, Austin",
    val activeReservesCount: Int = 28,
    val pins: List<MapPin> = emptyList(),
    val selectedEstateId: String = "the-glass-pavilions",
    val selectedEstate: Estate? = null,
    val savedEstateIds: Set<String> = emptySet(),
    val currentNavigationTab: NavigationTab = NavigationTab.MAP,
    val unreadNotificationsCount: Int = 1,
    val userLocation: String = "Austin, TX",
    val currentLayerType: MapLayerType = MapLayerType.STREET,
    val locateTrigger: Long = 0L,
    val feedbackMessage: String? = null
)
