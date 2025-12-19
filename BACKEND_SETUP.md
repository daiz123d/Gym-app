# 🚀 HƯỚNG DẪN SETUP BACKEND + MOBILE

## 📋 Tổng quan

- **Backend**: Spring Boot REST API (Java)
- **Database**: MySQL
- **Mobile**: Android app với Retrofit

---

## 🔧 BƯỚC 1: Setup MySQL Database

### 1.1. Chạy script SQL

```bash
mysql -u root -p < mysql_database_schema.sql
```

Hoặc mở MySQL Workbench và chạy file `mysql_database_schema.sql`

### 1.2. Kiểm tra database

```sql
USE workout_db;
SELECT * FROM workouts;
```

---

## 🖥️ BƯỚC 2: Setup Backend (Spring Boot)

### 2.1. Yêu cầu
- Java 17+ (kiểm tra: `java -version`)
- Maven 3.6+ (kiểm tra: `mvn -version`)

### 2.2. Cấu hình database

Mở `backend/src/main/resources/application.properties`:

```properties
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD_HERE
```

### 2.3. Chạy backend

**Cách 1: Command line**
```bash
cd backend
mvn spring-boot:run
```

**Cách 2: IntelliJ IDEA**
- Mở project `backend` folder trong IntelliJ
- Run `WorkoutApplication.java`

**Cách 3: Eclipse**
- Import Maven project
- Run as Spring Boot App

### 2.4. Kiểm tra backend

Mở browser: http://localhost:8080/api/workouts

Nếu thấy JSON data → Backend đã chạy thành công! ✅

---

## 📱 BƯỚC 3: Setup Android App

### 3.1. Tìm IP của máy chạy backend

**Windows:**
```cmd
ipconfig
# Tìm IPv4 Address, ví dụ: 192.168.1.100
```

**Mac/Linux:**
```bash
ifconfig
# Hoặc
ip addr show
```

### 3.2. Cấu hình IP trong Android

Mở `app/src/main/java/com/uilover/project1932/Api/ApiClient.java`:

```java
private static final String BASE_URL = "http://YOUR_IP:8080/api/";
// Ví dụ: "http://192.168.1.100:8080/api/"
```

### 3.3. Cấu hình Internet Permission

Mở `app/src/main/AndroidManifest.xml` và thêm (nếu chưa có):

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

### 3.4. Build và chạy Android app

```bash
./gradlew assembleDebug
# Hoặc chạy từ Android Studio
```

---

## 🧪 TEST KẾT NỐI

### 1. Test Backend API

```bash
curl http://localhost:8080/api/workouts
```

### 2. Test từ Android

- Mở app
- Xem Logcat để kiểm tra kết nối
- Nếu có lỗi, kiểm tra:
  - Backend đang chạy chưa?
  - IP đúng chưa?
  - Phone và máy tính cùng mạng chưa?

---

## 🔧 TROUBLESHOOTING

### Backend không kết nối được database

**Lỗi**: `Access denied for user`

**Giải pháp**:
```sql
-- Tạo user mới
CREATE USER 'workout_user'@'%' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON workout_db.* TO 'workout_user'@'%';
FLUSH PRIVILEGES;
```

Rồi đổi trong `application.properties`:
```properties
spring.datasource.username=workout_user
spring.datasource.password=password123
```

### Android không kết nối được backend

**Lỗi**: `Connection refused` hoặc `UnknownHostException`

**Giải pháp**:
1. ✅ Kiểm tra backend đang chạy: http://localhost:8080/api/workouts
2. ✅ Kiểm tra IP đúng trong `ApiClient.java`
3. ✅ Đảm bảo phone và máy tính cùng WiFi/LAN
4. ✅ Tắt firewall tạm thời để test
5. ✅ Dùng IP thay vì `localhost` hoặc `127.0.0.1`

### CORS Error (nếu test từ browser)

Backend đã cấu hình CORS cho phép tất cả origin. Nếu vẫn lỗi, kiểm tra `@CrossOrigin` trong Controllers.

---

## 📁 CẤU TRÚC PROJECT

```
project/
├── backend/                    # Spring Boot Backend
│   ├── src/main/java/
│   │   └── com/uilover/workout/
│   │       ├── WorkoutApplication.java
│   │       ├── controller/     # REST Controllers
│   │       ├── service/        # Business Logic
│   │       ├── repository/     # Data Access
│   │       └── entity/          # Database Entities
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
├── app/                        # Android App
│   ├── src/main/java/
│   │   └── com/uilover/project1932/
│   │       ├── Api/            # Retrofit API
│   │       ├── Activity/       # Activities
│   │       └── ...
│   └── build.gradle
│
└── mysql_database_schema.sql  # Database Schema
```

---

## 🎯 LUỒNG HOẠT ĐỘNG

```
Android App
    ↓ (HTTP Request)
Retrofit API Client
    ↓ (REST API Call)
Spring Boot Backend
    ↓ (JPA/Hibernate)
MySQL Database
```

---

## 📚 TÀI LIỆU THAM KHẢO

- Spring Boot: https://spring.io/projects/spring-boot
- Retrofit: https://square.github.io/retrofit/
- MySQL: https://dev.mysql.com/doc/

---

## ✅ CHECKLIST

- [ ] MySQL database đã được tạo
- [ ] Backend Spring Boot chạy được (port 8080)
- [ ] Test API thành công (curl hoặc browser)
- [ ] Đổi IP trong Android `ApiClient.java`
- [ ] Build Android app thành công
- [ ] App kết nối được backend
- [ ] Dữ liệu hiển thị trong app

---

**Chúc bạn thành công!** 🎉

