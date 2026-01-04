# Publishing Guide

This document describes how to publish the Permit.io Java SDK to Maven Central and GitHub Packages.

## Overview

The SDK is published to two repositories:

- **Maven Central** - Primary distribution for public consumption
- **GitHub Packages** - Secondary distribution for GitHub-based workflows

## Prerequisites

### Maven Central Portal Account

1. Create an account at [central.sonatype.com](https://central.sonatype.com)
2. Verify ownership of the `io.permit` namespace
3. Generate a User Token: Account → Generate User Token

### GPG Signing Key

Maven Central requires all artifacts to be signed with GPG.
[For more info see here.](https://central.sonatype.org/publish/requirements/gpg/)

#### Generate a new key (if you don't have one)

```bash
gpg --full-generate-key
```

When prompted:

1. **Key type**: Select `1` (RSA and RSA)
2. **Key size**: Enter `4096`
3. **Expiration**: Enter `0` (doesn't expire) or set a reasonable expiration
4. **Name and email**: Use the same email as your Maven Central account
5. **Passphrase**: Set a strong passphrase (this is your `signingInMemoryKeyPassword`)

#### List your keys

```bash
gpg --list-secret-keys --keyid-format LONG
```

#### Export the private key

For local use:

```bash
gpg --armor --export-secret-keys YOUR_KEY_ID > key.asc
```

For CI/CD (base64 encoded):

```bash
gpg --armor --export-secret-keys YOUR_KEY_ID | base64
```

#### Publish your public key (required for Maven Central verification)

```bash
gpg --keyserver keyserver.ubuntu.com --send-keys YOUR_KEY_ID
# Note: in case you are getting "no route to host" error, ping this server and use the IP.
```

## GitHub Secrets

Configure these secrets in your GitHub repository:

| Secret                   | Description                                            |
|--------------------------|--------------------------------------------------------|
| `MAVEN_CENTRAL_USERNAME` | Username from Central Portal TOKEN (not the user)      |
| `MAVEN_CENTRAL_PASSWORD` | Password from Central Portal TOKEN (not user password) |
| `GPG_SIGNING_KEY`        | Base64-encoded GPG private key                         |
| `GPG_SIGNING_PASSPHRASE` | Passphrase for the GPG key                             |

## Publishing Methods

### Automatic (CI/CD)

Publishing is triggered automatically when:

- A GitHub Release is created
- The workflow is manually dispatched

The workflow (`.github/workflows/publish.yaml`) handles:

1. Javadoc verification
2. Publishing to GitHub Packages
3. Publishing to Maven Central

### Manual (Local)

#### Publish to Local Maven Repository

Test artifact generation without uploading:

```bash
./gradlew publishToMavenLocal -PskipSigning
```

Artifacts are published to `~/.m2/repository/io/permit/permit-sdk-java/`

Note: Use `-PskipSigning` for local testing without GPG keys. This flag is not available for Maven Central publishing (
signing is required).

#### Publish to Maven Central (Staging Only)

Upload to Central Portal without releasing:

```bash
./gradlew publishToMavenCentral \
  -PmavenCentralUsername=TOKEN_USERNAME \
  -PmavenCentralPassword=TOKEN_PASSWORD \
  -PsigningInMemoryKey="$(cat key.asc)" \
  -PsigningInMemoryKeyPassword=KEY_PASSPHRASE
```

Review at [Central Portal Deployments](https://central.sonatype.com/publishing/deployments)

#### Publish and Release to Maven Central

Full publish with automatic release:

```bash
./gradlew publishAndReleaseToMavenCentral \
  -PmavenCentralUsername=TOKEN_USERNAME \
  -PmavenCentralPassword=TOKEN_PASSWORD \
  -PsigningInMemoryKey="$(cat key.asc)" \
  -PsigningInMemoryKeyPassword=KEY_PASSPHRASE
```

#### Publish to GitHub Packages

```bash
GITHUB_ACTOR=username GITHUB_TOKEN=token ./gradlew publish
```

## Publishing Types

The `mavenPublishing` block in `build.gradle` supports different publishing modes:

### CENTRAL_PORTAL (Current)

```groovy
publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
```

Uses the new Maven Central Portal API at `central.sonatype.com`. This is the current recommended method as Sonatype has
deprecated the legacy OSSRH system.

### S01 (Legacy OSSRH)

```groovy
publishToMavenCentral(SonatypeHost.S01)
```

Uses the legacy Sonatype OSSRH at `s01.oss.sonatype.org`. This method is deprecated and may stop working.

### DEFAULT (Legacy OSSRH)

```groovy
publishToMavenCentral(SonatypeHost.DEFAULT)
```

Uses the original Sonatype OSSRH at `oss.sonatype.org`. This is for older projects and is deprecated.

## Gradle Tasks

| Task                              | Description                                              |
|-----------------------------------|----------------------------------------------------------|
| `publishToMavenLocal`             | Publish to local Maven cache (~/.m2)                     |
| `publishToMavenCentral`           | Upload to Central Portal (staging)                       |
| `publishAndReleaseToMavenCentral` | Upload and release to Maven Central                      |
| `publish`                         | Publish to all configured repositories (GitHub Packages) |

## Versioning

Version is automatically determined by the `com.palantir.git-version` plugin based on git tags:

- Tagged commit: `2.2.0`
- Commits after tag: `2.2.0-1-gabcdef`
- Dirty working directory: `2.2.0-1-gabcdef.dirty`

To release a new version:

```bash
git tag 2.3.0
git push origin 2.3.0
```

## Troubleshooting

### 403 Forbidden

- Credentials may be invalid or expired
- Regenerate token at Central Portal

### Signature Verification Failed

- GPG key may be malformed
- Ensure key is base64 encoded without line breaks

### Version Already Exists

- Maven Central doesn't allow overwriting versions
- Bump the version and try again

## References

- [Maven Central Portal](https://central.sonatype.com)
- [vanniktech/gradle-maven-publish-plugin](https://vanniktech.github.io/gradle-maven-publish-plugin/central/)
- [Sonatype Publishing Guide](https://central.sonatype.org/publish/publish-portal-gradle/)
