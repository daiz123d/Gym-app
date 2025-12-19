# 🗄️ HƯỚNG DẪN SETUP MYSQL CHO WORKOUT APP

## 📋 Mục lục
1. [Yêu cầu](#yêu-cầu)
2. [Cài đặt MySQL](#cài-đặt-mysql)
3. [Chạy Script SQL](#chạy-script-sql)
4. [Kết nối từ Android](#kết-nối-từ-android)
5. [Thay đổi code Android](#thay-đổi-code-android)

---

## ✅ Yêu cầu

- MySQL Server 5.7+ hoặc MariaDB 10.2+
- MySQL Workbench hoặc phpMyAdmin (tùy chọn)
- Android Studio
- Network connectivity (WiFi/LAN)

---

## 🔧 Cài đặt MySQL

### Trên Windows:
1. Tải MySQL từ: https://dev.mysql.com/downloads/mysql/
2. Cài đặt MySQL Server
3. Lưu root password

### Trên Linux:
```bash
sudo apt update
sudo apt install mysql-server
sudo mysql_secure_installation
```

### Trên macOS:
```bash
brew install mysql
brew services start mysql
```

---

## 📝 Chạy Script SQL

### Cách 1: Sử dụng MySQL Command Line

```bash
# Kết nối MySQL
mysql -u root -p

# Chạy script
source /path/to/mysql_database_schema.sql

# Hoặc
mysql -u root -p < mysql_database_schema.sql
```

### Cách 2: Sử dụng MySQL Workbench

1. Mở MySQL Workbench
2. Kết nối đến MySQL Server
3. File → Open SQL Script → Chọn `mysql_database_schema.sql`
4. Execute (⚡ icon hoặc Ctrl+Shift+Enter)

### Cách 3: Sử dụng phpMyAdmin

1. Mở phpMyAdmin trong browser
2. Chọn database (hoặc tạo mới)
3. Tab "SQL"
4. Copy nội dung file `mysql_database_schema.sql`
5. Execute

---

## 📱 Kết nối từ Android

### Bước 1: Thêm Dependencies

Mở `app/build.gradle` và thêm:

```gradle
dependencies {
    // ... các dependencies hiện có ...
    
    // MySQL Connector (không khuyến khích dùng trực tiếp)
    // implementation 'com.mysql:mysql-connector-j:8.0.33'
    
    // Thay vào đó, dùng REST API với Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.okhttp3:okhttp:4.11.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.11.0'
}
```

### Bước 2: Tạo REST API Backend (Khuyến khích)

**KHÔNG NÊN** kết nối trực tiếp MySQL từ Android vì:
- ❌ Security issues (expose database credentials)
- ❌ Performance issues
- ❌ Firewall/NAT problems
- ❌ MySQL connector quá nặng cho mobile

**NÊN** tạo REST API backend:
- ✅ Secure (credentials trên server)
- ✅ Better performance
- ✅ Scalable
- ✅ Easier to maintain

---

## 🔄 Thay đổi Code Android

### Option 1: REST API (Khuyến khích)

#### 1. Tạo API Interface

**File**: `app/src/main/java/com/uilover/project1932/Api/WorkoutApiService.java`

```java
package com.uilover.project1932.Api;

import com.uilover.project1932.Domain.Workout;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import java.util.List;

public interface WorkoutApiService {
    @GET("workouts")
    Call<List<Workout>> getAllWorkouts();
    
    @GET("workouts/{id}")
    Call<Workout> getWorkoutById(@Query("id") int id);
    
    @POST("favorites")
    Call<Void> addToFavorites(@Query("title") String title);
    
    @GET("history")
    Call<List<WorkoutHistory>> getHistory();
    
    @POST("history")
    Call<Void> addToHistory(@Body WorkoutHistory history);
}
```

#### 2. Tạo Retrofit Client

**File**: `app/src/main/java/com/uilover/project1932/Api/ApiClient.java`

```java
package com.uilover.project1932.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://YOUR_SERVER_IP:PORT/api/";
    private static Retrofit retrofit;
    
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retrofit;
    }
    
    public static WorkoutApiService getApiService() {
        return getRetrofitInstance().create(WorkoutApiService.class);
    }
}
```

#### 3. Update Repository

```java
public class WorkoutRepository {
    private WorkoutApiService apiService;
    
    public WorkoutRepository(Context context) {
        apiService = ApiClient.getApiService();
    }
    
    public void getAllWorkouts(Callback<List<Workout>> callback) {
        apiService.getAllWorkouts().enqueue(callback);
    }
}
```

### Option 2: Trực tiếp MySQL (Không khuyến khích)

Nếu vẫn muốn kết nối trực tiếp:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLHelper {
    private static final String DB_URL = "jdbc:mysql://YOUR_IP:3306/workout_db";
    private static final String USER = "your_username";
    private static final String PASS = "your_password";
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
```

**⚠️ Cảnh báo**: Cần chạy trên background thread, và có thể gặp vấn đề với mobile networks.

---

## 🌐 Tạo REST API Backend (PHP Example)

### File: `api/workouts.php`

```php
<?php
header('Content-Type: application/json');
require_once 'config.php';

$method = $_SERVER['REQUEST_METHOD'];

switch ($method) {
    case 'GET':
        $stmt = $pdo->query("SELECT * FROM workouts");
        $workouts = $stmt->fetchAll(PDO::FETCH_ASSOC);
        echo json_encode($workouts);
        break;
        
    case 'POST':
        $data = json_decode(file_get_contents('php://input'), true);
        $stmt = $pdo->prepare("INSERT INTO workouts (title, description, ...) VALUES (?, ?, ...)");
        $stmt->execute([$data['title'], $data['description'], ...]);
        echo json_encode(['success' => true]);
        break;
}
?>
```

### File: `api/config.php`

```php
<?php
$host = 'localhost';
$db = 'workout_db';
$user = 'your_username';
$pass = 'your_password';

try {
    $pdo = new PDO("mysql:host=$host;dbname=$db;charset=utf8mb4", $user, $pass);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch(PDOException $e) {
    die("Connection failed: " . $e->getMessage());
}
?>
```

---

## 🔒 Security Tips

1. **Đừng hardcode credentials** trong Android app
2. **Sử dụng HTTPS** cho API calls
3. **Implement authentication** (JWT tokens)
4. **Validate input** trên server side
5. **Use prepared statements** để tránh SQL injection
6. **Limit database user permissions**

---

## 📊 Kiểm tra kết nối

### Test MySQL từ command line:
```bash
mysql -u root -p -e "USE workout_db; SELECT COUNT(*) FROM workouts;"
```

### Test từ Android (nếu dùng REST API):
```java
Call<List<Workout>> call = apiService.getAllWorkouts();
call.enqueue(new Callback<List<Workout>>() {
    @Override
    public void onResponse(Call<List<Workout>> call, Response<List<Workout>> response) {
        if (response.isSuccessful()) {
            List<Workout> workouts = response.body();
            Log.d("API", "Loaded " + workouts.size() + " workouts");
        }
    }
    
    @Override
    public void onFailure(Call<List<Workout>> call, Throwable t) {
        Log.e("API", "Error: " + t.getMessage());
    }
});
```

---

## 🔄 Migration từ SQLite sang MySQL

Nếu đã có dữ liệu trong SQLite (Room Database):

1. Export dữ liệu từ SQLite
2. Convert format sang MySQL
3. Import vào MySQL
4. Update Android code để dùng REST API

---

## 📞 Troubleshooting

### Lỗi: "Connection refused"
- Kiểm tra MySQL đang chạy: `sudo systemctl status mysql`
- Kiểm tra firewall: `sudo ufw allow 3306`
- Kiểm tra bind-address trong `/etc/mysql/mysql.conf.d/mysqld.cnf`

### Lỗi: "Access denied"
- Kiểm tra username/password
- Tạo user mới với quyền phù hợp:
```sql
CREATE USER 'workout_user'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON workout_db.* TO 'workout_user'@'%';
FLUSH PRIVILEGES;
```

### Lỗi: "Too many connections"
- Tăng max_connections trong MySQL config
- Đóng các connection không dùng

---

## 📚 Tài liệu tham khảo

- MySQL Documentation: https://dev.mysql.com/doc/
- Retrofit: https://square.github.io/retrofit/
- Room to REST API Migration Guide

---

**Lưu ý quan trọng**: Nên sử dụng REST API thay vì kết nối trực tiếp MySQL từ Android app!

