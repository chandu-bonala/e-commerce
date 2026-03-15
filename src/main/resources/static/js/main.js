/* ============================================================
   ShopNest - Main JavaScript
   Toast notifications, nav scroll, quantity selectors,
   animate-on-scroll, password strength, tab switching
   ============================================================ */

document.addEventListener('DOMContentLoaded', function () {

    // ---- Navbar scroll effect ----
    const navbar = document.querySelector('.navbar');
    if (navbar) {
        window.addEventListener('scroll', function () {
            navbar.classList.toggle('scrolled', window.scrollY > 20);
        });
    }

    // ---- Toast notifications ----
    const toastContainer = document.createElement('div');
    toastContainer.className = 'toast-container';
    document.body.appendChild(toastContainer);

    window.showToast = function (message, type) {
        type = type || 'info';
        const icons = {
            success: 'fas fa-check-circle',
            error: 'fas fa-exclamation-circle',
            warning: 'fas fa-exclamation-triangle',
            info: 'fas fa-info-circle'
        };
        const toast = document.createElement('div');
        toast.className = 'toast ' + type;
        toast.innerHTML =
            '<i class="' + icons[type] + '"></i>' +
            '<span class="toast-message">' + message + '</span>' +
            '<button class="toast-close" onclick="this.parentElement.remove()">' +
            '<i class="fas fa-times"></i></button>';
        toastContainer.appendChild(toast);
        setTimeout(function () {
            if (toast.parentElement) toast.remove();
        }, 4000);
    };

    // Show flash messages as toasts
    document.querySelectorAll('[data-flash]').forEach(function (el) {
        var type = el.getAttribute('data-flash');
        var msg = el.textContent.trim();
        if (msg) {
            if (type === 'danger') type = 'error';
            showToast(msg, type);
        }
    });

    // ---- Quantity selector ----
    document.querySelectorAll('.quantity-selector').forEach(function (selector) {
        var input = selector.querySelector('input');
        var minusBtn = selector.querySelector('.qty-minus');
        var plusBtn = selector.querySelector('.qty-plus');

        if (minusBtn && plusBtn && input) {
            minusBtn.addEventListener('click', function () {
                var val = parseInt(input.value) || 1;
                if (val > 1) {
                    input.value = val - 1;
                    input.dispatchEvent(new Event('change'));
                }
            });
            plusBtn.addEventListener('click', function () {
                var val = parseInt(input.value) || 1;
                input.value = val + 1;
                input.dispatchEvent(new Event('change'));
            });
        }
    });

    // ---- Product tabs ----
    document.querySelectorAll('.tab-btn').forEach(function (btn) {
        btn.addEventListener('click', function () {
            var target = this.getAttribute('data-tab');
            var tabGroup = this.closest('.product-tabs');

            tabGroup.querySelectorAll('.tab-btn').forEach(function (b) { b.classList.remove('active'); });
            tabGroup.querySelectorAll('.tab-content').forEach(function (c) { c.classList.remove('active'); });

            this.classList.add('active');
            var targetEl = tabGroup.querySelector('#' + target);
            if (targetEl) targetEl.classList.add('active');
        });
    });

    // ---- Password strength meter ----
    var passInput = document.getElementById('password');
    var strengthBar = document.querySelector('.password-strength-bar');
    if (passInput && strengthBar) {
        passInput.addEventListener('input', function () {
            var val = this.value;
            var score = 0;
            if (val.length >= 6) score++;
            if (val.length >= 10) score++;
            if (/[A-Z]/.test(val) && /[a-z]/.test(val)) score++;
            if (/[0-9]/.test(val)) score++;
            if (/[^A-Za-z0-9]/.test(val)) score++;

            strengthBar.className = 'password-strength-bar';
            if (score <= 1) strengthBar.classList.add('weak');
            else if (score === 2) strengthBar.classList.add('fair');
            else if (score === 3) strengthBar.classList.add('good');
            else strengthBar.classList.add('strong');
        });
    }

    // ---- Payment method selector ----
    document.querySelectorAll('.payment-method').forEach(function (method) {
        method.addEventListener('click', function () {
            document.querySelectorAll('.payment-method').forEach(function (m) { m.classList.remove('selected'); });
            this.classList.add('selected');
            var radio = this.querySelector('input[type="radio"]');
            if (radio) radio.checked = true;
        });
    });

    // ---- Animate on scroll ----
    var observerOptions = { threshold: 0.1, rootMargin: '0px 0px -50px 0px' };
    var observer = new IntersectionObserver(function (entries) {
        entries.forEach(function (entry) {
            if (entry.isIntersecting) {
                entry.target.classList.add('visible');
                observer.unobserve(entry.target);
            }
        });
    }, observerOptions);

    document.querySelectorAll('.animate-on-scroll').forEach(function (el) {
        observer.observe(el);
    });

    // ---- Search form ----
    var searchInput = document.getElementById('search-input');
    if (searchInput) {
        searchInput.addEventListener('keypress', function (e) {
            if (e.key === 'Enter') {
                var q = this.value.trim();
                if (q) {
                    window.location.href = '/products?search=' + encodeURIComponent(q);
                }
            }
        });
    }

    // ---- Mobile nav toggle ----
    var navToggle = document.querySelector('.nav-toggle');
    var navActions = document.querySelector('.navbar-actions');
    if (navToggle && navActions) {
        navToggle.addEventListener('click', function () {
            navActions.classList.toggle('show');
        });
    }
});
