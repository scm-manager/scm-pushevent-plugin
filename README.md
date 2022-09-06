<p align="center">
  <a href="https://www.scm-manager.org/">
    <img alt="SCM-Manager" src="https://download.scm-manager.org/images/logo/scm-manager_logo.png" width="500" />
  </a>
</p>
<h1 align="center">
   push event plugin
</h1>

This plugin sends data to an endpoint every time a push is received in any repository.
For this purpose a request is formed which contains meta information about the push as payload.

## Usage

The requests are sent via PUT to the relative URL `/events/<id>`. 
The sending of requests is disabled by default (see section Configuration below).
No information is transmitted about WHAT has changed, only which files have been touched and by whom.
The structure of the sent data is as follows:
````json
{
  "application":"scmm",
  "type":"push",
  "id":"29192626-9a2f-11ec-b909-0242ac120002",
  "time":"1646228770",
  "data":{
    "repositoryId":"2TSuOPqwJ3",
    "repositoryName":"testrepo",
    "repositoryNamespace":"scmadmin",
    "user":"scmuser",
    "datePushed":1642075169000,
    "commits":[
      {
        "commitId":"d1a59c1ff4b90396f9d8913860a639ddbeeb9c16",
        "message":"Init repo this is the first commit message",
        "fileChanges":{
          "added":[
            "docker-compose.yml",
            "index.html"
          ],
          "removed":[
            "old.js"
          ],
          "modified":[

          ],
          "moved":[

          ],
          "copied":[

          ]
        },
        "dateCommitted":1642075169000,
        "author":"developer_1",
        "branches":[
          "master"
        ]
      }
    ]
  }
}
````
### What can I do with this data?
The data is used, for example, in a gameification service of Cloudogu GmbH, which awards archivements and points for previously defined challenges. 
Otherwise, it is up to your free imagination what you do with it.

## Configuration
There are three main setting options. Firstly, the endpoint against which data is sent can be configured.
The entered URL is extended with `events/<id>`. For example, the entered endpoint `https://www.my-service.com/` becomes `https://www.my-service.com/events/213sfduj132asd1`.

The second setting option is a JWT token. This can be used to authenticate the requests to the endpoint.
The token is set as header field `Authentification: Bearer <token>`.

The last configuration option is the toggle `active` which controls if the plugin should send data. This option is default <b>OFF</b>  so as not to clog network traffic.

## Build and testing

The plugin can be compiled and packaged with the following tasks:

* clean - `gradle clean` - deletes the build directory
* run - `gradle run` - starts an SCM-Manager with the plugin pre-installed and with livereload for the ui
* build - `gradle build` - executes all checks, tests and builds the smp inclusive javadoc and source jar
* test - `gradle test` - run all java tests
* ui-test - `gradle ui-test` - run all ui tests
* check - `gradle check` - executes all registered checks and tests (java and ui)
* fix - `gradle fix` - fixes all fixable findings of the check task
* smp - `gradle smp` - Builds the smp file, without the execution of checks and tests

For the development and testing the `run` task of the plugin can be used:

* run - `gradle run` - starts scm-manager with the plugin pre-installed.

If the plugin was started with `gradle run`, the default browser of the os should be automatically opened.
If the browser does not start automatically, start it manually and go to [http://localhost:8081/scm](http://localhost:8081/scm).

In this mode each change to web files (src/main/js or src/main/webapp), should trigger reload of the browser with the made changes.

## Directory & File structure

A quick look at the files and directories you'll see in an SCM-Manager project.

    .
    ├── node_modules/
    ├── src/
    |   ├── main/
    |   |   ├── java/
    |   |   ├── js/
    |   |   └── resources/
    |   └── test/
    |       ├── java/
    |       └── resources/
    ├── .editorconfig
    ├── .gitignore
    ├── build.gradle
    ├── CHANGELOG.md
    ├── gradle.properties
    ├── gradlew
    ├── LICENSE.txt
    ├── package.json
    ├── README.md
    ├── settings.gradle
    ├── tsconfig.json
    └── yarn.lock

1.  **`node_modules/`**: This directory contains all modules of code that your project depends on (npm packages) are automatically installed.

2.  **`src/`**: This directory will contain all code related to what you see or not. `src` is a convention for “source code”.
    1. **`main/`**
        1. **`java/`**: This directory contains the Java code.
        2. **`js/`**: This directory contains the JavaScript code for the web ui, inclusive unit tests: suffixed with `.test.ts`
        3. **`resources/`**: This directory contains the classpath resources.
    2. **`test/`**
        1. **`java/`**: This directory contains the Java unit tests.
        2. **`resources/`**: This directory contains classpath resources for unit tests.

3.  **`.editorconfig`**: This is a configuration file for your editor using [EditorConfig](https://editorconfig.org/). The file specifies a style that IDEs use for code.

4.  **`.gitignore`**: This file tells git which files it should not track / not maintain a version history for.

5.  **`build.gradle`**: Gradle build configuration, which also includes things like metadata.

6.  **`CHANGELOG.md`**: All notable changes to this project will be documented in this file.

7.  **`gradle.properties`**: Defines the module version.

8.  **`gradlew`**: Bundled gradle wrapper if you don't have gradle installed.

9.  **`LICENSE.txt`**: This project is licensed under the MIT license.

10.  **`package.json`**: Here you can find the dependency/build configuration and dependencies for the frontend.

11.  **`README.md`**: This file, containing useful reference information about the project.

12.  **`settings.gradle`**: Gradle settings configuration.

13. **`tsconfig.json`** This is the typescript configuration file.

14. **`yarn.lock`**: This is the ui dependency configuration.

## Need help?

Looking for more guidance? Full documentation lives on our [homepage](https://www.scm-manager.org/docs/) or the dedicated pages for our [plugins](https://www.scm-manager.org/plugins/). Do you have further ideas or need support?

- **Community Support** - Contact the SCM-Manager support team for questions about SCM-Manager, to report bugs or to request features through the official channels. [Find more about this here](https://www.scm-manager.org/support/).
