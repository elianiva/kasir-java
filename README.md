# Aplikasi Kasir Java

> disclaimer: This is my first time creating a Java Swing app and I don't enjoy
> writing Java at all, so please lower your expectation :p

> Also bare in mind that this app is still a work in progress so things below
> are subjects to change.

## What I use to make this app

- [Java](https://java.com/ja/) - The language itself
- [Swing](https://en.wikipedia.org/wiki/Swing_(Java)) - Java framework for building GUI
- [Gradle](https://gradle.org/) - The build tool
- [Oracle Netbeans IDE](https://www.oracle.com/tools/technologies/netbeans-ide.html) - I only used it for its GUI builder which is quite nice
- [Neovim](https://neovim.io/) - The text editor, I edit all of the code from here
- [MariaDB/MySQL](https://mariadb.org/) - The database
- [Docker](https://www.docker.com/) - To make my life easier when setting up the database and phpmyadmin
- [FlatLaf](https://www.formdev.com/flatlaf/) - Better look and feel for Java Swing
- [JasperReport](https://community.jaspersoft.com/project/jasperreports-library) - The library needed to get the receipt in form of image
- [Apache POI](https://poi.apache.org/) - The library needed to export the report to Excel file


## Database

Here's how the database laid out. (My own version compared to the original design)

|           My version             |               Original              |
| -------------------------------- | ----------------------------------- |
| ![redesign](./pix/final_db.webp) | ![original](./pix/original_db.webp) |

I changed it to only use 5 tables instead of 6 because the original design
doesn't make any sense to me.

Two of them (order & detail_order) are *very* similar and that would just over-complicate the query
(to me anyways, I don't want to query 2 tables just to find out that both of
them are pretty much the same)

## Screenshots

I'm still planning some things to make this complete, but most of them are done.


### Login

![login](./pix/login.webp)


### Halaman Kasir

![cashier](./pix/cashier_page.webp)


### Manajemen Menu

![menu](./pix/menu_manager.webp)


### Manajemen User

![user](./pix/user_manager.webp)

## Manual

### Project Structure

Here's a project structure that will (hopefully) guide you to understand this app.

#### **app**
- **build.gradle** - This is where we declare the dependencies for this app.
- **src/main/java/kasir**
  - **App.java** - The entry point of the app
  - **controllers** - These files control the Create, Read, Update, and Delete operation for the models. (abstraction layer)
    - **FoodSource.java** - The `Food` controller (masakan)
    - **LevelSource.java** - The `Level` controller (level)
    - **OrderSource.java** - The `Order` controller (order)
    - **TransactionSource.java** - The `Transaction` controller (transaksi)
    - **UserSource.java** - The `User` controller (user)
  - **database** - Database related files
    - **ConnectionHelper.java** - Helper to get the database connection.
  - **helpers** - General utilities
    - **FormatRupiah.java** - Format a `long` value to a formatted `String` value and vice versa. Ex. `2000 -> Rp. 2000` and `Rp. 2000 -> 2000`
    - **OrderTable.java** - The *shared state* needed for the `Kasir` window.
    - **Popup.java** - Helper to spawn window popup easier.
    - **Receipt.java** - Get a receipt in form of an image, this depends on JasperReport.
    - **Report.java** - Get a report in form of an Excel file, this depends on Apache POI.
  - **models** - Where the model lives. Each model reflects a table in the database.
    - **Food.java** - The `Food` model (masakan)
    - **Level.java** - The `Level` model (level)
    - **Order.java** - The `Order` model (order)
    - **Transaction.java** - The `Transaction` model (transaksi)
    - **User.java** - The `User` model (user)
  - **reports**
    - **struk_pembayaran.jrxml** - This is the template for JasperReport.
  - **ui**
    - **Admin.java** - The `Admin` window where you can choose what you want to do.
    - **Kasir.java** - The `Kasir` window where you add a transaction by adding some orders.
    - **KasirPopup.java** - The `Kasir` popup, this is how you'd add an order.
    - **Login.java** - The `Login` window, this is the very first window you'd open.
    - **MenuManager.java** - The `MenuManager` window, this is where you manage the menu. (I should probably rename this?)
    - **MenuPopup.java** - The `MenuManager` popup, this is a form where you'd add a new *menu* or modify an existing *menu*.
    - **TransactionManager.java** - The `TransactionManager` window, this is where you manage transaction. (Getting a receipt, exporting a report, etc)
    - **TransactionPopup.java** - The `TransactionManager` popup, this is where you'd see the *orders* for each transaction.
    - **UserManager.java** - The `UserManager` window, this is where you'd manage the users of this app.
    - **UserPopup.java** - The `UserManager` popup, this is a form where you'd add a new *user* or modify an existing *user*.
    - **\*.form** - Form files needed for Netbeans GUI builder
- **gradlew** - Gradle Wrapper, this is how you'd run the tasks on Unix system (Linux, Mac, etc).
- **gradlew.bat** - Also a Gradle wrapper, but this one is for Windows.
- **kasir.sql** - This is the dump of the database to help you setup the database.
