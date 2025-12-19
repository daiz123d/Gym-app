# Hướng dẫn cập nhật ảnh cho các bài tập

## ✅ Đã hoàn thành

Tôi đã tạo sẵn:
1. **File `ExerciseImageUrls.java`** - Chứa tất cả URL ảnh từ Unsplash/Pexels
2. **Cập nhật một số bài tập mẫu** trong `ExerciseData.java`:
   - Bài tập ngực (Bench Press, Push-up, Flyes)
   - Bài tập lưng (Pull-up, Row)
   - Bài tập bắp tay trước (Biceps Curl)

## 📝 Cách cập nhật các bài tập còn lại

### Cách 1: Sử dụng URL có sẵn trong ExerciseImageUrls

```java
// Thay vì:
"pic_1"

// Dùng:
ExerciseImageUrls.CHEST_BENCH_PRESS
ExerciseImageUrls.BACK_PULL_UP
ExerciseImageUrls.LEGS_SQUAT
ExerciseImageUrls.SHOULDER_PRESS
ExerciseImageUrls.BICEPS_CURL
ExerciseImageUrls.TRICEPS_PUSHDOWN
// ... xem file ExerciseImageUrls.java để biết thêm
```

### Cách 2: Sử dụng hàm tự động tìm ảnh

```java
// Thay vì:
"pic_1"

// Dùng:
ExerciseImageUrls.getImageUrlForExercise("Bài tập ngực nằm đẩy tạ đòn")
// Hàm này sẽ tự động tìm ảnh phù hợp dựa trên tên bài tập
```

### Cách 3: Dùng URL trực tiếp từ browser

1. Tìm ảnh trên Unsplash: https://unsplash.com/s/photos/workout
2. Hoặc Pexels: https://www.pexels.com/search/workout/
3. Click chuột phải vào ảnh → "Copy image address"
4. Dán URL vào code:

```java
exercises.add(new ExerciseItem(
    "Tên bài tập",
    "https://images.unsplash.com/photo-1234567890?w=400&h=400&fit=crop", // URL từ browser
    "Mô tả",
    "Hướng dẫn",
    60
));
```

## 🎯 Danh sách URL ảnh có sẵn

### Ngực (Chest)
- `ExerciseImageUrls.CHEST_MAIN`
- `ExerciseImageUrls.CHEST_BENCH_PRESS`
- `ExerciseImageUrls.CHEST_PUSH_UP`
- `ExerciseImageUrls.CHEST_FLYES`

### Lưng (Back)
- `ExerciseImageUrls.BACK_MAIN`
- `ExerciseImageUrls.BACK_PULL_UP`
- `ExerciseImageUrls.BACK_ROW`
- `ExerciseImageUrls.BACK_DEADLIFT`

### Chân (Legs)
- `ExerciseImageUrls.LEGS_MAIN`
- `ExerciseImageUrls.LEGS_SQUAT`
- `ExerciseImageUrls.LEGS_LUNGE`
- `ExerciseImageUrls.LEGS_LEG_PRESS`

### Mông (Glutes)
- `ExerciseImageUrls.GLUTES_MAIN`
- `ExerciseImageUrls.GLUTES_HIP_THRUST`

### Vai (Shoulder)
- `ExerciseImageUrls.SHOULDER_MAIN`
- `ExerciseImageUrls.SHOULDER_PRESS`
- `ExerciseImageUrls.SHOULDER_LATERAL_RAISE`

### Bắp tay trước (Biceps)
- `ExerciseImageUrls.BICEPS_MAIN`
- `ExerciseImageUrls.BICEPS_CURL`
- `ExerciseImageUrls.BICEPS_HAMMER_CURL`

### Bắp tay sau (Triceps)
- `ExerciseImageUrls.TRICEPS_MAIN`
- `ExerciseImageUrls.TRICEPS_PUSHDOWN`
- `ExerciseImageUrls.TRICEPS_DIPS`

### Bụng (Abs)
- `ExerciseImageUrls.ABS_MAIN`
- `ExerciseImageUrls.ABS_CRUNCH`
- `ExerciseImageUrls.ABS_PLANK`

## 📋 Ví dụ cập nhật

### Trước:
```java
exercises.add(new ExerciseItem(
    "Bài tập chân squat",
    "pic_1",
    "Mô tả",
    "Hướng dẫn",
    60
));
```

### Sau (Cách 1):
```java
exercises.add(new ExerciseItem(
    "Bài tập chân squat",
    ExerciseImageUrls.LEGS_SQUAT,
    "Mô tả",
    "Hướng dẫn",
    60
));
```

### Sau (Cách 2):
```java
exercises.add(new ExerciseItem(
    "Bài tập chân squat",
    ExerciseImageUrls.getImageUrlForExercise("Bài tập chân squat"),
    "Mô tả",
    "Hướng dẫn",
    60
));
```

### Sau (Cách 3):
```java
exercises.add(new ExerciseItem(
    "Bài tập chân squat",
    "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=400&h=400&fit=crop",
    "Mô tả",
    "Hướng dẫn",
    60
));
```

## 🔍 Tìm ảnh phù hợp

### Unsplash (Khuyến nghị)
- URL: https://unsplash.com/s/photos/workout
- Tìm kiếm: "chest workout", "squat exercise", "pull up", etc.
- Tất cả ảnh đều miễn phí, có thể dùng thương mại

### Pexels
- URL: https://www.pexels.com/search/workout/
- Tương tự Unsplash, miễn phí

### Lưu ý
- Nên dùng ảnh có kích thước 400x400 hoặc lớn hơn
- Thêm `?w=400&h=400&fit=crop` vào cuối URL Unsplash để resize
- Test URL trong browser trước khi dùng

## ⚡ Tự động hóa

Nếu bạn muốn cập nhật tất cả bài tập một lúc, có thể dùng hàm `getImageUrlForExercise()`:

```java
// Thay vì cập nhật từng bài một, có thể dùng:
String imageUrl = ExerciseImageUrls.getImageUrlForExercise(exerciseTitle);
```

Hàm này sẽ tự động tìm ảnh phù hợp dựa trên tên bài tập.

## ✅ Checklist

- [x] Tạo file ExerciseImageUrls.java
- [x] Cập nhật một số bài tập mẫu
- [ ] Cập nhật tất cả bài tập ngực
- [ ] Cập nhật tất cả bài tập lưng
- [ ] Cập nhật tất cả bài tập chân
- [ ] Cập nhật tất cả bài tập vai
- [ ] Cập nhật tất cả bài tập tay
- [ ] Cập nhật tất cả bài tập bụng
- [ ] Test ảnh hiển thị đúng trong app

## 🎉 Kết quả

Sau khi cập nhật, app sẽ:
- ✅ Hiển thị ảnh thật từ internet
- ✅ Tự động cache ảnh để tăng tốc độ
- ✅ Có placeholder nếu ảnh không load được
- ✅ Hỗ trợ cả URL và drawable resource

