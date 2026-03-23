---
name: ktor-engine-logic
description: Backend-specific rules for the engine/ module (routing, serialization, predictions).
---

# Ktor Engine Logic

Rules for maintaining the VUELTA F1NAL backend robustly.

## Guidelines
1. **Modular Routes**: Define routing in separate extension functions located in `com.vueltaf1nal.routes`.
2. **Type Safety**: Use Kotlin Serialization for all JSON contracts.
3. **Predictions**: All algorithm-heavy logic should be isolated in the `domain` package of the engine.
4. **Asynchrony**: Use Ktor's native support for Coroutines for non-blocking I/O.
5. **JSON Config**: Maintain `ignoreUnknownKeys = true` to ensure backward compatibility with API changes.
