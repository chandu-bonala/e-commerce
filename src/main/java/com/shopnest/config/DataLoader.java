package com.shopnest.config;

import com.shopnest.model.Category;
import com.shopnest.model.Product;
import com.shopnest.model.User;
import com.shopnest.repository.CategoryRepository;
import com.shopnest.repository.ProductRepository;
import com.shopnest.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(CategoryRepository categoryRepo,
                                   ProductRepository productRepo,
                                   UserRepository userRepo,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            // Only seed if database is empty
            if (categoryRepo.count() > 0) return;

            // ---- Create Admin User ----
            User admin = new User("Admin User", "admin@shopnest.com",
                    passwordEncoder.encode("admin123"), User.Role.ADMIN);
            userRepo.save(admin);

            // ---- Create Demo Customer ----
            User customer = new User("John Doe", "john@example.com",
                    passwordEncoder.encode("password"), User.Role.CUSTOMER);
            customer.setPhone("+1 555-0100");
            customer.setAddress("123 Main Street");
            customer.setCity("New York");
            customer.setState("NY");
            customer.setZipCode("10001");
            userRepo.save(customer);

            // ---- Create Categories ----
            Category electronics = new Category("Electronics", "Latest gadgets and devices", "fas fa-laptop", "https://images.unsplash.com/photo-1498049794561-7780e7231661?w=400&fit=crop");
            Category fashion = new Category("Fashion", "Trendy clothing and accessories", "fas fa-tshirt", "https://images.unsplash.com/photo-1445205170230-053b83016050?w=400&fit=crop");
            Category home = new Category("Home & Living", "Furniture and home decor", "fas fa-couch", "https://images.unsplash.com/photo-1556228453-efd6c1ff04f6?w=400&fit=crop");
            Category sports = new Category("Sports & Fitness", "Equipment and activewear", "fas fa-running", "https://images.unsplash.com/photo-1517836357463-d25dfeac3438?w=400&fit=crop");
            Category books = new Category("Books", "Bestsellers and new releases", "fas fa-book", "https://images.unsplash.com/photo-1495446815901-a7297e633e8d?w=400&fit=crop");
            Category beauty = new Category("Beauty", "Skincare and cosmetics", "fas fa-spa", "https://images.unsplash.com/photo-1596462502278-27bfdc403348?w=400&fit=crop");

            categoryRepo.save(electronics);
            categoryRepo.save(fashion);
            categoryRepo.save(home);
            categoryRepo.save(sports);
            categoryRepo.save(books);
            categoryRepo.save(beauty);

            // ---- Electronics Products ----
            productRepo.save(new Product("Wireless Noise-Cancelling Headphones",
                    "Premium over-ear headphones with active noise cancellation, 30-hour battery life, and exceptional sound quality. Features Bluetooth 5.2, multipoint connection, and comfortable memory foam ear cushions.",
                    199.99, 299.99, "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=600&fit=crop",
                    "SoundMax", 150, 4.7, 2340, true, electronics));

            productRepo.save(new Product("Smart Watch Pro Series",
                    "Advanced smartwatch with health monitoring, GPS tracking, AMOLED display, and 7-day battery life. Water-resistant to 50m with over 100 workout modes.",
                    349.99, 449.99, "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=600&fit=crop",
                    "TechFit", 85, 4.5, 1856, true, electronics));

            productRepo.save(new Product("4K Ultra HD Webcam",
                    "Professional-grade webcam with 4K resolution, auto-focus, HDR, and built-in dual microphones. Perfect for streaming and video conferencing.",
                    129.99, 179.99, "https://images.unsplash.com/photo-1611532736597-de2d4265fba3?w=600&fit=crop",
                    "ClearView", 200, 4.3, 892, false, electronics));

            productRepo.save(new Product("Portable Bluetooth Speaker",
                    "Waterproof portable speaker with 360-degree sound, 24-hour battery, and rugged design. Pairs easily with any Bluetooth device.",
                    79.99, 119.99, "https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?w=600&fit=crop",
                    "BoomBox", 300, 4.6, 3421, true, electronics));

            productRepo.save(new Product("Mechanical Gaming Keyboard",
                    "RGB backlit mechanical keyboard with Cherry MX switches, programmable macros, and aircraft-grade aluminum frame. N-key rollover for competitive gaming.",
                    149.99, null, "https://images.unsplash.com/photo-1587829741301-dc798b83add3?w=600&fit=crop",
                    "KeyMaster", 120, 4.8, 1567, false, electronics));

            productRepo.save(new Product("Wireless Charging Pad",
                    "Fast wireless charger compatible with all Qi-enabled devices. Sleek design with LED indicator and overheat protection.",
                    39.99, 59.99, "https://images.unsplash.com/photo-1586953208448-b95a79798f07?w=600&fit=crop",
                    "ChargePro", 500, 4.4, 2100, false, electronics));

            // ---- Fashion Products ----
            productRepo.save(new Product("Classic Leather Jacket",
                    "Premium genuine leather jacket with a timeless design. Features YKK zippers, quilted lining, and adjustable waist. Available in black and brown.",
                    189.99, 259.99, "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=600&fit=crop",
                    "UrbanEdge", 60, 4.6, 876, true, fashion));

            productRepo.save(new Product("Designer Sunglasses",
                    "Polarized UV400 sunglasses with titanium frame. Lightweight, scratch-resistant lenses with anti-reflective coating.",
                    89.99, 139.99, "https://images.unsplash.com/photo-1572635196237-14b3f281503f?w=600&fit=crop",
                    "LuxVision", 200, 4.4, 1234, true, fashion));

            productRepo.save(new Product("Premium Cotton T-Shirt",
                    "Ultra-soft 100% organic cotton t-shirt with a modern slim fit. Pre-shrunk fabric with reinforced seams for lasting comfort.",
                    29.99, 44.99, "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=600&fit=crop",
                    "ComfortWear", 500, 4.5, 3456, false, fashion));

            productRepo.save(new Product("Running Sneakers Air Max",
                    "Lightweight running shoes with responsive cushioning, breathable mesh upper, and durable rubber outsole. Ideal for everyday training.",
                    119.99, 159.99, "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=600&fit=crop",
                    "FlexRun", 180, 4.7, 2890, true, fashion));

            // ---- Home & Living Products ----
            productRepo.save(new Product("Minimalist Desk Lamp",
                    "LED desk lamp with adjustable brightness, color temperature control, and USB charging port. Touch-sensitive controls with memory function.",
                    59.99, 89.99, "https://images.unsplash.com/photo-1507473885765-e6ed057ab6fe?w=600&fit=crop",
                    "LumiDesign", 250, 4.5, 1678, true, home));

            productRepo.save(new Product("Aromatherapy Diffuser Set",
                    "Ultrasonic essential oil diffuser with 7 LED colors, whisper-quiet operation, and auto shut-off. Includes 6 pure essential oils.",
                    44.99, 69.99, "https://images.unsplash.com/photo-1602928321679-560bb453f190?w=600&fit=crop",
                    "ZenAroma", 320, 4.6, 2345, false, home));

            productRepo.save(new Product("Luxury Throw Blanket",
                    "Ultra-soft microfiber throw blanket with elegant knit pattern. Machine washable, fade-resistant. Perfect for cozy evenings.",
                    49.99, null, "https://images.unsplash.com/photo-1555041469-a586c61ea9bc?w=600&fit=crop",
                    "CozyHome", 400, 4.8, 1890, false, home));

            // ---- Sports Products ----
            productRepo.save(new Product("Yoga Mat Premium",
                    "Extra-thick 6mm eco-friendly yoga mat with alignment lines, non-slip surface, and carrying strap. TPE material, free from harmful chemicals.",
                    34.99, 54.99, "https://images.unsplash.com/photo-1601925260368-ae2f83cf8b7f?w=600&fit=crop",
                    "FlexFit", 350, 4.7, 2567, true, sports));

            productRepo.save(new Product("Stainless Steel Water Bottle",
                    "Double-wall vacuum insulated bottle keeps drinks cold 24h or hot 12h. BPA-free, leak-proof cap. Available in 32oz.",
                    24.99, 39.99, "https://images.unsplash.com/photo-1602143407151-7111542de6e8?w=600&fit=crop",
                    "HydroMax", 600, 4.5, 4123, false, sports));

            productRepo.save(new Product("Resistance Bands Set",
                    "Complete set of 5 resistance bands with different tension levels. Includes door anchor, ankle straps, and carry bag for home or gym workouts.",
                    19.99, 34.99, "https://images.unsplash.com/photo-1598289431512-b97b0917affc?w=600&fit=crop",
                    "PowerBands", 450, 4.4, 1789, false, sports));

            // ---- Books Products ----
            productRepo.save(new Product("The Art of Programming",
                    "Comprehensive guide to modern software development practices. Covers clean code, design patterns, architecture, and test-driven development.",
                    39.99, 54.99, "https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?w=600&fit=crop",
                    "TechBooks", 200, 4.8, 3456, true, books));

            productRepo.save(new Product("Mindfulness & Meditation",
                    "A practical guide to mindfulness meditation. Includes guided exercises, breathing techniques, and daily practices for stress relief.",
                    14.99, 24.99, "https://images.unsplash.com/photo-1544947950-fa07a98d237f?w=600&fit=crop",
                    "WellnessBooks", 350, 4.6, 2134, false, books));

            // ---- Beauty Products ----
            productRepo.save(new Product("Vitamin C Serum",
                    "Advanced 20% Vitamin C serum with hyaluronic acid and vitamin E. Brightens skin, reduces dark spots, and boosts collagen production.",
                    29.99, 44.99, "https://images.unsplash.com/photo-1620916566398-39f1143ab7be?w=600&fit=crop",
                    "GlowSkin", 280, 4.7, 3890, true, beauty));

            productRepo.save(new Product("Natural Lip Collection",
                    "Set of 6 organic lip colors with moisturizing formula. Long-lasting, cruelty-free, and made with natural ingredients.",
                    24.99, 39.99, "https://images.unsplash.com/photo-1586495777744-4413f21062fa?w=600&fit=crop",
                    "PureLips", 400, 4.5, 1567, false, beauty));

            System.out.println("=== ShopNest Database Seeded Successfully ===");
            System.out.println("Admin: admin@shopnest.com / admin123");
            System.out.println("Customer: john@example.com / password");
        };
    }
}
