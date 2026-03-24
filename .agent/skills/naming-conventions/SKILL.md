# Naming Conventions Skill

This skill defines the directory and file naming standards for the VUELTA F1NAL ecosystem to ensure consistency across Frontend and Backend.

## Core Folders
Organize code into these specific directories based on their responsibility:

- **`dtos/`**: External data representations and API contracts (Data layer).
- **`mappers/`**: Data transformation logic (DTO to Domain model). In the Frontend, these classes are the direct data providers for ViewModels.
- **`repositories/`**: **(BACKEND ONLY)** Infrastructure and Interface contracts.
- **`remote/`**: **(FRONTEND ONLY)** Ktor services and network orchestration.
- **`models/`**: Pure business entities and domain logic (Domain layer).

## Rules
1. **Pluralization**: Always use plural names for directories containing multiple components (e.g., `dtos`, `mappers`, `routes`).
2. **Frontend Simplification**: The Frontend (`composeApp`) **MUST NOT** use Repository interfaces. ViewModels interact directly with `Mappers`.
3. **Single Responsibility**: Each folder should only contain files that strictly follow the convention above.
4. **Consistency**: Use the same naming for common layers (`dtos`, `mappers`, `models`) across the `engine` and `composeApp` modules.

## Example Structure (ComposeApp)
```
data/
  ├── dtos/          # DTOs
  ├── mappers/       # Mapper classes (used by ViewModels)
  └── remote/        # Ktor services
domain/
  └── models/        # Business entities
```
