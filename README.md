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

## License

This SDK is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.

## Support

- **Documentation**: [Permit.io Docs](https://docs.permit.io)
- **GitHub Issues**: [Report an issue](https://github.com/permitio/permit-java/issues)
- **Community**: [Permit.io Slack](https://permit.io/slack)
