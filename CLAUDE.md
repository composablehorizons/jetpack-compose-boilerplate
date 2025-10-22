# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Jetpack Compose Android boilerplate using **Compose Unstyled** - a library of fully accessible, renderless UI primitives. The project deliberately avoids Material Design dependencies (Material 3 or Material Compose) in favor of custom, token-based theming.

**Key Architecture:**
- Single-module Android app (`app`)
- Package: `org.example.project`
- Min SDK 24, Target SDK 36
- Java 11 toolchain
- Edge-to-edge UI enabled by default

## Build Commands

Since this project uses Gradle without a committed wrapper script, use Android Studio's built-in Gradle or install gradlew:

- **Run app**: Use Android Studio's "app" run configuration
- **Build project**: Sync Gradle in Android Studio or use: `gradle build`
- **Clean build**: `gradle clean`

## Compose Unstyled Theming System

The project uses a **token-based theme system** defined in `Theme.kt`. This is the central file for all design tokens.

**Theme Structure:**
1. **Theme Properties**: Define categories of design tokens (colors, textStyles, shapes)
2. **Theme Tokens**: Specific tokens within each property (primaryColor, headerStyle, etc.)
3. **AppTheme**: The buildTheme block that assigns values to all tokens

**Example token definition:**
```kotlin
val colors = ThemeProperty<Color>("colors")
val primaryColor = ThemeToken<Color>("primary")
```

**Accessing theme values:**
```kotlin
Theme[colors][primaryColor]  // Access the primary color token
Theme[textStyles][headerStyle]  // Access the header text style
```

## Component Development Guidelines

**All components must be themable using tokens from Theme.kt:**

1. **Import theme tokens** at the top of component files
2. **Use Theme[property][token]** syntax to access design values
3. **Never hardcode** colors, typography, or shapes
4. **Use Compose Unstyled primitives** instead of Material components

**Example component pattern (see Buttons.kt):**
```kotlin
@Composable
fun PrimaryButton(onClick: () -> Unit, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Button(
        // ... parameters
        backgroundColor = Theme[colors][primaryColor],  // Token-based theming
        contentColor = Theme[colors][onPrimaryColor],
        shape = Theme[shapes][smallShape],
        // ... more config
    ) {
        content()
    }
}
```

**Required patterns for interactive components:**
- Use `MutableInteractionSource` for hover/focus/press states
- Apply `.minimumInteractiveComponentSize()` modifier for accessibility
- Add `.focusRing()` modifier for keyboard navigation
- Add `.outline()` modifier when appropriate

## Compose Unstyled Library

**Documentation**: https://composables.com/docs/compose-unstyled

This library provides:
- Fully accessible, renderless primitives (Button, Switch, Dialog, etc.)
- Built-in accessibility (ARIA patterns, keyboard navigation)
- No default styling - you control all visual aspects
- Icons via Lucide icon pack
- Custom ripple indication system

**When building new components:**
1. Check the Compose Unstyled documentation for available primitives
2. Start with the unstyled primitive as the base
3. Apply theme tokens for styling
4. Follow accessibility patterns from existing components (Buttons.kt)

## Project Structure

```
app/src/main/java/org/example/project/
├── MainActivity.kt    - Entry point, enables edge-to-edge
├── App.kt            - Main composable, demonstrates theming
├── Theme.kt          - Central design token definitions
└── Buttons.kt        - Example themable components
```

## Important Constraints

- **No Material Design**: Never import or use `androidx.compose.material` or `androidx.compose.material3`
- **Token-based only**: All styling must use tokens from Theme.kt
- **Edge-to-edge**: UI is edge-to-edge by default, use `.safeDrawingPadding()` for content areas