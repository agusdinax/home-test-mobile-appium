# Mobile Automation Challenge

## Overview

This project contains an automated mobile testing framework developed using Java, Appium, TestNG, Maven,
and Allure Report following the POM design pattern.

---

# Allure Report

Each business step is wrapped using reusable AllureSteps, providing a clean and readable execution report.

Additionally:

* Every step automatically captures a screenshot.
* Failed executions include evidence for easier debugging.
* The report provides detailed execution history and step-by-step traceability.

---

# Debug Utilities

This project also reuses utility components previously developed for other automation frameworks.

One of them is DebugDumps, a utility that automatically captures the current Appium page source (XML hierarchy)
during execution.

It is especially useful for:

* Debugging locator issues
* Inspecting native Android components
* Analyzing dynamic screens
* Building new Page Objects
* Troubleshooting automation failures

This utility significantly reduces debugging time when working with native mobile applications.

---

# Running the Tests

Execute all tests:

```bash
mvn clean test
```

---

# Generating the Allure Report

After executing the tests:

Generate and open the report:

```bash
allure serve allure-results
```

Open the report:

```bash
allure open allure-report
```
