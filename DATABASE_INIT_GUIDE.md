# HƯỚNG DẪN KHỞI TẠO DỮ LIỆU VÀO DATABASE

## 📌 Tổng quan

Dữ liệu trong ứng dụng được tự động khởi tạo vào database khi app khởi động. Tất cả dữ liệu được định nghĩa trong `SampleData.java` và tự động được đồng bộ vào database.

---

## 🔄 Luồng hoạt động

### 1. Khi app khởi động

```
App Start → WorkoutApp.onCreate() → initializeData()
```

**File**: `app/src/main/java/com/uilover/project1932/WorkoutApp.java`

```java
@Override
public void onCreate() {
    super.onCreate();
    repository = new WorkoutRepository(this);
    initializeData(); // ← Tự động khởi tạo dữ liệu
}
```

### 2. Quá trình khởi tạo

1. **Lấy dữ liệu từ SampleData**
   - Đọc tất cả bài tập từ `SampleData.getAllWorkouts()`
   
2. **Đồng bộ vào database**
   - Chỉ thêm những bài tập **chưa tồn tại** trong database (theo title)
   - Tránh trùng lặp dữ liệu

3. **Lưu vào database**
   - Dữ liệu được lưu vào Room Database
   - Có thể truy cập từ bất kỳ Activity nào

---

## 📁 Cấu trúc file liên quan

### 1. **SampleData.java** - Nguồn dữ liệu
```
app/src/main/java/com/uilover/project1932/Data/SampleData.java
```
- Chứa tất cả dữ liệu bài tập mẫu
- **Đây là nơi bạn thêm bài tập mới**

### 2. **WorkoutApp.java** - Khởi tạo
```
app/src/main/java/com/uilover/project1932/WorkoutApp.java
```
- Tự động chạy khi app khởi động
- Gọi `syncWorkoutsFromSampleData()` để đồng bộ dữ liệu

### 3. **WorkoutRepository.java** - Quản lý database
```
app/src/main/java/com/uilover/project1932/Repository/WorkoutRepository.java
```
- Method `syncWorkoutsFromSampleData()`: Đồng bộ thông minh (chỉ thêm mới)
- Method `forceSyncWorkoutsFromSampleData()`: Force sync (xóa hết và thêm lại)

### 4. **MainActivity.java** - Hiển thị dữ liệu
```
app/src/main/java/com/uilover/project1932/Activity/MainActivity.java
```
- Chỉ load dữ liệu từ database
- Không cần khởi tạo lại (đã được làm trong WorkoutApp)

---

## 🎯 Cách thêm bài tập mới

### Bước 1: Mở file `SampleData.java`

### Bước 2: Thêm bài tập vào `getAllWorkouts()`

```java
list.add(new Workout(
    "Tên bài tập",
    "Mô tả...",
    "pic_4",
    120,
    "15 phút",
    getPlankLessons()
));
```

### Bước 3: Tạo method lấy bài học

```java
private static ArrayList<Lession> getPlankLessons() {
    ArrayList<Lession> list = new ArrayList<>();
    list.add(new Lession("Bài học 1", "05:30", "VIDEO_ID", "pic_4_1"));
    return list;
}
```

### Bước 4: Rebuild app

- Dữ liệu mới sẽ **tự động** được thêm vào database khi app khởi động
- Không cần xóa app hoặc database

---

## 🔧 Các method quan trọng

### `syncWorkoutsFromSampleData()`
- **Mục đích**: Đồng bộ thông minh
- **Hành động**: Chỉ thêm những bài tập chưa có trong database
- **Dùng khi**: App khởi động, muốn thêm bài tập mới mà không mất dữ liệu cũ

### `forceSyncWorkoutsFromSampleData()`
- **Mục đích**: Đồng bộ toàn bộ
- **Hành động**: Xóa hết dữ liệu cũ, thêm lại từ SampleData
- **Dùng khi**: Muốn reset database về trạng thái mặc định

### `deleteAllWorkouts()`
- **Mục đích**: Xóa tất cả bài tập
- **Dùng khi**: Reset database hoàn toàn

---

## 🗄️ Database Structure

### Bảng `workouts`
- `id`: Primary Key (Auto-increment)
- `title`: Tên bài tập (Unique)
- `description`: Mô tả
- `picPath`: Đường dẫn ảnh
- `kcal`: Calo đốt
- `durationAll`: Tổng thời gian
- `lessions`: Danh sách bài học (JSON)

### Các bảng khác
- `favorite_workouts`: Bài tập yêu thích
- `workout_history`: Lịch sử tập luyện
- `scheduled_workouts`: Lịch tập đã lên kế hoạch

---

## 📝 Log và Debug

### Kiểm tra dữ liệu đã được khởi tạo

**Logcat Filter**: `WorkoutApp`

```
Đã khởi tạo dữ liệu vào database: 3 bài tập
```

### Xem dữ liệu trong database

1. **Cách 1**: Long press "Xem tất cả" trong MainActivity → Mở `DatabaseViewActivity`
2. **Cách 2**: Sử dụng Database Inspector trong Android Studio

---

## ⚠️ Lưu ý quan trọng

1. **Dữ liệu tự động sync**: Mỗi lần app khởi động, dữ liệu mới từ `SampleData` sẽ tự động được thêm vào database

2. **Không mất dữ liệu cũ**: Method `syncWorkoutsFromSampleData()` chỉ thêm những bài tập chưa có, không xóa dữ liệu cũ

3. **Force sync**: Nếu muốn reset về trạng thái mặc định, dùng `forceSyncWorkoutsFromSampleData()` hoặc xóa dữ liệu trong ProfileActivity

4. **Thread safety**: Tất cả thao tác database đều chạy trên background thread để không block UI

---

## 🚀 Ví dụ: Thêm bài tập mới

```java
// Trong SampleData.java

public static ArrayList<Workout> getAllWorkouts() {
    // ... các bài tập cũ ...
    
    // Thêm bài tập mới
    list.add(new Workout(
        "Pilates",
        "Bài tập Pilates giúp tăng cường sức mạnh cơ bắp",
        "pic_5",
        200,
        "30 phút",
        getPilatesLessons()
    ));
    
    return list;
}

private static ArrayList<Lession> getPilatesLessons() {
    ArrayList<Lession> list = new ArrayList<>();
    list.add(new Lession("Bài học 1", "10:00", "VIDEO_ID_1", "pic_5_1"));
    list.add(new Lession("Bài học 2", "08:30", "VIDEO_ID_2", "pic_5_2"));
    return list;
}
```

Sau khi rebuild app, bài tập "Pilates" sẽ tự động xuất hiện trong database và hiển thị trong app!

---

## 📞 Troubleshooting

### Dữ liệu không hiển thị sau khi thêm mới

1. **Kiểm tra Logcat**: Xem có log "Đã khởi tạo dữ liệu" không
2. **Restart app**: Đóng và mở lại app để trigger khởi tạo
3. **Force sync**: Vào ProfileActivity → Xóa dữ liệu → Dữ liệu mẫu sẽ được khởi tạo lại

### Database trống sau khi xóa app

- Đây là hành vi bình thường
- Dữ liệu sẽ được khởi tạo lại từ `SampleData` khi app khởi động lần đầu

---

**Tóm lại**: Chỉ cần thêm dữ liệu vào `SampleData.java`, dữ liệu sẽ tự động được lưu vào database khi app khởi động! 🎉

