package com.example.project313

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.project313.ui.detail.EstateDetailViewModel
import com.example.project313.ui.explore.ExploreViewModel
import com.example.project313.ui.intro.IntroViewModel
import com.example.project313.ui.map.MapExplorerViewModel
import com.example.project313.ui.navigation.MainViewModel
import com.example.project313.ui.navigation.NavHost
import com.example.project313.ui.saved.SavedPropertiesViewModel
import com.example.project313.ui.theme.VerdantEstatesTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val introViewModel: IntroViewModel by viewModels()
    private val exploreViewModel: ExploreViewModel by viewModels()
    private val estateDetailViewModel: EstateDetailViewModel by viewModels()
    private val mapExplorerViewModel: MapExplorerViewModel by viewModels()
    private val savedPropertiesViewModel: SavedPropertiesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VerdantEstatesTheme {
                NavHost(
                    mainViewModel = mainViewModel,
                    introViewModel = introViewModel,
                    exploreViewModel = exploreViewModel,
                    estateDetailViewModel = estateDetailViewModel,
                    mapExplorerViewModel = mapExplorerViewModel,
                    savedPropertiesViewModel = savedPropertiesViewModel
                )
            }
        }
    }
}