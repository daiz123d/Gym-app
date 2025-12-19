# Hướng dẫn sử dụng ảnh từ URL trong app

App đã được cập nhật để hỗ trợ load ảnh từ URL (http/https) hoặc từ drawable resources.

## Cách sử dụng

### 1. Trong Adapter (đã được cập nhật tự động)

Tất cả các adapter đã được cập nhật để tự động hỗ trợ URL:
- `HandbookListAdapter`
- `ExerciseListAdapter`
- `DraggableWorkoutAdapter`
- `WorkoutAdapter`
- `MealAdapter`

### 2. Trong Activity

Sử dụng `ImageLoaderHelper`:

```java
import com.uilover.project1932.Helper.ImageLoaderHelper;

// Load ảnh từ URL hoặc drawable
ImageLoaderHelper.loadImage(context, imageSource, imageView);

// Hoặc với default image
ImageLoaderHelper.loadImage(context, imageSource, imageView, R.drawable.default_image);
```

### 3. Cách thêm ảnh từ URL vào data

#### Ví dụ với HandbookItem:

```java
// Thay vì dùng drawable resource name
new HandbookItem("Ngực", "pic_1", "Mô tả", "Nội dung")

// Bạn có thể dùng URL
new HandbookItem("Ngực", "https://example.com/images/chest.jpg", "Mô tả", "Nội dung")
```

#### Ví dụ với ExerciseItem:

```java
// Thay vì
new ExerciseItem("Push Up", "pic_1", "Mô tả", "Hướng dẫn", 30)

// Dùng URL
new ExerciseItem("Push Up", "https://example.com/images/pushup.jpg", "Mô tả", "Hướng dẫn", 30)
```

#### Ví dụ với Workout:

```java
// Trong Workout object, set picPath thành URL
workout.setPicPath("https://example.com/images/workout.jpg");
```

### 4. Lấy link ảnh từ browser

1. **Chrome/Edge:**
   - Click chuột phải vào ảnh
   - Chọn "Copy image address" hoặc "Copy image link"
   - Dán URL vào code

2. **Firefox:**
   - Click chuột phải vào ảnh
   - Chọn "Copy Image Location"
   - Dán URL vào code

3. **Safari:**
   - Click chuột phải vào ảnh
   - Chọn "Copy Image Address"
   - Dán URL vào code

### 5. Ví dụ thực tế

#### Cập nhật ExercisesActivity để dùng ảnh từ URL:

```java
private void loadExerciseItems() {
    allItems = new ArrayList<>();
    
    // Dùng URL thay vì drawable
    allItems.add(new HandbookItem(
        "Ngực", 
        "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=400", 
        "Các bài tập ngực", 
        ""
    ));
    
    allItems.add(new HandbookItem(
        "Lưng", 
        "https://images.unsplash.com/photo-1517836357463-d25dfeac3438?w=400", 
        "Các bài tập lưng", 
        ""
    ));
    
    // ... các nhóm cơ khác
}
```

#### Cập nhật ExerciseData để dùng ảnh từ URL:

```java
public static ArrayList<ExerciseItem> getChestExercises() {
    ArrayList<ExerciseItem> exercises = new ArrayList<>();
    
    exercises.add(new ExerciseItem(
        "Bench Press",
        "https://example.com/images/bench-press.jpg",  // URL thay vì "pic_1"
        "Đẩy ngực với tạ đòn",
        "Nằm trên ghế, đẩy tạ lên xuống",
        30
    ));
    
    return exercises;
}
```

### 6. Lưu ý

1. **HTTPS được khuyến nghị:** Sử dụng HTTPS thay vì HTTP để bảo mật
2. **Network Security Config:** App đã được cấu hình để cho phép HTTP (cho development)
3. **Cache:** Glide tự động cache ảnh để tăng hiệu suất
4. **Placeholder:** Nếu ảnh không load được, sẽ hiển thị placeholder mặc định
5. **Fallback:** Nếu URL không hợp lệ, app sẽ tự động fallback về drawable resource nếu có

### 7. Kiểm tra URL ảnh

Để kiểm tra URL ảnh có hợp lệ không:
1. Mở URL trong browser
2. Nếu ảnh hiển thị được, URL hợp lệ
3. Nếu không, kiểm tra lại URL hoặc dùng URL khác

### 8. Tối ưu hiệu suất

- Glide tự động cache ảnh
- Ảnh được load bất đồng bộ, không block UI
- Có thể thêm placeholder và error image

### 9. Ví dụ với nhiều nguồn ảnh

Bạn có thể mix cả URL và drawable:

```java
// Một số dùng URL
allItems.add(new HandbookItem("Ngực", "https://example.com/chest.jpg", "...", "..."));

// Một số dùng drawable
allItems.add(new HandbookItem("Lưng", "pic_2", "...", "..."));

// ImageLoaderHelper sẽ tự động xử lý cả hai
```

## Tóm tắt

✅ App đã hỗ trợ load ảnh từ URL  
✅ Chỉ cần thay `imageName` hoặc `picPath` thành URL  
✅ Tất cả adapter đã được cập nhật  
✅ Không cần thay đổi code logic, chỉ cần thay URL

