# Movement tests and CI

## Problem

The instrumentation suite used the removed `GameViewModel()` constructor, `player` property, and ViewModel-based `OverworldScreen` signature. JVM tests could pass while the Android test APK failed to compile.

## Changes

- Construct the ViewModel with the test application's `Application` on the UI thread.
- Use an empty 5 by 5 map and a centered player so every direction test verifies an actual move.
- Observe Compose state inside the composition and lay out the map above the controls.
- Read and update state through `runOnIdle` to synchronize with Compose.
- Cover all four directions, both opposite boundaries, and repeated clicks using updated state.
- Build the app and instrumentation APK in CI, including PRs targeting `codex/**` branches. Upload JVM reports even when a later step fails.

## Verification

Run `./gradlew testDebugUnitTest assembleDebug assembleDebugAndroidTest` to check JVM tests and compilation. With an Android emulator or authorized device connected, run `./gradlew connectedDebugAndroidTest` to execute instrumentation tests. APK compilation alone does not execute UI tests.

## Device execution in CI

A separate GitHub Actions job runs `connectedDebugAndroidTest` on an API 35 x86_64 emulator with Linux KVM enabled. Animations are disabled for deterministic UI synchronization. Device reports are uploaded even after failure. This allows device validation when the local Windows emulator lacks a hardware acceleration driver.

The encounter flow regression launches `MainActivity`, reaches the first spawned enemy using directional buttons, exits battle, fights a second enemy, and revisits the first enemy's cleared tile. It verifies the actual startup asset, spawn wiring, controls, and battle return flow together. This regression depends on the encounter spawning and enemy preservation changes.
