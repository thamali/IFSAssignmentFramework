# FAO Schwarz Selenium Framework

A robust, maintainable automation framework designed for end-to-end testing of the FAO Schwarz website. This project leverages Selenium WebDriver, TestNG, and Allure to automate web and API tests, generate detailed reports, and ensure the quality and reliability of the FAO Schwarz online experience.

## Automation Scope

The automation framework covers end-to-end scenarios for the FAO Schwarz website, ensuring comprehensive test coverage across key user journeys and functionalities. The following workflow is automated to validate critical aspects of the site:

1. **Navigate to the site:**  
    - Open [https://faoschwarz.com/](https://faoschwarz.com/)
    - Verify the Home Page loads properly.

2. **Search Functionality:**  
    - Click on the Search option.
    - Ensure the search layout loads.
    - Search for the term `cars`.
    - Confirm that relevant search results appear.

3. **Sorting Results:**  
    - Sort the search results by price, from low to high.
    - Validate that the results are sorted accurately.

4. **Product Selection:**  
    - Click on any product from the search results.
    - Verify the Product Display Page loads.

5. **Cart Operations:**  
    - Increase the product quantity to 3.
    - Ensure the item count updates correctly.
    - Click "Add to cart".
    - Confirm the cart slider pop-up appears.

6. **Cart Validation:**  
    - Validate that the cart displays the correct product information:
      - The number of items matches the selection.
      - The subtotal updates accordingly.
    - Check for the presence of the real-time chat icon.

## Source Folder Files Summary

Below is a summary of key files and their purposes within the `src` directory:

### `src/main/java/com/faoschwarz/`
- **constants/Constants.java**: Stores framework-wide constant values (e.g., timeouts, Page Titles, Page Navigation Header Titles).
- **exceptions/**: Custom exception classes for handling browser specific errors and framework-specific errors.
- **listeners/**: TestNG listeners for logging, reporting,Retry and event handling during test execution.
- **pages/**: Contains Page Object Model (POM) classes representing web pages with web elements and their actions.
- **driverFactory/**: Manages WebDriver instances and browser setup for test execution.
- **utils/ExcelUtil.java**: Utility class for reading and writing Excel files using Apache POI for data-driven testing.
- **utils/CSVUtil.java**: Utility class for reading and writing CSV files using OpenCSV.
- **utils/**: Includes other utility classes for waits, reusable helpers, and web element interactions (e.g., clicks, waits, visibility checks).
- **api/**: Includes classes for REST API testing using Rest-Assured.

### `src/test/java/com/faoschwarz/`
- **tests/**: Contains TestNG test classes that implement test scenarios for the FAO Schwarz website.
- **base/BaseTest.java**: Serves as the base class for all test classes, handling common setup and teardown logic such as WebDriver initialization, configuration loading, and test environment preparation.

### `src/main/resources/`
- **log4j2.xml**: Log4j2 configuration file for logging setup.

### `src/test/resources/`
- **testng.xml**: TestNG suite configuration file for organizing and running test groups.
- **config.properties**: Centralized configuration file for environment variables and framework settings.
- **testdata/**: Directory for storing test data files (e.g., Excel, CSV) used in data-driven testing.

This summary helps you quickly identify the purpose of each major file and folder in the source directory.


- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Setup & Installation](#setup--installation)
- [Running Tests](#running-tests)
- [Reporting](#reporting)


---

## Features

- **Page Object Model (POM):** The framework follows the Page Object Model design pattern, organizing web page interactions into dedicated classes.
- **Modular Test Structure:** Test logic is separated from page interactions, allowing for easy updates and scalability as the application evolves.
- **Centralized Element Locators:** All web element locators are maintained within their respective page classes, reducing duplication and simplifying maintenance.
- **Reusable Page Actions:** Common user actions (e.g., login, navigation, form submission) are implemented as methods in page classes, enabling concise and readable test scripts.
- **Allure for Rich Test Reporting:** Generates interactive and detailed test reports with Allure. 
- **TakesScreenshot Utility:** Captures screenshots during test execution, especially on failures, for debugging and reporting purposes.
- **Allure Screenshot Attachment:** Automatically attaches captured screenshots to Allure reports for enhanced visibility of test results
- **TestNG Listeners Integration:** Utilizes custom listeners in `listeners/` for logging, reporting, event handling, and enhanced test lifecycle management.
- **listeners/RetryAnalyzer.java:** Provides a retry mechanism for failed tests.
- **testng.xml:** TestNG suite configuration file supporting parallel and browser-wise parallel execution.
- **config.properties:** Centralized configuration file for environment variables and framework settings.
- **driver/ThreadLocalDriver:** Implements thread-safe WebDriver management for parallel test execution.
- **Data Providers:** Supplies test data to TestNG tests for data-driven execution.
- **Comprehensive Logging:** Integrates Log4j2 for detailed logging of test execution and framework events.
- **Parallel Execution:** Enabled via TestNG and ThreadLocal WebDriver for faster test runs.
- **Browser-wise Parallel Execution:** Supports running tests in parallel across different browsers (e.g., Chrome, Firefox) using TestNG configuration.
- **ChromeOptions (headless, incognito):** Customizable browser options for headless and incognito test execution.
- **Reusable Utilities:** Offers utility classes for common actions like waits, element interactions, and browser operations.
- **utils/JavaScriptUtils.java:** Contains utilities for executing JavaScript commands, scrolling, and interacting with elements via JavaScriptExecutor and handles browser cookie operation
- **REST API Testing Support:** Includes API testing capabilities using Rest-Assured for end-to-end validation.
- **ExcelUtil & CSVUtil:** Data-driven testing support for Excel and CSV files using Apache POI and OpenCSV. Easily read/write test data from Excel and CSV files for parameterized and large-scale test scenarios.


## Tech Stack
- Java 17
- Selenium WebDriver 4.28.1
- TestNG 7.7.0
- Maven
- Allure 2.23.0
- Log4j2
- Rest-Assured
- Apache POI (Excel support)
- OpenCSV (CSV support)

## Project Structure
```
pom.xml
src/
  main/         # Main framework code (page objects, utilities, etc.)
  test/         # Test cases
allure-results/ # Allure report results
logs/           # Log files
reports/        # Custom reports
target/         # Maven build output
test-output/    # TestNG output
```

## Setup & Installation
1. **Clone the repository:**
   ```powershell
   git clone <repository-url>
   cd FAOSchwarz_SeleniumFramework
   ```
2. **Install dependencies:**
   ```powershell
   mvn clean install

## Running Tests
To execute all tests:
```powershell
mvn clean test
```
To run tests with a specific environment and a custom TestNG suite (e.g., Production with a sanity suite):
```powershell
mvn clean test -Denv="prod" -DsuiteXmlFile=src/test/resources/testrunners/testng_Sanity.xml
```

## Reporting
- **Allure Reports:**
  1. Run tests to generate results in `allure-results/`.
  2. Serve the report:
     ```powershell
     mvn allure serve ./allure-results/
     ```
- **TestNG Reports:**
  - View HTML reports in `test-output/` after test execution.
- **Logs:**
  - Check `logs/faoschwarz.log` for execution logs.
    
---
## Author

- Thamali T (thamali1989@gmail.com)
---

*Updated on June 19, 2025*
