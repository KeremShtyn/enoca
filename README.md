# E-Commerce Application

This project is an E-commerce application developed using Spring Boot. It includes the management of products, customers, carts, and orders. The application ensures that a customer's cart and order are properly linked and managed, including handling stock management and price consistency.

## Features

- **Customer Management**: Add, update, and delete customers.
- **Product Management**: Add, update, and delete products with stock tracking.
- **Cart Management**: Each customer has a cart to add and remove products, with automatic price calculation.
- **Order Management**: Customers can place orders from their cart. Orders maintain the product prices at the time of purchase.
- **Stock Management**: Tracks product stock and prevents orders if stock is insufficient.
- **Order History**: Customers can view their past orders with the prices at the time of purchase.

## Entities

### BaseEntity

A base class for all entities containing common fields:
- `id`: Primary key
- `createdAt`: Timestamp of creation
- `updatedAt`: Timestamp of last update

### User

Represents a customer with fields:
- `firstName`
- `lastName`
- `username`
- `password`
- `email`
- `role`: Enum (ADMIN, CUSTOMER, BUSINESS)
- `cart`: One-to-one relationship with `Cart`
- `orders`: One-to-many relationship with `Order`

### Product

Represents a product with fields:
- `name`
- `description`
- `price`
- `stock`

### Cart

Represents a shopping cart with fields:
- `user`: One-to-one relationship with `User`
- `products`: Many-to-many relationship with `Product` including quantity
- `totalPrice`

### Order

Represents an order with fields:
- `user`: Many-to-one relationship with `User`
- `products`: Many-to-many relationship with `Product` including quantity
- `totalPrice`
- `orderCode`: Unique code for the order

## Services

### CustomerService

Methods:
- `addCustomer(UserDTO userDTO)`: Adds a new customer.
- `getCustomer(String username)`: Retrieves a customer by username.

### ProductService

Methods:
- `createProduct(ProductDTO productDTO)`: Creates a new product.
- `getProduct(Long productId)`: Retrieves a product by ID.
- `updateProduct(ProductDTO productDTO)`: Updates an existing product.
- `deleteProduct(Long productId)`: Deletes a product by ID.

### CartService

Methods:
- `getCart(String username)`: Retrieves the cart of a user.
- `addProductToCart(String username, Long productId, int quantity)`: Adds a product to the cart.
- `removeProductFromCart(String username, Long productId)`: Removes a product from the cart.
- `emptyCart(String username)`: Empties the cart.

### OrderService

Methods:
- `placeOrder(String username)`: Places an order for the user.
- `getOrderForCode(String orderCode)`: Retrieves an order by order code.
- `getAllOrdersForCustomer(String username)`: Retrieves all orders for a customer.

- ## Running the Application

1. **Clone the repository:**
   ```bash
   git clone https://github.com/KeremShtyn/enoca.git
   cd enoca
