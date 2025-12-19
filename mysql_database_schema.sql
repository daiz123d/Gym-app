-- =====================================================
-- MYSQL DATABASE SCHEMA CHO WORKOUT APP
-- =====================================================
-- Database: workout_db
-- Version: 1.0
-- =====================================================

-- Tạo database
CREATE DATABASE IF NOT EXISTS workout_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE workout_db;

-- =====================================================
-- BẢNG 1: workouts (Bài tập)
-- =====================================================
CREATE TABLE IF NOT EXISTS workouts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL UNIQUE COMMENT 'Tên bài tập',
    description TEXT COMMENT 'Mô tả bài tập',
    pic_path VARCHAR(255) COMMENT 'Đường dẫn ảnh',
    kcal INT DEFAULT 0 COMMENT 'Calo đốt cháy',
    duration_all VARCHAR(50) COMMENT 'Tổng thời gian (VD: "9 phút")',
    lessions JSON COMMENT 'Danh sách bài học (JSON)',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày tạo',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Ngày cập nhật',
    INDEX idx_title (title)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Bảng lưu thông tin bài tập';

-- =====================================================
-- BẢNG 2: favorite_workouts (Bài tập yêu thích)
-- =====================================================
CREATE TABLE IF NOT EXISTS favorite_workouts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    workout_title VARCHAR(255) NOT NULL COMMENT 'Tên bài tập',
    added_date BIGINT NOT NULL COMMENT 'Ngày thêm (timestamp)',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày tạo bản ghi',
    FOREIGN KEY (workout_title) REFERENCES workouts(title) ON DELETE CASCADE,
    UNIQUE KEY unique_favorite (workout_title),
    INDEX idx_added_date (added_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Bảng lưu bài tập yêu thích';

-- =====================================================
-- BẢNG 3: workout_history (Lịch sử tập luyện)
-- =====================================================
CREATE TABLE IF NOT EXISTS workout_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    workout_title VARCHAR(255) NOT NULL COMMENT 'Tên bài tập',
    completion_date BIGINT NOT NULL COMMENT 'Ngày hoàn thành (timestamp)',
    duration VARCHAR(50) COMMENT 'Thời gian tập (VD: "9 phút")',
    kcal_burned INT DEFAULT 0 COMMENT 'Calo đã đốt',
    completed_lessons INT DEFAULT 0 COMMENT 'Số bài học đã hoàn thành',
    total_lessons INT DEFAULT 0 COMMENT 'Tổng số bài học',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày tạo bản ghi',
    INDEX idx_workout_title (workout_title),
    INDEX idx_completion_date (completion_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Bảng lưu lịch sử tập luyện';

-- =====================================================
-- BẢNG 4: scheduled_workouts (Lịch tập đã lên kế hoạch)
-- =====================================================
CREATE TABLE IF NOT EXISTS scheduled_workouts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    workout_title VARCHAR(255) NOT NULL COMMENT 'Tên bài tập',
    scheduled_date BIGINT NOT NULL COMMENT 'Ngày lên lịch (timestamp)',
    scheduled_time VARCHAR(10) COMMENT 'Giờ tập (VD: "08:00")',
    is_completed BOOLEAN DEFAULT FALSE COMMENT 'Đã hoàn thành chưa',
    created_date BIGINT NOT NULL COMMENT 'Ngày tạo lịch (timestamp)',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày tạo bản ghi',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Ngày cập nhật',
    INDEX idx_workout_title (workout_title),
    INDEX idx_scheduled_date (scheduled_date),
    INDEX idx_is_completed (is_completed)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Bảng lưu lịch tập đã lên kế hoạch';

-- =====================================================
-- BẢNG 5: lessons (Bài học - Normalized - Tùy chọn)
-- =====================================================
-- Nếu muốn normalize database, có thể tách lessons ra bảng riêng
CREATE TABLE IF NOT EXISTS lessons (
    id INT AUTO_INCREMENT PRIMARY KEY,
    workout_id INT NOT NULL COMMENT 'ID bài tập',
    title VARCHAR(255) NOT NULL COMMENT 'Tên bài học',
    duration VARCHAR(20) COMMENT 'Thời lượng (VD: "03:46")',
    link VARCHAR(255) COMMENT 'Link YouTube Video ID',
    pic_path VARCHAR(255) COMMENT 'Đường dẫn ảnh',
    order_index INT DEFAULT 0 COMMENT 'Thứ tự bài học',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày tạo',
    FOREIGN KEY (workout_id) REFERENCES workouts(id) ON DELETE CASCADE,
    INDEX idx_workout_id (workout_id),
    INDEX idx_order_index (order_index)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Bảng lưu bài học (normalized)';

-- =====================================================
-- DỮ LIỆU MẪU
-- =====================================================

-- Xóa dữ liệu cũ (nếu có)
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE scheduled_workouts;
TRUNCATE TABLE workout_history;
TRUNCATE TABLE favorite_workouts;
TRUNCATE TABLE lessons;
TRUNCATE TABLE workouts;
SET FOREIGN_KEY_CHECKS = 1;

-- Thêm bài tập mẫu 1: Chạy bộ
INSERT INTO workouts (title, description, pic_path, kcal, duration_all, lessions) VALUES (
    'Chạy bộ',
    'Bạn vừa thức dậy. Đây là một ngày mới. Tấm canvas trống rỗng. Bạn sẽ bắt đầu như thế nào? Hãy dành 21 phút để nuôi dưỡng một tâm trí bình yên và cơ thể khỏe mạnh',
    'pic_1',
    160,
    '9 phút',
    JSON_ARRAY(
        JSON_OBJECT('title', 'Bài học 1', 'duration', '03:46', 'link', 'HBPMvFkpNgE', 'picPath', 'pic_1_1'),
        JSON_OBJECT('title', 'Bài học 2', 'duration', '03:41', 'link', 'K6I24WgiiPw', 'picPath', 'pic_1_2'),
        JSON_OBJECT('title', 'Bài học 3', 'duration', '01:57', 'link', 'Zc08v4YYOeA', 'picPath', 'pic_1_3')
    )
);

-- Thêm bài tập mẫu 2: Kéo giãn
INSERT INTO workouts (title, description, pic_path, kcal, duration_all, lessions) VALUES (
    'Kéo giãn',
    'Bạn vừa thức dậy. Đây là một ngày mới. Tấm canvas trống rỗng. Bạn sẽ bắt đầu như thế nào? Hãy dành 21 phút để nuôi dưỡng một tâm trí bình yên và cơ thể khỏe mạnh',
    'pic_2',
    230,
    '85 phút',
    JSON_ARRAY(
        JSON_OBJECT('title', 'Bài học 1', 'duration', '20:23', 'link', 'L3eImBAXT7I', 'picPath', 'pic_3_1'),
        JSON_OBJECT('title', 'Bài học 2', 'duration', '18:27', 'link', '47ExgzO7FlU', 'picPath', 'pic_3_2'),
        JSON_OBJECT('title', 'Bài học 3', 'duration', '32:25', 'link', 'OmLx8tmaQ-4', 'picPath', 'pic_3_3'),
        JSON_OBJECT('title', 'Bài học 4', 'duration', '07:52', 'link', 'w86EalEoFRY', 'picPath', 'pic_3_4')
    )
);

-- Thêm bài tập mẫu 3: Yoga
INSERT INTO workouts (title, description, pic_path, kcal, duration_all, lessions) VALUES (
    'Yoga',
    'Bạn vừa thức dậy. Đây là một ngày mới. Tấm canvas trống rỗng. Bạn sẽ bắt đầu như thế nào? Hãy dành 21 phút để nuôi dưỡng một tâm trí bình yên và cơ thể khỏe mạnh',
    'pic_3',
    180,
    '65 phút',
    JSON_ARRAY(
        JSON_OBJECT('title', 'Bài học 1', 'duration', '23:00', 'link', 'v7AYKMP6rOE', 'picPath', 'pic_3_1'),
        JSON_OBJECT('title', 'Bài học 2', 'duration', '27:00', 'link', 'Eml2xnoLpYE', 'picPath', 'pic_3_2'),
        JSON_OBJECT('title', 'Bài học 3', 'duration', '25:00', 'link', 'v7SN-d4qXx0', 'picPath', 'pic_3_3'),
        JSON_OBJECT('title', 'Bài học 4', 'duration', '21:00', 'link', 'LqXZ628YNj4', 'picPath', 'pic_3_4')
    )
);

-- Thêm dữ liệu mẫu cho favorite_workouts
INSERT INTO favorite_workouts (workout_title, added_date) VALUES
('Chạy bộ', UNIX_TIMESTAMP(NOW())),
('Yoga', UNIX_TIMESTAMP(NOW()) - 86400); -- 1 ngày trước

-- Thêm dữ liệu mẫu cho workout_history
INSERT INTO workout_history (workout_title, completion_date, duration, kcal_burned, completed_lessons, total_lessons) VALUES
('Chạy bộ', UNIX_TIMESTAMP(NOW()) - 86400, '9 phút', 160, 3, 3),
('Kéo giãn', UNIX_TIMESTAMP(NOW()) - 172800, '85 phút', 230, 4, 4),
('Yoga', UNIX_TIMESTAMP(NOW()) - 259200, '65 phút', 180, 4, 4);

-- Thêm dữ liệu mẫu cho scheduled_workouts
INSERT INTO scheduled_workouts (workout_title, scheduled_date, scheduled_time, is_completed, created_date) VALUES
('Chạy bộ', UNIX_TIMESTAMP(DATE_ADD(NOW(), INTERVAL 1 DAY)), '07:00', FALSE, UNIX_TIMESTAMP(NOW())),
('Yoga', UNIX_TIMESTAMP(DATE_ADD(NOW(), INTERVAL 2 DAY)), '18:00', FALSE, UNIX_TIMESTAMP(NOW()));

-- Thêm dữ liệu mẫu cho lessons (nếu sử dụng bảng normalized)
INSERT INTO lessons (workout_id, title, duration, link, pic_path, order_index) VALUES
(1, 'Bài học 1', '03:46', 'HBPMvFkpNgE', 'pic_1_1', 1),
(1, 'Bài học 2', '03:41', 'K6I24WgiiPw', 'pic_1_2', 2),
(1, 'Bài học 3', '01:57', 'Zc08v4YYOeA', 'pic_1_3', 3),
(2, 'Bài học 1', '20:23', 'L3eImBAXT7I', 'pic_3_1', 1),
(2, 'Bài học 2', '18:27', '47ExgzO7FlU', 'pic_3_2', 2),
(2, 'Bài học 3', '32:25', 'OmLx8tmaQ-4', 'pic_3_3', 3),
(2, 'Bài học 4', '07:52', 'w86EalEoFRY', 'pic_3_4', 4),
(3, 'Bài học 1', '23:00', 'v7AYKMP6rOE', 'pic_3_1', 1),
(3, 'Bài học 2', '27:00', 'Eml2xnoLpYE', 'pic_3_2', 2),
(3, 'Bài học 3', '25:00', 'v7SN-d4qXx0', 'pic_3_3', 3),
(3, 'Bài học 4', '21:00', 'LqXZ628YNj4', 'pic_3_4', 4);

-- =====================================================
-- VIEWS HỮU ÍCH
-- =====================================================

-- View: Tổng hợp thống kê
CREATE OR REPLACE VIEW v_workout_stats AS
SELECT 
    COUNT(DISTINCT wh.id) AS total_sessions,
    SUM(wh.kcal_burned) AS total_kcal,
    COUNT(DISTINCT fw.workout_title) AS total_favorites,
    COUNT(DISTINCT sw.id) AS total_scheduled
FROM workouts w
LEFT JOIN workout_history wh ON w.title = wh.workout_title
LEFT JOIN favorite_workouts fw ON w.title = fw.workout_title
LEFT JOIN scheduled_workouts sw ON w.title = sw.workout_title AND sw.is_completed = FALSE;

-- View: Danh sách bài tập với thống kê
CREATE OR REPLACE VIEW v_workouts_with_stats AS
SELECT 
    w.*,
    COUNT(DISTINCT wh.id) AS times_completed,
    COUNT(DISTINCT fw.id) AS is_favorite,
    COUNT(DISTINCT sw.id) AS scheduled_count
FROM workouts w
LEFT JOIN workout_history wh ON w.title = wh.workout_title
LEFT JOIN favorite_workouts fw ON w.title = fw.workout_title
LEFT JOIN scheduled_workouts sw ON w.title = sw.workout_title
GROUP BY w.id;

-- =====================================================
-- STORED PROCEDURES HỮU ÍCH
-- =====================================================

-- Procedure: Lấy tổng calo đốt cháy
DELIMITER //
CREATE PROCEDURE sp_get_total_kcal()
BEGIN
    SELECT IFNULL(SUM(kcal_burned), 0) AS total_kcal
    FROM workout_history;
END //
DELIMITER ;

-- Procedure: Lấy tổng số buổi tập
DELIMITER //
CREATE PROCEDURE sp_get_total_workouts()
BEGIN
    SELECT COUNT(*) AS total_workouts
    FROM workout_history;
END //
DELIMITER ;

-- Procedure: Thêm bài tập vào yêu thích
DELIMITER //
CREATE PROCEDURE sp_add_favorite(IN p_workout_title VARCHAR(255))
BEGIN
    INSERT INTO favorite_workouts (workout_title, added_date)
    VALUES (p_workout_title, UNIX_TIMESTAMP(NOW()))
    ON DUPLICATE KEY UPDATE added_date = UNIX_TIMESTAMP(NOW());
END //
DELIMITER ;

-- Procedure: Xóa bài tập khỏi yêu thích
DELIMITER //
CREATE PROCEDURE sp_remove_favorite(IN p_workout_title VARCHAR(255))
BEGIN
    DELETE FROM favorite_workouts WHERE workout_title = p_workout_title;
END //
DELIMITER ;

-- =====================================================
-- KẾT THÚC
-- =====================================================

SELECT 'Database và dữ liệu mẫu đã được tạo thành công!' AS status;

