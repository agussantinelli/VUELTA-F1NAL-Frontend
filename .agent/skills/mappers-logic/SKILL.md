# Mappers Logic Skill

This skill defines the standard for data transformation and repository implementation in the VUELTA F1NAL project.

## Overview
In this architecture, the data layer uses "Mappers" to bridge the gap between external data (DTOs) and internal business entities (Domain Models).

## Naming Conventions
- **Folder**: `data/mappers/`
- **Classes**: `*Mapper` (e.g., `ResultsMapper`, `TopSpeedsMapper`).
- **Functions**: Extension functions named `toDomain()` and `toDto()`.

## Implementation Rules
1. **Extension Functions**: Define mapping logic as extension functions on DTO or Domain classes.
   ```kotlin
   fun DriverResultDto.toDomain(): DriverResult = DriverResult(...)
   ```
2. **Mapper Classes**: When a class implements a Repository interface from the domain layer, it should be named `*Mapper` if its primary role is data orchestration and transformation.
   ```kotlin
   class ResultsMapper(private val service: ResultsService) : ResultsRepository {
       override suspend fun getResults() = service.getResults().map { it.toDomain() }
   }
   ```
3. **No Business Logic**: Mappers should only handle data conversion and basic orchestration. Complex logic belongs in the Domain layer.

## Benefits
- Clearer separation of concerns.
- Avoids duplicate "repository" folder names across layers.
- Simplifies repository implementations by delegating mapping to pure functions.
