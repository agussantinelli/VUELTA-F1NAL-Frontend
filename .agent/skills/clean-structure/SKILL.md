---
name: clean-structure
description: Component organization and modern Android/Compose principles for the FrontEnd.
---

# Clean Structure (Mobile)

A well-organized codebase ensures VUELTA F1NAL is maintains racing speed.

## Guidelines
1. **Module Separation**:
   - `:composeApp`: Android UI and platform-specific logic.
   - `:engine`: Ktor backend and prediction core.
2. **Package Organization (App)**:
   - `ui.components`: Reusable UI elements (Buttons, Cards).
   - `ui.screens`: Screen-level Composables.
   - `ui.viewmodel`: State management and UI logic.
   - `ui.theme`: Design system (Color, Type, Theme).
   - `data.models.api`: DTOs for network/serialization.
   - `data.remote`: Ktor services and network clients.
   - `data.repository`: Repository implementations.
   - `domain.models`: Business models (Entities).
   - `domain.repository`: Repository interfaces (Abstractions).
3. **Naming**:
   - Composables: PascalCase (e.g., `RaceCard`).
   - ViewModels: PascalCase ending in `ViewModel`.
4. **Consistency**: Use a consistent MVI/MVVM pattern for state handling across all screens.
