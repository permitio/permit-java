# Java SDK for Permit.io

Java SDK for interacting with the Permit.io full-stack permissions platform.

## Overview

This guide will walk you through the steps of installing the Permit.io Java SDK and integrating it into your code.

## Installation

For [Maven](https://maven.apache.org/) projects, use:
```xml
<dependency>
  <groupId>io.permit</groupId>
  <artifactId>permit-sdk-java</artifactId>
  <version>1.1.0</version>
</dependency>
```

For [Gradle](https://gradle.org/) projects, configure `permit-sdk-java` as a dependency in your `build.gradle` file:
```groovy
dependencies {
    // ...

    implementation 'io.permit:permit-sdk-java:1.1.0'
}
```

## Usage

### Initializing the SDK
To init the SDK, you need to create a new Permit client with the API key you got from the Permit.io dashboard.

First we will create a new `PermitConfig` object so we can pass it to the Permit client.

Second, we will create a new `Permit` client with the `PermitConfig` object we created.

```java
import io.permit.sdk.Permit;
import io.permit.sdk.PermitConfig;

// This line initializes the SDK and connects your Java app
// to the Permit.io PDP container you've set up in the previous step.
Permit permit = new Permit(
    new PermitConfig.Builder("[YOUR_API_KEY]")
        // in production, you might need to change this url to fit your deployment
        .withPdpAddress("http://localhost:7766")
        // optionally, if you wish to get more debug messages to your log, set this to true
        .withDebugMode(false)
        .build()
    );
```

### Checking permissions
To check permissions using our `permit.check()` method, you will have to create User and Resource models as input to the permission check.
The models are located in ``

Follow the example below:

```java
import io.permit.sdk.enforcement.Resource;
import io.permit.sdk.enforcement.User;
import io.permit.sdk.Permit;

boolean permitted = permit.check(
    // building the user object using User.fromString()
    // the user key (this is the unique identifier of the user in the permission system).
    User.fromString("[USER KEY]"),
    // the action key (string)
    "create",
    // the resource object, can be initialized from string if the "default" tenant is used.
    Resource.fromString("document")
);

if (permitted) {
  System.out.println("User is PERMITTED to create a document in the 'default' tenant");
} else {
  System.out.println("User is NOT PERMITTED to create a document in the 'default' tenant");
}
```

A more complicated example (passing attributes on the user object, using an explicit tenant in the resource):
```java
import io.permit.sdk.enforcement.Resource;
import io.permit.sdk.enforcement.User;
import java.util.HashMap;


HashMap<String, Object> userAttributes = new HashMap<>();
userAttributes.put("age", Integer.valueOf(20));
userAttributes.put("favorite_color", "yellow");

boolean permitted = permit.check(
    // building the user object using the User.Builder class
    new User.Builder("[USER KEY]").withAttributes(userAttributes).build(),
    // the action key (string)
    "create",
    // building the resource object using the Resource.Builder in order to pass an explicit tenant key: "awesome-inc"
    new Resource.Builder("document").withTenant("awesome-inc").build()
);

if (permitted) {
  System.out.println("User is PERMITTED to create a document in the 'awesome-inc' tenant");
} else {
  System.out.println("User is NOT PERMITTED to create a document in the 'awesome-inc' tenant");
}
```

### Syncing users
When the user first logins, and after you check if he authenticated successfully (i.e: **by checking the JWT access token**) -
you need to declare the user in the permission system so you can run `permit.check()` on that user.

To declare (or "sync") a user in the Permit.io API, use the `permit.api.users.sync()` method.

Follow the example below:
```java
import io.permit.sdk.api.models.CreateOrUpdateResult;
import io.permit.sdk.enforcement.User;

HashMap<String, Object> userAttributes = new HashMap<>();
userAttributes.put("age", Integer.valueOf(50));
userAttributes.put("fav_color", "red");

CreateOrUpdateResult<UserRead> result = permit.api.users.sync(
    (new User.Builder("auth0|elon"))
        .withEmail("elonmusk@tesla.com")
        .withFirstName("Elon")
        .withLastName("Musk")
        .withAttributes(userAttributes)
        .build()
);
UserRead user = result.getResult();
assertTrue(result.wasCreated());
```

Most params to UserCreates are optional, and only the unique user key is needed. This is valid:

```java
CreateOrUpdateResult<UserRead> result = permit.api.users.sync(new UserCreate("[USER KEY]"));
```
