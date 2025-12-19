# 🔧 HƯỚNG DẪN SỬA LỖI KẾT NỐI API

## ⚠️ LỖI PHỔ BIẾN

### 1. Lỗi: "Lỗi tải thống kê" / "Lỗi tải yêu thích"

**Nguyên nhân:**
- Backend chưa chạy
- IP trong `ApiClient.java` sai
- Phone/Emulator và máy tính không cùng mạng

---

## ✅ CÁCH SỬA

### Bước 1: Kiểm tra Backend đang chạy

Mở browser và test:
```
http://localhost:8080/api/workouts
```

Nếu thấy JSON data → Backend đang chạy ✅
Nếu không thấy → Backend chưa chạy ❌

**Chạy backend:**
```bash
cd backend
mvn spring-boot:run
```

---

### Bước 2: Tìm IP máy tính

**Windows:**
```cmd
ipconfig
```
Tìm **IPv4 Address**, ví dụ: `192.168.1.100`

**Mac/Linux:**
```bash
ifconfig
# hoặc
ip addr show
```

---

### Bước 3: Cấu hình IP trong Android

Mở file: `app/src/main/java/com/uilover/project1932/Api/ApiClient.java`

#### Nếu dùng Android Emulator:
```java
private static final String BASE_URL = "http://10.0.2.2:8080/api/";
```
(`10.0.2.2` = localhost cho Android Emulator)

#### Nếu dùng Phone thật:
```java
private static final String BASE_URL = "http://YOUR_IP:8080/api/";
```
Ví dụ: `"http://192.168.1.100:8080/api/"`

**⚠️ QUAN TRỌNG:** Đảm bảo phone và máy tính cùng WiFi/LAN!

---

## 🔍 KIỂM TRA LOG

Mở **Logcat** trong Android Studio:

1. Filter: `MainActivity`, `ProfileActivity`, `FavoritesActivity`, `ApiWorkoutRepository`
2. Xem các log có dạng:
   - `Error loading statistics: ...`
   - `Error loading favorites: ...`
   - `Network error: ...`

---

## 🐛 CÁC LỖI THƯỜNG GẶP

### Lỗi 1: "Unable to resolve host"
**Nguyên nhân:** IP sai hoặc không có kết nối mạng

**Giải pháp:**
- ✅ Kiểm tra IP đúng chưa
- ✅ Phone và máy tính cùng mạng chưa?
- ✅ Tắt VPN nếu có

### Lỗi 2: "Connection refused" / "Failed to connect"
**Nguyên nhân:** Backend chưa chạy hoặc firewall block

**Giải pháp:**
- ✅ Chạy backend: `mvn spring-boot:run`
- ✅ Test bằng browser: `http://localhost:8080/api/workouts`
- ✅ Tắt firewall tạm thời để test

### Lỗi 3: "HTTP 404" hoặc "HTTP 500"
**Nguyên nhân:** Endpoint sai hoặc backend có lỗi

**Giải pháp:**
- ✅ Kiểm tra backend logs
- ✅ Test endpoint bằng Postman/curl
- ✅ Kiểm tra database đã có dữ liệu chưa

---

## 🧪 TEST TỪNG BƯỚC

### Test 1: Backend API
```bash
curl http://localhost:8080/api/workouts
curl http://localhost:8080/api/favorites
curl http://localhost:8080/api/history/stats
```

### Test 2: Từ Android Emulator
```bash
adb shell
curl http://10.0.2.2:8080/api/workouts
```

### Test 3: Từ Phone (qua browser)
Mở browser trên phone:
```
http://YOUR_IP:8080/api/workouts
```

---

## 📝 CHECKLIST

- [ ] Backend đang chạy (port 8080)
- [ ] Database có dữ liệu (chạy `mysql_database_schema.sql`)
- [ ] IP trong `ApiClient.java` đúng
- [ ] Phone/Emulator và máy tính cùng mạng
- [ ] Firewall không block port 8080
- [ ] Test API bằng curl/browser thành công

---

## 🔄 SAU KHI SỬA

1. **Sync Gradle** trong Android Studio
2. **Rebuild Project**
3. **Uninstall app cũ** (nếu cần)
4. **Install và chạy lại**

---

## 💡 TIP

Nếu vẫn lỗi, kiểm tra Logcat để xem error message chi tiết. Code đã được cập nhật để hiển thị error message rõ ràng hơn.

