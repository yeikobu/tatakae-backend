# Tatakae Backend - Core Domain

Anti-fraud training session validation and rep leaderboard domain for **Tatakae**, an iOS calisthenics app that counts repetitions via AI.

> **Academic project.** This repository was built for academic purposes as the **Milestone 1 (Hito 1)** deliverable for the Java program, **Talento Ready, Desafío Latam**. It models, as a learning exercise, what the leaderboard backend for Tatakae could look like — it is not the production backend of the published app.

This repository contains the **Pure Domain Core** (Core de Entidades de Dominio Puro) for two bounded features — anti-fraud session validation and rep leaderboard — completely isolated from any frameworks, databases, or external interfaces, following the principles of Clean Architecture / Hexagonal Architecture (Ports & Adapters).

## Architecture Highlights
- **Pure Java**: No Spring, JPA, or web annotations. The domain depends only on itself.
- **Dependency Inversion**: External dependencies are modeled as interfaces (`SessionRepository`, `Clock`) and injected via the constructor — never instantiated directly inside the domain.
- **English Nomenclature**: Clean, modular code entirely in English, including exception messages.

## Testing & Quality Assurance
This project uses **JUnit 5** and **Mockito** to ensure the highest standards of quality.
- **Rigorous AAA Pattern**: All tests are strictly structured using Arrange, Act, and Assert phases.
- **Business Exceptions**: Custom exceptions (`InconsistentSessionException`, `FraudulentSessionException`, `InvalidUserException`) are verified with `assertThrows`.
- **Mocked Dependencies**: `SessionRepository` is stubbed with Mockito (`when(...).thenReturn(...)`) to isolate `Leaderboard` from any real data source.
- **Parameterized Tests**: Data-driven testing is used to reduce duplication (e.g. `@ValueSource`, `@NullAndEmptySource`).
- **100% Coverage Enforced**: The test suite guarantees 100% Line and Branch coverage in the domain package, ensuring no orphan logic exists.

## How to Verify
To run the automated tests and generate the JaCoCo coverage report, execute the following command in the root of the project:

```bash
mvn clean test jacoco:report
```

After running the command, you can view the coverage evidence by opening the generated HTML report:
`target/site/jacoco/index.html`
