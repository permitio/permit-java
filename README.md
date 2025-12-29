![Java.png](imgs/Java.png)

# Permit.io Java SDK

> **Important Notice**: This is the last version of the SDK that supports Java 8. Starting from the next major release,
> the SDK will require **Java 17** or higher, aligning with the most widely adopted LTS version in production
> environments.

The official Java SDK for interacting with the Permit.io full-stack permissions platform.

## Overview

This guide walks you through installing the Permit.io Java SDK and integrating it into your application.

## Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
    - [Maven](#maven)
    - [Gradle](#gradle)
- [Usage](#usage)
- [Examples](#examples)
    - [Running Tests](#running-tests)
- [API Reference](#api-reference)
- [Contributing](#contributing)
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

For complete, runnable examples, see the [Examples](#examples) section below.

### Initializing the SDK

Create a `Permit` client with your API key from the [Permit.io Dashboard](https://app.permit.io):

- See: [BasicPermissionCheckExample.java](src/main/java/io/permit/sdk/examples/BasicPermissionCheckExample.java) - Shows
  SDK initialization with `PermitConfig.Builder`

### Checking Permissions

Use `permit.check()` to verify if a user can perform an action on a resource:

- **Basic checks**:
  [BasicPermissionCheckExample.java](src/main/java/io/permit/sdk/examples/BasicPermissionCheckExample.java) -
  Permission checks with default tenant
- **Advanced checks**:
  [AdvancedPermissionCheckExample.java](src/main/java/io/permit/sdk/examples/AdvancedPermissionCheckExample.java) -
  Checks with user attributes and explicit tenant.

### Syncing Users

Before running permission checks, sync users to Permit.io using `permit.api.users.sync()`:

- See: [UserSyncExample.java](src/main/java/io/permit/sdk/examples/UserSyncExample.java) - User synchronization with
  full profile and minimal information.

## Examples

Complete, runnable examples are available in the [
`src/main/java/io/permit/sdk/examples`](src/main/java/io/permit/sdk/examples) directory:

| Example                                                                                                         | Description                                               |
|-----------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------|
| [BasicPermissionCheckExample.java](src/main/java/io/permit/sdk/examples/BasicPermissionCheckExample.java)       | SDK initialization and basic permission checks            |
| [AdvancedPermissionCheckExample.java](src/main/java/io/permit/sdk/examples/AdvancedPermissionCheckExample.java) | Permission checks with user attributes and tenant context |
| [UserSyncExample.java](src/main/java/io/permit/sdk/examples/UserSyncExample.java)                               | Synchronizing users with the Permit API                   |

### Running Tests

Each example has a corresponding unit test that uses Mockito mocks (no PDP required):

| Example                        | Test                                                                                                                    |
|--------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| BasicPermissionCheckExample    | [BasicPermissionCheckExampleTest.java](src/test/java/io/permit/sdk/examples/BasicPermissionCheckExampleTest.java)       |
| AdvancedPermissionCheckExample | [AdvancedPermissionCheckExampleTest.java](src/test/java/io/permit/sdk/examples/AdvancedPermissionCheckExampleTest.java) |
| UserSyncExample                | [UserSyncExampleTest.java](src/test/java/io/permit/sdk/examples/UserSyncExampleTest.java)                               |

```bash
# Run all tests
./gradlew test

# Run only example tests
./gradlew test --tests "io.permit.sdk.examples.*"

# Run a specific test
./gradlew test --tests "io.permit.sdk.examples.BasicPermissionCheckExampleTest"
```

## API Reference

For complete API documentation, refer to
the [Javadoc reference](https://javadoc.io/doc/io.permit/permit-sdk-java/latest/index.html).

The recommended starting point is
the [Permit](https://javadoc.io/doc/io.permit/permit-sdk-java/latest/io/permit/sdk/Permit.html) class, which serves as
the main entry point for the SDK.

## Contributing

We welcome contributions to the Permit.io Java SDK! Please see our [Contributing Guide](CONTRIBUTING.md) for details on:

- Setting up your development environment
- Development workflow and code style guidelines
- How to regenerate OpenAPI models
- Submitting pull requests

## License

This SDK is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.

## Support

- **Documentation**: [Permit.io Docs](https://docs.permit.io)
- **GitHub Issues**: [Report an issue](https://github.com/permitio/permit-java/issues)
- **Community**: [Permit.io Slack](https://permit.io/slack)
