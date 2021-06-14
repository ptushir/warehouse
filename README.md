# Warehouse
Warehouse Management Software

This software is a solution to manage the warehouse activities like add articles, add products, get products and sell product. 
It is developed on Java 11 and H2 database.

Steps to run the software:
  In Postman, run following API's
  1. Create the inventory by passing inventory.json file in body (form-data) of request URL: http://localhost:8080/warehouse/inventory/addArticles 
  2. Create the products by passing products.json file in body (form-data) of request URL: http://localhost:8080/warehouse/product/addProducts
  3. Get all products available in the inventory with URL: http://localhost:8080/warehouse/product/getProducts
  4. Remove or Sell product and update the inventory with URL: http://localhost:8080/warehouse/product/sellProduct/{productId}
  
Json file path: src/main/resources/Json/
 
  Future Scope:
  1. Logging framework enhancement
  2. Exception handling enhancement
  3. Junit enhancement
  4. Javadoc enhancement
  5. Swagger UI enhancement
  6. Code coverage (SonarQube)
  7. Cloud deployment
