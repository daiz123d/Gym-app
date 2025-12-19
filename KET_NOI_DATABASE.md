# 📍 NƠI KẾT NỐI DATABASE

## 🔗 Luồng kết nối Database

```
WorkoutApp (Application)
    ↓
WorkoutRepository (Constructor)
    ↓
WorkoutDatabase.getInstance()
    ↓
Room.databaseBuilder() ← KẾT NỐI DATABASE Ở ĐÂY!
    ↓
Database được tạo và lưu trong instance
```

---

## 🎯 Các điểm kết nối Database

### 1. **WorkoutDatabase.java** - Nơi KẾT NỐI database
**File**: `app/src/main/java/com/uilover/project1932/Database/WorkoutDatabase.java`

```java
// DÒNG 29-38: ĐÂY LÀ NƠI KẾT NỐI DATABASE!
public static synchronized WorkoutDatabase getInstance(Context context) {
    if (instance == null) {
        instance = Room.databaseBuilder(context.getApplicationContext(),
                        WorkoutDatabase.class, "workout_database")
                .allowMainThreadQueries() 
                .fallbackToDestructiveMigration()
                .build();
    }
    return instance;
}
```

**Giải thích**:
- `Room.databaseBuilder()`: Tạo và kết nối database
- `"workout_database"`: Tên file database (sẽ được lưu trong `/data/data/[package]/databases/workout_database`)
- `instance`: Singleton pattern - chỉ tạo 1 lần, dùng lại nhiều lần

---

### 2. **WorkoutRepository.java** - Sử dụng database
**File**: `app/src/main/java/com/uilover/project1932/Repository/WorkoutRepository.java`

```java
// DÒNG 25-31: Lấy kết nối database và các DAO
public WorkoutRepository(Context context) {
    WorkoutDatabase database = WorkoutDatabase.getInstance(context); // ← KẾT NỐI Ở ĐÂY
    workoutDao = database.workoutDao();
    favoriteWorkoutDao = database.favoriteWorkoutDao();
    workoutHistoryDao = database.workoutHistoryDao();
    scheduledWorkoutDao = database.scheduledWorkoutDao();
}
```

**Giải thích**:
- Constructor nhận `Context` để truyền vào `getInstance()`
- Lấy các DAO từ database để thao tác với từng bảng

---

### 3. **WorkoutApp.java** - Khởi tạo repository (và database)
**File**: `app/src/main/java/com/uilover/project1932/WorkoutApp.java`

```java
// DÒNG 22: Tạo repository → Tự động kết nối database
@Override
public void onCreate() {
    super.onCreate();
    repository = new WorkoutRepository(this); // ← Database được kết nối ở đây
    initializeData();
}
```

**Giải thích**:
- `WorkoutApp` chạy khi app khởi động
- Tạo `WorkoutRepository` → Gọi `WorkoutDatabase.getInstance()` → Database được kết nối

---

## 📂 Cấu trúc Database

### WorkoutDatabase.java
```java
@Database(
    entities = {
        WorkoutEntity.class,      // Bảng workouts
        FavoriteWorkout.class,    // Bảng favorite_workouts
        WorkoutHistory.class,      // Bảng workout_history
        ScheduledWorkout.class     // Bảng scheduled_workouts
    }, 
    version = 2
)
public abstract class WorkoutDatabase extends RoomDatabase {
    // Các abstract methods để lấy DAO
    public abstract WorkoutDao workoutDao();
    public abstract FavoriteWorkoutDao favoriteWorkoutDao();
    public abstract WorkoutHistoryDao workoutHistoryDao();
    public abstract ScheduledWorkoutDao scheduledWorkoutDao();
}
```

---

## 🔄 Luồng hoạt động chi tiết

### Khi app khởi động:

1. **Android System** → Gọi `WorkoutApp.onCreate()`

2. **WorkoutApp.onCreate()** 
   ```java
   repository = new WorkoutRepository(this);
   ```
   - Tạo mới `WorkoutRepository`

3. **WorkoutRepository Constructor**
   ```java
   WorkoutDatabase database = WorkoutDatabase.getInstance(context);
   ```
   - Gọi `getInstance()` để lấy database

4. **WorkoutDatabase.getInstance()**
   ```java
   if (instance == null) {
       instance = Room.databaseBuilder(...).build(); // ← KẾT NỐI DATABASE
   }
   ```
   - **Lần đầu**: Tạo database mới
   - **Lần sau**: Trả về instance đã có (Singleton)

5. **Database sẵn sàng!**
   - Có thể dùng các DAO để thao tác với database

---

## 📍 Tóm tắt: Kết nối database ở đâu?

### ✅ **ĐIỂM KẾT NỐI CHÍNH**: 
**`WorkoutDatabase.java` - Dòng 31**
```java
instance = Room.databaseBuilder(context.getApplicationContext(),
                WorkoutDatabase.class, "workout_database")
        .build();
```

### 🔄 **LUỒNG GỌI**:
```
WorkoutApp.onCreate()
    ↓
WorkoutRepository(context)
    ↓
WorkoutDatabase.getInstance(context) ← ĐÂY!
    ↓
Room.databaseBuilder() ← TẠO KẾT NỐI
```

---

## 📝 Thông tin Database

- **Tên database**: `workout_database`
- **Vị trí lưu**: `/data/data/com.uilover.project1932/databases/workout_database`
- **Loại**: SQLite (Room Database)
- **Version**: 2
- **Pattern**: Singleton (chỉ tạo 1 instance)

---

## 🔧 Các DAO được kết nối

Khi database được kết nối, các DAO sau được tạo:

1. **WorkoutDao** - Thao tác với bảng `workouts`
2. **FavoriteWorkoutDao** - Thao tác với bảng `favorite_workouts`
3. **WorkoutHistoryDao** - Thao tác với bảng `workout_history`
4. **ScheduledWorkoutDao** - Thao tác với bảng `scheduled_workouts`

---

## ⚠️ Lưu ý

1. **Singleton Pattern**: Database chỉ được tạo 1 lần, dùng lại nhiều lần
2. **Thread Safety**: Method `getInstance()` có `synchronized` để đảm bảo an toàn đa luồng
3. **Context**: Phải dùng `context.getApplicationContext()` để tránh memory leak
4. **allowMainThreadQueries()**: Cho phép query trên main thread (chỉ để đơn giản, nên tránh trong production)

---

## 🚀 Cách kiểm tra Database đã kết nối

### 1. Xem Logcat
```
Filter: WorkoutApp
→ Sẽ thấy: "Đã khởi tạo dữ liệu vào database: X bài tập"
```

### 2. Sử dụng Database Inspector
- Android Studio → View → Tool Windows → App Inspection
- Chọn device và app
- Xem database `workout_database`

### 3. Code kiểm tra
```java
// Trong MainActivity hoặc bất kỳ Activity nào
WorkoutRepository repo = WorkoutApp.getRepository();
List<WorkoutEntity> workouts = repo.getAllWorkouts();
Log.d("DB_TEST", "Số bài tập trong DB: " + workouts.size());
```

---

**Tóm lại**: Database được kết nối ở **`WorkoutDatabase.getInstance()`** trong file **`WorkoutDatabase.java`**, được gọi lần đầu từ **`WorkoutRepository` constructor**, khởi tạo trong **`WorkoutApp.onCreate()`** khi app khởi động.

