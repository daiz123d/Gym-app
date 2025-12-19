# HƯỚNG DẪN THÊM DỮ LIỆU VÀO ỨNG DỤNG

## 📍 Vị trí thêm dữ liệu

**File chính**: `app/src/main/java/com/uilover/project1932/Data/SampleData.java`

Đây là file duy nhất bạn cần chỉnh sửa để thêm dữ liệu bài tập mới.

---

## 🔧 Cách thêm BÀI TẬP mới

### Bước 1: Mở file `SampleData.java`

### Bước 2: Thêm bài tập mới vào method `getAllWorkouts()`

```java
// Ví dụ: Thêm bài tập "Plank"
list.add(new Workout(
        "Plank",                                    // Tên bài tập
        "Mô tả chi tiết về bài tập Plank...",      // Mô tả
        "pic_4",                                    // Tên file ảnh (phải có trong drawable/)
        150,                                        // Kcal đốt
        "20 phút",                                  // Tổng thời gian
        getPlankLessons()                           // Danh sách bài học
));
```

### Bước 3: Tạo method để lấy danh sách bài học

Thêm method mới ở cuối file:

```java
private static ArrayList<Lession> getPlankLessons() {
    ArrayList<Lession> list = new ArrayList<>();
    
    list.add(new Lession(
        "Bài học 1",           // Tên bài học
        "05:30",               // Thời lượng (phút:giây)
        "VIDEO_ID_YOUTUBE",    // ID video YouTube (lấy từ URL youtube.com/watch?v=VIDEO_ID)
        "pic_4_1"              // Tên file ảnh (phải có trong drawable/)
    ));
    
    list.add(new Lession("Bài học 2", "06:00", "VIDEO_ID_2", "pic_4_2"));
    list.add(new Lession("Bài học 3", "04:45", "VIDEO_ID_3", "pic_4_3"));
    
    return list;
}
```

---

## 📝 Cấu trúc dữ liệu

### 1. Workout (Bài tập)
```java
new Workout(
    String title,              // Tên bài tập (VD: "Chạy bộ")
    String description,        // Mô tả chi tiết
    String picPath,            // Tên file ảnh (VD: "pic_1")
    int kcal,                 // Calo đốt (số nguyên)
    String durationAll,        // Tổng thời gian (VD: "9 phút")
    ArrayList<Lession> lessions // Danh sách bài học
)
```

### 2. Lession (Bài học)
```java
new Lession(
    String title,      // Tên bài học (VD: "Bài học 1")
    String duration,   // Thời lượng (VD: "03:46")
    String link,       // YouTube Video ID (VD: "HBPMvFkpNgE")
    String picPath     // Tên file ảnh (VD: "pic_1_1")
)
```

---

## 🖼️ Thêm ảnh cho bài tập

### Bước 1: Thêm ảnh vào thư mục drawable
- Đặt file ảnh vào: `app/src/main/res/drawable/`
- Tên file: phải khớp với `picPath` (VD: `pic_4.jpg`, `pic_4_1.jpg`)

### Bước 2: Sử dụng trong code
```java
picPath = "pic_4"  // Ảnh chính của bài tập
picPath = "pic_4_1" // Ảnh của bài học 1
```

---

## 🎥 Lấy YouTube Video ID

### Cách lấy ID từ URL YouTube:
```
URL: https://www.youtube.com/watch?v=HBPMvFkpNgE
ID:  HBPMvFkpNgE  ← Copy phần này
```

### Sử dụng trong code:
```java
new Lession("Bài học 1", "03:46", "HBPMvFkpNgE", "pic_1_1")
```

---

## 📋 Ví dụ đầy đủ: Thêm bài tập mới "Pilates"

### 1. Thêm vào `getAllWorkouts()`:
```java
list.add(new Workout(
    "Pilates",
    "Bài tập Pilates giúp tăng cường sức mạnh cơ bắp, cải thiện tư thế và tăng tính linh hoạt",
    "pic_5",
    200,
    "30 phút",
    getPilatesLessons()
));
```

### 2. Thêm method `getPilatesLessons()`:
```java
private static ArrayList<Lession> getPilatesLessons() {
    ArrayList<Lession> list = new ArrayList<>();
    
    list.add(new Lession("Bài học 1", "10:00", "VIDEO_ID_1", "pic_5_1"));
    list.add(new Lession("Bài học 2", "08:30", "VIDEO_ID_2", "pic_5_2"));
    list.add(new Lession("Bài học 3", "11:30", "VIDEO_ID_3", "pic_5_3"));
    
    return list;
}
```

### 3. Thêm ảnh vào drawable:
- `pic_5.jpg` - Ảnh chính bài tập
- `pic_5_1.jpg` - Ảnh bài học 1
- `pic_5_2.jpg` - Ảnh bài học 2
- `pic_5_3.jpg` - Ảnh bài học 3

---

## 🔄 Sau khi thêm dữ liệu

1. **Xóa dữ liệu cũ** (nếu cần):
   - Mở app → Vào Profile → Xóa dữ liệu
   - Hoặc uninstall và reinstall app

2. **Rebuild app**:
   - Dữ liệu mới sẽ tự động được thêm vào database khi mở app lần đầu

---

## 📌 Lưu ý quan trọng

1. **Tên file ảnh**: Phải khớp chính xác với `picPath`
2. **YouTube ID**: Chỉ lấy phần ID, không phải toàn bộ URL
3. **Database**: Dữ liệu được lưu tự động vào database khi app khởi động lần đầu
4. **Format thời gian**: Dùng format "XX:XX" (phút:giây) hoặc "X phút"

---

## 🎯 Quick Reference

| Mục cần chỉnh sửa | File | Dòng |
|-------------------|------|------|
| Thêm bài tập mới | `SampleData.java` | Method `getAllWorkouts()` |
| Thêm bài học mới | `SampleData.java` | Các method `getXXXLessons()` |
| Thay đổi logo/app name | `strings.xml` | Dòng `app_name` |
| Thay đổi intro text | `activity_intro.xml` | TextView với id `textView` |

