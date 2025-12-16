# Contributing to Permit.io Java SDK

Thank you for your interest in contributing to the Permit.io Java SDK! This document provides guidelines and
instructions for contributing to the project.

## Table of Contents

- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Development Workflow](#development-workflow)
- [Regenerating OpenAPI Models](#regenerating-openapi-models)
- [Project Structure](#project-structure)
- [Code Style](#code-style)
- [Submitting Changes](#submitting-changes)
- [Reporting Issues](#reporting-issues)
- [Community](#community)

## Getting Started

1. **Fork the repository** on GitHub
2. **Clone your fork** locally:
   ```bash
   git clone https://github.com/YOUR_USERNAME/permit-java.git
   cd permit-java
   ```
3. **Add the upstream remote**:
   ```bash
   git remote add upstream https://github.com/permitio/permit-java.git
   ```

## Prerequisites

Before contributing, ensure you have the following installed:

- **Java JDK 8+**: For building and running the SDK
- **Java JDK 11+**: Required for running the OpenAPI Generator (the SDK itself targets Java 8)
- **Gradle**: Version 7.0+ (or use the included Gradle wrapper `./gradlew`)
- **OpenAPI Generator**: Required for regenerating API models from the Permit.io OpenAPI specification

### Installing OpenAPI Generator

The OpenAPI Generator is used to generate Java model classes from the Permit.io API specification. Install it using one
of the following methods:

**Using Homebrew (macOS):**

```bash
brew install openapi-generator
```

**Using npm:**

```bash
npm install @openapitools/openapi-generator-cli -g
```

**Using Docker:**

```bash
docker pull openapitools/openapi-generator-cli
```

For more installation options, see
the [OpenAPI Generator Installation Guide](https://openapi-generator.tech/docs/installation).

## Development Workflow

1. **Create a feature branch** from `master`:
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make your changes** and ensure all tests pass:
   ```bash
   ./gradlew build
   ./gradlew test
   ```

3. **Commit your changes** with clear, descriptive messages:
   ```bash
   git commit -m "Add feature: description of your changes"
   ```

4. **Push to your fork**:
   ```bash
   git push origin feature/your-feature-name
   ```

5. **Submit a pull request** with a clear description of your changes

## Regenerating OpenAPI Models

The SDK includes auto-generated model classes from the Permit.io OpenAPI specification. If you need to regenerate these
models (e.g., when the API is updated), use the provided Makefile targets:

### Generate OpenAPI Models

```bash
make generate-openapi
```

If your default Java is version 8, you need to use Java 11+ for the generator:

```bash
# macOS - use Java 11+ for the generator
JAVA_HOME=$(/usr/libexec/java_home -v 17) make generate-openapi
```

This command:

1. Fetches the latest OpenAPI specification from `https://api.permit.io/v2/openapi.json`
2. Generates Java model classes using the configuration in `openapi-config.json`
3. Outputs the generated code to the `generated/` directory

Note: The `--skip-validate-spec` flag is used to bypass minor validation issues in the upstream API specification that
do not affect code generation.

### Copying Generated Files to the Project

After running `make generate-openapi`, you need to **manually copy** the generated model files to the SDK source
directory:

```bash
# Copy generated models to the SDK
cp generated/src/main/java/io/permit/sdk/openapi/model/*.java \
   src/main/java/io/permit/sdk/openapi/models/
```

**Important notes:**

- The generator outputs to `generated/src/main/java/io/permit/sdk/openapi/model/` (singular "model")
- The SDK uses `src/main/java/io/permit/sdk/openapi/models/` (plural "models")
- Only copy the model files you need; not all generated files are used in the SDK
- Review the changes before committing to ensure compatibility

### Clean Generated Files

```bash
make clean-openapi
```

This removes the `generated/` directory and all auto-generated files.

### Generate JSON Schema (Optional)

```bash
make generate-jsonschema
```

This generates JSON schema files from the OpenAPI specification and outputs them to the `schemas/` directory. This
requires the `openapi2jsonschema` tool.

## Project Structure

The SDK source code is organized under `src/main/java/io/permit/sdk/`:

```
src/main/java/io/permit/sdk/
|-- api/                    # API client classes for Permit.io REST API
|   |-- models/             # SDK-specific models for API operations
|   |   |-- CreateOrUpdateResult.java  # Result wrapper for sync operations
|   |   |-- UserModel.java             # User data model
|   |   |-- RoleModel.java             # Role data model
|   |   +-- ...                        # Other API models
|   |-- UsersApi.java       # User management operations
|   |-- RolesApi.java       # Role management operations
|   |-- TenantsApi.java     # Tenant management operations
|   |-- ResourcesApi.java   # Resource management operations
|   +-- ...                 # Other API clients
|
|-- enforcement/            # Enforcement models for permission checks
|   |-- User.java           # User model for check() calls
|   |-- Resource.java       # Resource model for check() calls
|   |-- Enforcer.java       # Core enforcement logic
|   |-- CheckQuery.java     # Permission check query builder
|   +-- ...                 # Other enforcement models
|
|-- examples/               # Example code demonstrating SDK usage
|   |-- BasicPermissionCheckExample.java
|   |-- AdvancedPermissionCheckExample.java
|   +-- UserSyncExample.java
|
|-- openapi/                # Auto-generated OpenAPI models
|   +-- models/             # Generated model classes from Permit.io API spec
|                           # (copy generated files here from generated/src/main/java/...)
|
|-- util/                   # Utility classes and helpers
|
|-- Permit.java             # Main SDK entry point
|-- PermitConfig.java       # SDK configuration builder
|-- PermitContext.java      # Context management for API calls
|-- ApiKeyLevel.java        # API key permission levels
|-- ApiContextLevel.java    # API context level definitions
+-- FactsSyncTimeoutPolicy.java  # Timeout policy for facts synchronization
```

## Code Style

Please follow these guidelines when contributing:

- Follow standard Java naming conventions
- Use meaningful variable and method names
- Include Javadoc comments for public APIs
- Write unit tests for new functionality
- Keep methods focused and concise
- Avoid introducing new dependencies unless necessary

## Submitting Changes

### Pull Request Guidelines

- **One feature per PR**: Keep pull requests focused on a single feature or fix
- **Clear description**: Explain what your changes do and why
- **Tests**: Include tests for new functionality
- **Documentation**: Update documentation if needed
- **Clean history**: Rebase your branch on `master` before submitting

### Pull Request Template

When submitting a PR, please include:

1. **Summary**: Brief description of the changes
2. **Motivation**: Why these changes are needed
3. **Testing**: How the changes were tested
4. **Breaking Changes**: Note any breaking changes (if applicable)

## Reporting Issues

When reporting issues, please include:

- **Java version**: Output of `java -version`
- **SDK version**: The version of the Permit.io SDK you're using
- **Steps to reproduce**: Clear steps to reproduce the issue
- **Expected behavior**: What you expected to happen
- **Actual behavior**: What actually happened
- **Stack trace**: If applicable, include the full stack trace

Use the [GitHub Issues](https://github.com/permitio/permit-java/issues) page to report bugs or request features.

## Community

- **Documentation**: [Permit.io Docs](https://docs.permit.io)
- **Slack**: [Permit.io Community](https://permit.io/slack)
- **GitHub Discussions**: For questions and discussions about the SDK

## License

By contributing to this project, you agree that your contributions will be licensed under
the [Apache License 2.0](LICENSE).
