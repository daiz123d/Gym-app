# 🚀 BACKEND + MOBILE APP - HƯỚNG DẪN ĐẦY ĐỦ

## ✅ Đã hoàn thành

### Backend (Spring Boot)
- ✅ Spring Boot REST API
- ✅ MySQL Database connection
- ✅ CRUD operations cho Workouts, Favorites, History
- ✅ CORS configured

### Mobile (Android)
- ✅ Retrofit integration
- ✅ API Service interfaces
- ✅ ApiWorkoutRepository
- ✅ Internet permissions

---

## 📋 BƯỚC TIẾP THEO

### 1. Setup MySQL Database

```bash
mysql -u root -p < mysql_database_schema.sql
```

### 2. Chạy Backend

```bash
cd backend
mvn spring-boot:run
```

**Hoặc trong IntelliJ:**
- Mở folder `backend`
- Run `WorkoutApplication.java`

### 3. Cấu hình IP trong Android

Mở `app/src/main/java/com/uilover/project1932/Api/ApiClient.java`:

```java
private static final String BASE_URL = "http://YOUR_COMPUTER_IP:8080/api/";
```

**Tìm IP:**
- Windows: `ipconfig` → IPv4 Address
- Mac/Linux: `ifconfig` hoặc `ip addr`

### 4. Build và chạy Android App

```bash
./gradlew assembleDebug
# Hoặc từ Android Studio
```

---

## 📁 CẤU TRÚC PROJECT

```
project/
├── backend/                          # Spring Boot Backend
│   ├── src/main/java/
│   │   └── com/uilover/workout/
│   │       ├── WorkoutApplication.java
│   │       ├── controller/          # REST Controllers
│   │       │   ├── WorkoutController.java
│   │       │   ├── FavoriteController.java
│   │       │   └── HistoryController.java
│   │       ├── service/             # Business Logic
│   │       │   ├── WorkoutService.java
│   │       │   ├── FavoriteService.java
│   │       │   └── WorkoutHistoryService.java
│   │       ├── repository/          # Data Access (JPA)
│   │       │   ├── WorkoutRepository.java
│   │       │   ├── FavoriteWorkoutRepository.java
│   │       │   └── WorkoutHistoryRepository.java
│   │       ├── entity/               # Database Entities
│   │       │   ├── Workout.java
│   │       │   ├── Lession.java
│   │       │   ├── FavoriteWorkout.java
│   │       │   └── WorkoutHistory.java
│   │       └── converter/          # JSON Converters
│   │           └── LessionListConverter.java
│   ├── src/main/resources/
│   │   └── application.properties   # Database config
│   └── pom.xml
│
├── app/                              # Android App
│   ├── src/main/java/
│   │   └── com/uilover/project1932/
│   │       ├── Api/                 # Retrofit API
│   │       │   ├── ApiClient.java
│   │       │   ├── WorkoutApiService.java
│   │       │   └── Response/
│   │       │       ├── WorkoutResponse.java
│   │       │       ├── FavoriteResponse.java
│   │       │       └── WorkoutHistoryResponse.java
│   │       ├── Repository/
│   │       │   └── ApiWorkoutRepository.java
│   │       └── ...
│   └── build.gradle
│
└── mysql_database_schema.sql         # Database schema
```

---

## 🔗 API ENDPOINTS

### Workouts
- `GET /api/workouts` - Lấy tất cả bài tập
- `GET /api/workouts/{id}` - Lấy bài tập theo ID
- `POST /api/workouts` - Tạo bài tập mới
- `DELETE /api/workouts/{id}` - Xóa bài tập

### Favorites
- `GET /api/favorites` - Lấy tất cả yêu thích
- `GET /api/favorites/check/{title}` - Kiểm tra đã yêu thích chưa
- `POST /api/favorites` - Thêm vào yêu thích
- `DELETE /api/favorites/{title}` - Xóa khỏi yêu thích

### History
- `GET /api/history` - Lấy lịch sử tập luyện
- `POST /api/history` - Thêm vào lịch sử
- `GET /api/history/stats` - Lấy thống kê (totalKcal, totalWorkouts)

---

## 🧪 TEST API

### 1. Test từ Browser

```
http://localhost:8080/api/workouts
```

### 2. Test từ Command Line

```bash
curl http://localhost:8080/api/workouts
```

### 3. Test từ Android

- Mở Logcat trong Android Studio
- Filter: "ApiWorkoutRepository"
- Xem logs khi app load data

---

## ⚠️ LƯU Ý QUAN TRỌNG

1. **Backend phải chạy trước khi test Android app**
2. **Đảm bảo phone và máy tính cùng WiFi/LAN**
3. **Đổi IP trong ApiClient.java cho đúng**
4. **MySQL phải có dữ liệu** (chạy mysql_database_schema.sql)

---

## 🔧 TROUBLESHOOTING

### Backend không kết nối được MySQL

**Lỗi:** `Access denied`

**Fix:**
1. Kiểm tra username/password trong `application.properties`
2. Tạo user mới:
```sql
CREATE USER 'workout_user'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON workout_db.* TO 'workout_user'@'%';
```

### Android không kết nối được backend

**Lỗi:** `Connection refused`

**Fix:**
1. ✅ Backend đang chạy? Test: `curl http://localhost:8080/api/workouts`
2. ✅ IP đúng? Kiểm tra trong `ApiClient.java`
3. ✅ Cùng mạng? Phone và máy tính phải cùng WiFi
4. ✅ Firewall? Tắt firewall tạm thời để test

---

## 📚 TÀI LIỆU

- Backend README: `backend/README.md`
- Setup Guide: `BACKEND_SETUP.md`
- Database Schema: `mysql_database_schema.sql`

---

**Sẵn sàng để test!** 🎉

