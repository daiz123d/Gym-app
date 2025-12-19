package com.uilover.project1932.Helper;

/**
 * Utility class để tự động cập nhật ảnh cho tất cả bài tập
 * Sử dụng hàm này để tự động map ảnh dựa trên tên bài tập
 */
public class AutoImageUpdater {
    
    /**
     * Tự động lấy URL ảnh dựa trên tên bài tập
     * Hàm này sẽ tự động phân tích tên và trả về URL phù hợp
     */
    public static String getAutoImage(String exerciseTitle) {
        return ExerciseImageUrls.getImageUrlForExercise(exerciseTitle);
    }
}

