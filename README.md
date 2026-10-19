::: {align="center"}
# Medical Store Management System

**A Java desktop application for managing medical store operations using
Java Swing, JDBC, and MySQL.**
:::

------------------------------------------------------------------------

## Overview

The **Medical Store Management System** is an academic team project that
aims to organize the core operations of a medical store in one desktop
application. The system is being developed with Java and object-oriented
programming, with MySQL providing persistent storage through JDBC.

The project is currently under active development. The database schema,
JDBC connection setup, model classes, and DAO interfaces have been
established. JDBC DAO implementations, business workflows, and the Swing
user interface are being developed next.

> **Development status:** Work in progress. The repository is not yet a
> finished, end-to-end application. Features listed as planned below
> should not be interpreted as already implemented.

## Project Goals

-   Organize medicine and stock information.
-   Maintain supplier and customer records.
-   Record purchases and sales.
-   Track purchase and sale line items.
-   Associate transactions with the system user who records them.
-   Use a relational database and a layered Java architecture that is
    easier to test and maintain.

## Planned Application Modules

  -----------------------------------------------------------------------
  Module                  Intended responsibility Status
  ----------------------- ----------------------- -----------------------
  User management         Store user records and  In progress
                          support authentication

  Medicine management     Maintain medicine       In progress
                          details, expiry dates,
                          prices, and quantities

  Supplier management     Maintain supplier       Planned
                          contact information

  Customer management     Maintain customer       Planned
                          contact information

  Purchase management     Record purchases from   Planned
                          suppliers and their
                          line items

  Sales management        Record customer sales,  Planned
                          discounts, and sale
                          line items

  Inventory updates       Keep medicine           Planned
                          quantities consistent
                          with purchases and
                          sales

  Desktop interface       Provide forms and       Planned
                          screens using Java
                          Swing
  -----------------------------------------------------------------------

The exact available functionality will be updated as features are
implemented and tested.

## Technology Stack

  Technology                Purpose
  ------------------------- ----------------------------------------------------------
  Java 17                   Application language and object-oriented design
  Java Swing                Planned desktop graphical user interface
  JDBC                      Java-to-MySQL database connectivity
  MySQL Server 8.4          Relational database
  MySQL Connector/J 8.4.0   JDBC driver
  Maven                     Dependency management, compilation, tests, and packaging
  JUnit 5                   Automated testing

## Architecture

The project is organized around a simple layered structure:

``` text
Swing UI
   |
   v
Business / Service Layer       (to be developed)
   |
   v
DAO Interfaces                 (created)
   |
   v
JDBC DAO Implementations       (to be developed)
   |
   v
MySQL Database
```

-   **Model layer:** Java classes representing the application's data.
-   **DAO interfaces:** Contracts describing database operations.
-   **DAO implementations:** Classes that will execute SQL using JDBC.
-   **Configuration:** A shared database connection helper.
-   **Service layer and UI:** Planned layers for business workflows and
    user interaction.

## Database Design

The database is named `medical_store_management_system` and contains
eight tables:

  -----------------------------------------------------------------------
  Table                               Purpose
  ----------------------------------- -----------------------------------
  `Users`                             Application user details and role

  `Medicines`                         Medicine name, manufacturer, expiry
                                      date, selling price, and quantity

  `Suppliers`                         Supplier and contact details

  `Purchases`                         Purchase header, supplier, user,
                                      date, amount, and payment method

  `Purchase_Items`                    Medicines and quantities included
                                      in each purchase

  `Customers`                         Customer contact details

  `Sales`                             Sale header, customer, user, date,
                                      amount, and discount

  `Sale_Items`                        Medicines and quantities included
                                      in each sale
  -----------------------------------------------------------------------

Foreign keys connect transaction records to the relevant suppliers,
customers, users, and medicines. The schema script is maintained at:

``` text
src/main/resources/medical_store.sql
```

**Database safety:** If you already created the project database, do not
blindly rerun the SQL script. Inspect the script and your existing
schema first; do not drop or recreate a database containing data just to
set up the application.

## Project Structure

The current repository follows this Maven layout:

``` text
medical-store-management/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/com/medicalstore/
│   │   │   ├── App.java
│   │   │   ├── configuration/
│   │   │   │   └── DBconnection.java
│   │   │   ├── model/
│   │   │   │   ├── User.java
│   │   │   │   ├── Medicine.java
│   │   │   │   ├── Supplier.java
│   │   │   │   ├── Purchase.java
│   │   │   │   ├── PurchaseItem.java
│   │   │   │   ├── Customer.java
│   │   │   │   ├── Sale.java
│   │   │   │   └── SaleItem.java
│   │   │   └── dao/
│   │   │       ├── UserDAO.java
│   │   │       ├── MedicineDAO.java
│   │   │       ├── SupplierDAO.java
│   │   │       ├── PurchaseDAO.java
│   │   │       ├── PurchaseItemDAO.java
│   │   │       ├── CustomerDAO.java
│   │   │       ├── SaleDAO.java
│   │   │       └── SaleItemDAO.java
│   │   └── resources/
│   │       └── medical_store.sql
│   └── test/java/com/medicalstore/
├── .gitignore
├── pom.xml
└── README.md
```

The `dao/impl/` package will hold the JDBC implementations as they are
added.

## Getting Started

### 1. Prerequisites

Install the following:

-   JDK 17
-   Git
-   MySQL Server 8.4
-   Maven (or use the Maven Wrapper included in the repository)
-   An IDE such as Visual Studio Code, IntelliJ IDEA, or Eclipse

Check your installations in PowerShell:

``` powershell
java -version
git --version
mysql --version
mvn -version
```

If Maven is not installed globally, the Maven Wrapper can be used from
the project root:

``` powershell
.\mvnw.cmd -version
```

### 2. Clone the repository

``` powershell
git clone https://github.com/ArchitShah1008/medical-store-management.git
cd medical-store-management
```

### 3. Set up the database

1.  Start your local MySQL Server.
2.  Sign in using your own MySQL account.
3.  Confirm that the database `medical_store_management_system` and its
    eight tables exist.
4.  If you are setting up a fresh database, inspect
    `src/main/resources/medical_store.sql` and run it using the MySQL
    client according to the script's contents.

For example, from the MySQL command-line client, a script can be loaded
using an absolute path:

``` sql
SOURCE C:/path/to/medical-store-management/src/main/resources/medical_store.sql;
```

Replace the example path with the actual path on your computer. Only run
the script after checking whether it contains statements that could
alter or delete existing data.

### 4. Configure the database password

`com.medicalstore.configuration.DBconnection` reads the MySQL password
from the `MEDICAL_STORE_DB_PASSWORD` environment variable. The current
local connection settings use:

-   Host: `localhost`
-   Port: `3306`
-   Database: `medical_store_management_system`
-   Username: `root`
-   Password: supplied through the environment variable

In a **PowerShell terminal**, set the password for that terminal
session:

``` powershell
$secure = Read-Host "Enter MySQL password" -AsSecureString
$env:MEDICAL_STORE_DB_PASSWORD = [System.Net.NetworkCredential]::new("", $secure).Password
```

Run Maven commands in the same terminal. The variable must be set again
when you open a new terminal session.

To clear it from the current session:

``` powershell
Remove-Item Env:MEDICAL_STORE_DB_PASSWORD
```

Never put your actual password in Java source code, the README, SQL
files, screenshots, or Git commits.

### 5. Build and test

Run commands from the directory containing `pom.xml`:

``` powershell
mvn clean test
```

This removes old build output, recompiles the application and tests, and
runs the automated tests. The database connection test requires MySQL to
be running and `MEDICAL_STORE_DB_PASSWORD` to be set correctly.

Other useful commands:

``` powershell
mvn test
mvn clean package
mvn -version
```

`mvn -DskipTests package` skips test execution and should not be used as
evidence that the tests pass.

**Current limitation:** A successful test run verifies only the code and
tests that exist at that time. It does not prove that unfinished DAO
methods or planned application modules work.

## Development Conventions

-   Use Java 17-compatible code.
-   Keep source files in the existing Maven directories and packages.
-   Treat the DAO interfaces as contracts; coordinate before changing
    method signatures.
-   Obtain database connections through `DBconnection.getConnection()`.
-   Use `PreparedStatement` for parameterized SQL and try-with-resources
    for JDBC resources.
-   Use `BigDecimal` for monetary values.
-   Use the column names and foreign keys defined in
    `medical_store.sql`.
-   Use transactions for workflows that update multiple related records,
    such as sales, sale items, and stock.
-   Add or update tests when implementing functionality.
-   Never commit passwords, local configuration secrets, IDE-specific
    files, or generated `target/` output.

## Contributing

This is a collaborative project. Contributors should work on separate
feature branches and submit pull requests for review rather than pushing
assigned implementation work directly to `main`.

Example workflow:

``` powershell
git switch main
git pull origin main
git switch -c feature/your-task-name

# Make your changes, then verify them
mvn clean test
git status
git diff --check

git add path/to/your/changed/files
git diff --cached
git diff --cached --check
git commit -m "Describe the change"
git push -u origin feature/your-task-name
```

Open a pull request on GitHub from your feature branch into `main`.
Explain what changed and which tests you ran.

## Roadmap

-   [x] Establish the Maven project and Java 17 configuration
-   [x] Configure the MySQL JDBC connection
-   [x] Create the database schema
-   [x] Create the eight model classes
-   [x] Define the eight DAO interfaces
-   [ ] Implement and test the eight JDBC DAOs
-   [ ] Complete transaction-safe purchase, sales, and inventory
    workflows
-   [ ] Build the Java Swing screens
-   [ ] Integrate the UI, service logic, and database layer
-   [ ] Perform end-to-end testing and prepare a runnable release

## Team

-   **Project lead:** Archit Shah
-   **Contributors:** Parth and Suhas

## Project Disclaimer

This repository is an academic software project and is under active
development. It is not intended for production pharmacy operations.
Real-world use would require additional security, data protection,
auditability, correctness, and regulatory review.

## License

No license has been selected in this repository yet. Until a license is
added, do not assume that the code is licensed for unrestricted reuse or
redistribution.
