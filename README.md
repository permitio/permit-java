![Java.png](imgs/Java.png)

# Permit.io Java SDK

The official Java SDK for interacting with the Permit.io full-stack permissions platform.

## Overview

This guide walks you through installing the Permit.io Java SDK and integrating it into your application.

## Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
  - [Maven](#maven)
  - [Gradle](#gradle)
- [Usage](#usage)
  - [Initializing the SDK](#initializing-the-sdk)
  - [Checking Permissions](#checking-permissions)
  - [Syncing Users](#syncing-users)
- [Examples](#examples)
- [API Reference](#api-reference)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
  - [Prerequisites](#prerequisites)
  - [Regenerating OpenAPI Models](#regenerating-openapi-models)
- [License](#license)
- [Support](#support)

## Requirements

- **Java**: Version 8 or higher
- **Build Tool**: Maven 3.6+ or Gradle 7.0+
- **Permit.io Account**: An API key from the [Permit.io Dashboard](https://app.permit.io)
- **PDP (Policy Decision Point)**: A running Permit.io PDP container for authorization checks

## Installation

### Maven

Add the following dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>io.permit</groupId>
    <artifactId>permit-sdk-java</artifactId>
    <version>2.0.0</version>
</dependency>
```

### Gradle

Add the following dependency to your `build.gradle` file:

```groovy
dependencies {
    implementation 'io.permit:permit-sdk-java:2.0.0'
}
```

For Kotlin DSL (`build.gradle.kts`):

```kotlin
dependencies {
    implementation("io.permit:permit-sdk-java:2.0.0")
}
```

## Usage

### Initializing the SDK

To initialize the SDK, create a new `Permit` client with the API key obtained from the Permit.io dashboard.

First, create a `PermitConfig` object to configure the client. Then, instantiate the `Permit` client with the configuration.

```java
import io.permit.sdk.Permit;
import io.permit.sdk.PermitConfig;

// Initialize the SDK and connect your Java application
// to the Permit.io PDP container you have set up.
Permit permit = new Permit(
    new PermitConfig.Builder("[YOUR_API_KEY]")
        // In production, you may need to change this URL to match your deployment
        .withPdpAddress("http://localhost:7766")
        // Optionally, enable debug mode for more detailed log messages
        .withDebugMode(false)
        .build()
);
```

### Checking Permissions

To check permissions using the `permit.check()` method, you need to create `User` and `Resource` model objects as input
for the permission check. These models are located in the `io.permit.sdk.enforcement` package.

#### Basic Permission Check

The following example demonstrates a basic permission check using the default tenant.
See the full example:
[BasicPermissionCheckExample.java](src/main/java/io/permit/sdk/examples/BasicPermissionCheckExample.java)

```java
import io.permit.sdk.Permit;
import io.permit.sdk.enforcement.Resource;
import io.permit.sdk.enforcement.User;
import io.permit.sdk.api.PermitApiError;
import java.io.IOException;

try {
    boolean permitted = permit.check(
        // Build the user object using User.fromString()
        // The user key is the unique identifier of the user in the permission system
        User.fromString("[USER_KEY]"),
        // The action key (string)
        "create",
        // The resource object, initialized from a string when using the default tenant
        Resource.fromString("document")
    );

    if (permitted) {
        System.out.println("User is PERMITTED to create a document in the 'default' tenant");
    } else {
        System.out.println("User is NOT PERMITTED to create a document in the 'default' tenant");
    }
} catch (PermitApiError | IOException e) {
    System.err.println("Authorization check failed: " + e.getMessage());
}
```

#### Advanced Permission Check with Attributes

The following example demonstrates a more advanced scenario with user attributes and an explicit tenant.
See the full
example: [AdvancedPermissionCheckExample.java](src/main/java/io/permit/sdk/examples/AdvancedPermissionCheckExample.java)

```java
import io.permit.sdk.Permit;
import io.permit.sdk.enforcement.Resource;
import io.permit.sdk.enforcement.User;
import io.permit.sdk.api.PermitApiError;
import java.io.IOException;
import java.util.HashMap;

HashMap<String, Object> userAttributes = new HashMap<>();
userAttributes.put("age", Integer.valueOf(20));
userAttributes.put("favorite_color", "yellow");

try {
    boolean permitted = permit.check(
        // Build the user object using the User.Builder class
        new User.Builder("[USER_KEY]")
            .withAttributes(userAttributes)
            .build(),
        // The action key (string)
        "create",
        // Build the resource object using Resource.Builder to specify an explicit tenant
        new Resource.Builder("document")
            .withTenant("awesome-inc")
            .build()
    );

    if (permitted) {
        System.out.println("User is PERMITTED to create a document in the 'awesome-inc' tenant");
    } else {
        System.out.println("User is NOT PERMITTED to create a document in the 'awesome-inc' tenant");
    }
} catch (PermitApiError | IOException e) {
    System.err.println("Authorization check failed: " + e.getMessage());
}
```

### Syncing Users

When a user first logs in, and after you verify that they have authenticated successfully (for example, by validating
the JWT access token), you need to declare the user in the permission system before you can run `permit.check()` on that
user.

To declare (or "sync") a user in the Permit.io API, use the `permit.api.users.sync()` method.

#### Syncing a User with Full Details

The following example demonstrates how to sync a user with complete profile information.
See the full example: [UserSyncExample.java](src/main/java/io/permit/sdk/examples/UserSyncExample.java)

```java
import io.permit.sdk.api.models.CreateOrUpdateResult;
import io.permit.sdk.enforcement.User;
import io.permit.sdk.openapi.models.UserRead;
import io.permit.sdk.api.PermitApiError;
import java.io.IOException;
import java.util.HashMap;

HashMap<String, Object> userAttributes = new HashMap<>();
userAttributes.put("age", Integer.valueOf(50));
userAttributes.put("fav_color", "red");

try {
    CreateOrUpdateResult<UserRead> result = permit.api.users.sync(
        new User.Builder("auth0|elon")
            .withEmail("elonmusk@tesla.com")
            .withFirstName("Elon")
            .withLastName("Musk")
            .withAttributes(userAttributes)
            .build()
    );

    UserRead user = result.getResult();
    boolean wasCreated = result.wasCreated();

    System.out.println("User synced successfully. Created: " + wasCreated);
} catch (PermitApiError | IOException e) {
    System.err.println("User sync failed: " + e.getMessage());
}
```

#### Syncing a User with Minimal Information

Most parameters are optional. Only the unique user key is required:

```java
import io.permit.sdk.api.models.CreateOrUpdateResult;
import io.permit.sdk.openapi.models.UserCreate;
import io.permit.sdk.openapi.models.UserRead;
import io.permit.sdk.api.PermitApiError;
import java.io.IOException;

try {
    CreateOrUpdateResult<UserRead> result = permit.api.users.sync(
        new UserCreate("[USER_KEY]")
    );
} catch (PermitApiError | IOException e) {
    System.err.println("User sync failed: " + e.getMessage());
}
```

## Examples

Complete, runnable examples are available in the [`src/main/java/io/permit/sdk/examples`](src/main/java/io/permit/sdk/examples) directory:

| Example                                                                                                         | Description                                                                     |
|-----------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------|
| [BasicPermissionCheckExample.java](src/main/java/io/permit/sdk/examples/BasicPermissionCheckExample.java)       | Basic SDK initialization and permission checks                                  |
| [AdvancedPermissionCheckExample.java](src/main/java/io/permit/sdk/examples/AdvancedPermissionCheckExample.java) | Permission checks with user attributes, resource attributes, and tenant context |
| [UserSyncExample.java](src/main/java/io/permit/sdk/examples/UserSyncExample.java)                               | Synchronizing users with the Permit API                                         |

### Running the Examples

To run an example, set your API key and execute:

```bash
export PERMIT_API_KEY="your-api-key"
./gradlew run -PmainClass=io.permit.sdk.examples.BasicPermissionCheckExample
```

### Running Tests

Unit tests for these examples use Mockito and do not require a running PDP.

Run all tests:

```bash
./gradlew test
```

Run only the example tests:

```bash
./gradlew test --tests "io.permit.sdk.examples.*"
```

Run a specific test class:

```bash
./gradlew test --tests "io.permit.sdk.examples.BasicPermissionCheckExampleTest"
```

## API Reference

For complete API documentation, refer to
the [Javadoc reference](https://javadoc.io/doc/io.permit/permit-sdk-java/latest/index.html).

The recommended starting point is
the [Permit](https://javadoc.io/doc/io.permit/permit-sdk-java/latest/io/permit/sdk/Permit.html) class, which serves as
the main entry point for the SDK.

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

## Contributing

We welcome contributions to the Permit.io Java SDK! This section explains how to set up your development environment and
contribute to the project.

### Prerequisites

Before contributing, ensure you have the following installed:

- **Java JDK**: Version 8 or higher (JDK 11+ recommended for development)
- **Gradle**: Version 7.0+ (or use the included Gradle wrapper `./gradlew`)
- **OpenAPI Generator**: Required for regenerating API models from the Permit.io OpenAPI specification.

#### Installing OpenAPI Generator

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

### Regenerating OpenAPI Models

The SDK includes auto-generated model classes from the Permit.io OpenAPI specification. If you need to regenerate these
models (e.g., when the API is updated), use the provided Makefile targets:

#### Generate OpenAPI Models

```bash
make generate-openapi
```

This command:

1. Fetches the latest OpenAPI specification from `https://api.permit.io/v2/openapi.json`
2. Generates Java model classes using the configuration in `openapi-config.json`
3. Outputs the generated code to the `generated/` directory

#### Clean Generated Files

```bash
make clean-openapi
```

This removes the `generated/` directory and all auto-generated files.

#### Generate JSON Schema (Optional)

```bash
make generate-jsonschema
```

This generates JSON schema files from the OpenAPI specification and outputs them to the `schemas/` directory. This
requires the `openapi2jsonschema` tool.

### Development Workflow

1. **Fork and clone** the repository
2. **Create a feature branch** from `master`
3. **Make your changes** and ensure all tests pass
4. **Run tests** using `./gradlew test`
5. **Submit a pull request** with a clear description of your changes

### Code Style

- Follow standard Java naming conventions
- Use meaningful variable and method names
- Include Javadoc comments for public APIs
- Write unit tests for new functionality

## License

This SDK is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.

## Support

- **Documentation**: [Permit.io Docs](https://docs.permit.io)
- **GitHub Issues**: [Report an issue](https://github.com/permitio/permit-java/issues)
- **Community**: [Permit.io Slack](https://permit.io/slack)
