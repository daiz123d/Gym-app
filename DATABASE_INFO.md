 # Thông tin Database

## Vị trí Database
Database được lưu tại: 
```
/data/data/com.uilover.project1932/databases/workout_database
```

## Cách xem dữ liệu trong Database

### Cách 1: Dùng Activity Debug (Trong app)
1. Mở app
2. Vào màn hình MainActivity
3. **Nhấn và giữ** vào text "Xem tất cả" (màu cam)
4. Màn hình DatabaseViewActivity sẽ hiển thị tất cả dữ liệu:
   - Danh sách bài tập
   - Bài tập yêu thích
   - Lịch sử tập luyện
   - Thống kê

### Cách 2: Dùng ADB (Android Debug Bridge)
```bash
# Kết nối device/emulator qua USB
adb shell

# Vào thư mục database
cd /data/data/com.uilover.project1932/databases

# Xem danh sách file
ls -la

# Copy database ra máy tính để xem
adb pull /data/data/com.uilover.project1932/databases/workout_database ~/Desktop/

# Dùng SQLite Browser để mở file
# Download: https://sqlitebrowser.org/
```

### Cách 3: Dùng Android Studio Database Inspector
1. Mở Android Studio
2. Chạy app trên emulator/device
3. Vào menu: **View > Tool Windows > App Inspection**
4. Chọn device và app
5. Chọn tab **Database Inspector**
6. Xem bảng: `workouts`, `favorite_workouts`, `workout_history`

## Cấu trúc Database

### Bảng 1: workouts
- id (INTEGER, PRIMARY KEY)
- title (TEXT)
- description (TEXT)
- pic_path (TEXT)
- kcal (INTEGER)
- duration_all (TEXT)
- lessions (TEXT - JSON format)

### Bảng 2: favorite_workouts
- id (INTEGER, PRIMARY KEY)
- workout_title (TEXT)
- added_date (INTEGER - timestamp)

### Bảng 3: workout_history
- id (INTEGER, PRIMARY KEY)
- workout_title (TEXT)
- completion_date (INTEGER - timestamp)
- duration (TEXT)
- kcal_burned (INTEGER)
- completed_lessons (INTEGER)
- total_lessons (INTEGER)

## Kiểm tra dữ liệu đã lưu

1. **Thêm vào yêu thích**: 
   - Vào WorkoutActivity
   - Nhấn vào icon trái tim (favorite)
   - Kiểm tra trong DatabaseViewActivity

2. **Lưu lịch sử**:
   - Vào WorkoutActivity
   - Nhấn "Bắt đầu tập luyện"
   - Lịch sử sẽ được lưu tự động
   - Kiểm tra trong DatabaseViewActivity

3. **Xem thống kê**:
   - MainActivity tự động hiển thị:
     - Tổng calo đốt (từ workout_history)
     - Tổng số bài tập đã làm

## Lưu ý
- Database tự động được tạo khi app chạy lần đầu
- Dữ liệu mẫu (3 bài tập) được tự động thêm vào khi database rỗng
- Dữ liệu sẽ bị xóa khi uninstall app (hoặc clear app data)

