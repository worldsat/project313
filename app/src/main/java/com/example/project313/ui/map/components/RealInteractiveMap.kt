package com.example.project313.ui.map.components

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.viewinterop.AndroidView
import com.example.project313.data.model.MapPin

class MapJsBridge(private val onPinClick: (String) -> Unit) {
    private val mainHandler = Handler(Looper.getMainLooper())

    @JavascriptInterface
    fun onPinClicked(estateId: String) {
        mainHandler.post {
            onPinClick(estateId)
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun RealInteractiveMap(
    pins: List<MapPin>,
    selectedEstateId: String,
    onPinClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    layerType: String = "street",
    locateTrigger: Long = 0L,
    cartoApiKey: String? = null
) {
    if (LocalInspectionMode.current) {
        ArchitecturalMapCanvas(
            pins = pins,
            selectedEstateId = selectedEstateId,
            onPinClick = onPinClick,
            modifier = modifier
        )
        return
    }

    val context = LocalContext.current
    val bridge = remember(onPinClick) { MapJsBridge(onPinClick) }
    var webViewRef: WebView? = remember { null }

    val initialHtml = remember(pins, cartoApiKey) {
        buildMapHtml(pins, selectedEstateId, layerType, cartoApiKey)
    }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { ctx ->
            WebView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    loadWithOverviewMode = true
                    useWideViewPort = true
                    cacheMode = WebSettings.LOAD_DEFAULT
                    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                    userAgentString = "Mozilla/5.0 (Linux; Android 14; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36 VerdantEstates/1.0"
                }
                setBackgroundColor(android.graphics.Color.parseColor("#EBF2EC"))
                addJavascriptInterface(bridge, "AndroidBridge")
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        view?.evaluateJavascript("highlightMarker('$selectedEstateId', false);", null)
                        view?.evaluateJavascript("switchLayer('$layerType');", null)
                    }
                }
                loadDataWithBaseURL("https://www.openstreetmap.org", initialHtml, "text/html", "UTF-8", null)
                webViewRef = this
            }
        },
        update = { webView ->
            webViewRef = webView
        }
    )

    LaunchedEffect(selectedEstateId) {
        webViewRef?.evaluateJavascript("highlightMarker('$selectedEstateId', true);", null)
    }

    LaunchedEffect(layerType) {
        webViewRef?.evaluateJavascript("switchLayer('$layerType');", null)
    }

    LaunchedEffect(locateTrigger) {
        if (locateTrigger > 0L) {
            webViewRef?.evaluateJavascript("recenterMap();", null)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            webViewRef?.destroy()
        }
    }
}

private fun buildMapHtml(
    pins: List<MapPin>,
    initialSelectedId: String,
    initialLayerType: String = "street",
    cartoApiKey: String? = null
): String {
    val pinsJson = pins.joinToString(separator = ",\n") { pin ->
        """
        {
            "id": "${pin.estateId}",
            "price": "${pin.priceLabel}",
            "lat": ${pin.latitude},
            "lng": ${pin.longitude}
        }
        """.trimIndent()
    }

    val cartoOptionalLayer = if (!cartoApiKey.isNullOrBlank()) {
        """
        baseLayers['carto'] = L.tileLayer('https://{s}.basemaps.cartocdn.com/rastertiles/voyager/{z}/{x}/{y}{r}.png?key=$cartoApiKey', {
            maxZoom: 19,
            subdomains: 'abcd',
            attribution: '&copy; OpenStreetMap contributors &copy; CARTO'
        });
        """.trimIndent()
    } else ""

    return """
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0, user-scalable=yes" />
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
        <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
        <style>
            html, body {
                margin: 0;
                padding: 0;
                width: 100%;
                height: 100%;
                background-color: #EBF2EC;
                font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
                overflow: hidden;
            }
            #map {
                width: 100%;
                height: 100%;
                background-color: #EBF2EC;
            }
            .leaflet-control-attribution {
                font-size: 8px !important;
                background: rgba(255, 255, 255, 0.6) !important;
                display: none !important;
            }
            .leaflet-control-zoom {
                display: none !important;
            }
            .estate-price-pill {
                display: inline-flex;
                align-items: center;
                justify-content: center;
                background: #FFFFFF;
                color: #1B4332;
                border: 1.5px solid #2D6A4F;
                border-radius: 50px;
                padding: 5px 12px;
                font-size: 12px;
                font-weight: 700;
                white-space: nowrap;
                box-shadow: 0 4px 14px rgba(0, 0, 0, 0.16);
                cursor: pointer;
                transition: transform 0.2s ease, background 0.2s ease, color 0.2s ease;
                transform-origin: center center;
            }
            .estate-price-pill:active {
                transform: scale(0.94);
            }
            .estate-price-pill.selected {
                background: #1B4332;
                color: #FFFFFF;
                border: 2px solid #52B788;
                box-shadow: 0 6px 18px rgba(27, 67, 50, 0.45);
                transform: scale(1.12);
                z-index: 1000 !important;
            }
            .estate-price-pill.selected::before {
                content: '● ';
                color: #52B788;
                font-size: 9px;
                margin-right: 4px;
            }
        </style>
    </head>
    <body>
        <div id="map"></div>
        <script>
            var pinsData = [
                $pinsJson
            ];
            var activeId = '$initialSelectedId';
            var markersMap = {};

            // Center on Austin, Texas Hill Country & Downtown
            var map = L.map('map', {
                center: [30.2780, -97.7780],
                zoom: 12.5,
                zoomControl: false,
                tap: true
            });

            // Reliable, watermark-free basemaps requiring NO API key
            var baseLayers = {
                'street': L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
                    maxZoom: 19,
                    attribution: '&copy; OpenStreetMap contributors'
                }),
                'topo': L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer/tile/{z}/{y}/{x}', {
                    maxZoom: 19,
                    attribution: 'Tiles &copy; Esri'
                }),
                'satellite': L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}', {
                    maxZoom: 19,
                    attribution: 'Tiles &copy; Esri'
                })
            };

            $cartoOptionalLayer

            var activeLayerKey = '$initialLayerType';
            var currentLayer = baseLayers[activeLayerKey] || baseLayers['street'];
            currentLayer.addTo(map);

            window.switchLayer = function(layerKey) {
                var target = baseLayers[layerKey] || baseLayers['street'];
                if (target && currentLayer !== target) {
                    map.removeLayer(currentLayer);
                    currentLayer = target;
                    currentLayer.addTo(map);
                }
            };

            window.recenterMap = function() {
                map.flyTo([30.2780, -97.7780], 12.5, {
                    duration: 0.8
                });
            };

            function createPillIcon(price, isSelected) {
                var selectedClass = isSelected ? 'estate-price-pill selected' : 'estate-price-pill';
                return L.divIcon({
                    className: 'custom-pill-icon',
                    html: '<div class="' + selectedClass + '">' + price + '</div>',
                    iconSize: null,
                    iconAnchor: [38, 16]
                });
            }

            pinsData.forEach(function(pin) {
                var isSelected = (pin.id === activeId);
                var icon = createPillIcon(pin.price, isSelected);
                var marker = L.marker([pin.lat, pin.lng], { icon: icon }).addTo(map);
                
                marker.on('click', function() {
                    highlightMarker(pin.id, true);
                    if (window.AndroidBridge && window.AndroidBridge.onPinClicked) {
                        window.AndroidBridge.onPinClicked(pin.id);
                    }
                });

                markersMap[pin.id] = {
                    marker: marker,
                    data: pin
                };
            });

            window.highlightMarker = function(estateId, shouldFly) {
                activeId = estateId;
                for (var id in markersMap) {
                    var item = markersMap[id];
                    var isSelected = (id === estateId);
                    item.marker.setIcon(createPillIcon(item.data.price, isSelected));
                    if (isSelected && shouldFly) {
                        map.flyTo([item.data.lat, item.data.lng], Math.max(map.getZoom(), 13), {
                            duration: 0.7
                        });
                    }
                }
            };
        </script>
    </body>
    </html>
    """.trimIndent()
}
