# ⚡ QUICK START - Lịch Tập Theo BMI

## 5 Phút Để Chạy Chức Năng

### ✅ Step 1: Kiểm tra Files (1 phút)

```bash
# Verify tất cả files đã tạo
ls app/src/main/java/com/uilover/project1932/Data/BMIScheduleData.java
ls app/src/main/java/com/uilover/project1932/Adapter/BMIScheduleAdapter.java
ls app/src/main/java/com/uilover/project1932/Activity/BMIScheduleActivity.java
ls app/src/main/res/layout/activity_bmi_schedule.xml
ls app/src/main/res/layout/item_bmi_schedule.xml
```

### ✅ Step 2: Build Project (2 phút)

```bash
# Terminal / PowerShell
cd c:\Users\84846\Downloads\193_2UT67RyDces
./gradlew clean build

# Hoặc trong Android Studio: Build > Make Project
```

### ✅ Step 3: Run on Emulator (1 phút)

```bash
./gradlew installDebug
adb shell am start -n com.uilover.project1932/.Activity.MainActivity
```

### ✅ Step 4: Test Feature (1 phút)

```
1. App mở → MainActivity
2. Bottom navigation → Vào Meal Plan (hoặc tương tự)
3. Nhấn nút BMI / Input height-weight
4. Nhập: 60 kg, 175 cm
5. Nhấn "Apply" / "Áp dụng"
6. Xem Toast message (Bình thường category)
7. Nhấn nút "Schedule" / "Lịch Tập"
8. ✅ BMIScheduleActivity mở với 7 ngày lịch
9. Xem lịch tập chi tiết
10. Thay đổi category từ Spinner → Update ngay
```

---

## 📝 Các Bước Chi Tiết

### Test Người Gầy (BMI < 18.5)

```
Input: 50 kg, 170 cm = BMI 17.3 ✓
Expected: "Gầy" category
Lịch: 6 ngày tập nặng + 1 ngày yoga
Màu: Xanh lá (Green)
Tip: Tăng cơ, tăng cân
```

### Test Người Bình Thường (18.5 - 24.9)

```
Input: 60 kg, 175 cm = BMI 19.6 ✓
Expected: "Bình thường" category
Lịch: Cân bằng cardio + strength
Màu: Xanh dương (Blue)
Tip: Duy trì sức khỏe
```

### Test Người Béo Phì (BMI ≥ 25)

```
Input: 80 kg, 165 cm = BMI 29.4 ✓
Expected: "Béo phì" category
Lịch: Tập nhẹ + cardio
Màu: Đỏ (Red)
Tip: Giảm cân an toàn
```

---

## 🎮 UI Interactions

### 1. Mở Schedule Activity
```
MealPlanActivity
    └─> [Schedule Button]
        └─> BMIScheduleActivity
```

### 2. Spinner Selection
```
Spinner
    ├─> "Gầy" → Green, Underweight schedule
    ├─> "Bình thường" → Blue, Normal schedule
    └─> "Béo phì" → Red, Obese schedule
```

### 3. Expand/Collapse
```
Card (maxLines = 2)
    └─> [Click]
        └─> Card (maxLines = MAX_VALUE)
            └─> [Click]
                └─> Card (maxLines = 2)
```

---

## 🧪 Test Checklist

- [ ] App build thành công
- [ ] No compile errors
- [ ] BMIScheduleActivity opens
- [ ] 7 days displayed
- [ ] Spinner works
- [ ] Colors change correctly
- [ ] Expand/collapse works
- [ ] Description updates
- [ ] No crashes

---

## 💾 Verify SharedPreferences

```bash
# Check saved data
adb shell
cd /data/data/com.uilover.project1932
cat shared_prefs/WorkoutPrefs.xml | grep bmi_category

# Should output:
# <string name="bmi_category">Bình thường</string>
```

---

## 🐛 Troubleshooting 2-Min Edition

| Problem | Solution |
|---------|----------|
| **Layout not found** | Verify `item_bmi_schedule.xml` exists |
| **Activity not found** | Check AndroidManifest.xml has registration |
| **RecyclerView empty** | Verify adapter initialized in onCreate |
| **Button not working** | Check `binding.scheduleBtn` exists in layout |
| **Colors not showing** | Verify `setColorByCategory()` called in adapter |
| **Spinner not loading** | Verify ArrayAdapter created with 3 items |
| **Category not saving** | Verify `setBmiCategory()` called in MealPlanActivity |

---

## 📦 Minimal Test (Copy-Paste Ready)

```java
// Test in MainActivity or any Activity
LocalDataManager dataManager = LocalDataManager.getInstance(this);
dataManager.setBmiCategory("Bình thường");

// Verify saved
String saved = dataManager.getBmiCategory();
Toast.makeText(this, "Saved: " + saved, Toast.LENGTH_SHORT).show();

// Open activity
Intent intent = new Intent(this, BMIScheduleActivity.class);
startActivity(intent);
```

---

## ⏱️ Time Estimates

- Build: ~30 seconds (incremental)
- Install: ~10 seconds
- Test flow: ~2 minutes
- **Total: ~3 minutes**

---

## ✅ Success Indicators

✅ Build completes without errors
✅ App launches without crash
✅ BMI calculation works
✅ Category saved correctly
✅ Schedule Activity opens
✅ 7 days displayed
✅ Spinner updates schedule
✅ Colors change
✅ Expand/collapse works
✅ All 3 categories work

---

## 🎯 Next (If Everything Works)

1. **Customize:** Thêm bài tập của bạn
2. **Deploy:** Chuẩn bị release
3. **Share:** Chia sẻ đến users
4. **Track:** Monitor usage

---

## 📞 Emergency Help

**If stuck:**
1. Check `BMI_SCHEDULE_README.md`
2. Check `BMI_SCHEDULE_CHECKLIST.md`
3. Verify all 5 Java/Layout files exist
4. Check AndroidManifest registration
5. Look for compile errors
6. Check logcat for runtime errors

---

## 🚀 Ready?

```bash
./gradlew installDebug
adb shell am start -n com.uilover.project1932/.Activity.MainActivity
```

**That's it! Happy testing! 💪**
