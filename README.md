# Order-Management-DAO
##  Features  

-  **Database Integration:** Connected through `ConnectionFactory` using JDBC  
-  **Layered Architecture:**  
  - **Model Layer:** Defines entities like `Client`, `Product`, and `Order`  
  - **DAO Layer:** Uses `GenericDAO` for CRUD operations  
  - **BLL Layer:** Implements business logic through `ClientBLL`, `ProductBLL`, and `OrderBLL`  
  - **Presentation Layer:** Generates interactive tables with `ReflectionTableBuilder`  
-  **Bill Generation:** Automatically creates invoices using the `Bill` class  
-  **Dynamic Data Handling:** Reflection and validation for flexible object management  
-  **Modular Design:** Separation of concerns ensures scalability and maintainability  

---

## Purpose  

Developed to apply **OOP, database management, and layered architecture** principles in Java.  
Demonstrates how to structure a real-world application with **data persistence, business logic, and UI integration**.  
