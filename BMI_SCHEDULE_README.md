# Hướng Dẫn Sử Dụng Lịch Tập Theo BMI

## Tổng Quan

Chức năng **Lịch Tập Cố Định Theo BMI** giúp bạn nhận được lịch tập chuyên biệt dựa trên chỉ số BMI của mình. Ứng dụng hỗ trợ 3 loại người:

1. **Gầy (BMI < 18.5)** - Tập để tăng cơ và tăng cân
2. **Bình thường (18.5 - 24.9)** - Tập để duy trì sức khỏe
3. **Béo phì (BMI ≥ 25)** - Tập để giảm cân an toàn

---

## Các Thành Phần Được Tạo

### 1. **BMIScheduleData.java** (Data Model)
- Chứa 3 lịch tập tiêu chuẩn cho từng loại BMI
- Mỗi tuần có 7 ngày tập luyện cụ thể
- Mỗi ngày tập có:
  - Tên ngày
  - Danh sách bài tập chi tiết
  - Calories burned (khoảng bao nhiêu)
  - Thời lượng tập
  - Mô tả mục tiêu

**Dữ liệu lịch tập:**
- **Người Gầy**: Tập nặng 6 ngày, tập nhẹ 1 ngày (Chủ Nhật)
- **Người Bình Thường**: Cân bằng cardio + tập lực 6 ngày
- **Người Béo Phì**: Tập nhẹ, cardio, yoga - không quá sức

### 2. **BMIScheduleAdapter.java** (RecyclerView Adapter)
- Hiển thị lịch tập dưới dạng danh sách
- Hỗ trợ expand/collapse chi tiết bài tập
- Màu sắc thay đổi theo loại BMI:
  - Gầy = Xanh lá
  - Bình thường = Xanh dương
  - Béo phì = Đỏ

### 3. **BMIScheduleActivity.java** (Main Activity)
- Hiển thị lịch tập đầy đủ
- Cho phép chuyển đổi giữa các loại BMI
- Hiển thị description và tips cho từng loại

**Các tính năng:**
- 📊 Hiển thị category BMI hiện tại
- 🎯 Mục tiêu tập luyện cụ thể
- 💡 Lời khuyên theo loại BMI
- 🔄 Cho phép chuyển đổi loại BMI
- 📝 Chi tiết bài tập từng ngày

### 4. **Layout Files**
- `activity_bmi_schedule.xml` - Layout chính cho BMIScheduleActivity
- `item_bmi_schedule.xml` - Layout cho từng item lịch tập

### 5. **LocalDataManager Updates**
- Thêm method `setBmiCategory()` - Lưu loại BMI
- Thêm method `getBmiCategory()` - Lấy loại BMI từ SharedPreferences

---

## Cách Sử Dụng

### 1. **Từ MealPlanActivity**
```java
// Bước 1: Tính toán BMI
// Nhập cân nặng (kg) và chiều cao (cm)
// Ứng dụng sẽ tự động tính BMI và phân loại

// Bước 2: Lịch tập sẽ được lưu tự động
// Loại BMI sẽ được lưu trong SharedPreferences

// Bước 3: Mở Lịch Tập
// Nhấn nút "Schedule" hoặc "Lịch Tập" 
// -> Mở BMIScheduleActivity
```

### 2. **Các Loại Lịch Tập**

#### 👤 Người Gầy (BMI < 18.5)
**Mục tiêu:** Tăng cơ và tăng cân
- **Thứ 2**: Tay Trước + Ngực (800 kcal)
- **Thứ 3**: Lưng + Cáp (750 kcal)
- **Thứ 4**: Yoga nhẹ (200 kcal) - Ngày nghỉ
- **Thứ 5**: Chân + Mông (900 kcal)
- **Thứ 6**: Vai + Cáp Sau (800 kcal)
- **Thứ 7**: Tay Sau + Cardio (750 kcal)
- **CN**: Yoga + Thư giãn (150 kcal)

**Lời khuyên:**
- Tăng cường tập lực lượng
- Ăn nhiều protein và carbs
- Tăng calories hàng ngày

#### 💪 Người Bình Thường (18.5 - 24.9)
**Mục tiêu:** Duy trì sức khỏe tốt
- **Thứ 2**: Upper Body (600 kcal)
- **Thứ 3**: Lower Body (700 kcal)
- **Thứ 4**: Cardio + Core (500 kcal)
- **Thứ 5**: Full Body (650 kcal)
- **Thứ 6**: Các cơ nhỏ (550 kcal)
- **Thứ 7**: Cardio nhẹ + Yoga (300 kcal)
- **CN**: Stretching (100 kcal)

**Lời khuyên:**
- Kết hợp cardio và tập lực
- Ăn cân bằng các chất
- Tập 5-6 ngày mỗi tuần

#### 🏋️ Người Béo Phì (BMI ≥ 25)
**Mục tiêu:** Giảm cân an toàn
- **Thứ 2**: Đi bộ + Core nhẹ (350 kcal)
- **Thứ 3**: Chân nhẹ (400 kcal)
- **Thứ 4**: Yoga + Kéo giãn (200 kcal)
- **Thứ 5**: Cardio vừa (400 kcal)
- **Thứ 6**: Upper Body nhẹ (350 kcal)
- **Thứ 7**: Đi bộ + Yoga (250 kcal)
- **CN**: Stretching + Nghỉ (50 kcal)

**Lời khuyên:**
- Tập cardio thường xuyên
- Giảm lượng calories
- Tập nhẹ nhàng, tăng dần

---

## Tích Hợp Với Ứng Dụng

### Điều kiện tiên quyết:
1. ✅ `BMIScheduleData.java` - Model dữ liệu
2. ✅ `BMIScheduleAdapter.java` - Adapter hiển thị
3. ✅ `BMIScheduleActivity.java` - Activity chính
4. ✅ `activity_bmi_schedule.xml` - Layout Activity
5. ✅ `item_bmi_schedule.xml` - Layout Item
6. ✅ `LocalDataManager` - Cập nhật BMI storage
7. ✅ `MealPlanActivity` - Thêm nút mở Schedule
8. ✅ `AndroidManifest.xml` - Đăng ký Activity

### Cách mở Lịch Tập:

**Từ code:**
```java
// Cách 1: Từ MealPlanActivity
openBmiSchedule(); // Tự động lấy category từ SharedPreferences

// Cách 2: Tạo intent trực tiếp
Intent intent = new Intent(context, BMIScheduleActivity.class);
startActivity(intent);
```

**Từ giao diện:**
1. Mở ứng dụng
2. Vào Meal Plan / Dinh Dưỡng
3. Nhấn nút "Lịch Tập" hoặc "Schedule"
4. Chọn loại BMI từ Spinner
5. Xem chi tiết bài tập từng ngày

---

## Dữ Liệu Lưu Trữ

### SharedPreferences Keys:
- `bmi_category` - Loại BMI được chọn
- `daily_calorie_goal` - Mục tiêu calories hàng ngày

**Dữ liệu lưu trữ indefinitely** cho đến khi người dùng thay đổi.

---

## Customization

### Để thêm bài tập mới:

**File:** `BMIScheduleData.java`

```java
public static ArrayList<DailySchedule> getUnderWeightSchedule() {
    ArrayList<DailySchedule> schedule = new ArrayList<>();
    
    // Thêm ngày mới
    schedule.add(new DailySchedule(
        "Thứ Hai",                           // Tên ngày
        new String[]{                        // Danh sách bài tập
            "Tập Tay Trước 30 phút",
            "Tập Ngực 30 phút"
        },
        800,                                 // Calories
        "1h 15 phút",                       // Duration
        "Tăng cơ"                           // Description
    ));
    
    return schedule;
}
```

### Để thay đổi màu sắc:

**File:** `BMIScheduleAdapter.java`

```java
private void setColorByCategory(ViewHolder holder, int position) {
    int color;
    if (BMIScheduleData.BMI_UNDER_WEIGHT.equalsIgnoreCase(bmiCategory)) {
        color = ContextCompat.getColor(context, android.R.color.holo_green_dark);
        // Thay đổi màu ở đây
    }
}
```

---

## Kiểm Thử

### Test Cases:

1. ✅ Tính BMI đúng
   - Input: 60 kg, 175 cm → BMI 19.6 (Bình thường)
   - Input: 45 kg, 160 cm → BMI 17.6 (Gầy)
   - Input: 80 kg, 165 cm → BMI 29.4 (Béo phì)

2. ✅ Lưu category đúng
   - Lưu category vào SharedPreferences
   - Truy xuất lại được category cũ

3. ✅ Hiển thị lịch tập
   - Mỗi category hiển thị 7 ngày
   - Expand/collapse hoạt động
   - Màu sắc đúng theo category

4. ✅ Chuyển đổi category
   - Spinner cho phép chọn category
   - Description cập nhật ngay
   - Adapter cập nhật lịch tập

---

## Troubleshooting

### Problem: Lịch tập không hiển thị
**Solution:**
1. Kiểm tra `BMIScheduleData.java` có được import đúng
2. Kiểm tra `BMIScheduleAdapter.java` kế thừa RecyclerView.Adapter
3. Kiểm tra layout `item_bmi_schedule.xml` tồn tại

### Problem: Category không lưu được
**Solution:**
1. Kiểm tra `LocalDataManager.getInstance()` được gọi
2. Kiểm tra `setBmiCategory()` được gọi sau khi tính BMI
3. Kiểm tra SharedPreferences permission

### Problem: Màu sắc không đổi
**Solution:**
1. Kiểm tra `setColorByCategory()` được gọi
2. Kiểm tra resource color tồn tại
3. Kiểm tra ContextCompat.getColor() được sử dụng

---

## Tương Lai

### Có thể thêm:
- 📊 Biểu đồ tiến độ tập luyện
- 🔔 Thông báo nhắc tập
- 📱 Ngoài lịch tập có lịch ăn theo BMI
- 🎯 Theo dõi hoàn thành bài tập
- 📈 Ghi chép kết quả tập luyện
- 👥 Chia sẻ lịch tập

---

## Liên Hệ & Support

Nếu có vấn đề, vui lòng kiểm tra:
1. Import đầy đủ
2. Layout files tồn tại
3. AndroidManifest.xml có đăng ký Activity
4. SharedPreferences có write permission

Chúc bạn tập luyện vui vẻ! 💪
