# Verdant Estates - Cross-Platform Project Rules (Jetpack Compose + Flutter)

> Project: Verdant Estates
> Product: Sustainable Eco-Luxury Real Estate Discovery (Austin, Texas)
> Targets: **Android / Kotlin / Jetpack Compose / Material 3** AND **Flutter / Dart / Material 3**
> Canonical visual specification: current project `design.md` (v1.0.0; authored for Compose)
> Reference size: 390 x 844 **logical** portrait units
> Source photographs: current local project `assets/` (verified names from screenshot)
> This version supersedes the previously Android-only Verdant Estates `rules.md`.

You are an expert senior mobile UI engineer with equal proficiency in **native
Jetpack Compose** and **Flutter**. Build production-quality **code organization,
compilable UI, and deterministic behavior**, but do not pretend that an absent
real-estate backend, map/geodata integration, payment flow, or booking service
exists. Implement the requested platform(s) faithfully and idiomatically.

**Core mandate:** one shared product/design/interaction specification; **two
independent implementations**. Never embed Flutter inside the Android app, use
Android Compose APIs inside Dart, silently rewrite the other project's files,
or mistake the Android-only code snippets in `design.md` for Flutter APIs.

Both implementations must:

- Implement the same **five documented screens** and consistent navigation.
- Use the same exact theme values, photos, labels, supported data, and seed state.
- Share UX intent (selection, bookmarks, filtering, detail, map demo), not code.
- Use local assets and offline-capable deterministic UI without unrequested APIs.
- Apply Material 3 correctly, with responsive safe-area-aware portrait layouts.
- Respect the existing build/configuration, packages, routes, and architecture.
- Verify only the build and platform(s) that were actually run.

**Platform gate:** when the user asks for Compose, modify Compose; when the user
asks for Flutter, modify Flutter; when they explicitly ask for both, produce
**two separate verified projects** with matching behavior and design. Never
infer that requesting a screen authorizes rebuilding both codebases.

---

## 1. Source of truth and conflict resolution

Use this order for the specific subject in question:

1. A **current Verdant Estates screen screenshot** (`screen.png`, a user-supplied
   screen image, or an equivalent confirmed reference), when available, for visible
   arrangement, initial content, crop, positions, and selected states.
2. The **canonical Verdant Estates `design.md`** (copy it into or make it
   accessible to each target project) for brand identity, exact colors,
   typography, spacing, shapes, five-screen scope, and interaction intent. The
   document is Android-authored, so its Compose/Kotlin API examples apply only to
   Android and must be translated idiomatically for Flutter.
3. **Actual files in the current local `assets/` folder** for asset availability,
   exact filenames, content, and true pixel dimensions.
4. **Existing matching source code for the selected target platform** for package name, route conventions,
   working infrastructure, and previously implemented behavior that is consistent
   with the current design.
5. **Current-project Stitch HTML**, but only if such files are actually provided,
   for secondary interaction details not otherwise specified. HTML is never the
   implementation for either platform.

Special rules:

- The screenshot wins for initial visible presentation; `design.md` wins for
  shared theme values and UX intent, while each framework owns its native APIs.
- The real file on disk wins over an asset token or filename invented in docs.
- Preserve each target's existing application ID/package/bundle identity. Do not
  copy the Android package ID or source directory conventions into Flutter.
- The latest explicit user instruction wins over an older project document.
- Do not infer a missing number, image, screen, price, coordinate, address, or
  action merely because a UI layout has space for it.
- If sources disagree and the priority above cannot resolve the conflict, document
  the conflict and use the smallest reversible implementation choice.
- Never silently fetch replacement images or data from the internet.

### Current evidence boundary

The materials accompanying this rules rewrite establish:

- The **Verdant Estates `design.md`** describing five planned screens.
- A screenshot of the Windows `assets/` folder showing **six named images**.
- The previous coffee-app rules, which are a structural example only.

No individual current-project screen PNG or HTML implementation file was included
among the materials used for this rewrite. Therefore, the design specification is
currently authoritative for screen structure, but its description is **not proof of
pixel-exact screenshot composition or of any unspecified screen text/data**. If
actual current screen screenshots are later available in the working project, inspect
and apply them before claiming visual parity.

The image-folder screenshot is evidence of filenames and thumbnail appearance, **not
verification of each original image's pixel dimensions or file metadata**. Verify
those in the actual folder at implementation time.

### Dual-platform execution scope

These are **shared product and visual rules** for two independently implemented apps:

- **Android:** Kotlin, Jetpack Compose, Material 3.
- **Flutter:** Dart, Flutter Material 3, using bundled Flutter assets; support Android and iOS targets when those targets are configured in the Flutter project.

The user's explicit target for a particular task takes precedence. If asked to work
in the Android project, edit **Android only**. If asked to work in the Flutter
project, edit **Flutter only**. If explicitly asked for both, implement separately
and verify both. Do not create or overwrite the other platform automatically.

All user-facing screen structure, names, photos, selected-state defaults, theme
colors, text, logical spacing, and user-visible behavior must match. Kotlin and
Dart architecture, widget choices, build systems, and platform-specific safe
areas do **not** have to share source code.

Do not treat Android-specific code snippets from `design.md` as executable Flutter
instructions. The binding invariant is their **visual and behavioral meaning**.

---

## 2. Product identity and scope

**Verdant Estates** is a luxury property-discovery experience emphasizing:

- Sustainable and architecturally distinctive homes.
- Austin, Texas, and nearby luxury residential areas.
- Deep evergreen, sage, limestone, and warm architectural brass styling.
- High-quality residential photography.
- Curated estate discovery, property details, saved properties, and map discovery.
- Contact with a named listing advisor via UI actions.

The five screens **defined in the current `design.md`** are:

1. `VerdantIntroScreen` - immersive welcome and introduction.
2. `ExploreEstatesScreen` - home/discovery property feed.
3. `EstateDetailScreen` - one property's detailed presentation.
4. `SavedPropertiesScreen` - saved estates and collection tabs.
5. `MapExplorerScreen` - property discovery in a map-style interface.

These are five **design-specified** screens. Do not describe them as five
pixel-verified screens until their actual screen images have been checked.

Do **not** invent full additional screens for:

- Authentication, sign-up, or password reset.
- User profile/account settings (only a Profile navigation entry is documented).
- Agent directory, advisor biography, messaging inbox, or call history.
- Mortgage applications, mortgage calculators, loans, or payment checkout.
- Tour booking calendars, appointment databases, or contracts.
- Full property search result pages beyond the documented Explore/Map patterns.
- Push alert center, property comparison, or detailed price-history graphs.
- A remote listing-management dashboard or administrative panel.

A button or navigation label is not, by itself, permission to invent an entire
missing destination.

---

### Platforms and screen naming

The five names in `design.md` are **cross-platform screen identities**, not a
requirement that Flutter copy Kotlin files or Android APIs. Flutter may retain the
same feature names (`VerdantIntroScreen`, `ExploreEstatesScreen`, etc.) for clear
parity, implemented as Dart `Widget` classes and native Flutter layout.

Do not silently replace any screen with a WebView, a screenshot image, or an
embedded Android view in the Flutter implementation.

---

## 3. Official Material 3 color system

Implement the exact design tokens centrally in **each app**: for Android in
`ui/theme/Color.kt` or its existing equivalent; for Flutter in its `ThemeData`,
explicit `ColorScheme`, and named theme/token extensions. Do not scatter raw hex
constants through either app.

| Token | Exact value | Intended usage |
|---|---|---|
| `Primary` | `#1B4332` | Deep evergreen, primary CTA and brand |
| `OnPrimary` | `#FFFFFF` | Content on primary |
| `PrimaryContainer` | `#2D6A4F` | Mid-forest accents and selected containers |
| `OnPrimaryContainer` | `#E8F5E9` | Contrast on primary container |
| `Secondary` | `#40916C` | Leaf-green secondary actions |
| `OnSecondary` | `#FFFFFF` | Content on secondary |
| `SecondaryContainer` | `#D8F3DC` | Soft celadon accent |
| `OnSecondaryContainer` | `#081C15` | Contrast on secondary container |
| `Tertiary` | `#D4A373` | Architectural brass/gold |
| `OnTertiary` | `#2B1700` | Contrast on tertiary |
| `TertiaryContainer` | `#FFDDB8` | Warm brass tint |
| `OnTertiaryContainer` | `#2B1700` | Contrast on tertiary container |
| `Surface` | `#F8FAF7` | Main limestone surface |
| `OnSurface` | `#191C1B` | Main text |
| `SurfaceDim` | `#EBEFEA` | Muted surface |
| `SurfaceBright` | `#FFFFFF` | Highlight surface |
| `SurfaceVariant` | `#DEE5DD` | Sage-neutral secondary surface |
| `OnSurfaceVariant` | `#404943` | Secondary text |
| `SurfaceContainerLowest` | `#FFFFFF` | Brightest cards |
| `SurfaceContainerLow` | `#F2F5F1` | Low container |
| `SurfaceContainer` | `#ECEFEA` | Standard container |
| `SurfaceContainerHigh` | `#E6EAE4` | Elevated container |
| `SurfaceContainerHighest` | `#E0E5DF` | Highest surface container |
| `Background` | `#F8FAF7` | App background |
| `OnBackground` | `#191C1B` | Main background content |
| `Outline` | `#707972` | Control outlines |
| `OutlineVariant` | `#C0C9C0` | Delicate borders/dividers |
| `IntroGradientDark` | `#071B12` | Deep pine intro backdrop |
| `IntroGradientMid` | `#143828` | Middle intro gradient |
| `IntroGradientLight` | `#245A40` | Luminous jade intro gradient |
| `GlassmorphismOverlay` | `#CCFFFFFF` | White, approximately 80% opaque |
| `GlassmorphismBorder` | `#66FFFFFF` | White, approximately 40% opaque |

Deliver matching Material color roles via Compose's `MaterialTheme.colorScheme`
on Android and Flutter's `Theme.of(context).colorScheme` on Flutter. Keep ambient/intro and glass tokens as
named theme extensions where necessary.

- Primary CTA background: `#1B4332`.
- Primary CTA content: white.
- Brass badge/accent: `#D4A373`.
- Selected green surfaces: `#2D6A4F` with readable text.
- Ordinary content background: limestone white `#F8FAF7`, not a generic grey.
- Preserve legibility when placing translucent white UI over bright photographs.
- Do not use Material dynamic color to override the documented brand palette by
  default. If the project already supports dynamic colors, keep a deterministic
  branded mode for screenshots and previews.
- The uploaded design defines a light palette and a **dark intro composition**;
  it does not establish a complete app-wide dark-mode palette. Do not invent one.

---

### Flutter theme interpretation

- Set `ThemeData(useMaterial3: true)` and supply an **explicit** brand `ColorScheme`.
- Do **not** rely on `ColorScheme.fromSeed` alone when it changes the supplied
  hexadecimal values, and do not enable unrequested dynamic palette behavior.
- Preserve all documented colors exactly; use `ThemeExtension` or centralized
  constants for gradient, glass, and uncommon surface tokens that are not native
  `ColorScheme` properties in the installed Flutter version.
- Keep the existing light app surfaces; the dark forest Intro is **not** proof of
  a complete separate dark-mode scheme.
- No unapproved theme color differences between Android and Flutter.

---

## 4. Official typography

The design calls for **Plus Jakarta Sans / Inter** styling.

| Material style | Size | Line height | Weight | Letter spacing |
|---|---:|---:|---|---:|
| `headlineLarge` | 32sp | 40sp | 700 | -0.25sp |
| `headlineMedium` | 28sp | 36sp | 600 | 0sp |
| `headlineSmall` | 24sp | 32sp | 600 | 0sp |
| `titleLarge` | 20sp | 26sp | 600 | 0sp |
| `titleMedium` | 16sp | 24sp | 500 | 0.15sp |
| `titleSmall` | 14sp | 20sp | 500 | 0.1sp |
| `bodyLarge` | 16sp | 24sp | 400 | 0.5sp |
| `bodyMedium` | 14sp | 20sp | 400 | 0.25sp |
| `bodySmall` | 12sp | 16sp | 400 | 0.4sp |
| `labelLarge` | 14sp | 20sp | 600 | 0.1sp |
| `labelMedium` | 12sp | 16sp | 500 | 0.5sp |
| `labelSmall` | 11sp | 14sp | 500 | 0.5sp |

- Prefer an actual locally bundled Plus Jakarta Sans or Inter font if present.
- The current asset screenshot does not prove the presence of any font files.
- Do not download Google Fonts at runtime or silently bundle unprovided fonts.
- If neither font is available, use the existing system sans-serif while preserving
  the documented size, weight, line height, and spacing.
- Use the named Material typography roles rather than arbitrary per-screen values
  in **either** implementation.
- Keep a large, refined editorial hierarchy appropriate to real-estate photography.

---

### Font scaling in Flutter

The Android design expresses type in `sp`. For the Flutter counterpart, use
matching **logical-pixel** `TextStyle(fontSize: ...)` values at default text scale,
matching height via `TextStyle.height` (lineHeight / fontSize) and tracking via
`letterSpacing`. Respect Flutter text scaling and do not disable accessibility
scaling to force a screenshot match. Bundle only font files that actually exist
and are licensed for use; do not request Google Fonts at runtime.

---

## 5. Spacing, shapes, and component density

Preserve the exact design language:

- Screen horizontal gutter: `16.dp` on compact devices, up to `20.dp` where room
  allows and the current reference supports a more spacious presentation.
- Section separation: approximately `24.dp`.
- Typical card internal padding: `16.dp`.
- Micro spacing: `4.dp` and `8.dp`.
- CTA/buttons: full pill, e.g., `RoundedCornerShape(percent = 50)`.
- Intro floating bottom panel: `32.dp` radius.
- Estate/gallery/modal cards: `24.dp` radius.
- Specs/advisor/search cards: `16.dp` radius.
- Compact chips and thumbnail/map elements: `8.dp` where documented.
- Hero CTA minimum height: `56.dp`.
- Normal buttons: minimum height `48.dp`.
- Preserve an uncluttered, premium visual rhythm; do not make dense catalog layouts
  or rectangular default-looking cards that contradict the design.

Use reusable components such as:

- `EstatePhotoCard`.
- `FeaturedEstateCard`.
- `EstateSpecChip`.
- `AdvisorCard`.
- `EstateFilterChip`.
- `PriceMapPin`.
- `EstatePreviewCard`.
- `VerdantPrimaryButton`.

These names are suggestions; retain established clean conventions in existing code.

---

### Density-independent cross-platform conversion

The values written above in Android `dp` are the **logical layout dimensions**
for both apps. Map `16.dp` in Compose to `16.0` Flutter logical pixels, not to
physical pixels. Keep corner radii, touch targets, horizontal gutters, sheet
heights, and hero proportions aligned at the reference **390 x 844 logical-size**
portrait viewport. Physical device resolution and pixel density will differ.

---

## 6. Shared local asset registry (six filenames visible in folder screenshot)

The attached Windows Explorer screenshot shows the current project folder at:

`F:\jamali\dl\project313\assets`

The path above describes the source machine shown in the screenshot, not a promise
that this absolute path exists on every execution machine. Prefer the current
project's relative `assets/` directory and verify the actual files.

The following **six filenames** are visible and must be used for the corresponding
content when confirmed to exist in the real project:

| Exact source filename | Corresponding design item | Grounded UI role |
|---|---|---|
| `architectural_photograph_of_the_glass.png` | `IMAGE_10` - The Glass Pavilions | Explore featured hero and Glass Pavilions detail hero |
| `emerald_grove.png` | `IMAGE_9` - Emerald Grove Residence | Explore listing and Saved card |
| `botanica_luxury.png` | `IMAGE_8` - Skyline Botanica Penthouse | Explore listing and Saved card |
| `modern_luxury.png` | `IMAGE_7` - The Cedar Ridge Villa | Explore architectural listing |
| `sanctuary_cottage_in.png` | `IMAGE_5` - Oakwood Sanctuary Cottage | Saved listing/card |
| `female_luxury.png` | `IMAGE_6` - Sarah Jenkins | Listing advisor portrait in Detail and consultation UI |

This mapping is supported by the documented asset descriptions and the thumbnails
visible in the supplied folder screenshot. Check the original images before final
crops, resource imports, or dimension assumptions.

### Asset token versus file name

The `IMAGE_*` identifiers in `design.md` are **design documentation tokens**, not
proven Android/Flutter paths, download URLs, drawable IDs, or Flutter asset keys. Map them to the exact
local files listed above.

Android: import the confirmed images as drawable resources and refer to actual
`R.drawable.*` IDs, for example `R.drawable.architectural_photograph_of_the_glass`.
Flutter: include the confirmed image files under an agreed local path such as
`assets/images/` and declare them in `pubspec.yaml`; reference them with
`Image.asset`/`AssetImage`. Do not assume an arbitrary filesystem path works at
runtime. Do not rename source files merely for aesthetic reasons; if processing
is necessary, preserve the exact source-to-runtime mapping.

### Documented but NOT confirmed locally

`IMAGE_2` - secondary advisor in a cream linen blazer - is listed in `design.md`
but is **not visible in the current assets screenshot**.

Therefore:

- Do not claim its file is available.
- Do not download or AI-generate a substitute without explicit user instruction.
- Do not recast `female_luxury.png` as the missing alternate portrait.
- For an optional Profile avatar, use an icon, monogram, or neutral native
  placeholder unless a verified corresponding portrait exists.

Do not invent filenames such as `glass_pavilions.jpg`, `sarah_jenkins.jpg`, or
`cedar_ridge.png` unless those files are actually found in the current project.

---

### Asset parity manifest

Maintain an easy-to-audit mapping in each project (or a shared documentation file):

`estateId -> exact source filename -> Android drawable name -> Flutter asset key`.

The mapping must be **semantically identical** in both builds. Do not require
Flutter to read an Android `res/drawable` path or Android to read Flutter's
`pubspec.yaml`. Check capitalization (including on case-sensitive systems), image
integrity, and presence after build. The folder screenshot proves visible names
and subject matter, **not actual image-file contents or full-resolution dimensions**.

---

## 7. Property-image semantics and restrictions

### The Glass Pavilions

Use `architectural_photograph_of_the_glass.png` for:

- The Featured Estate of the Month photo card.
- The primary hero in The Glass Pavilions detail screen.
- A thumbnail of that same estate if shown in a legitimately related UI location.

Its visual content is a contemporary glass-and-limestone residence at dusk with
an illuminated pool. Preserve the building, water, and dusk atmosphere in the crop.

### Emerald Grove Residence

Use `emerald_grove.png` for a modern luxury home surrounded by greenery, as
documented for Barton Creek Preserve. Appropriate for Explore and Saved cards.

### Skyline Botanica Penthouse

Use `botanica_luxury.png` for the interior terrace/penthouse scene overlooking
Austin's skyline at sunset. Do not label it as a cottage or exterior villa.

### The Cedar Ridge Villa

Use `modern_luxury.png` for the wood/stone modern Hill Country property image
shown in the folder. The image should retain its warm dusk porch lighting.

### Oakwood Sanctuary Cottage

Use `sanctuary_cottage_in.png` for the older live-oak / Austin-limestone cottage
setting documented for Tarrytown. Prefer its Saved listing use unless a current
screenshot specifies additional placement.

### Sarah Jenkins

Use `female_luxury.png` as the female advisor portrait associated with
`Sarah Jenkins` in the design. Its confirmed role is an advisor, **not** a user
avatar, house listing image, or an additional agent with an invented name.

### Global image rules

- Never use one property image to represent another named property.
- Never use a property photo as Sarah Jenkins's portrait.
- Do not fabricate separate interior/gallery photographs using unrelated estate
  assets. Preserve unfilled gallery tiles appropriately if required.
- Do not load `lh3.googleusercontent.com`, `aida-public`, Unsplash, random image
  endpoints, or other reference CDN photos.
- Do not use network image loading when a local drawable will do.
- Verify source dimensions and visually tune Compose `ContentScale.Crop` / Flutter
  `BoxFit.cover`, alignment, and overlay for each screenshot slot.

---

## 8. Launch flow and first-launch behavior

The **current** `design.md` explicitly specifies a welcome tour screen:

`VerdantIntroScreen`

Unlike the previous coffee project, the Intro **is part of this project** and must
not be silently omitted.

Recommended coherent flow when no established app navigation contradicts it:

`VerdantIntroScreen -> ExploreEstatesScreen -> EstateDetailScreen`

Explore also provides access to:

- Map.
- Saved.
- A Profile navigation affordance, without fabricating a Profile screen.

Implementation expectations:

- The first entry should show Intro when the project is being demonstrated as a
  first-launch experience.
- A primary Intro action should navigate to Explore.
- If the corresponding app already has a durable first-launch flag, respect it.
- If persistence of first-launch completion is required, use a minimal appropriate
  local preference mechanism; do not introduce user accounts or a backend.
- Do not force Intro to reappear on every back press or when returning from Detail.
- Use the existing navigation stack and avoid duplicate Explore destinations.
- The exact Intro CTA copy is **not established** by the supplied `design.md`;
  prefer a screenshot when available and otherwise use minimal provisional copy.

---

### Flutter launch equivalence

Use Flutter-native navigation to enter Explore from Intro; respect route history
and avoid repeated Intro screens after Back. Only persist the flag when an
existing requirement calls for first-launch persistence. Android and Flutter
must agree on whether the screenshot/demo begins in Intro or Explore.

---

## 9. Verdant Intro screen - composition

The design specifies an immersive eco-luxury landing screen, not a conventional
photo carousel or account registration wizard.

Build:

1. Full-screen rich forest/jade canvas backdrop.
2. Organic waves made from approximately three staggered overlapping curves.
3. Ambient concentric halo/ring shapes, subordinate to the heading.
4. Two floating accolade pills using the documented content:
   - `42 Curated Homes`.
   - `LEED Platinum`.
5. Large editorial introduction/brand typography.
6. A translucent, floating bottom Material 3 card with rounded 32dp corners.
7. A primary action to continue into Explore and secondary copy/actions only when
   supported by a real visual reference or current project implementation.

### Intro canvas

Use native drawing primitives: Compose `Canvas`/`Path`/`Brush` or Flutter
`CustomPainter`/`Canvas`/`Path`/`Gradient`.
The specified dark-to-light colors are:

- `#071B12` deep pine.
- `#143828` verdant forest.
- `#245A40` jade luster.
- Brand evergreen `#1B4332` as appropriate.

The `design.md` calls for a subtle pulsing/moving wave offset of about 8dp to 16dp.
A Compose `rememberInfiniteTransition` or Flutter `AnimationController`
(with an appropriate ticker lifecycle) may support that effect, but:

- Keep it slow and subtle; it should not interfere with text legibility.
- Do not add flashy particles, parallax galleries, or unrequested intro scenes.
- Respect reduced-motion preferences where practical.
- Make screenshot/previews deterministic by using a fixed animation phase or a
  stable preview configuration.
- Avoid full-screen expensive blur passes unless an existing efficient solution
  is available.

### Glass-style bottom panel

Preserve a near-white translucent panel over the dark intro background, with
legible dark foreground content and a thin translucent white border. Use the
named design tokens and approximately the 32dp shape in `design.md`.

`42 Curated Homes` is documented marketing copy; it is **not proof** that 42 unique
property items or 42 real database records must exist.

`LEED Platinum` is a design accolade and does **not** establish verified LEED
certification for every listed house. Do not assign it to every property by default.

---

## 10. Explore Estates screen - structure

Screen name:

`ExploreEstatesScreen`

This is the main property discovery feed. The documented composition is:

1. Custom top app bar including brand/search affordance.
2. Search UI appropriate to luxury property discovery.
3. Horizontally scrollable filter chips.
4. Large `Featured Estate of the Month` card.
5. Curated vertical property listing cards in a `LazyColumn`.
6. Four-entry bottom navigation on feed screens.

Use Compose `Scaffold`/`LazyColumn` or Flutter `Scaffold` with
`CustomScrollView`/`ListView`, and propagate system/shell insets correctly. The layout should be
portrait-first and visually spacious. Avoid making it a generic commerce storefront,
a crowded dashboard, or a realtor contact database.

### Exact documented featured content

The design provides these specific values/labels for the featured treatment:

- `Featured Estate of the Month`.
- `Exclusive Reserve`.
- Featured photo: `architectural_photograph_of_the_glass.png`.
- Design property identity: `The Glass Pavilions`.
- Documented prominent price example: `$7,850,000`.

Use the above in the associated featured card unless an actual current screen
screenshot establishes a different arrangement or copy.

Do not apply the `$7,850,000` price to other properties.

### Featured card layout

The design explicitly describes:

- A `Card` with approximately `24.dp` corner radius.
- A bright `SurfaceContainerLowest` card surface.
- Elevation around `2.dp`.
- An image area of approximately `260.dp` height on its reference layout.
- A subtle gradient scrim over the photo for readability.
- A small floating `Exclusive Reserve` badge toward the top-left.
- A prominent price treatment near the lower-right of the photo.
- Title/name and a visual verified checkmark where the reference calls for it.
- A row of compact spec pills beneath the main photo/content.

The visual verified checkmark in a design is not proof that any property, license,
listing, or legal claim has been independently verified. It is UI treatment only
unless authentic verification data exists.

### Other listing entries

The current `design.md` documents:

- `Emerald Grove Residence` (Barton Creek Preserve): `emerald_grove.png`.
- `Skyline Botanica Penthouse`: `botanica_luxury.png`.
- `The Cedar Ridge Villa` (Hill Country): `modern_luxury.png`.
- `Oakwood Sanctuary Cottage` (Tarrytown): `sanctuary_cottage_in.png`, primarily
  assigned to Saved in the design.

Use only verified property names and their correct photos. Do not invent precise
prices, bed/bath counts, square footage, listing dates, listing IDs, or exact street
addresses. Where a card design requires such a field but no verified value exists,
keep the model nullable and use a neutral presentation or an explicit local demo
placeholder rather than a fake factual claim.

---

## 11. Explore interactions

Create explicit local state/events for controls that are visually present or
clearly required by the design:

- Search query entry.
- Filter chip selection.
- Tapping a property to open the corresponding detail view.
- Toggling a saved/bookmark state.
- Opening Saved via bottom navigation.
- Opening Map via bottom navigation.

### Search

- Search only the actual local demo property collection unless a real existing
  repository provides additional data.
- A simple case-insensitive filter on documented property name/area is acceptable.
- Do not invent backend search, geocoding, keyword suggestions, or popularity
  ranking.
- Search field copy should follow a real screen reference where available.

### Filter chips

- Implement single-selection visually and semantically unless a current reference
  specifies multi-select.
- Apply actual filtering **only** when the criterion is grounded in local data.
- Do not create fake results for labels with no backing property metadata.
- If the exact chip labels are not available from a supplied screenshot or source
  data, do not present guessed labels as screenshot-confirmed.

### Featured/listing navigation

- Selecting The Glass Pavilions can open the documented Estate Detail screen.
- Other property cards may navigate to a reusable detail renderer when their local
  model has enough supported information, rather than inventing new screens.
- Never show the Glass Pavilions price/photo while retaining another estate's name.

---

## 12. Estate Detail screen - composition

Screen name:

`EstateDetailScreen`

The design specifies a premium property detail view with:

1. An immersive, edge-to-edge property photograph at the top.
2. Overlayed translucent back, bookmark, and share controls.
3. A property title and supporting architectural narrative.
4. A floating property specification bar with:
   - Beds.
   - Baths.
   - Square footage.
   - Acreage.
   - LEED rating (when actually supported for the property).
5. An editorial long-form architecture/property description.
6. A curated property photo grid.
7. A dedicated listing advisor card for Sarah Jenkins.
8. A fixed bottom booking/consultation action bar.

Use The Glass Pavilions as the default document-grounded featured/detail example
when no user navigation or real source state provides another selected property.

### Detail image and parallax

- Main verified local photo: `architectural_photograph_of_the_glass.png`.
- Preserve the hero building and pool in the crop.
- The specification mentions a parallax effect, but the effect must be subtle and
  must not compromise smooth scrolling, screenshot fidelity, or accessibility.
- Provide an accessible static fallback when motion is reduced.
- Maintain sufficient scrim/contrast behind overlay action icons.

### Property specifications

The specification defines which *fields* should exist, but does **not** provide
confirmed per-property bed, bath, square footage, or acreage values.

Therefore:

- Model unknown values as nullable.
- Do not automatically display `4 Beds`, `5 Baths`, or similar invented figures.
- Only expose a stat chip when its verified value is present or the current screen
  screenshot explicitly supplies the figure.
- Preserve the visual spec-bar shape and rhythm without suggesting fabricated
  measurements are verified.
- Do not claim a property has a LEED certificate merely because the intro badge
  reads `LEED Platinum`.

### Architectural text

Match current confirmed copy if screenshots/source content exists. Otherwise keep
provisional copy minimal and clearly confined to local UI demonstration; never
fabricate environmental ratings, legal disclosures, acreage, historic awards, or
engineering certifications.

---

## 13. Estate Detail gallery rule

The design calls for a curated photo grid, but the attached local image evidence
establishes **one** photo per named property, not a multi-photo album for any
individual estate.

Therefore:

1. Inspect the real project for additional estate-specific gallery files.
2. If genuine photos for the same estate exist, use them.
3. If only one photo is available, keep the main hero accurate and preserve the
   gallery layout with understated native placeholders/appropriate empty treatment
   until more confirmed photos arrive.
4. Do not relabel Emerald Grove, Botanica, Cedar Ridge, or Oakwood photos as
   alternate interior shots of The Glass Pavilions.
5. Never fabricate a gallery URL, photo count, or 360-degree tour.

Image slots do not authorize downloading or generating new photos.

---

## 14. Sarah Jenkins - advisor card

The advisor represented in the supplied design is:

- Name: `Sarah Jenkins`.
- Role: `Principal Private Advisor`.
- Division: `Austin Luxury Division`.
- Photo: `female_luxury.png`.

The design also mentions an advisor license-ID position but does **not** supply an
actual license number. Never invent one.

The documented component is:

- A `Surface` with about 16dp rounded corners.
- A `SurfaceContainerLow` background.
- A soft `OutlineVariant` border.
- A circular avatar approximately 56dp.
- Name and role/division text beside the portrait.
- A compact trailing Phone/Chat action in primary evergreen.

Do not use the Sarah portrait for the Profile tab's account identity. The Profile
tab is a separate unprovided product surface.

### Contact behavior

- Keep contact actions as UDF events.
- Do not invent a phone number, email address, booking URL, or chat identifier.
- Do not implement a call or message as completed if it only produced local demo
  feedback.
- If a real verified contact URI exists in the selected platform project, a
  user-initiated, platform-appropriate external action may be appropriate.
- Avoid direct-call permission; use safe external intents where supported.
- Do not add an advisor CRM, chat server, appointment backend, or user login.

---

## 15. Saved Properties screen

Screen name:

`SavedPropertiesScreen`

The design establishes:

- A saved-property shortlist.
- Tabbed collections exactly named:
  - `All Saved`.
  - `Hill Country`.
  - `Waterfront`.
- A grid or list of luxury property cards, according to available width/current
  screenshot.
- A `New Price` treatment where price-movement status is genuinely part of demo
  source state.
- Share and advisor-consultation affordances.

Documented asset roles for Saved:

- `emerald_grove.png` - Emerald Grove Residence.
- `botanica_luxury.png` - Skyline Botanica Penthouse.
- `sanctuary_cottage_in.png` - Oakwood Sanctuary Cottage.

These are supported Saved **card roles** in `design.md`; they do not, by themselves,
prove that every item must be bookmarked at app launch. Use a screenshot or
pre-existing local state for exact initial selections. If none exists, create a
small clearly local deterministic demo seed that permits the Saved layout to be
demonstrated, without claiming an externally synchronized favorites account.

### Saved interactions

- The bookmark state should be shared with Explore and Detail.
- Tapping a bookmark toggles the same property record, not a duplicate UI-only
  flag in each screen.
- `All Saved` shows saved records regardless of category.
- `Hill Country` and `Waterfront` must be backed by explicit category tags; do
  not infer waterfront status from a swimming pool, skyline photo, or greenery.
- If a category has no grounded matches, show an elegant empty state.
- A `New Price` badge is only a displayable demo state when the local record
  explicitly says so; it does not imply real-time market monitoring.
- Sharing uses a real platform share sheet only with grounded content; no
  invented listing URLs or live property data.

---

## 16. Map Explorer screen

Screen name:

`MapExplorerScreen`

The design calls for an Austin-oriented geographic discovery screen containing:

- A full-screen map presentation.
- Custom, branded green/white/brass estate price pins.
- Example pin labels such as `$7.4M` and `$5.8M`.
- A selectable active estate.
- An expandable `BottomSheetScaffold` with an estate preview card.
- Tap-through from property preview to the corresponding detail screen.

### Crucial map-data distinction

The pin prices `$7.4M` and `$5.8M` are examples of **map-pin UI labels** in the
design, not confirmed sale prices for a particular estate. Do not bind them to
The Glass Pavilions or any other named property without a real source mapping.

The currently supplied materials do **not** include:

- Coordinates for individual houses.
- A map style JSON or offline map tiles.
- A provider API key.
- A declared map SDK or entitlement.
- Confirmed Austin property boundaries or street-level geography.

Therefore:

- Inspect the actual project for a working local/native map provider first.
- If an SDK and verified location data already exist, preserve them and match
  the design on top of that infrastructure.
- Otherwise build a clearly illustrative, local **native map-style composition**
  (Compose Canvas/layout or Flutter `CustomPainter`/`Stack`) for the UI demo,
  without representing decorative pin positions as actual Austin geographic
  coordinates.
- Do not add a paid map dependency, expose an API key, silently fetch remote
  tiles, or request GPS permission just to reproduce a mockup.
- An approximate or illustrative map must not be called geographically accurate.
- If real location/map functionality is later requested, treat it as a separate
  integration requiring verified coordinates, SDK choice, terms, and credentials.

### Map behavior

- A pin selection updates the active preview state.
- The selected pin has a visually distinct selected treatment.
- The bottom sheet can expand/collapse smoothly.
- Back navigation and system navigation gestures remain usable with the sheet.
- The preview displays the selected **known** estate name/image, with price only
  if verified for that estate.
- Keep the bottom navigation visible/usable as the current screen design permits;
  do not create a stacked or overlaid layout that blocks touch targets.
- Do not simulate current user location, calculate travel times, or display
  distances without real map/location data.

---

### Map parity

Android's `BottomSheetScaffold` and Flutter's `DraggableScrollableSheet` (or an
existing equivalent) should expose comparable collapsed/expanded states, marker
selection, and preview actions. Prefer the same deterministic mock-marker catalog
and selected property ID. Native drawing/layout is sufficient when real geographic
coordinates and a provider are not present. **Do not add `google_maps_flutter`,
Mapbox, Google Maps Compose, API keys, GPS, or tile downloads by default.**

---

## 17. App chrome and bottom navigation

The bottom navigation labels explicitly established by `design.md` are:

1. `Explore` - home icon.
2. `Map` - place/pin icon.
3. `Saved` - bookmark icon.
4. `Profile` - person icon.

This is **not** the previous app's Home/Menu/Cart/Activity navigation.

Use a Material 3 `NavigationBar` on each platform (Compose `NavigationBar` or
Flutter `NavigationBar`):

- Container: `SurfaceContainerLow`.
- Subtle tonal/elevation treatment consistent with the reference (about 3dp).
- Active indicator: rounded capsule, approximately `16.dp` radius.
- Active color: `PrimaryContainer` with readable icon/label tint.
- Icons: native filled/outlined variants as documented.
- Navigate without accumulating duplicate top-level destinations.
- Preserve appropriate scroll/selection state when switching between known tabs.

### The missing Profile screen

The bottom tab `Profile` is listed, but the five-screen design gallery contains
**no `ProfileScreen` specification, screenshot, or matching profile asset**.

Therefore:

- Keep the Profile item in bottom navigation where the design shows it.
- If a real existing Profile implementation exists, keep and integrate it.
- Otherwise do not invent login/account-editing screens or an identity for the
  current user.
- For a UI-only demo, a minimal non-deceptive unavailable-state message/action
  is acceptable pending a dedicated Profile reference.
- Do not use `female_luxury.png` as the current user's avatar.

### App bars

- On discovery/list screens, use a flat surface app bar consistent with the design.
- On Estate Detail, use transparent/translucent overlay controls on the hero.
- Back should pop the real navigation stack.
- Bookmark and Share actions should affect local state or supported Android
  platform behavior, rather than being decorative only.

---

### Framework-specific navigation bar APIs

- Compose: `NavigationBar`, `NavigationBarItem`, `NavigationBarItemDefaults`.
- Flutter: `NavigationBar`, `NavigationDestination`, `NavigationBarThemeData`.
- Both: four **identical English labels**, one selected destination, comparable
  pill indicator/icon semantics, safe area handling, and stable top-level state.

Do not use Flutter's default adaptive/Cupertino navigation style if that would
change the documented shared Material 3 appearance. In the Flutter app the visual
spec remains Material 3 on both Android and iOS unless the user requests otherwise.

---

## 18. Navigation boundaries

Use actual existing route naming conventions when they already exist. The design
screen names are reference identifiers, not proof that a specific route string
is already installed in either app.

Suggested high-level route identities if needed:

- `intro`.
- `explore`.
- `estate/{propertyId}`.
- `saved`.
- `map`.

The Profile tab remains an affordance until its own content is sourced.

Rules:

- Navigate to Detail by a stable local property ID, not by passing a photo bitmap
  or duplicating a full object in every route.
- Restored navigation must recover the selected property from local data.
- Return to the prior screen using Back.
- Do not pop to Intro unexpectedly after visiting an estate.
- Avoid multiple back stacks or navigation libraries unless the project already
  benefits from them.
- Provide a stable fallback for an unknown property ID rather than displaying a
  mismatched house or crashing.

---

## 19. Centralized deterministic property data

Create a small local repository/catalog if a consistent source is not already in
the project. One property model should drive Explore, Detail, Saved, and Map.

Example **Android/Kotlin** shape (field presence is illustrative, not permission
to invent content):

```kotlin
data class Estate(
    val id: String,
    val name: String,
    @DrawableRes val imageRes: Int,
    val areaLabel: String? = null,
    val priceUsd: Long? = null,
    val bedrooms: Int? = null,
    val bathrooms: Int? = null,
    val squareFeet: Int? = null,
    val acreage: Double? = null,
    val leedRating: String? = null,
    val collectionTags: Set<String> = emptySet(),
    val hasNewPrice: Boolean = false,
)
```

Document-grounded starter identities:

- The Glass Pavilions.
- Emerald Grove Residence.
- Skyline Botanica Penthouse.
- The Cedar Ridge Villa.
- Oakwood Sanctuary Cottage.

Only the featured `$7,850,000` example is clearly associated with the featured
estate card in the current design description. Other prices/stats should remain
absent or require verified project data.

Do not generate random real-estate prices, IDs, addresses, square footage,
certifications, advisor contacts, or geocoordinates. Stable **internal** IDs are
allowed for navigation/state and are not claims about MLS listing identifiers.

For map demo markers, distinguish decorative marker examples from markers
connected to a verified property record.

---

### Flutter catalog model

Create a Dart equivalent with the **same logical fields**. Store an asset **key**
(`String imageAsset`) rather than Android's `@DrawableRes int` and keep
unknown real-estate fields nullable. For example:

```dart
class Estate {
  const Estate({
    required this.id,
    required this.name,
    required this.imageAsset,
    this.areaLabel,
    this.priceUsd,
    this.bedrooms,
    this.bathrooms,
    this.squareFeet,
    this.acreage,
    this.leedRating,
    this.collectionTags = const {},
    this.hasNewPrice = false,
  });

  final String id;
  final String name;
  final String imageAsset;
  final String? areaLabel;
  final int? priceUsd;
  final int? bedrooms;
  final int? bathrooms;
  final int? squareFeet;
  final double? acreage;
  final String? leedRating;
  final Set<String> collectionTags;
  final bool hasNewPrice;
}
```

Use matching **stable local IDs and semantic data** across both projects.
Reusing an ID does not claim the property is a genuine MLS record. Android data
models must not depend on Dart; Flutter models must not depend on Kotlin.

---

## 20. Shared state and UDF

Use one shared local source of truth for saved properties and catalog records.
Acceptable mechanisms include:

- An application-scoped in-memory repository.
- On Android, a navigation-graph-scoped shared ViewModel when appropriate.
- On Flutter, one shared repository/controller using an established project
  state-management pattern (for example `ChangeNotifier`/`ValueNotifier`, an
  existing Provider setup, or an existing Riverpod/Bloc architecture).
- An existing project repository and persistence mechanism.

Do not add Room/Firebase merely to show a few bookmarked homes.

Required state principles:

- UI state is immutable.
- UI events represent a user intention.
- Compose ViewModels/reducers or Flutter controllers/notifiers/reducers change state.
- Stateless Compose screen renderers / Flutter `Widget` renderers receive state
  and emit events.
- No screen silently mutates another screen's local booleans.
- State changes are deterministic and immediately reflected throughout the app.

Example UI state:

```kotlin
data class ExploreUiState(
    val searchQuery: String = "",
    val selectedFilterId: String? = null,
    val estates: List<Estate> = emptyList(),
    val savedEstateIds: Set<String> = emptySet(),
    val selectedTab: BottomTab = BottomTab.Explore,
    val isLoading: Boolean = false,
)

sealed interface ExploreAction {
    data class SearchChanged(val value: String) : ExploreAction
    data class FilterSelected(val id: String?) : ExploreAction
    data class EstateSelected(val id: String) : ExploreAction
    data class BookmarkToggled(val id: String) : ExploreAction
}
```

Names can be adapted to the actual project. Avoid a large state-management
framework for a five-screen native UI demo.

---

### Flutter state rules

- Prefer immutable `UiState` objects and explicit typed actions/events. Update
  state through a controller/notifier/reducer, not mutations inside `build()`.
- Keep transient widget-only concerns (e.g., sheet expansion animation) local;
  keep saved IDs, search/filter, and active estate in the shared state/repository.
- Use `setState` only for small local presentation state, **not** a disconnected
  second copy of bookmarks or catalog data.
- Dispose controllers/animation objects correctly; do not leak listeners.
- Use only the existing project's state-management library when it is already
  appropriate. Do not introduce Riverpod, Bloc, Provider, GetX, or another package
  solely to follow a stylistic preference.

---

## 21. Stateless screen boundaries

For **both platforms**, prefer a route/container responsible for state
subscription and navigation, plus a renderer receiving explicit state/actions.

```kotlin
// ANDROID / Jetpack Compose example
@Composable
fun ExploreRoute(
    viewModel: ExploreViewModel,
    onEstateOpen: (String) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ExploreScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is ExploreAction.EstateSelected -> onEstateOpen(action.id)
                else -> viewModel.onAction(action)
            }
        },
    )
}

@Composable
fun ExploreScreen(
    state: ExploreUiState,
    onAction: (ExploreAction) -> Unit,
) {
    // Render current immutable state. No API calls or business logic here.
}
```

Apply this principle to:

- Intro.
- Explore.
- Detail.
- Saved.
- Map.

A small local presentation state such as tooltip expansion can be held in
Compose `rememberSaveable` or Flutter widget state as appropriate. Shared
business state must not live in per-screen local presentation flags.

---

### Flutter screen boundary example

```dart
// FLUTTER / conceptual structure; adapt to the existing state container.
class ExploreRoute extends StatelessWidget {
  const ExploreRoute({super.key, required this.controller});
  final ExploreController controller;

  @override
  Widget build(BuildContext context) => ListenableBuilder(
        listenable: controller,
        builder: (context, _) => ExploreScreen(
          state: controller.state,
          onAction: controller.onAction,
        ),
      );
}

class ExploreScreen extends StatelessWidget {
  const ExploreScreen({
    super.key,
    required this.state,
    required this.onAction,
  });
  final ExploreUiState state;
  final void Function(ExploreAction) onAction;

  @override
  Widget build(BuildContext context) {
    // Render immutable state; emit user actions; no network/business logic.
    return const SizedBox.shrink(); // Replace with the actual screen UI.
  }
}
```

The snippet illustrates a **boundary**, not a requirement to create custom
classes with precisely these names or to use a specific package. In both apps,
UI layout and domain calculations stay separated.

---

## 22. Property selection and bookmarks

Bookmarking is a first-class cross-screen interaction:

- The same estate ID must be used in Explore, Detail, and Saved.
- Bookmark toggles update the shared local saved set.
- Saved cards disappear or remain according to the resulting state.
- The bookmark icon reflects the current state, including after navigation.
- Do not use independent hardcoded bookmark booleans per screen.
- No login or cloud synchronization is implied.
- Do not invent user notifications about price decreases just because the Saved
  design shows a `New Price` label.

If the project has real saved-item persistence, respect it. Otherwise a local
in-memory demo state is enough unless persistence is expressly requested.

---

## 23. Buttons, consultation, and share actions

Visible actions should not be empty clickable surfaces. Prefer:

- Real in-app navigation for documented destinations.
- Local state changes for saved/filter/map selection.
- Android Sharesheet or Flutter's configured platform share mechanism, when
  suitable grounded share text and an installed implementation exist.
- Platform-native dial/message actions only when a verified number/address is
  known and the implementation has necessary user-triggered integration.
- Honest and brief local Snackbar feedback when an external action is not yet
  integrated.

Do not falsely claim any of the following occurred:

- A property tour was booked.
- A call was placed.
- A message was delivered.
- An agent contacted the user.
- A listing was verified externally.
- A property price or availability was updated live.

Booking and consultation UI are **presentation-level** until a real scheduling
service or contact workflow is explicitly supplied.

---

## 24. Shared image behavior; framework-specific loading

Both apps must ship **local** copies of the same six available image sources, with
matching semantic mapping and crop. Never load a network photo or generate a fake
additional real-estate photo without an explicit user request.

### Android / Jetpack Compose

```kotlin
Image(
    painter = painterResource(id = estate.imageRes),
    contentDescription = estate.name,
    contentScale = ContentScale.Crop,
    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp)),
)
```

- Prefer `painterResource` for local drawables. Existing Coil is acceptable if
  already used correctly; do not add it solely for static assets.
- Clip the image; match scaling, focal point, and scrim to the supplied visuals.
- Use `contentDescription = null` for purely decorative images.

### Flutter

```dart
ClipRRect(
  borderRadius: BorderRadius.circular(24),
  child: Image.asset(
    estate.imageAsset,
    fit: BoxFit.cover,
    alignment: Alignment.center,
    semanticLabel: estate.name,
  ),
)
```

- Declare image assets under `flutter/assets/images/` or the actual selected
  Flutter project's valid relative asset directory in `pubspec.yaml`.
- Prefer `Image.asset` / `AssetImage` for the current static photographs, not
  `Image.network`, external CDN images, or an image-loading package.
- Use `fit: BoxFit.cover`, `alignment`, a clipped shape, and optional gradient
  scrim to reproduce the same composition as Compose.
- For purely decorative images exclude redundant semantics appropriately.
- Optimize downsampling/cache only when the real dimensions/memory profile
  warrant it; do not degrade source quality unnecessarily.

### Shared image acceptance

- The image of one estate must never be shown under another estate's name.
- The advisor portrait must not become a user Profile avatar.
- Missing extra gallery photos must never be filled with photos of other estates.
- Screenshots must be compared at equivalent logical dimensions and image crops.


---

## 25. Native Material 3 components and icons

Use each framework's **native Material 3**, not WebView/HTML/CSS/JavaScript or a
platform-native view embedded just to reproduce the design.

### Android

- Use `androidx.compose.material3`, not M2 imports from
  `androidx.compose.material.*`.
- Use standard Compose/Android vector icons or local vectors.
- Preserve filled/outlined semantics and selected/unselected bookmark states.

### Flutter

- Use Flutter `material.dart` widgets with `ThemeData(useMaterial3: true)`.
- Use `Icons.*` or verified local vector assets; do not fetch icon fonts remotely.
- Match icon meaning, visual weight, hit areas, and spacing to the Android design.
- Prefer Flutter `NavigationBar`, `FilterChip`, `Card`, `FilledButton` /
  `ElevatedButton` as appropriate, `BottomSheet` / `DraggableScrollableSheet`,
  and `CustomPainter` for the Intro and illustrative map.
- Do not substitute Cupertino widgets merely because the Flutter build targets
  iOS, unless the user explicitly asks for an adaptive Cupertino design.

Icon packages must not be added solely to obtain a minor decorative symbol that
has a sufficiently close native Material counterpart.


---

## 26. Responsive portrait layouts on Android and Flutter

Use the design's **390 x 844 logical portrait** composition as a reference, not a
hardcoded size. Both apps must scale to narrower and taller devices, support
system bars/gesture insets, and remain accessible when text size changes.

### Android / Compose

- `Scaffold` / `WindowInsets` for shell and safe-area layout.
- `LazyColumn` for scrolling Explore / Detail / Saved content.
- `LazyRow` for filter chips and a responsive grid where documented.
- `Box`, `Row`, `Column`, and optional constraints for intentional hero overlays.
- `BottomSheetScaffold` when available and suited to the current Map design.
- Use a fixed Detail bottom action region above system insets without covering
  the scrollable content.

### Flutter

- `Scaffold`, `SafeArea`, `MediaQuery.paddingOf(context)`, and `viewInsets`
  appropriately; avoid double-safe-area padding.
- `CustomScrollView`, `SliverList`, `ListView.builder`, `SingleChildScrollView`,
  or `GridView` as appropriate, not a giant pre-rendered image.
- Horizontal `ListView`/`SingleChildScrollView` for filter chips.
- `Stack`, `Positioned` and `Align` only for local anchored overlays; avoid
  screenshot-sized absolute positioning of entire screens.
- `DraggableScrollableSheet` / existing bottom sheet mechanism for Map,
  respecting touch/scroll gestures and bottom navigation.
- Use `Scaffold.bottomNavigationBar` or a properly inset bottom action region
  for persistent navigation/actions; pad scrolling content so fixed bars never
  obscure property details.

### Both

- Use 16-20 logical-pixel page gutters, premium 24px section cadence, and exact
  theme radii.
- Keep a logical minimum touch target of about 48 x 48 where practical.
- Avoid nested scroll conflicts, hardcoded full-viewport image heights, text
  clipped by insets, unreachable buttons, and layout overflows.
- Keep the initial visible content and list ordering consistent across platforms.


---

## 27. Detail fixed bottom action area

The Detail screen specifies a fixed booking/consultation bar.

It must:

- Remain above the system navigation/safe-area inset on either platform.
- Use the primary evergreen theme.
- Retain the specified premium pill-button treatment.
- Remain fully visible without hiding gallery/advisor content.
- Provide sufficient bottom content padding to the scroll body.
- Reflect any locally supported state; do not show fabricated booking success.
- Have a clear accessible action label.

Do not emulate fixed positioning with a giant spacer at the bottom of a
screenshot-sized Compose `Column` or Flutter `Column`.

---

## 28. Glassmorphism and elevation

Use translucency only where documented:

- Intro bottom floating card.
- Overlay navigation buttons on the Detail hero.
- Select photo overlays / premium media controls.

For an intro panel, use roughly white alpha 0.80-0.85 and a delicate white border
matching the design token, with a 32dp / 32 logical-pixel corner radius.

For bright listing surfaces, prefer opaque `SurfaceContainerLowest` cards and
subtle Material 3 elevation (~2dp), not large blurry shadows.

Preserve readability and frame stability. If blur is expensive, unsupported, or
reduces contrast, use simple translucent surfaces instead.

---

## 29. Motion and feedback

Grounded motion expectations:

- Subtle Intro wave pulse.
- Lightweight bookmark toggle feedback.
- Filter selected-state transitions.
- Normal pressed/ripple states.
- Optional modest Detail hero scroll/parallax behavior.
- Map bottom-sheet expansion and marker selection.

Avoid:

- Confetti, fireworks, particles, uncontrolled bounces.
- Unrequested page-transition cinematics.
- Automatic carousel motion that hides actual property content.
- Random counters or changing prices.
- Animation loops that make screenshot tests nondeterministic.

Prefer native Compose animation primitives on Android and Flutter's built-in
`AnimationController`, `Animated*`, and `CustomPainter` equivalents; respect
reduced-motion preferences and properly dispose animation resources.

---

## 30. Accessibility

Provide:

- At least approximately 48dp interactive touch targets where practical.
- Correct semantics and content descriptions for action icons.
- Clear labels for selected filters, Saved tabs, and map pins.
- Accessible content descriptions for meaningful house photos and the advisor.
- Selected states shown by more than green color alone.
- Sufficient contrast on photo gradients and translucent controls.
- Readable large-font layout without dropping essential actions offscreen.
- Screen reader-friendly descriptions of known prices (when price is confirmed).
- Correct Back semantics, map sheet state, and consistent focus order.

Do not rely entirely on subtle metallic/brass color differences to show state.

---

## 31. No unsupported backend or business systems

The currently established source is a UI/design specification, not a production
backend contract. Do not add or claim support for:

- Real MLS listings, live listing synchronization, or paid real-estate feeds.
- Authentication, account registration, social login.
- Firebase databases or notifications.
- Agent CRM or chat backends.
- Calendar APIs or real tour appointment confirmations.
- Payment or loan processing.
- Price-tracking services or real notification subscriptions.
- Real-time geolocation/search services without source data.
- Imaginary property analytics/valuation or recommendation models.
- Remote property photo services.

Preserve existing working infrastructure if the actual project already contains
such a system and the user intends to keep it. Otherwise build deterministic
local UI behavior.

---

## 32. Platform permissions, privacy, and external intents

Do not add platform permissions merely because a visual reference contains an
icon or design element.

Avoid by default on **both** platforms:

- Precise or background location.
- Direct phone calls/contacts.
- Camera or microphone.
- Media library/storage access.
- Notifications or tracking.

Rules:

- Map discovery does not imply real-time device GPS.
- Displaying local bundled estate photos does not need device photo permissions.
- Contact action does not mean a verified phone number exists.
- Android external dial/share intents or Flutter equivalent platform actions
  require actual user intent and grounded data. Never claim contact occurred
  when only a local placeholder/snackbar was displayed.
- Do not add iOS privacy usage descriptions or Android manifest permissions for
  integrations that the project does not implement.


---

## 33. Strings and text fidelity

Keep documented English labels exactly where known:

- `Verdant Estates`.
- `42 Curated Homes`.
- `LEED Platinum`.
- `Featured Estate of the Month`.
- `Exclusive Reserve`.
- `The Glass Pavilions`.
- `Emerald Grove Residence`.
- `Skyline Botanica Penthouse`.
- `The Cedar Ridge Villa`.
- `Oakwood Sanctuary Cottage`.
- `Sarah Jenkins`.
- `Principal Private Advisor`.
- `Austin Luxury Division`.
- `All Saved`.
- `Hill Country`.
- `Waterfront`.
- `New Price`.
- `Explore`, `Map`, `Saved`, `Profile`.

Keep documented text in Android `strings.xml` and Flutter's chosen
localization/constants mechanism (`gen_l10n`/ARB when already configured, or a
centralized Dart copy catalog for a small demo). Avoid hardcoding divergent copy
inside per-platform screen layout files.

Unspecified headings, descriptions, stat figures, placeholder text, CTA exact
wording, and per-property sale prices must not be misrepresented as copied from a
screen screenshot. When temporary copy is required for a working demo, keep it
concise and clearly provisional in seed data/resources, rather than scattering it
throughout composables.

---

## 34. Project boundaries and suggested source structures

Keep the Android and Flutter applications as **independent builds**. Do not
change the existing Android application ID, Android package names, Flutter
bundle/application IDs, or root layout merely to mirror illustrative structures.

### Android / Jetpack Compose example

```text
<android-app>/app/src/main/java/<existing-package>/
  ui/
    theme/        (Color.kt, Type.kt, Theme.kt)
    components/   (EstatePhotoCard, FeaturedEstateCard, AdvisorCard)
    intro/
    explore/
    detail/
    saved/
    map/
  data/
    model/        (Estate.kt)
    repository/   (EstateRepository, SavedEstateRepository)
  navigation/     (AppNavigation.kt)

<android-app>/app/src/main/res/drawable/
  architectural_photograph_of_the_glass.png
  emerald_grove.png
  botanica_luxury.png
  modern_luxury.png
  sanctuary_cottage_in.png
  female_luxury.png
```

### Flutter example (only if no clean project structure exists)

```text
<flutter-app>/
  lib/
    main.dart
    app/
      verdant_app.dart
      app_router.dart
    design/
      colors.dart
      typography.dart
      spacing.dart
      app_theme.dart
    features/
      intro/
      explore/
      detail/
      saved/
      map/
    widgets/
      estate_photo_card.dart
      featured_estate_card.dart
      advisor_card.dart
    data/
      estate.dart
      estate_repository.dart
      saved_estate_repository.dart
  assets/
    images/
      architectural_photograph_of_the_glass.png
      emerald_grove.png
      botanica_luxury.png
      modern_luxury.png
      sanctuary_cottage_in.png
      female_luxury.png
  pubspec.yaml
  test/
```

The Flutter path is **illustrative**, not proof that a sibling folder or
repository with that name already exists. Copy verified source assets into the
chosen target project and declare them in `pubspec.yaml`.

Avoid scattering identical demo data in Android and Flutter screens. Each app
has one local catalog; both catalogs must be semantically synchronized.


---

## 35. Dependency selection per platform

Before adding any dependency, inspect the **actual target project's** current
build files and existing libraries. Do not assume dependency names or versions
from another project.

### Android

1. Check the module Gradle files, version catalog, lifecycle, Material 3, and
   navigation setup.
2. Prefer built-in Compose Canvas and `painterResource` for static local UI.
3. Do not add Coil, Accompanist, map SDK, or other libraries without a concrete
   capability gap.
4. Use current compatible Material 3 APIs; do not mix Material 2.

### Flutter

1. Inspect `pubspec.yaml`, Flutter/Dart SDK constraints, platforms, and existing
   packages before changing package dependencies.
2. Prefer core Flutter `material.dart`, `widgets.dart`, `CustomPainter`, bundled
   assets, and an existing simple state pattern.
3. Use `Navigator` or existing app router; do not add `go_router` solely because
   Android uses Compose Navigation.
4. Do not add Provider/Riverpod/Bloc/GetX, `cached_network_image`,
   `google_maps_flutter`, map tile fetchers, remote font packages, or sharing
   packages just for a static UI reference.
5. If the project already uses a well-established compatible package, retain
   it rather than rewriting working routing/state management.

### Both

- Do not build an unnecessary custom backend.
- Do not fetch reference images/icons/fonts from external URLs at runtime.
- Do not add GPS permissions or map credentials for a map-style UI mock.
- Verify builds with installed compatible tools rather than guessing versions.


---

## 36. Deterministic demo state and previews

Keep all demo data deterministic in **both** apps. Never randomize property
names/prices, advisor identity, new-price badges, bookmark defaults, intro
accolades, or map labels. Unknown source values remain nullable or receive an
honestly labeled temporary UI state.

### Android

- Provide useful `@Preview` states for Intro, Explore, Detail, Saved populated /
  empty states, Map marker selection and expanded/collapsed preview.
- Use controlled UI states and real bundled photos; do not require internet or
  credentials for previews.

### Flutter

- Make each of the five screens renderable with injected/local fixture state.
- Use `flutter_test` widget tests and golden tests where feasible/established,
  pinning surface size, text scale, deterministic animation phases and fonts.
- Keep asynchronous requests, animation loops, timestamps and map textures out
  of deterministic UI/golden test setup.
- Avoid visual snapshots that depend on host-specific fonts without accounting
  for them. When fonts are missing, report the limitation, don't fabricate them.

Use matching **initial** UI values on both platforms, including bookmarked
records, selected tabs/filter states, visible featured property, and intro state.


---

## 37. Required implementation workflow

For each requested screen or change:

1. Determine the explicit target: **Compose**, **Flutter**, or **both**. Never
   assume work in one project authorizes modification of the other.
2. Inspect the real target repository, current visual evidence, `design.md`,
   asset files and these rules before coding.
3. Identify missing screen images or data; do not invent them.
4. Preserve exact design tokens and the shared asset/id map.
5. Implement the feature in the native architecture of the selected framework.
6. Use an immutable UI model and actions/events for interactive state.
7. Connect only grounded navigation and local demo behavior.
8. Verify the target's asset registration, safe areas, content scale and icons.
9. Add or update reasonable unit/widget/UI tests and preview fixtures.
10. For Android: run relevant Gradle compile/build and tests when the tooling is
    available.
11. For Flutter: run `flutter pub get` (as appropriate), `dart format`,
    `flutter analyze`, `flutter test`, and a target-platform build/check when
    available; report commands actually executed.
12. Compare rendered screenshots at equivalent 390 x 844 **logical** portrait
    dimensions against real current references when provided; compare Android
    versus Flutter for major layout/behavior parity when both exist.
13. Report which platform(s) changed, what actually built/tested, and what
    remains unverified due to missing files, references, emulator or SDK.

Never claim "pixel perfect", "build passed", "Flutter/iOS ready", or parity
verified without performing the corresponding inspection or execution.


---

## 38. Minimum tests for both implementations

Use the current project's established test mechanisms; avoid adding a bulky
framework solely for this five-screen demo.

### Domain/catalog tests

- Exactly documented five estate identities map to the correct six local
  images (five properties and one advisor).
- Featured price `$7,850,000` belongs to The Glass Pavilions featured card;
  no invented prices are attached to other properties.
- Detail selection never mixes one estate's title, hero and details.
- Stable local IDs resolve across Explore, Detail, Saved, and Map.

### State/navigation tests

- Intro continues to Explore; Back does not unexpectedly reopen Intro.
- Toggling Saved in Explore reflects in Detail and Saved and vice versa.
- `All Saved`/`Hill Country`/`Waterfront` respect explicit collection tags.
- Search/filter empty states are safe and accurately labeled.
- Map marker selection updates the preview and the sheet can expand/collapse.
- Profile tab does not create an unsupported account journey.

### UI/layout tests

- Core theme tokens, crop, text, rounded surfaces and navigation ordering match.
- Persistent bottom CTA and navigation respect safe areas on tested devices.
- Narrow portrait, larger text scale, and missing optional gallery data do not
  crash or overflow.
- Android Compose previews/widget UI tests use local fixture state.
- Flutter `flutter_test` widget tests/goldens use the same semantic seed state.
- No network photo, real map, GPS, real booking, or live listing system is
  implicitly required to render any core screen.

### Parity checkpoint when both builds exist

Compare the same app state on both platforms for: screen hierarchy, featured
property, text, token colors, typography scale, photos and crop, bottom tabs,
bookmarks, saved collections, detail CTA, intro, and map mock interaction.
Do not claim pixel-identical rasterization across different renderers/font
engines; the requirement is **close design fidelity and equivalent behavior**.


---

## 39. Final acceptance checklist (repeat per platform)

### Evidence and identity

- [ ] Correct product: **Verdant Estates** (not BrewCraft).
- [ ] Current `design.md` and actual supplied screen references were consulted.
- [ ] All five documented screens are implemented/treated appropriately.
- [ ] There are no unsupported new login, profile, booking, or payment flows.
- [ ] No unknown property data were presented as externally verified facts.

### Design and assets

- [ ] Exact color roles/gradients and documented typography are centralized.
- [ ] 16-20 logical gutters, premium shapes, 390 x 844 reference and accessibility
      scaling work without clipped content.
- [ ] Every named property uses its correct local photo.
- [ ] `female_luxury.png` is used for Sarah Jenkins, **not** a profile avatar.
- [ ] Missing `IMAGE_2` and missing additional estate gallery images were not
      silently downloaded or generated.
- [ ] No remote asset/CDN/font request is necessary for core UI.

### Screen composition

- [ ] Intro has deep green organic waves, `42 Curated Homes`, `LEED Platinum`,
      and a translucent rounded bottom panel.
- [ ] Explore has search, filters, featured/card feed, `Exclusive Reserve`,
      the correct Glass Pavilions hero and documented `$7,850,000` price.
- [ ] Detail has hero, safe overlay actions, spec fields without fabricated
      numbers, curated gallery treatment, Sarah advisor card and inset CTA.
- [ ] Saved has `All Saved`/`Hill Country`/`Waterfront` and synchronized bookmarks.
- [ ] Map shows visually distinguishable marker selections and a working preview
      sheet, while being honest about illustrative markers/unknown coordinates.
- [ ] Navigation labels are `Explore / Map / Saved / Profile`.

### Architecture and interaction

- [ ] Both app implementations use their **own native UI framework** and
      appropriate one-way state flow.
- [ ] Catalog and Saved have a single local source of truth **per app**.
- [ ] User actions have deterministic state change, grounded navigation, or
      honest unimplemented-action feedback; no fake booking/call success.
- [ ] No unnecessary services, location/media/phone permissions, or dependencies.
- [ ] No HTML, WebView, CSS, JavaScript UI, or cross-framework source imports.

### Android-only checks (when Android is requested)

- [ ] Kotlin/Compose Material 3 compiles using actual compatible Gradle setup.
- [ ] Lifecycle-aware state collection and Compose navigation work.
- [ ] Resources are local drawables and previews don't require remote data.
- [ ] Android tests/build that were actually executed are reported accurately.

### Flutter-only checks (when Flutter is requested)

- [ ] Dart Flutter Material 3 code compiles with installed SDK constraints.
- [ ] Exact local images are declared in `pubspec.yaml` and loaded via assets.
- [ ] Shared state updates all screens; no state mutation inside `build()`.
- [ ] Controllers and animation resources are disposed of appropriately.
- [ ] iOS platform readiness is claimed only if iOS target/configuration has
      actually been built or checked, not simply because Flutter is cross-platform.
- [ ] Actual `flutter analyze`/`flutter test`/build results are reported.

### Parity (only when both implementations exist)

- [ ] Shared property IDs, images, source-derived strings and seed state agree.
- [ ] Screen layouts, primary actions, selected states, bookmark changes,
      navigation behavior, and map demo are functionally equivalent.
- [ ] Visual comparison was made at comparable logical dimensions; any known
      deliberate platform differences are documented.


---

## 40. Final governing principle

If an implementation decision is not supported by current evidence, **do not
turn that assumption into a product requirement or a purported factual claim**.

Work in this order:

**Actual current Verdant screen visuals (if supplied) -> current `design.md`
-> actual current `assets/` -> existing matching target-platform source ->
optional current-project HTML as behavior reference only.**

The deliverables are two **separate, native, maintainable versions of the same
Verdant Estates product** when both are requested:

- **Android:** Kotlin + Jetpack Compose + Material 3.
- **Flutter:** Dart + Flutter Material 3, with Android/iOS targets only to the
  extent the current Flutter project enables and verifies them.

They must preserve the same five-screen design, identity, local photography,
static seed content, interaction states, and UX. They need **not** share source
code or force one framework's APIs onto another. No fictional real-estate
backend, data, coordinates, assets or extra screens are authorized by these rules.


---

## 41. Android-specific implementation contract

- Language: **Kotlin**. UI: **Jetpack Compose Material 3**.
- Architecture: Single Activity where compatible with existing code, **MVVM +
  UDF**, immutable `UiState`, `StateFlow`, explicit UI events and lifecycle-aware
  collection (`collectAsStateWithLifecycle()`).
- Navigation: existing Compose Navigation or current native routing; use stable
  local property IDs and restore detail state from the catalog.
- Rendering: keep `@Composable` screen bodies primarily stateless; pass state
  and `onAction`; keep state mutations and navigation coordination in
  ViewModel/route boundary.
- Assets: verified local images under drawable resources and
  `painterResource`, with correct `ContentScale.Crop`.
- Motion: `Canvas`, cubic curves, `Brush`, and standard Compose animation,
  respecting motion accessibility preferences.
- System UI: `Scaffold`/insets, Material `NavigationBar`, fixed CTA in Detail,
  and `BottomSheetScaffold` or equivalent for the mock map.
- Keep existing clean architectural patterns; do not refactor a working app
  into a new architecture just to match illustrative names.

## 42. Flutter-specific implementation contract

- Language: **Dart**. UI: native **Flutter Material 3** (`ThemeData(useMaterial3: true)`).
- The Flutter version is **a real Flutter app**, not screenshots, platform views
  hosting Compose, translated HTML, or unconnected widgets.
- Architecture: presentation Widgets + immutable screen state + a shared local
  catalog/repository and explicit events/actions. Prefer the current Flutter
  project's existing state-management solution; otherwise built-in
  `ChangeNotifier`/`ValueNotifier` with well-defined lifetime is sufficient.
- Centralize palettes and typography in `ThemeData`/`ColorScheme`/`TextTheme`
  plus design constants / `ThemeExtension` for custom dark-Intro/glass tokens.
- Navigation: retain existing Navigator/router. Use stable property IDs and
  avoid passing image bytes/full property objects in route arguments.
- Local images: declare under `pubspec.yaml`; render with `Image.asset` /
  `AssetImage` and `BoxFit.cover`; preserve advisor/property semantic mapping.
- Responsive layout: `Scaffold`, `SafeArea`, slivers/lists, `Stack` only for
  meaningful overlays, inset fixed Detail CTA and bottom navigation.
- Intro art: Flutter `CustomPainter` + paths/gradients; manage animations via
  `AnimationController` and ticker lifecycle, with deterministic previews.
- Map: illustrative local `Stack`/`CustomPainter` composition plus
  `DraggableScrollableSheet`/current equivalent; **no real map SDK by default**.
- Do not require a network connection, API key, fonts from the internet, GPS,
  storage access, or account login to render the five designed screens.
- Ensure local state survives top-level tab changes as the existing navigation
  architecture permits. Dispose focus/scroll/animation controllers properly.
- Support iOS as a Flutter target **only to the extent the actual project has
  it configured**; do not promise tested iOS compatibility without a build.

## 43. Cross-platform equivalence reference

| User-facing requirement | Android / Compose | Flutter / Dart |
|---|---|---|
| Brand theme | `MaterialTheme.colorScheme` | `ThemeData` + exact `ColorScheme` |
| Text styles | `MaterialTheme.typography` (sp) | `TextTheme` / `TextStyle` (logical px) |
| Local photography | `painterResource(R.drawable...)` | `Image.asset` declared in `pubspec.yaml` |
| Image crop | `ContentScale.Crop` | `BoxFit.cover` |
| Theme shapes | `RoundedCornerShape` | `RoundedRectangleBorder` / `BorderRadius` |
| Scroll feed | `LazyColumn`, `LazyRow` | `ListView` / slivers / horizontal scroll |
| Material shell | `Scaffold` | `Scaffold` |
| Bottom destinations | `NavigationBarItem` | `NavigationDestination` |
| Stateful route | ViewModel + `StateFlow` | Controller/Notifier with immutable state |
| UI feedback | Material Snackbar / local states | `ScaffoldMessenger` / local states |
| Intro waves | Compose `Canvas`/`Path` | Flutter `CustomPainter`/`Path` |
| Hero gradients | Compose `Brush` | Flutter `LinearGradient` |
| Map demo markers | Compose `Box`/`Canvas` | Flutter `Stack`/`CustomPainter` |
| Map preview sheet | Material3 `BottomSheetScaffold` | `DraggableScrollableSheet` / current sheet |
| Platform sharing | Android sharesheet intent if valid | Existing platform share integration if valid |
| Verification | Gradle / Android tests / previews | `flutter analyze`, `flutter test`, build / goldens |

The table is an **equivalence guide**, not instructions to add dependencies or
force class names. Follow actual project package conventions.

## 44. Required parity matrix for the five screens

| Screen | Shared visuals | Shared behavior / state |
|---|---|---|
| Intro | dark pine gradient, organic waves/halos, two accolade pills, white glass bottom panel | primary CTA enters Explore; do not re-open Intro on Back without an established first-launch policy |
| Explore | search bar, selected filter row, Glass Pavilions featured hero and curated cards | local query/filter state, property selection, consistent save/bookmark actions |
| Detail | full-bleed estate hero, overlay controls, spec bar, narrative, gallery, Sarah advisor, fixed CTA | property matches selected ID; saved state synchronized; safe share/consult action |
| Saved | luxury saved-card grid/list, All Saved/Hill Country/Waterfront | shared saved IDs, explicit tags only, accurate empty state |
| Map | Austin-themed illustrated map area, green/brass price pins, expandable preview | same demo markers, selection, bottom-sheet state; no fake geodata claims |

Also keep `Explore / Map / Saved / Profile` nav items, the same primary accent,
image crop targets and interaction labels. A documented Profile **tab** is not
permission to construct a sixth, unsupplied Profile screen.

## 45. Screen/data contract versus mock content

- The existing `design.md` is **Android-oriented**, but brand strings, tokens,
  five-screen structure, filenames, and applicable interaction descriptions
  define **both** apps.
- Tokens like `IMAGE_10` denote source imagery in documentation, **not** a Dart
  constant, Android drawable resource, web URL, or remote image endpoint.
- `$7,850,000` is the featured property example for The Glass Pavilions. `$7.4M`
  and `$5.8M` are **map-pin example labels**, not data for particular named
  properties. Never silently equate these values.
- Unknown bedding/bath/area/LEED/license/address/coordinate data remain missing.
- Local demo state can be deterministic and illustrative, but it must never be
  described as a verified current listing, live price, map coordinate, or booked
  consultation.
- Keep English UI labels where documented. Do not add unrequested localization
  to one platform but not the other.

## 46. Flutter creation mode for a sibling implementation

When explicitly asked to create Flutter from an existing Verdant Compose app:

**Inputs needed:** a usable copy of the canonical `design.md`, the actual six
image files (a screenshot of their folder is not the image data), and any existing
current screen references/source to be matched. If some are unavailable, do not
fabricate files or claim parity has been visually verified.

1. **Read this file and `design.md`** plus actual Compose screens (as a visual
   and behavior reference), without copying Android API/lifecycle assumptions.
2. Inspect the existing Flutter project, if one exists. If not, create a clean
   Flutter app in the user's requested location; **do not assume** an exact sibling
   folder or overwrite the Android source tree.
3. Import the **six existing verified images** into the Flutter asset bundle,
   preserving names and source identity; register them in `pubspec.yaml`.
4. Implement theme colors and typography centrally before screens.
5. Create the five Flutter screens and native navigation with the same seeded
   property catalog, IDs and saved behavior as Compose.
6. Use Flutter-native `Widget`s, `CustomPainter`, state management and routing.
7. If the Android implementation adds content not grounded in the design,
   **do not automatically import it as a product requirement**; prefer the
   verified screenshot/`design.md` and reconcile meaningful conflicts.
8. Run Flutter diagnostics/tests and verify actual display/layout.
9. If both apps exist, compare them at equivalent logical size and the same UI
   state; correct meaningful deviations before declaring parity.

Creating Flutter does **not** authorize deleting, moving or refactoring the
working Compose app. Creating Compose does **not** authorize rewriting Flutter.

## 47. Safe Flutter development boundaries

- `flutter create` should only run when needed and at an explicitly authorized
  location, never inside the existing Android project just to obtain tooling.
- Respect current `pubspec.yaml` constraints, lockfile, iOS/Android platform
  configuration, namespace/bundle identity, lints and directory conventions.
- Never add or hardcode map API credentials, remote listing URLs, fake phone
  numbers, advisor license IDs, Firebase credentials or payment parameters.
- Use user-triggered external actions only where the target platform actually
  has compatible integration and grounded data; otherwise show honest local UI
  feedback without claiming the action succeeded.
- Prefer Flutter Material 3 styling on both OSes for consistent references.
- Never implement the Flutter build by transpiling Kotlin files, using WebView,
  displaying screenshot composites, or embedding a different app's UI.

## 48. Cross-project handoff report

At the end of any implementation request, report concisely:

1. Target project(s) actually modified: Android, Flutter, or both.
2. Screen(s), models, theme and assets changed.
3. Verified interactions and exact seed state/copy used.
4. Which build/analyze/test/screenshot commands **actually ran** and their results.
5. Remaining evidence gaps or limitations (missing screen references, missing
   secondary advisor photo, missing maps/geocoordinates, unavailable SDK/OS).
6. Any meaningful visual/behavior parity differences between platforms.

Do not report a successful Flutter iOS build without an iOS build, a successful
Android Gradle build without running Gradle, or pixel-perfect matching without
checking corresponding renders.
