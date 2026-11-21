# BreadFrame

BreadFrame forks [Playback](https://github.com/awruff/Playback), a client-side Minecraft replay mod distributed as Keyframe. It adds first-person capture and replay features to Flashback and can also record, edit, and export replays in standalone mode. The [upstream DeepWiki](https://deepwiki.com/awruff/Playback) describes the architecture. Check its details against the current source.

## Build and test

- The maintained targets are Minecraft 1.8.9 Ornithe and Minecraft 26.3 Fabric. Use `./gradlew :1.8.9:build --configure-on-demand` or `./gradlew :26.3:build --configure-on-demand` to build and test one target. Install the regular jar from `versions/<target>/build/libs/`.
- Use `./gradlew :<target>:test --configure-on-demand` to run the existing tests alone. For manual testing, `./gradlew :<target>:runClient -Pstandalone --configure-on-demand` starts the standalone client without Flashback.
- Both maintained targets use an Adoptium Java 25 toolchain and require OneConfig 1.9.0 or later with PolyCompose. After a OneConfig update, the user can check that the editor opens and that the export settings render.
- `settings.gradle.kts` declares the Minecraft targets, `stonecutter.properties.toml` pins per-version dependencies, and `stonecutter.gradle.kts` handles version-specific source transformations.
- Install the regular mod jar in the matching Fabric profile. Check the jar's `fabric.mod.json` for runtime requirements. Flashback is optional and selects the add-on mode when present.
- At the end of each successful build, replace the installed Keyframe jar in `/home/user/.local/share/Polyfrost/OneClient/clusters/1.8.9 Fabric/mods/` with the latest regular 1.8.9 Ornithe jar. Build the Ornithe target first if needed and leave only one Keyframe jar in that profile.
- Leave graphical tests to the user. Do not capture screenshots or simulate GUI clicks or other GUI input for testing.

### Ornithe build dependencies

The Ornithe build uses three local Maven artifacts from [Flashback Legacy](https://github.com/Polyfrost/flashback-legacy/tree/73bcf569ef7972ce4430671ae5b36b5a2f62c492). In that checkout, run `./gradlew remapJar patchFlashback compileStubs`. Install each output into Maven Local with `mvn install:install-file`, using group `org.polyfrost`, version `0.1.0+1.8.9`, and packaging `jar`.

| Artifact ID | Flashback Legacy build output |
| --- | --- |
| `flashback_legacy` | `build/libs/flashback_legacy-0.1.0+1.8.9.jar` |
| `flashback_legacy-patched-flashback` | `build/flashback/compile/flashback-patched.jar` |
| `flashback_legacy-stubs` | `build/flashback/compile-stubs.jar` |

## Source layout

- Edit shared sources in `src/main` and tests in `src/test`. Stonecutter generates version-specific sources and build outputs under `versions/`.
- `src/main/java/org/polyfrost/keyframe/boot/` contains the Fabric entrypoint and mixin plugin that select the runtime mode. The neighboring Java packages implement Flashback integration and shared replay features.
- `src/main/kotlin/org/polyfrost/keyframe/standalone/` contains standalone recording, playback, editing, and export. Platform-specific 1.8.9 Ornithe sources live in `ornithe` packages.
- When changing a Minecraft hook or mixin, check the existing version guards and resource generation. Run the applicable non-graphical tests for the affected Minecraft version and runtime mode.

Keep this file current when build targets, dependencies, or source layout change.
