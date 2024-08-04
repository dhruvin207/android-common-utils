## 📦 Multi-Flavors With Environments Android CI/CD Automation 🚀

## 📜 Purpose

The purpose of this automation setup is to streamline the Continuous Integration (CI) and Continuous Deployment (CD) process for Android apps with multiple flavors. By leveraging GitHub Actions and Fastlane, this process ensures efficient building, uploading, and deploying of different app versions with a single trigger. 

## 🏗️ Structure of the Automation Process

This CI/CD setup is organized into GitHub Actions workflows and composite actions. Here's a breakdown of how it all fits together:

### 1. GitHub Actions Workflows

#### `Android_ci.yml` 🛠️

- **Name**: Android CI
- **Triggers**: Runs on `push` events to the `dev` and `main` branches.
- **Jobs**:
  - **Identify Environment**: Determines if the pipeline should run in a development or production environment based on the branch name.
  - **Build and Deploy**: Executes the build and deployment processes based on the identified environment and selected app flavors.

### 2. Composite Actions

#### `setup.yml` ☕🔧

- **Purpose**: Sets up the system environment by installing necessary tools like Java, Ruby, and dependencies such as BundleTool and Fastlane.
- **Steps**:
  - Install Java and Ruby
  - Download and configure BundleTool
  - Install and update Fastlane
  - Install Firebase App Distribution plugin

#### `flavors.yml` 🎨

- **Purpose**: Determines which app flavor to build based on environment variables.
- **Steps**:
  - Build each specified flavor using the `bud` action.

#### `bud.yml` 🚀

- **Purpose**: Executes the build, upload, and deployment of the app for a specific flavor.
- **Steps**:
  - Set environment variables
  - Build the app
  - Upload and deploy the app

#### `build.yml` 🔨

- **Purpose**: Triggers Fastlane to build the app.
- **Steps**:
  - Execute the build lane in Fastlane for the specified flavor.

#### `upload.yml` 📦

- **Purpose**: Handles uploading the build artifacts to GitHub.
- **Steps**:
  - Create a zip file of native symbols
  - Extract and upload build information and artifacts

#### `deploy.yml` 🚀

- **Purpose**: Deploys the app using Fastlane.
- **Steps**:
  - Deploy to Firebase or Play Store based on the environment
  - Create a release tag and upload artifacts

## 🔧 Integrating Fastlane in Android

To integrate Fastlane into your Android project, follow these steps:

1. **Install Fastlane**:
   ```bash
   gem install fastlane
   ```

2. **Initialize Fastlane**:
   ```bash
   fastlane init
   ```

3. **Configure Fastlane**:
   Create and configure your `Fastfile` (as shown in the Fastlane configuration provided) to define lanes for building and deploying your app.

4. **Setup Environment Variables**:
   Ensure that required environment variables and secrets are properly set up in your CI/CD system.

## 🛠️ Fastlane Configuration

### `Fastfile` 🏗️🚀

- **`build` Lane**:
  - Builds the app based on flavor and environment.
  - Decodes signing credentials and builds the app using Gradle.

- **`deploy` Lane**:
  - Deploys the app to Firebase or Play Store depending on the environment.
  - Handles Firebase App Distribution for development and Play Store uploads for production.

## 🔄 Overall Process Summary

1. **Trigger**: Code changes pushed to `dev` or `main` branches.
2. **Identify Environment**: The workflow identifies the environment (dev or prod).
3. **Build**: The app is built for the specified flavors using Fastlane.
4. **Upload**: Build artifacts are uploaded to GitHub.
5. **Deploy**: The app is deployed to the specified platform (Firebase or Play Store).

## 🌟 Benefits

- **Consistency**: Automated and consistent build and deployment processes.
- **Efficiency**: Reduces manual effort and potential errors in the build and deployment process.
- **Flexibility**: Supports multiple app flavors and environments with a single workflow.
- **Scalability**: Easily extendable to include more flavors or environments as needed.

## ⚙️ Capabilities

- **Multi-Flavors**: Can handle multiple app flavors and deploy them based on specified flags.
- **Dynamic Environments**: Each flavor can be built and deployed dynamically based on the environment (dev or prod). The configuration is adjusted automatically according to the environment settings.
- **Single Trigger**: One workflow trigger can build and deploy multiple apps.
- **Conditional Deployment**: Deployment based on environment variables and secret flags.

## 💡 Final Note

This process is designed to be a "set it and forget it" kind of setup. Once configured, it will handle the build and deployment of your app flavors automatically based on the environment, minimizing manual intervention and streamlining your development workflow.

---
