# Workout Backend API

Spring Boot REST API backend cho Workout App.

## Yêu cầu

- Java 17+
- Maven 3.6+
- MySQL 5.7+ hoặc 8.0+

## Cài đặt

1. **Clone và cài đặt dependencies:**
```bash
cd backend
mvn clean install
```

2. **Cấu hình database:**
- Mở `src/main/resources/application.properties`
- Đổi `spring.datasource.username` và `spring.datasource.password` theo MySQL của bạn
- Đảm bảo database `workout_db` đã được tạo (chạy `mysql_database_schema.sql` ở thư mục gốc)

3. **Chạy backend:**
```bash
mvn spring-boot:run
```

Hoặc trong IDE (IntelliJ/Eclipse):
- Run `WorkoutApplication.java`

## API Endpoints

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
- `GET /api/history/stats` - Lấy thống kê

## Cấu hình CORS

Backend đã được cấu hình để cho phép CORS từ mọi origin. Có thể tùy chỉnh trong `application.properties`.

## Port

Mặc định chạy trên port `8080`. Có thể thay đổi trong `application.properties`.

## Testing

Sử dụng Postman hoặc curl để test API:

```bash
curl http://localhost:8080/api/workouts
```

