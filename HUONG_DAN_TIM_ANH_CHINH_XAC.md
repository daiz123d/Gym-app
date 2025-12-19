# Hướng dẫn tìm ảnh chính xác cho từng bài tập

## 🎯 Vấn đề hiện tại

Các URL ảnh hiện tại là placeholder từ Unsplash, có thể không khớp chính xác với từng bài tập. Bạn cần tìm ảnh cụ thể cho từng bài tập.

## 📋 Cách tìm ảnh chính xác

### Bước 1: Xác định tên bài tập tiếng Anh

Ví dụ:
- "Bài tập ngực nằm đẩy tạ đòn" → "bench press"
- "Bài tập chân ngồi xổm" → "squat"
- "Bài tập lưng kéo xà đơn" → "pull up"

### Bước 2: Tìm ảnh trên Unsplash

1. Truy cập: https://unsplash.com/s/photos/[tên-bài-tập]
   - Ví dụ: https://unsplash.com/s/photos/bench-press
   - Ví dụ: https://unsplash.com/s/photos/squat
   - Ví dụ: https://unsplash.com/s/photos/pull-up

2. Chọn ảnh phù hợp với bài tập

3. Click vào ảnh để xem chi tiết

4. Click chuột phải vào ảnh → "Copy image address" hoặc "Copy image link"

5. URL sẽ có dạng: `https://images.unsplash.com/photo-1234567890-abcdef?ixlib=...`

6. Thêm tham số resize: `?w=400&h=400&fit=crop&q=80`
   - Ví dụ: `https://images.unsplash.com/photo-1234567890-abcdef?w=400&h=400&fit=crop&q=80`

### Bước 3: Cập nhật vào code

#### Cách 1: Cập nhật trực tiếp trong ExerciseData.java

```java
exercises.add(new ExerciseItem(
    "Bài tập ngực nằm đẩy tạ đòn",
    "https://images.unsplash.com/photo-1234567890-abcdef?w=400&h=400&fit=crop&q=80", // URL mới
    "Bench press cơ bản",
    "1. Nằm trên ghế, cầm tạ đòn\n" +
    "2. Hạ tạ xuống ngực\n" +
    "3. Đẩy tạ lên mạnh mẽ\n" +
    "4. Giữ 1 giây ở vị trí cao nhất\n" +
    "5. Lặp lại động tác",
    90
));
```

#### Cách 2: Thêm vào ExerciseImageUrls.java

```java
// Trong file ExerciseImageUrls.java
public static final String CHEST_BENCH_PRESS = "https://images.unsplash.com/photo-1234567890-abcdef?w=400&h=400&fit=crop&q=80";
```

Sau đó dùng trong ExerciseData.java:
```java
ExerciseImageUrls.CHEST_BENCH_PRESS
```

## 🔍 Từ khóa tìm kiếm cho từng nhóm bài tập

### Ngực (Chest)
- "bench press" - Đẩy tạ đòn
- "dumbbell press" - Đẩy tạ đơn
- "push up" - Hít đất
- "chest fly" - Mở ngực
- "incline bench press" - Đẩy tạ dốc lên
- "decline bench press" - Đẩy tạ dốc xuống
- "cable crossover" - Kéo cáp chéo

### Lưng (Back)
- "pull up" - Kéo xà
- "barbell row" - Kéo tạ đòn
- "deadlift" - Kéo tạ chết
- "lat pulldown" - Kéo cáp
- "one arm row" - Kéo tạ một tay
- "back workout" - Tập lưng

### Chân (Legs)
- "squat" - Ngồi xổm
- "leg press" - Đẩy chân
- "lunge" - Chùng chân
- "leg curl" - Gập chân
- "leg extension" - Duỗi chân
- "leg workout" - Tập chân

### Mông (Glutes)
- "hip thrust" - Đẩy hông
- "glute bridge" - Cầu mông
- "glute workout" - Tập mông

### Vai (Shoulder)
- "shoulder press" - Đẩy vai
- "lateral raise" - Nâng vai ngang
- "front raise" - Nâng vai trước
- "rear delt fly" - Mở vai sau
- "shoulder workout" - Tập vai

### Bắp tay trước (Biceps)
- "bicep curl" - Cuốn tạ
- "hammer curl" - Cuốn búa
- "preacher curl" - Cuốn trên ghế
- "cable curl" - Cuốn cáp
- "bicep workout" - Tập bắp tay trước

### Bắp tay sau (Triceps)
- "tricep pushdown" - Đẩy cáp xuống
- "tricep dips" - Hít xà khuỷu tay
- "overhead extension" - Duỗi tay trên đầu
- "close grip press" - Đẩy tạ tay hẹp
- "tricep workout" - Tập bắp tay sau

### Bụng (Abs)
- "ab crunch" - Gập bụng
- "plank" - Plank
- "sit up" - Ngồi dậy
- "leg raise" - Nâng chân
- "ab workout" - Tập bụng

### Cẳng tay (Forearm)
- "forearm curl" - Cuốn cẳng tay
- "wrist curl" - Cuốn cổ tay

### Bắp chân (Calves)
- "calf raise" - Nâng bắp chân
- "calf workout" - Tập bắp chân

## 🌐 Nguồn ảnh khác (nếu Unsplash không có)

### Pexels
- URL: https://www.pexels.com/search/[tên-bài-tập]/
- Ví dụ: https://www.pexels.com/search/bench-press/

### Pixabay
- URL: https://pixabay.com/images/search/[tên-bài-tập]/
- Ví dụ: https://pixabay.com/images/search/squat/

## ⚡ Tự động hóa với hàm getImageUrlForExercise()

Hàm này đã được cải thiện để mapping chính xác hơn. Nó sẽ tự động tìm ảnh dựa trên tên bài tập:

```java
// Thay vì dùng URL cố định
String imageUrl = ExerciseImageUrls.getImageUrlForExercise("Bài tập ngực nằm đẩy tạ đòn");
// Sẽ trả về CHEST_BENCH_PRESS

String imageUrl2 = ExerciseImageUrls.getImageUrlForExercise("Bài tập chân ngồi xổm");
// Sẽ trả về LEGS_SQUAT
```

## 📝 Checklist cập nhật ảnh

- [ ] Tìm ảnh cho tất cả bài tập ngực
- [ ] Tìm ảnh cho tất cả bài tập lưng
- [ ] Tìm ảnh cho tất cả bài tập chân
- [ ] Tìm ảnh cho tất cả bài tập mông
- [ ] Tìm ảnh cho tất cả bài tập vai
- [ ] Tìm ảnh cho tất cả bài tập bắp tay trước
- [ ] Tìm ảnh cho tất cả bài tập bắp tay sau
- [ ] Tìm ảnh cho tất cả bài tập bụng
- [ ] Test ảnh hiển thị đúng trong app
- [ ] Kiểm tra ảnh load nhanh

## 🎯 Ví dụ cụ thể

### Ví dụ 1: Cập nhật ảnh Bench Press

1. Tìm trên Unsplash: https://unsplash.com/s/photos/bench-press
2. Chọn ảnh phù hợp
3. Copy URL: `https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixlib=rb-4.0.3&...`
4. Thêm resize: `?w=400&h=400&fit=crop&q=80`
5. URL cuối: `https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=400&h=400&fit=crop&q=80`
6. Cập nhật vào `ExerciseImageUrls.java`:
   ```java
   public static final String CHEST_BENCH_PRESS = "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=400&h=400&fit=crop&q=80";
   ```

### Ví dụ 2: Cập nhật ảnh Squat

1. Tìm trên Unsplash: https://unsplash.com/s/photos/squat
2. Chọn ảnh
3. Copy và resize URL
4. Cập nhật:
   ```java
   public static final String LEGS_SQUAT = "https://images.unsplash.com/photo-[ID]?w=400&h=400&fit=crop&q=80";
   ```

## 💡 Tips

1. **Chọn ảnh rõ ràng**: Ảnh nên thể hiện rõ động tác của bài tập
2. **Kích thước phù hợp**: Dùng `w=400&h=400&fit=crop` để resize
3. **Chất lượng**: Thêm `q=80` để có chất lượng tốt
4. **Test trước**: Mở URL trong browser để kiểm tra ảnh
5. **Lưu backup**: Lưu danh sách URL vào file riêng để dễ quản lý

## ✅ Kết quả mong đợi

Sau khi cập nhật:
- ✅ Mỗi bài tập có ảnh chính xác
- ✅ Ảnh load nhanh từ internet
- ✅ Ảnh được cache tự động
- ✅ App chuyên nghiệp hơn

