# DESIGN.md — Verdant Estates (Android Jetpack Compose & Material 3 Spec)

> **Specification Version:** 1.0.0  
> **Target Framework:** Jetpack Compose (androidx.compose.material3)  
> **Brand & App Theme:** Verdant Estates — Sustainable Eco-Luxury Real Estate (Austin, TX)  
> **Form Factor:** Android Mobile (Portrait, 390dp x 844dp reference base)

---

## 1. GLOBAL DESIGN TOKENS

All tokens conform to Google Material Design 3 (M3) specifications, tuned for an architectural, eco-luxury aesthetic dominated by deep forest hues, warm sage, crisp limestone surfaces, and brass/gold accents.

### 1.1 Color Palette (M3 Hex Tokens)

```kotlin
// Compose Color Definitions
val Primary = Color(0xFF1B4332)             // Deep Evergreen (Primary brand identity)
val OnPrimary = Color(0xFFFFFFFF)           // High contrast text/icon on Primary
val PrimaryContainer = Color(0xFF2D6A4F)    // Mid Forest Green for chips & accent surfaces
val OnPrimaryContainer = Color(0xFFE8F5E9)  // Mint tint for text on PrimaryContainer

val Secondary = Color(0xFF40916C)           // Vibrant Leaf Green (Secondary actions/badges)
val OnSecondary = Color(0xFFFFFFFF)
val SecondaryContainer = Color(0xFFD8F3DC)  // Soft Celadon tint container
val OnSecondaryContainer = Color(0xFF081C15)

val Tertiary = Color(0xFFD4A373)            // Architectural Brass / Warm Sand (Luxury accents)
val OnTertiary = Color(0xFF2B1700)
val TertiaryContainer = Color(0xFFFFDDB8)
val OnTertiaryContainer = Color(0xFF2B1700)

val Surface = Color(0xFFF8FAF7)             // Crisp Off-White / Natural Limestone
val OnSurface = Color(0xFF191C1B)           // Near-black charcoal typography
val SurfaceDim = Color(0xFFEBEFEA)          // Subdued canvas layer
val SurfaceBright = Color(0xFFFFFFFF)       // Card highlight & elevate base
val SurfaceVariant = Color(0xFFDEE5DD)      // Light sage neutral container
val OnSurfaceVariant = Color(0xFF404943)    // Medium-contrast secondary text & metadata
val SurfaceContainerLowest = Color(0xFFFFFFFF)
val SurfaceContainerLow = Color(0xFFF2F5F1)
val SurfaceContainer = Color(0xFFECEFEA)
val SurfaceContainerHigh = Color(0xFFE6EAE4)
val SurfaceContainerHighest = Color(0xFFE0E5DF)

val Background = Color(0xFFF8FAF7)
val OnBackground = Color(0xFF191C1B)

val Outline = Color(0xFF707972)             // Standard component borders
val OutlineVariant = Color(0xFFC0C9C0)      // Subtle hairline dividers and glass borders

// Special Ambient & Dark Shell Gradients (For Intro & Hero headers)
val IntroGradientDark = Color(0xFF071B12)   // Deep Pine
val IntroGradientMid = Color(0xFF143828)    // Verdant Forest
val IntroGradientLight = Color(0xFF245A40)  // Jade Luster
val GlassmorphismOverlay = Color(0xCCFFFFFF) // 80% opacity white with blur
val GlassmorphismBorder = Color(0x66FFFFFF)  // 40% translucent rim
```

### 1.2 Typography (Font: Plus Jakarta Sans / Inter)

| Token Name | Font Size (`sp`) | Line Height (`sp`) | Font Weight | Letter Spacing (`sp`) | Intended Usage |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **HeadlineLarge** | `32.sp` | `40.sp` | Bold (`FontWeight.W700`) | `-0.25.sp` | Screen titles, Intro punchlines |
| **HeadlineMedium**| `28.sp` | `36.sp` | SemiBold (`FontWeight.W600`)| `0.sp` | Property estate hero titles |
| **HeadlineSmall** | `24.sp` | `32.sp` | SemiBold (`FontWeight.W600`)| `0.sp` | Section headers, BottomSheet titles |
| **TitleLarge**    | `20.sp` | `26.sp` | SemiBold (`FontWeight.W600`)| `0.sp` | Estate listing card headings, Price tags |
| **TitleMedium**   | `16.sp` | `24.sp` | Medium (`FontWeight.W500`)  | `0.15.sp` | TopAppBar titles, Tab labels |
| **TitleSmall**    | `14.sp` | `20.sp` | Medium (`FontWeight.W500`)  | `0.1.sp` | Card subheaders, Stat labels |
| **BodyLarge**     | `16.sp` | `24.sp` | Normal (`FontWeight.W400`)  | `0.5.sp` | Primary descriptions, agent bios |
| **BodyMedium**    | `14.sp` | `20.sp` | Normal (`FontWeight.W400`)  | `0.25.sp` | Spec items (Beds, Baths, Sqft), reviews |
| **BodySmall**     | `12.sp` | `16.sp` | Normal (`FontWeight.W400`)  | `0.4.sp` | Disclaimer, legal copy, timestamps |
| **LabelLarge**    | `14.sp` | `20.sp` | SemiBold (`FontWeight.W600`)| `0.1.sp` | CTA button labels, interactive chips |
| **LabelMedium**   | `12.sp` | `16.sp` | Medium (`FontWeight.W500`)  | `0.5.sp` | Category filters, badges (LEED, Price) |
| **LabelSmall**    | `11.sp` | `14.sp` | Medium (`FontWeight.W500`)  | `0.5.sp` | Bottom navigation bar labels |

### 1.3 Spacing & Shapes

- **Screen Padding:** Standard horizontal gutter = `16.dp` (compact) to `20.dp` (generous luxury feel).
- **Vertical Rhythm:** Section margins = `24.dp`; Component internal padding = `16.dp`; Micro spacing = `8.dp` or `4.dp`.
- **Corner Radii (Material 3 Shapes):**
    - **Full Pill (`RoundedCornerShape(percent = 50)`):** CTA Buttons, Floating Badges, Navigation indicator capsules, Filter chips.
    - **Extra Large (`RoundedCornerShape(32.dp)`):** Intro bottom floating sheet, Estate Hero bottom sheet overlays.
    - **Large (`RoundedCornerShape(24.dp)`):** Estate listing cards, Modal dialogs, Gallery viewports.
    - **Medium (`RoundedCornerShape(16.dp)`):** Spec badges, Agent profile cards, Search input fields.
    - **Small (`RoundedCornerShape(8.dp)`):** Small indicator chips, Map thumbnail badges.

---

## 2. SCREEN REFERENCE GALLERY

The mobile application consists of 5 dedicated screens structured as a coherent luxury architectural journey:

1. **`VerdantIntroScreen` (Intro & Welcome Tour)**
    - **Role:** First-launch immersive landing experience.
    - **Structure:** Full-screen organic wavy canvas gradient in emerald/pine, ambient concentric halo rings, floating accolade pills ("42 Curated Homes", "LEED Platinum"), and a bottom floating M3 Card with call-to-action buttons.
2. **`ExploreEstatesScreen` (Discovery Feed / Home)**
    - **Role:** Curated architectural listing feed.
    - **Structure:** `Scaffold` with custom search `TopAppBar`, horizontal filter chip carousel, hero "Featured Estate of the Month" billboard card, and vertical `LazyColumn` of architectural sanctuary cards.
3. **`EstateDetailScreen` (Property Deep Dive)**
    - **Role:** High-touch estate showcase and advisor contact sheet.
    - **Structure:** Edge-to-edge parallax photo hero with translucent navigation controls, floating specs bar (Beds, Baths, Sqft, Acreage, LEED rating), architectural narrative copy, curated photo grid, dedicated Sarah Jenkins listing advisor card, and fixed bottom booking bar.
4. **`SavedPropertiesScreen` (Favorites & Collections)**
    - **Role:** Saved luxury shortlist and price movement alerts.
    - **Structure:** Tabbed collections ("All Saved", "Hill Country", "Waterfront"), 2-column or list cards with "New Price" chips and quick-share / advisor consultation triggers.
5. **`MapExplorerScreen` (Interactive Geographic Reserve)**
    - **Role:** Austin enclave geospatial search.
    - **Structure:** Full-screen vector map view with custom eco-reserve pins ($7.4M, $5.8M, etc.) and an expandable `BottomSheetScaffold` displaying active estate preview cards with rapid navigation.

---

## 3. COMPONENT-LEVEL SPECIFICATIONS

### 3.1 App Chrome (Navigation & Bars)

#### Bottom Navigation Bar (`NavigationBar`)
- **Container:** Standard M3 `NavigationBar(containerColor = MaterialTheme.colorScheme.SurfaceContainerLow, tonalElevation = 3.dp)`.
- **Active Indicator:** Pill capsule shape `RoundedCornerShape(16.dp)`, background `PrimaryContainer`, icon & text in `OnPrimaryContainer` or `Primary`.
- **Items:**
    1. *Explore* (`Icons.Outlined.Home` / `Icons.Filled.Home`)
    2. *Map* (`Icons.Outlined.Place` / `Icons.Filled.Place`)
    3. *Saved* (`Icons.Outlined.BookmarkBorder` / `Icons.Filled.Bookmark`)
    4. *Profile* (`Icons.Outlined.Person` / `Icons.Filled.Person`)

#### Top App Bar (`CenterAlignedTopAppBar` / `TopAppBar`)
- **Style:** Flat or slightly translucent (`containerColor = Color.Transparent` on detail views; `Surface` on feeds).
- **Navigation Action:** Rounded back arrow button with circular surface background `Color.White.copy(alpha = 0.85f)`.
- **Actions:** Bookmark toggle button and Share button.

### 3.2 Custom Cards & Lists

#### Featured Estate Card (`FeaturedEstateCard`)
- **Container:** `Card(shape = RoundedCornerShape(24.dp), colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest))` with `elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)`.
- **Layout:**
    - `Box(modifier = Modifier.fillMaxWidth().height(260.dp))` hosting image with gradient scrim at top and bottom.
    - Floating status badge at top-left: `SuggestionChip` with "Exclusive Reserve" in `Surface.copy(alpha = 0.9f)`.
    - Floating price tag at bottom-right: `Text("$7,850,000", style = TitleLarge, color = Color.White)`.
    - Content column below image with `Row` for title + verified checkmark, followed by spec pills (`Row(horizontalArrangement = Arrangement.spacedBy(8.dp))`).

#### Advisor Representation Card (`AdvisorCard`)
- **Container:** `Surface(shape = RoundedCornerShape(16.dp), color = SurfaceContainerLow, border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.5f)))`.
- **Layout:** `Row(verticalAlignment = Alignment.CenterVertically)`
    - Circular avatar (size `56.dp`, clip to `CircleShape`).
    - Text column: Name ("Sarah Jenkins"), Role ("Principal Private Advisor • Austin Luxury Division"), License ID.
    - Trailing action button: `IconButton` or compact `FilledTonalButton` with Phone/Chat icon in `Primary`.

### 3.3 Buttons & Interactive Elements

- **Primary Action (CTA):**
    - Component: `Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(containerColor = Primary, contentColor = OnPrimary))`
    - Min Height: `56.dp` for hero/checkout CTAs; `48.dp` for regular buttons.
    - Ripple/Pressed: M3 default state layer.
- **Secondary / Ghost Action:**
    - Component: `OutlinedButton(border = BorderStroke(1.dp, Primary), shape = RoundedCornerShape(50))`
- **Filter Chips:**
    - Component: `FilterChip(shape = RoundedCornerShape(12.dp))` with selected colors `selectedContainerColor = PrimaryContainer`.

---

## 4. FIXED IMAGE ASSET REGISTRY

All assets are pre-rendered, verified high-resolution photographs aligned with the verdant eco-luxury motif:

| Asset Token | Asset Title | Dimensions | Visual Description & Screen Role |
| :--- | :--- | :--- | :--- |
| `IMAGE_10` | **The Glass Pavilions** | 16:9 Landscape (HQ) | Luxury Westlake Hills modern glass & limestone residence at dusk with illuminated infinity pool. **Role:** Featured Hero card on Explore Screen & Primary Hero on Estate Detail. |
| `IMAGE_9` | **Emerald Grove Residence** | 16:9 Landscape (HQ) | Barton Creek Preserve modern architectural villa surrounded by lush greenery and Japanese maples. **Role:** Listing card in Explore & Saved properties. |
| `IMAGE_8` | **Skyline Botanica Penthouse** | 16:9 Landscape (HQ) | High-end penthouse interior terrace overlooking downtown Austin river skyline at sunset. **Role:** Listing card in Explore & Saved properties. |
| `IMAGE_7` | **The Cedar Ridge Villa** | 16:9 Landscape (HQ) | Hill Country contemporary villa with cedar siding, black metal standing seam roof, and dusk porch lanterns. **Role:** Listing card in Explore feed. |
| `IMAGE_5` | **Oakwood Sanctuary Cottage** | 16:9 Landscape (HQ) | Historic live-oak cottage in Tarrytown with Austin limestone, gravel driveway, and warm exterior lighting. **Role:** Card item on Saved Properties screen. |
| `IMAGE_6` | **Sarah Jenkins (Advisor Portrait)** | 1:1 Square (HQ) | Elegant studio headshot of female luxury real estate advisor wearing navy tailored blazer with warm professional smile. **Role:** Advisor profile on Detail & Consultation sheets. |
| `IMAGE_2` | **Advisor Alternative (Linen Blazer)** | 1:1 Square (HQ) | High-end real estate advisor in tailored cream-linen blazer against soft outdoor architectural garden background. **Role:** Secondary agent avatar / Profile tab. |

---

## 5. AI IMPLEMENTATION SPECIAL INSTRUCTIONS (FOR CODE GENERATORS)

To ensure seamless, error-free generation in IDE assistants like Cursor, Copilot, or Claude Code, strictly follow these implementation rules:

### 5.1 Architecture & State Rules
1. **Unidirectional Data Flow (UDF):** Every screen must accept a single UI State data class (e.g., `data class ExploreUiState(...)`) and an event lambda `(ExploreUiEvent) -> Unit`. Never place business logic or API calls inside Composables.
2. **Preview Parameter Providers:** Provide `@Preview` composables with mock data for every screen variant (`ExploreScreenPreview`, `EstateDetailScreenPreview`).
3. **Scaffold Integration:** Use `androidx.compose.material3.Scaffold` with `contentPadding` propagated cleanly into `LazyColumn` or `Box` root modifiers (`Modifier.padding(paddingValues)`).

### 5.2 Exact CTA & Visual Timeline Guidance
1. **CTA Button Colors:** Primary CTAs must strictly use `containerColor = Color(0xFF1B4332)` (Primary Deep Evergreen) and `contentColor = Color(0xFFFFFFFF)`. Secondary accent badges use `Color(0xFFD4A373)` (Architectural Brass) or `Color(0xFF2D6A4F)`.
2. **Custom Canvas Wavy Background (`VerdantIntroScreen`):**
    - Implement the organic wavy background via `androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize())`.
    - Use cubic Bezier curves (`Path().cubicTo(...)`) with 3 staggered overlapping layers filled with gradients from `Color(0xFF071B12)` to `Color(0xFF1B4332)` and `Color(0xFF245A40)`.
    - Add subtle infinite pulse transition (`rememberInfiniteTransition()`) modulating wave offsets by `8.dp` to `16.dp` with ease in/out.
3. **Glassmorphism in Compose:**
    - For bottom sheets on dark backgrounds, use `Modifier.background(Color.White.copy(alpha = 0.85f), shape = RoundedCornerShape(32.dp))` with `Modifier.border(1.dp, Color.White.copy(alpha = 0.35f), shape = RoundedCornerShape(32.dp))`.
4. **Image Loading:**
    - Use `AsyncImage` (Coil 3 / Accompanist) with `contentScale = ContentScale.Crop`.
    - Always specify `crossfade(true)` and supply a fallback `ColorPainter(SurfaceContainerHigh)` during initial load.
5. **No Deprecated APIs:** Use solely Material 3 APIs (`TopAppBarDefaults`, `ButtonDefaults`, `CardDefaults`, `NavigationBarItemDefaults`). Do **not** import any `androidx.compose.material.*` (M2) packages.
