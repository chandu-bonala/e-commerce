# Initialize and seed the database with sample data

from app import create_app, db
from models import User, Category, Product, Review
from datetime import datetime

def seed_database():
    """Populate database with sample data"""
    app = create_app('development')

    with app.app_context():
        # Drop and recreate tables
        print("📦 Dropping existing tables...")
        db.drop_all()

        print("📦 Creating tables...")
        db.create_all()

        # Create Admin User
        print("👤 Creating admin user...")
        admin = User(
            name='Admin User',
            email='admin@shopnest.com',
            phone='+91 9876543210',
            role='admin'
        )
        admin.set_password('admin123')
        db.session.add(admin)

        # Create Sample Customers
        print("👤 Creating sample customers...")
        customer1 = User(
            name='John Doe',
            email='john@example.com',
            phone='+91 9876543211',
            role='customer'
        )
        customer1.set_password('password123')
        db.session.add(customer1)

        customer2 = User(
            name='Jane Smith',
            email='jane@example.com',
            phone='+91 9876543212',
            role='customer'
        )
        customer2.set_password('password123')
        db.session.add(customer2)

        # Create Categories
        print("📁 Creating categories...")
        categories_data = [
            {'name': 'Electronics', 'slug': 'electronics', 'icon': 'fa-laptop', 'description': 'Latest gadgets and electronics'},
            {'name': 'Fashion', 'slug': 'fashion', 'icon': 'fa-tshirt', 'description': 'Trending fashion and apparel'},
            {'name': 'Home & Living', 'slug': 'home-living', 'icon': 'fa-home', 'description': 'Home decor and furniture'},
            {'name': 'Sports', 'slug': 'sports', 'icon': 'fa-dumbbell', 'description': 'Sports equipment and gear'},
            {'name': 'Books', 'slug': 'books', 'icon': 'fa-book', 'description': 'Books and stationery'},
        ]

        categories = {}
        for cat_data in categories_data:
            category = Category(**cat_data)
            db.session.add(category)
            categories[cat_data['slug']] = category

        db.session.flush()  # Get category IDs

        # Create Sample Products
        print("📦 Creating sample products...")
        products_data = [
            # Electronics
            {
                'name': 'Wireless Bluetooth Headphones',
                'slug': 'wireless-bluetooth-headphones',
                'description': 'Premium noise-cancelling wireless headphones with 30-hour battery life. Crystal clear sound quality and comfortable design.',
                'price': 4999.00,
                'discount_price': 3499.00,
                'stock': 50,
                'category_id': categories['electronics'].id,
                'is_featured': True,
                'is_deal': True,
                'rating': 4.5,
                'reviews_count': 127
            },
            {
                'name': 'Smart Watch Fitness Tracker',
                'slug': 'smart-watch-fitness-tracker',
                'description': 'Track your fitness goals with this advanced smartwatch. Heart rate monitor, sleep tracking, and 100+ workout modes.',
                'price': 6999.00,
                'discount_price': 4999.00,
                'stock': 35,
                'category_id': categories['electronics'].id,
                'is_featured': True,
                'rating': 4.3,
                'reviews_count': 89
            },
            {
                'name': 'Wireless Mouse - Ergonomic',
                'slug': 'wireless-mouse-ergonomic',
                'description': 'Comfortable ergonomic wireless mouse with precision tracking. Perfect for work and gaming.',
                'price': 899.00,
                'stock': 100,
                'category_id': categories['electronics'].id,
                'rating': 4.1,
                'reviews_count': 234
            },
            {
                'name': 'USB-C Fast Charger 65W',
                'slug': 'usb-c-fast-charger',
                'description': 'Fast charging adapter with multiple ports. Charge your laptop, phone, and tablet simultaneously.',
                'price': 1499.00,
                'discount_price': 999.00,
                'stock': 75,
                'category_id': categories['electronics'].id,
                'is_deal': True,
                'rating': 4.6,
                'reviews_count': 156
            },

            # Fashion
            {
                'name': 'Classic Cotton T-Shirt - Pack of 3',
                'slug': 'classic-cotton-tshirt-pack',
                'description': 'Premium quality 100% cotton t-shirts. Comfortable, breathable, and perfect for everyday wear.',
                'price': 1499.00,
                'discount_price': 999.00,
                'stock': 200,
                'category_id': categories['fashion'].id,
                'is_featured': True,
                'is_deal': True,
                'rating': 4.4,
                'reviews_count': 345
            },
            {
                'name': 'Denim Jacket - Unisex',
                'slug': 'denim-jacket-unisex',
                'description': 'Stylish vintage denim jacket. Perfect for any season. Available in multiple sizes.',
                'price': 2999.00,
                'stock': 45,
                'category_id': categories['fashion'].id,
                'rating': 4.7,
                'reviews_count': 78
            },
            {
                'name': 'Running Shoes - Sports Edition',
                'slug': 'running-shoes-sports',
                'description': 'Lightweight running shoes with superior cushioning. Perfect for jogging and gym workouts.',
                'price': 3499.00,
                'discount_price': 2799.00,
                'stock': 60,
                'category_id': categories['fashion'].id,
                'is_deal': True,
                'rating': 4.5,
                'reviews_count': 192
            },

            # Home & Living
            {
                'name': 'Ceramic Coffee Mug Set - 6 Pieces',
                'slug': 'ceramic-coffee-mug-set',
                'description': 'Beautiful ceramic mug set. Microwave and dishwasher safe. Perfect for tea and coffee lovers.',
                'price': 899.00,
                'stock': 120,
                'category_id': categories['home-living'].id,
                'is_featured': True,
                'rating': 4.2,
                'reviews_count': 89
            },
            {
                'name': 'LED Desk Lamp with USB Port',
                'slug': 'led-desk-lamp-usb',
                'description': 'Modern LED desk lamp with adjustable brightness. Built-in USB port for charging devices.',
                'price': 1299.00,
                'discount_price': 899.00,
                'stock': 85,
                'category_id': categories['home-living'].id,
                'is_deal': True,
                'rating': 4.3,
                'reviews_count': 67
            },
            {
                'name': 'Premium Bed Sheet Set - King Size',
                'slug': 'premium-bed-sheet-king',
                'description': 'Luxurious 100% cotton bed sheet set. Soft, comfortable, and long-lasting.',
                'price': 2499.00,
                'stock': 40,
                'category_id': categories['home-living'].id,
                'rating': 4.6,
                'reviews_count': 124
            },

            # Sports
            {
                'name': 'Yoga Mat - Anti-Slip',
                'slug': 'yoga-mat-anti-slip',
                'description': 'Premium quality yoga mat with excellent grip. Perfect for yoga, pilates, and home workouts.',
                'price': 799.00,
                'stock': 95,
                'category_id': categories['sports'].id,
                'rating': 4.4,
                'reviews_count': 156
            },
            {
                'name': 'Dumbbell Set - 10kg',
                'slug': 'dumbbell-set-10kg',
                'description': 'Adjustable dumbbell set for home gym. Quality construction with comfortable grip.',
                'price': 2499.00,
                'discount_price': 1999.00,
                'stock': 30,
                'category_id': categories['sports'].id,
                'is_deal': True,
                'rating': 4.5,
                'reviews_count': 89
            },

            # Books
            {
                'name': 'The Complete Guide to Python Programming',
                'slug': 'python-programming-guide',
                'description': 'Comprehensive guide for learning Python. Perfect for beginners and advanced programmers.',
                'price': 599.00,
                'stock': 150,
                'category_id': categories['books'].id,
                'is_featured': True,
                'rating': 4.8,
                'reviews_count': 234
            },
            {
                'name': 'Notebook Set - A5 Size - Pack of 5',
                'slug': 'notebook-set-a5',
                'description': 'High-quality ruled notebooks. Perfect for students and professionals.',
                'price': 349.00,
                'stock': 200,
                'category_id': categories['books'].id,
                'rating': 4.1,
                'reviews_count': 178
            },
        ]

        products = []
        for prod_data in products_data:
            product = Product(**prod_data)
            db.session.add(product)
            products.append(product)

        db.session.flush()  # Get product IDs

        # Create Sample Reviews
        print("⭐ Creating sample reviews...")
        reviews_data = [
            {
                'product_id': products[0].id,
                'user_id': customer1.id,
                'rating': 5,
                'title': 'Excellent sound quality!',
                'comment': 'Best headphones I have ever used. The noise cancellation is amazing and battery life is great.'
            },
            {
                'product_id': products[0].id,
                'user_id': customer2.id,
                'rating': 4,
                'title': 'Great value for money',
                'comment': 'Very comfortable to wear for long hours. Sound quality is excellent for the price.'
            },
            {
                'product_id': products[4].id,
                'user_id': customer1.id,
                'rating': 5,
                'title': 'Perfect fit and quality',
                'comment': 'The t-shirts are of excellent quality. Fit is perfect and very comfortable to wear.'
            },
            {
                'product_id': products[12].id,
                'user_id': customer2.id,
                'rating': 5,
                'title': 'Must-have for Python learners',
                'comment': 'Very comprehensive guide. Explains concepts clearly with practical examples.'
            },
        ]

        for review_data in reviews_data:
            review = Review(**review_data)
            db.session.add(review)

        # Commit all changes
        print("💾 Saving to database...")
        db.session.commit()

        print("\n✅ Database seeded successfully!")
        print("\n📊 Summary:")
        print(f"   - Admin User: admin@shopnest.com / admin123")
        print(f"   - Sample Customer: john@example.com / password123")
        print(f"   - Categories: {len(categories_data)}")
        print(f"   - Products: {len(products_data)}")
        print(f"   - Reviews: {len(reviews_data)}")
        print("\n🚀 You can now run the application!")


if __name__ == '__main__':
    seed_database()
