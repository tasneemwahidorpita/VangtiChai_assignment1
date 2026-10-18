================================================================================
                               VANGTICHAI
                      Android Money Change Calculator
================================================================================

1. PROJECT OVERVIEW & FEATURES
--------------------------------------------------------------------------------
VangtiChai is an intuitive Android application designed for Bangladeshi Taka (BDT)
money change calculation. The app breaks down any entered integer Taka amount into
the minimum count of notes across standard BDT denominations:
500, 100, 50, 20, 10, 5, 2, and 1 Taka.

Features:
- Instant Change Calculation: Greedy note breakdown updated in real time as digits are pressed.
- On-Screen Numeric Keypad: Custom 10-digit keypad (0–9) and CLEAR button.
- Clean Input Logic: Automatic prevention of leading zeros (e.g. typing '0', '0', '7' becomes '7')
  and overflow prevention (bounded to 12 digits max).
- Orientation Support: Optimized layouts for both Portrait and Landscape orientations.
- Multi-Screen Density & Qualifier Support: Responsive design tailored for standard
  smartphones (Pixel XL) and 10-inch tablets (Nexus 10).
- Seamless State Persistence: Full UI state persistence across screen rotations and
  activity/process recreation using ViewModel and SavedStateHandle.


2. ARCHITECTURE OVERVIEW
--------------------------------------------------------------------------------
- Language & API Level: Kotlin, Minimum API Level 21 (Android 5.0 Lollipop).
- Layout Engine: ConstraintLayout with flexible horizontal and vertical chains/weights.
- State Management: Architecture Components ViewModel + SavedStateHandle + LiveData.
- Modular Layout Qualifiers:
  * res/layout/activity_main.xml (Phone Portrait)
  * res/layout-land/activity_main.xml (Phone Landscape)
  * res/layout-sw600dp/activity_main.xml (Tablet Portrait)
  * res/layout-sw600dp-land/activity_main.xml (Tablet Landscape)
- Shared View IDs: All 4 layout configurations share 100% identical View IDs
  (tvEnteredAmount, tvNote500..tvNote1, btn0..btn9, btnClear) ensuring zero missing view
  references or crashes across configuration changes.
- Resource Externalization: All dimensions (dimens.xml & dimens-sw600dp.xml),
  strings (strings.xml), and colors (colors.xml) are externalized with 0 hardcoded values.


3. TESTED DEVICES & SCREEN PROFILES
--------------------------------------------------------------------------------
The application layout and lifecycle state retention have been verified across:
1. Pixel XL (Phone - 411 x 731 dp / 5.5" QHD)
   - Portrait: 3x4 Keypad grid, single-column vertical note breakdown list.
   - Landscape: 4x3 Keypad grid, 2-column balanced note breakdown table.
2. Nexus 10 (Tablet - 800 x 1280 dp / 10.0" WXGA)
   - Portrait (sw600dp): Scaled text sizes (40sp header, 32sp keypad), spacious layout.
   - Landscape (sw600dp-land): Side-by-side arrangement utilizing high horizontal real estate.
3. Pixel 7 / Pixel 8 Pro (Standard Modern Phones)
4. 7" & 10" Android Tablets (Portrait & Landscape)


4. BUILDING AND RUNNING THE APPLICATION
--------------------------------------------------------------------------------
Prerequisites:
- Android Studio Iguana / Jellyfish / Ladybug or newer.
- Android SDK 34 (Build Tools 34.0.0).
- Java JDK 17 / JDK 21.

Steps to Build & Run:
1. Open Android Studio and select "Open an Existing Project".
2. Navigate to and select the root directory: Assignment1_CSE489
3. Let Gradle sync the project dependencies.
4. Select a virtual device (e.g., Pixel XL or Nexus 10) in the Device Manager.
5. Click 'Run' (Shift + F10) or execute via terminal:
   - Linux/macOS: ./gradlew installDebug
   - Windows: gradlew.bat installDebug
6. Run unit tests to verify core logic:
   - Linux/macOS: ./gradlew test
   - Windows: gradlew.bat test
