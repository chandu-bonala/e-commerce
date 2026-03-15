
# ShopNest E-Commerce Platform

A professional, full-featured e-commerce platform built with **Spring Boot (Java)**, **Thymeleaf**, and **MySQL**.

## 🌟 Features

### Customer Features
- 🏠 Beautiful responsive home page
- 🔍 Product search and filtering
- 🛒 Shopping cart
- 💳 Multi-step checkout
- 📦 Order tracking and history
- 👤 User authentication and profile management

### Admin Features
- 📊 Admin dashboard with analytics
- 📦 Product management (CRUD)
- 📋 Order management
- 📁 Category management
- 👥 User management

### Technical Features
- ✅ Spring Boot architecture
- 📱 Fully responsive UI (Thymeleaf templates)
- 🔐 Secure authentication with Spring Security
- 💾 MySQL database with Spring Data JPA

## 🛠️ Technology Stack

**Backend:**
- Java 17
- Spring Boot 3.2+
- Spring Data JPA
- Spring Security
- Thymeleaf
- Lombok

**Frontend:**
- HTML5
- CSS3
- JavaScript
- Thymeleaf templates

**Database:**
- MySQL 8.0+

## 📋 Prerequisites

- Java 17 or higher
- Maven
- MySQL 8.0 or higher

## 🚀 Installation & Setup

### 1. Clone the repository
```bash
git clone <repo-url>
cd "c:/Users/HP/OneDrive/Desktop/new one"
```

### 2. Configure MySQL Database

Create a MySQL database:
```sql
CREATE DATABASE shopnest_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

Update `src/main/resources/application.properties` with your MySQL credentials.

### 3. Build and Run the Application
```bash
mvnw spring-boot:run
```

The application will be available at: **http://localhost:8080**

## 📁 Project Structure

```
new one/
├── mvnw, mvnw.cmd, pom.xml         # Maven wrapper and config
├── seed_database.py                # (Optional) Python script for seeding
├── src/
│   ├── main/
│   │   ├── java/com/shopnest/      # Java source code
│   │   │   ├── controller/         # Controllers (Home, Product, Cart, Order, Admin)
│   │   │   ├── model/              # Entity models
│   │   │   ├── repository/         # Spring Data JPA repositories
│   │   │   ├── service/            # Business logic/services
│   │   │   └── config/             # Security and app config
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   ├── static/             # Static files (css, js, images)
│   │   │   └── templates/          # Thymeleaf HTML templates
│   └── test/                       # Test code
├── templates/                      # Extra HTML templates
├── static/uploads/                 # Uploaded images
└── target/                         # Build output
```

## 🔑 Default Login Credentials

**Admin Account:**
- Email: `admin@shopnest.com`
- Password: `admin123`

**Customer Account:**
- Email: `john@example.com`
- Password: `password123`

## 📦 Key Modules

### 1. User Module
- User registration and login
- Profile management
- Role-based access (customer/admin)

### 2. Product Module
- Product listing with pagination
- Filtering and search
- Product details
- Categories

### 3. Cart Module
- Add/remove items
- Update quantities
- Price calculation

### 4. Order Module
- Multi-step checkout
- Order confirmation
- Order history and tracking

### 5. Admin Module
- Dashboard with analytics
- Product management (CRUD)
- Order management
- Category management
- User management

## 🔐 Security Features

- Password hashing with BCrypt
- CSRF protection
- SQL injection prevention
- Secure session management

## 📱 Main Endpoints

### Public Routes
- `/` - Home page
- `/products` - Product listing
- `/products/{id}` - Product details
- `/login` - Login page
- `/register` - Registration page

### Protected Routes (Require Authentication)
- `/cart` - View cart
- `/cart/add` - Add to cart
- `/cart/remove` - Remove from cart
- `/orders/checkout` - Checkout page
- `/orders/place` - Place order
- `/orders/history` - Order history

### Admin Routes (Require Admin Role)
- `/admin/dashboard` - Admin dashboard
- `/admin/products` - Manage products
- `/admin/orders` - Manage orders
- `/admin/categories` - Manage categories
- `/admin/users` - Manage users

## 🎯 Future Enhancements

- [ ] Payment gateway integration
- [ ] Wishlist functionality
- [ ] Product comparison
- [ ] Advanced analytics dashboard
- [ ] Email notifications
- [ ] Social media login
- [ ] Multi-language support
- [ ] Product recommendations
- [ ] Discount coupons
- [ ] Inventory management

## 🐛 Troubleshooting

**Database Connection Error:**
- Ensure MySQL is running
- Verify credentials in `application.properties`
- Check if database exists

**Build/Dependency Error:**
- Run `mvnw clean install`
- Ensure Java 17+ and Maven are installed

**Port Already in Use:**
- Change port in `application.properties`: `server.port=8081`

## 📝 License

This project is licensed under the MIT License.

## 👨‍💻 Author

Built with ❤️ using Spring Boot and Java

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

---

**Happy Shopping! 🛍️**

## 🌟 Features

### Customer Features
- 🏠 Beautiful responsive home page
- 🔍 Advanced product search and filtering
- 🛒 Shopping cart with real-time updates
- 💳 Multi-step checkout process
- 📦 Order tracking and history
- ⭐ Product reviews and ratings
- 👤 User authentication and profile management

### Admin Features
- 📊 Comprehensive admin dashboard
- 📦 Product management (CRUD)
- 📋 Order management
- 📁 Category management
- 👥 User management
- 📈 Sales analytics

### Technical Features
- ✅ Professional UI/UX design
- 📱 Fully responsive (mobile, tablet, desktop)
- 🎨 Modern CSS with custom variables
- ⚡ Fast and interactive JavaScript
- 🔐 Secure authentication with Flask-Login
- 💾 MySQL database with SQLAlchemy ORM
- 🔄 RESTful API architecture

## 🛠️ Technology Stack

**Backend:**
- Python 3.8+
- Flask 3.0
- Flask-SQLAlchemy (MySQL)
- Flask-Login
- Flask-Bcrypt
- Flask-WTF

**Frontend:**
- HTML5
- CSS3 (Custom CSS with variables)
- JavaScript (Vanilla)
- Font Awesome 6.5
- Inter Font (Google Fonts)

**Database:**
- MySQL 8.0+

## 📋 Prerequisites

- Python 3.8 or higher
- MySQL 8.0 or higher
- pip (Python package manager)

## 🚀 Installation & Setup

### 1. Clone the repository
```bash
cd "c:/Users/HP/OneDrive/Desktop/new one"
```

### 2. Install Python dependencies
```bash
pip install -r requirements.txt
```

### 3. Configure MySQL Database

Create a MySQL database:
```sql
CREATE DATABASE ecommerce_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 4. Configure Environment Variables

Create a `.env` file by copying `.env.example`:
```bash
cp .env.example .env
```

Edit `.env` and update the database connection:
```bash
DATABASE_URL=mysql+pymysql://root:YOUR_PASSWORD@localhost/ecommerce_db
SECRET_KEY=your-secret-key-here
```

### 5. Initialize and Seed the Database

Run the seed script to create tables and populate with sample data:
```bash
python seed_database.py
```

This will create:
- Admin user: `admin@shopnest.com` / `admin123`
- Sample customer: `john@example.com` / `password123`
- 5 product categories
- 14 sample products
- Sample reviews

### 6. Run the Application

```bash
python app.py
```

The application will be available at: **http://localhost:5000**

## 📁 Project Structure

```
new one/
├── app.py                 # Flask application factory
├── config.py              # Configuration settings
├── models.py              # Database models
├── requirements.txt       # Python dependencies
├── seed_database.py       # Database seeding script
│
├── routes/                # Route blueprints (controllers)
│   ├── main.py           # Home page routes
│   ├── auth.py           # Authentication routes
│   ├── products.py       # Product routes
│   ├── cart.py           # Cart routes
│   ├── orders.py         # Order routes
│   └── admin.py          # Admin routes
│
├── static/                # Static files
│   ├── css/
│   │   └── style.css     # Main stylesheet
│   ├── js/
│   │   └── main.js       # Main JavaScript
│   └── uploads/          # Uploaded images
│
└── templates/             # HTML templates
    ├── base.html          # Base layout
    ├── home.html          # Home page
    ├── products.html      # Product listing
    ├── product-detail.html
    ├── cart.html
    ├── checkout.html
    ├── login.html
    ├── register.html
    ├── order-confirmation.html
    └── admin/
        └── dashboard.html
```

## 🔑 Default Login Credentials

**Admin Account:**
- Email: `admin@shopnest.com`
- Password: `admin123`

**Customer Account:**
- Email: `john@example.com`
- Password: `password123`

## 🎨 Design System

### Color Palette
- Primary: `#2563eb` (Blue)
- Accent: `#f59e0b` (Amber)
- Success: `#10b981` (Green)
- Danger: `#ef4444` (Red)
- Warning: `#f59e0b` (Orange)

### Typography
- Font Family: Inter (Google Fonts)
- Base Font Size: 16px

### Responsive Breakpoints
- Desktop: 1200px+
- Laptop: 1024px+
- Tablet: 768px+
- Mobile: < 768px

## 📦 Key Modules

### 1. User Module
- User registration and login
- Profile management
- Role-based access (customer/admin)

### 2. Product Module
- Product listing with pagination
- Advanced filtering and search
- Product details with image gallery
- Categories organization

### 3. Cart Module
- Add/remove items
- Update quantities
- Real-time price calculation
- Free shipping on orders above ₹500

### 4. Order Module
- Multi-step checkout
- Multiple payment methods (COD, Card, UPI)
- Order confirmation
- Order history and tracking

### 5. Admin Module
- Dashboard with analytics
- Product management (CRUD)
- Order management
- Category management
- User management

### 6. Review Module
- Product ratings (1-5 stars)
- Written reviews
- Review moderation

## 🔐 Security Features

- Password hashing with Bcrypt
- CSRF protection with Flask-WTF
- SQL injection prevention with SQLAlchemy
- XSS protection
- Secure session management

## 📱 API Endpoints

### Public Routes
- `GET /` - Home page
- `GET /products` - Product listing
- `GET /products/<id>` - Product details
- `GET /auth/login` - Login page
- `GET /auth/register` - Registration page

### Protected Routes (Require Authentication)
- `GET /cart` - View cart
- `POST /cart/add/<id>` - Add to cart
- `POST /cart/remove/<id>` - Remove from cart
- `GET /orders/checkout` - Checkout page
- `POST /orders/place` - Place order
- `GET /orders/history` - Order history

### Admin Routes (Require Admin Role)
- `GET /admin/dashboard` - Admin dashboard
- `GET /admin/products` - Manage products
- `GET /admin/orders` - Manage orders
- `GET /admin/categories` - Manage categories
- `GET /admin/users` - Manage users

## 🎯 Future Enhancements

- [ ] Payment gateway integration (Razorpay/Stripe)
- [ ] Wishlist functionality
- [ ] Product comparison
- [ ] Advanced analytics dashboard
- [ ] Email notifications
- [ ] Social media login
- [ ] Multi-language support
- [ ] Product recommendations
- [ ] Discount coupons
- [ ] Inventory management

## 🐛 Troubleshooting

**Database Connection Error:**
- Ensure MySQL is running
- Verify database credentials in `.env`
- Check if database exists

**Module Not Found Error:**
- Run `pip install -r requirements.txt`
- Ensure you're using Python 3.8+

**Port Already in Use:**
- Change port in `app.py`: `app.run(port=5001)`

## 📝 License

This project is licensed under the MIT License.

## 👨‍💻 Author

Built with ❤️ using Flask and Python

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

---

**Happy Shopping! 🛍️**
