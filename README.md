 The Movie Database (TMDB) API kullanılarak geliştirilmiş, modern Android mimarisi ve Jetpack Compose tabanlı bir film keşif uygulamasıdır.  
Uygulama; çoklu dil desteği, profil yönetimi ve sonsuz listeleme (infinite scroll) gibi özellikler sunar.


<img width="180" height="300" alt="Ekran görüntüsü 2025-12-15 173542" src="https://github.com/user-attachments/assets/83e64ed8-c91a-477f-a86e-d9d04e39478a" />
<img width="180" height="300" alt="Ekran görüntüsü 2025-12-15 173721" src="https://github.com/user-attachments/assets/e4f61ee7-4ae0-428b-b3cf-80976e4218c0" />
<img width="180" height="300" alt="Ekran görüntüsü 2025-12-15 173930" src="https://github.com/user-attachments/assets/568d2046-d519-481b-9e24-0fea657f14fc" />
<img width="180" height="300" alt="Ekran görüntüsü 2025-12-15 174019" src="https://github.com/user-attachments/assets/ac5d9f16-b982-4db7-a9d3-138305985c21" />
<img width="180" height="300" alt="image" src="https://github.com/user-attachments/assets/0b683c14-14ac-4d06-b924-affb0bcd316b" />



## 🚀 Özellikler
- Ana sayfada film kategorilerinin (Now Playing, Popular, Top Rated, Upcoming) yatay listeler halinde gösterilmesi
- Kategori bazlı “Tümünü Gör” ekranı ve **sonsuz kaydırma (infinite scrolling)** desteği
- Film arama ekranı:
  - Debounce mekanizmalı arama algoritması
  - Gerçek zamanlı sonuç listeleme
- Film detay sayfası:
  - Film posteri
  - Türler (genre) listesi
  - Puan, çıkış tarihi, orijinal dil ve açıklama bilgileri
- Çoklu dil desteği (Türkçe / İngilizce):
  - Uygulama içi metinler için localization
  - API istekleri için otomatik dil parametresi
- Kullanıcı profil ekranı:
  - İsim, soyisim düzenleme
  - Profil fotoğrafı seçme (galeriden)
  - Dil tercihlerini yönetme
- Uygulama yeniden başlatıldığında tüm kullanıcı tercihlerinin korunması

  ---

## 🛠 Kullanılan Teknolojiler

- **Kotlin** – Android uygulama geliştirme dili
- **Jetpack Compose** – Modern ve deklaratif UI geliştirme
- **Material 3** – Güncel tasarım bileşenleri
- **MVVM + Clean Architecture** – Katmanlı ve sürdürülebilir mimari yapı
- **StateFlow & Flow** – Reaktif state ve veri yönetimi
- **Kotlin Coroutines** – Asenkron ve arka plan işlemleri
- **Retrofit** – REST API istekleri
- **OkHttp & Interceptor** – Network yapılandırması ve global dil yönetimi
- **Gson Converter** – JSON veri dönüşümleri
- **Dagger Hilt** – Dependency Injection
- **Room Database** – Kullanıcı profili ve tercihleri için local veri saklama
- **Coil** – Film ve profil görsellerinin yüklenmesi
- **Navigation Compose** – Sayfalar arası geçiş yönetimi
