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

This scope ensures reliability and early detection of issues across both web and API layers, covering product search, cart management, checkout, user authentication, and API validations.

A robust, maintainable automation framework designed for end-to-end testing of the FAO Schwarz website. This project leverages Selenium WebDriver, TestNG, and Allure to automate web and API tests, generate detailed reports, and ensure the quality and reliability of the FAO Schwarz online experience.

A robust, maintainable automation framework for testing the FAO Schwarz website using Selenium WebDriver, TestNG, and Allure reporting.


## Source Folder Files Summary

Below is a summary of key files and their purposes within the `src` directory:

### `src/main/java/com/faoschwarz/`
- **constants/Constants.java**: Stores framework-wide constant values (e.g., timeouts, Page Titles, Page Navigation Header Titles).
- **exceptions/**: Custom exception classes for handling browser specific errors and framework-specific errors.
- **listeners/**: TestNG listeners for logging, reporting,Retry and event handling during test execution.
- **pages/**: Contains Page Object Model (POM) classes representing web pages with web elements and their actions.
- **driverFactory/**: Manages WebDriver instances and browser setup for test execution.
- **utils/**: Includes utility classes for waits,and other reusable helpers
- **api/**: Includes classes for REST API testing using Rest-Assured.
- **utils/**: Provides reusable methods for interacting with web elements (e.g., clicks, waits, visibility checks).

### `src/test/java/com/faoschwarz/`
- **tests/**: Contains TestNG test classes that implement test scenarios for the FAO Schwarz website.
- **base/BaseTest.java**: Serves as the base class for all test classes, handling common setup and teardown logic such as WebDriver initialization, configuration loading, and test environment preparation.

### `src/main/resources/`
- **config/**: Stores environment-specific configuration files (e.g., QA, Staging, Production).
- **log4j2.xml**: Log4j2 configuration file for logging setup.

### Additional Features & Configurations

- **Page Object Model (POM):** The framework follows the Page Object Model design pattern, organizing web page interactions into dedicated classes under `src/main/java/com/faoschwarz/pages/`. This promotes code reusability, readability, and maintainability by encapsulating page elements and actions.
- **Modular Test Structure:** Test logic is separated from page interactions, allowing for easy updates and scalability as the application evolves.
- **Centralized Element Locators:** All web element locators are maintained within their respective page classes, reducing duplication and simplifying maintenance.
- **Reusable Page Actions:** Common user actions (e.g., login, navigation, form submission) are implemented as methods in page classes, enabling concise and readable test scripts.
- **Consistent Naming Conventions:** Classes and methods follow clear naming standards, making it easy to identify and use page objects throughout the test suite.

These practices ensure the framework remains robust, scalable, and easy to extend as new features or pages are added to the FAO Schwarz website.

- **Allure for Rich Test Reporting**: Generates interactive and detailed test reports with Allure, providing insights into test execution and results.
- **TakesScreenshot Utility**: Captures screenshots during test execution, especially on failures, to aid in debugging and reporting.
- **Allure Screenshot Attachment**: Automatically attaches captured screenshots to Allure reports for enhanced visibility of test outcomes.
- **Thread-Safe WebDriver Management**: Uses `driver/ThreadLocalDriver` to ensure thread safety and enable parallel test execution.
- **Retry Mechanism**: Implements `listeners/RetryAnalyzer.java` to automatically retry failed tests, improving test reliability.
- **TestNG Listeners Integration**: Utilizes custom listeners in `listeners/` for logging, reporting, event handling, and enhanced test lifecycle management.
- **Data Providers**: Supplies dynamic test data to TestNG tests, supporting data-driven testing approaches.
- **Parallel & Browser-wise Execution**: Supports running tests in parallel and across multiple browsers (e.g., Chrome, Firefox) via TestNG configuration.
- **Custom Browser Options**: Allows configuration of ChromeOptions such as headless and incognito modes for flexible test execution.
- **JavaScript Utilities**: Provides `utils/JavaScriptUtils.java` for executing JavaScript commands, scrolling, and advanced element interactions.
- **Centralized Configuration**: Uses `config.properties` and environment-specific files for easy management of framework settings and environment variables.
- **Comprehensive Logging**: Integrates Log4j2 for detailed logging of test execution and framework events.
- **REST API Testing Support**: Includes API testing capabilities using Rest-Assured for end-to-end validation.
- **Reusable Utilities**: Offers utility classes for common actions like waits, element interactions, and browser operations.

These features enhance the robustness, maintainability, and flexibility of the FAO Schwarz Selenium Framework, making it suitable for comprehensive web and API test automation.


- **Allure for Rich Test Reporting**:Generates interactive and detailed test reports with Allure. 
- **TakesScreenshot Utility**: Captures screenshots during test execution, especially on failures, for debugging and reporting purposes.
- **Allure Screenshot Attachment**: Automatically attaches captured screenshots to Allure reports for enhanced visibility of test results.
- **driver/ThreadLocalDriver**: Implements thread-safe WebDriver management for parallel test execution.
- **listeners/RetryAnalyzer.java**: Provides a retry mechanism for failed tests.
- **Data Providers**: Supplies test data to TestNG tests for data-driven execution.
- **testng.xml**: TestNG suite configuration file supporting parallel and browser-wise parallel execution.
- **config.properties**: Centralized configuration file for environment variables and framework settings.
- **Parallel Execution**: Enabled via TestNG and ThreadLocal WebDriver for faster test runs.
- **Browser-wise Parallel Execution**: Supports running tests in parallel across different browsers (e.g., Chrome, Firefox) using TestNG configuration.
- **ChromeOptions (headless, incognito)**: Customizable browser options for headless and incognito test execution.
- **utils/JavaScriptUtils.java**: Contains utilities for executing JavaScript commands, scrolling, and interacting with elements via JavaScriptExecutor and Handles browser cookie operations.

---

This summary helps you quickly identify the purpose of each major file and folder in the source directory.


- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Setup & Installation](#setup--installation)
- [Running Tests](#running-tests)
- [Reporting](#reporting)
- [Contributing](#contributing)
- [License](#license)

## Tech Stack
- Java 17
- Selenium WebDriver 4.28.1
- TestNG 7.7.0
- Maven
- Allure 2.23.0
- Log4j2
- Rest-Assured

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
   ```
3. **Configure browser drivers:**
   - Ensure the required WebDriver executables (e.g., chromedriver.exe, geckodriver.exe) are available in your PATH or configured in the framework.

## Running Tests
To execute all tests:
```powershell
mvn clean test
```
To run tests with a specific environment and a custom TestNG suite (e.g., Production with a sanity suite):
```powershell
mvn clean test -Denv="prod" -DsuiteXmlFile=src/test/resources/testng_Sanity.xml
```

## Reporting
- **Allure Reports:**
  1. Run tests to generate results in `allure-results/`.
  2. Serve the report:
     ```powershell
     mvn allure serve /allure report/
     ```
- **TestNG Reports:**
  - View HTML reports in `test-output/` after test execution.
- **Logs:**
  - Check `logs/faoschwarz.log` for execution logs.

## Contributing
1. Fork the repository
2. Create a new branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add feature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a Pull Request

---
