---
name: ktor-engine-logic
description: Backend-specific rules for the engine/ module (routing, serialization, predictions).
---

# Ktor Engine Logic

Rules for maintaining the VUELTA F1NAL backend robustly.

## Guidelines
1. **Modular Routes**: Define routing in separate extension functions on `Route` located in `com.vueltaf1nal.routes` (e.g., `ResultsRoutes.kt`).
2. **Layered Responsibility**:
   - `Routes` handles HTTP requests and calls Repositories.
   - `Repositories` (in `data.repositories`) handle logic and use `Mappers` to return `DomainModels`.
   - `DTOs` (in `data.dtos`) are used only for serialization at the edge.
3. **Type Safety**: Use Kotlin Serialization for all JSON contracts.
4. **Predictions**: All algorithm-heavy logic should be isolated in the `domain.models` or specialized domain services.
5. **JSON Config**: Maintain `ignoreUnknownKeys = true` in Ktor content negotiation.
