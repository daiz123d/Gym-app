# 📝 HƯỚNG DẪN THÊM DỮ LIỆU MẪU VÀO DATABASE

## 🎯 Có 3 cách để thêm dữ liệu mẫu vào MySQL database:

---

## ✅ CÁCH 1: Dùng file SQL script (Khuyến nghị)

File `mysql_database_schema.sql` đã có sẵn dữ liệu mẫu và INSERT statements.

### Bước 1: Mở MySQL Command Line hoặc MySQL Workbench

### Bước 2: Chạy script SQL

```bash
mysql -u root -p < mysql_database_schema.sql
```

**Hoặc trong MySQL Workbench:**
1. Mở MySQL Workbench
2. Kết nối đến MySQL server
3. File → Open SQL Script → Chọn `mysql_database_schema.sql`
4. Execute (⚡ icon hoặc Ctrl+Shift+Enter)

### Kết quả:
- Database `workout_db` được tạo
- Các bảng được tạo
- **3 bài tập mẫu** đã được thêm vào
- Dữ liệu mẫu cho favorites, history cũng được thêm

---

## ✅ CÁCH 2: Dùng REST API Endpoint (Tự động)

Backend có endpoint để tự động thêm dữ liệu mẫu.

### Bước 1: Đảm bảo backend đang chạy

```bash
cd backend
mvn spring-boot:run
```

### Bước 2: Gọi API endpoint

**Dùng curl:**
```bash
curl -X POST http://localhost:8080/api/seed/workouts
```

**Dùng Postman:**
- Method: `POST`
- URL: `http://localhost:8080/api/seed/workouts`
- Send request

**Dùng Browser (chỉ để test):**
- Cài extension REST Client cho browser
- Hoặc dùng JavaScript console:
```javascript
fetch('http://localhost:8080/api/seed/workouts', {method: 'POST'})
  .then(r => r.json())
  .then(console.log);
```

### Kết quả:
```json
{
  "success": true,
  "message": "Đã thêm 3 bài tập vào database",
  "count": 3
}
```

**Lưu ý:** Endpoint này sẽ **chỉ thêm** những bài tập chưa tồn tại (kiểm tra theo title), nên có thể gọi nhiều lần an toàn.

---

## ✅ CÁCH 3: Thêm thủ công qua MySQL

### Bước 1: Kết nối MySQL

```bash
mysql -u root -p
USE workout_db;
```

### Bước 2: Thêm bài tập thủ công

```sql
INSERT INTO workouts (title, description, pic_path, kcal, duration_all, lessions) VALUES (
    'Chạy bộ',
    'Bạn vừa thức dậy. Đây là một ngày mới...',
    'pic_1',
    160,
    '9 phút',
    JSON_ARRAY(
        JSON_OBJECT('title', 'Bài học 1', 'duration', '03:46', 'link', 'HBPMvFkpNgE', 'picPath', 'pic_1_1'),
        JSON_OBJECT('title', 'Bài học 2', 'duration', '03:41', 'link', 'K6I24WgiiPw', 'picPath', 'pic_1_2'),
        JSON_OBJECT('title', 'Bài học 3', 'duration', '01:57', 'link', 'Zc08v4YYOeA', 'picPath', 'pic_1_3')
    )
);
```

---

## 🔍 KIỂM TRA DỮ LIỆU ĐÃ THÊM

### Kiểm tra bằng SQL:

```sql
USE workout_db;

-- Xem tất cả bài tập
SELECT * FROM workouts;

-- Đếm số bài tập
SELECT COUNT(*) FROM workouts;

-- Xem chi tiết bài tập và số bài học
SELECT 
    title, 
    kcal, 
    duration_all,
    JSON_LENGTH(lessions) AS so_bai_hoc
FROM workouts;
```

### Kiểm tra bằng API:

```bash
# Xem tất cả bài tập
curl http://localhost:8080/api/workouts

# Xem bài tập theo ID
curl http://localhost:8080/api/workouts/1
```

---

## 📋 DỮ LIỆU MẪU CÓ SẴN

File `mysql_database_schema.sql` đã chứa:

### 1. Bài tập:
- ✅ **Chạy bộ** (3 bài học)
- ✅ **Kéo giãn** (4 bài học)
- ✅ **Yoga** (4 bài học)

### 2. Dữ liệu mẫu khác:
- ✅ Favorite workouts (2 bài tập)
- ✅ Workout history (3 bản ghi)
- ✅ Scheduled workouts (2 bản ghi)

---

## 🚀 THÊM BÀI TẬP MỚI

### Cách 1: Thêm vào SQL script

Mở `mysql_database_schema.sql`, tìm phần INSERT và thêm:

```sql
INSERT INTO workouts (title, description, pic_path, kcal, duration_all, lessions) VALUES (
    'Tên bài tập mới',
    'Mô tả...',
    'pic_4',
    120,
    '15 phút',
    JSON_ARRAY(
        JSON_OBJECT('title', 'Bài học 1', 'duration', '05:00', 'link', 'VIDEO_ID', 'picPath', 'pic_4_1')
    )
);
```

### Cách 2: Thêm qua API

**Tạo bài tập mới:**
```bash
curl -X POST http://localhost:8080/api/workouts \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Plank",
    "description": "Mô tả bài tập Plank",
    "picPath": "pic_4",
    "kcal": 120,
    "durationAll": "15 phút",
    "lessions": [
      {
        "title": "Bài học 1",
        "duration": "05:00",
        "link": "VIDEO_ID",
        "picPath": "pic_4_1"
      }
    ]
  }'
```

### Cách 3: Cập nhật SeedDataController

Mở `backend/src/main/java/com/uilover/workout/controller/SeedDataController.java` và thêm vào method `getSampleWorkouts()`.

---

## ⚠️ LƯU Ý

1. **File SQL đã có sẵn dữ liệu mẫu** - Cách 1 là nhanh nhất
2. **API endpoint tự động** - Cách 2 tiện lợi khi backend đã chạy
3. **Backup database** trước khi xóa/thay đổi dữ liệu:
```sql
mysqldump -u root -p workout_db > backup.sql
```

---

## 🔄 RESET DATABASE

Nếu muốn xóa hết và thêm lại từ đầu:

```sql
DROP DATABASE IF EXISTS workout_db;
-- Rồi chạy lại mysql_database_schema.sql
```

---

**Tóm lại:** Cách nhanh nhất là chạy file `mysql_database_schema.sql` - nó đã có đầy đủ dữ liệu mẫu! 🎯

