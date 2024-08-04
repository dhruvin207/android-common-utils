### 📜 **Gradle Flavor Configuration Explained**

Welcome to the detailed walkthrough of your Gradle flavor configuration file! This file is crucial for managing multiple build variants and environments in your Android project. Let’s dive into each part and understand how it all works. 🌟

---

#### 📋 **Flavor Configuration Setup**

```groovy
def flavorConfig = [
    [
        name        : 'flavorA',
        environments: [
            [name: 'Dev', app_id_suffix: 'flavorA.dev', 'url': '"https://api.dev.com"'],
            [name: 'Prod', app_id_suffix: 'flavorA', 'url': '"https://api.com"']
        ]
    ],
    [
        name        : 'flavorB',
        environments: [
            [name: 'Dev', app_id_suffix: 'flavorB.dev', 'url': '"https://api.dev.com"'],
            [name: 'Prod', app_id_suffix: 'flavorB', 'url': '"https://api.com"']
        ]
    ],
    [
        name        : 'flavorC',
        environments: [
            [name: 'Dev', app_id_suffix: 'flavorC.dev', 'url': '"https://api.dev.com"'],
            [name: 'Prod', app_id_suffix: 'flavorC', 'url': '"https://api.com"']
        ]
    ],
]
```

**What it does:**
- Defines a list of flavors, each with different environments (e.g., Development, Production).
- Each flavor has configurations for application ID suffixes and server URLs.

**Customization:**
- **To Add a New Flavor:** Add a new dictionary to the list with the appropriate configurations.
- **To Remove a Flavor:** Remove the dictionary corresponding to that flavor.

---

#### 🔐 **Signing Configurations**

```groovy
Properties signProps = new Properties()
def propertiesFile = project.rootProject.file('local.properties')
if (propertiesFile.exists()) {
    signProps.load(propertiesFile.newDataInputStream())
}
```

**What it does:**
- Loads signing configurations from the `local.properties` file.
- This file should contain keystore paths, aliases, and passwords for signing your APKs.

**Customization:**
- **To Update Signing Config:** Modify `local.properties` with new values or paths.

---

#### 🔢 **Versioning Properties**

```groovy
Properties versionProps = new Properties()
def versionPropsFile = rootProject.file('version.properties')
if (versionPropsFile.exists()) {
    versionProps.load(versionPropsFile.newDataInputStream())
} else {
    println "Version properties file not found: ${versionPropsFile.path}"
}
```

**What it does:**
- Loads versioning information from `version.properties` which includes version codes and names for different flavors.

**Customization:**
- **To Update Version Info:** Change `version.properties` with new version codes or names.

---

#### ⚙️ **Android Build Configuration**

```groovy
android {
    flavorConfig.each { agency ->
        def flavorName = agency['name']
        signingConfigs.create(flavorName) {
            storeFile file(signProps.getProperty("${flavorName}_keystore_path", 'blank'))
            keyAlias signProps.getProperty("${flavorName}_key_alias", 'blank')
            storePassword signProps.getProperty("${flavorName}_key_store_password", 'blank')
            keyPassword signProps.getProperty("${flavorName}_key_password", 'blank')
        }
    }

    setFlavorDimensions(["flavors"])
```

**What it does:**
- Iterates over the flavor configurations and sets up signing configurations for each flavor.
- Sets the flavor dimension to manage different product flavors.

**Customization:**
- **To Add/Remove Flavors:** Update the `flavorConfig` list and corresponding signing configuration setup.

---

#### 🛠️ **Product Flavors Configuration**

```groovy
    flavorConfig.each { agency ->
        def flavorName = agency['name']
        def environments = agency['environments']

        environments.each { env ->
            def envName = env['name']
            def app_id_suffix = env['app_id_suffix']
            String server_url = env['url']

            def envNameLowerCase = envName.toLowerCase()
            def codeKey = "${flavorName}_${envNameLowerCase}_version_code"
            def nameKey = "${flavorName}_${envNameLowerCase}_version_name"

            def _versionCode = versionProps.getProperty(codeKey)?.toInteger()
            def _versionName = versionProps.getProperty(nameKey)

            String productFlavorName = "$flavorName$envName"

            productFlavors.create("$productFlavorName") {
                dimension 'flavors'
                versionCode _versionCode
                versionName _versionName

                if (!app_id_suffix.isEmpty()) {
                    applicationIdSuffix = ".$app_id_suffix"
                }

                if (propertiesFile.exists()) {
                    signingConfig signingConfigs."$flavorName"
                }

                buildConfigField "String", "SERVER_URL", server_url

                if (envName == "Dev") {
                    buildConfigField "Boolean", "IS_DEV_ENV", 'true'
                } else {
                    buildConfigField "Boolean", "IS_DEV_ENV", 'false'
                }

                println "Suffix: $applicationIdSuffix, Version [code: $_versionCode, Name: $_versionName]," +
                        " BuildConfig:[URL: $server_url, FLAVOR_ID: $flavorName, ENV: $envName]"
            }
        }
    }
```

**What it does:**
- Creates product flavors based on the configurations.
- Sets version codes, version names, application ID suffixes, signing configurations, and other build parameters.

**Customization:**
- **To Modify a Flavor:** Update `flavorConfig` and `version.properties` accordingly.
- **To Add a New Environment:** Add the environment to the flavor configuration and adjust `version.properties`.

---

#### 📁 **Source Sets Configuration**

```groovy
    if (project.name.containsIgnoreCase("my-project")) {
        productFlavors.configureEach { flavor ->
            def matcher = flavor.name =~ /^(.*?)(Dev|Prod)$/
            def (flavorName, environmentName) = matcher.find() ? [matcher.group(1), matcher.group(2)] : [flavor.name, '']
            println "Flavor Name: $flavorName, Environment Name: $environmentName"

            if (flavor.name.endsWith('Prod')) {
                sourceSets.named(flavor.name) {
                    res.srcDirs = ['src/' + flavorName + '/res']
                }
            }
            if (flavor.name.endsWith('Dev')) {
                sourceSets.named(flavor.name) {
                    res.srcDirs = ['src/' + flavorName + '/res']
                }
            }
        }
    }
}
```

**What it does:**
- Configures source sets to use different resource directories based on the flavor and environment.

**Customization:**
- **To Adjust Resource Directories:** Modify the source sets configuration to match your project structure.

---

### 📚 **Summary**

This Gradle file is designed to handle multiple product flavors and environments for your Android project. It automates the configuration of signing, versioning, and source sets for each combination of flavor and environment, ensuring that your builds are tailored to specific requirements. 

### 🚀 **Getting Started**

- **Add a New Flavor:** Extend the `flavorConfig` list.
- **Remove a Flavor:** Delete the relevant entry from `flavorConfig`.
- **Update Environment Settings:** Modify `flavorConfig` and update `version.properties` as needed.
