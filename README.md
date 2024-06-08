# CodeGauge

**CodeGauge** is a tool designed to measure and evaluate code quality and performance metrics for Java projects. It provides insights into various aspects of code, helping developers maintain high standards and improve their coding practices.

## Features

- Measures code quality metrics such as complexity, duplication, and test coverage.
- Easy integration with existing Java projects using Gradle.

## Installation

To install CodeGauge, clone the repository and build the project using Gradle.

```sh
git clone https://github.com/FilipSzm/CodeGauge.git
cd CodeGauge
./gradlew jar
```

## Usage

### Running CodeGauge

After building the project, you can run CodeGauge on your Java project. Ensure you navigate to the directory containing your project before executing CodeGauge.

```console
java -jar build/libs/CodeGauge-1.0.jar [options]
```

### Command-Line Options

- `--config <configPath>`: Specify the configuration file path for metric definitions.
- `<filePaths>`: List of files/paths to analyze.

### Example

```sh
java -jar build/libs/CodeGauge-1.0.jar .
```

This command will analyze all Java files in this project using the metrics defined in the default configuration file `config.json`. If it does not exist, it will be automatically created in the command execution directory.

## Configuration

The configuration file should define the metrics to be evaluated. An example configuration file (`config.json`) might look like this:

```json
[ {
  "name" : "METRIC_1",
  "" : true,
  "type" : "lines_of_code",
  "config" : {
    "countPackageDeclaration" : true,
    "countImportDeclaration" : false,
    "countFieldDeclaration" : true
  }
}, {
  "name" : "METRIC_2",
  "enabled" : false,
  "type" : "lines_of_code",
  "config" : {
    "countPackageDeclaration" : false
  }
}, {
  "name" : "METRIC_3",
  "enabled" : true,
  "type" : "number_of_functions",
  "config" : {
    "countLambdaExpression" : true,
    "countConstructorDeclaration" : true
  }
} ]
```

### Explanation

- `name`: custom name of the metric
- `enabled`: flag that defines if metric should be evaluated
- `type`: type of metric, possible values: `lines_of_code`, `number_of_functions`, `cyclomatic_complexity`
- `config`: additional configuration, different for all metric types

All the metric types (`type`) are defined in default config (`config.json`) with all of corresponding configuration (`config`) values.




