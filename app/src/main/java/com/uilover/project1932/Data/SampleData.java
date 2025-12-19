package com.uilover.project1932.Data;

import com.uilover.project1932.Domain.Lession;
import com.uilover.project1932.Domain.Workout;

import java.util.ArrayList;

/**
 * Class quản lý dữ liệu mẫu của ứng dụng
 * Để thêm bài tập mới, chỉ cần thêm vào các method bên dưới
 */
public class SampleData {
    
    /**
     * Lấy tất cả dữ liệu bài tập mẫu
     * @return Danh sách tất cả bài tập
     */
    public static ArrayList<Workout> getAllWorkouts() {
        ArrayList<Workout> list = new ArrayList<>();

        // Thêm bài tập Chạy bộ
        list.add(new Workout(
                "Chạy bộ",
                "Bạn vừa thức dậy. Đây là một ngày mới. Tấm canvas trống rỗng. Bạn sẽ bắt đầu như thế nào? Hãy dành 21 phút để nuôi dưỡng một tâm trí bình yên và cơ thể khỏe mạnh",
                "pic_1",
                160,
                "9 phút",
                getRunningLessons()
        ));

        // Thêm bài tập Kéo giãn
        list.add(new Workout(
                "Kéo giãn",
                "Bạn vừa thức dậy. Đây là một ngày mới. Tấm canvas trống rỗng. Bạn sẽ bắt đầu như thế nào? Hãy dành 21 phút để nuôi dưỡng một tâm trí bình yên và cơ thể khỏe mạnh",
                "pic_2",
                230,
                "85 phút",
                getStretchingLessons()
        ));

        // Thêm bài tập Yoga
        list.add(new Workout(
                "Yoga",
                "Bạn vừa thức dậy. Đây là một ngày mới. Tấm canvas trống rỗng. Bạn sẽ bắt đầu như thế nào? Hãy dành 21 phút để nuôi dưỡng một tâm trí bình yên và cơ thể khỏe mạnh",
                "pic_3",
                180,
                "65 phút",
                getYogaLessons()
        ));

        // Bài tập HIIT 15 phút
        list.add(new Workout(
                "HIIT 15 phút",
                "Chuỗi HIIT cường độ cao giúp đốt mỡ nhanh, phù hợp tập tại nhà, không dụng cụ.",
                "pic_3_4",
                210,
                "15 phút",
                getHiitLessons()
        ));

        // Bài tập Tăng cơ tay
        list.add(new Workout(
                "Tăng cơ tay",
                "Tập trung tay trước, tay sau và vai, cải thiện sức mạnh thân trên.",
                "pic_1_2",
                180,
                "20 phút",
                getArmStrengthLessons()
        ));

        // Bài tập Core bụng
        list.add(new Workout(
                "Core bụng",
                "Kích hoạt cơ bụng và lưng dưới, hỗ trợ cải thiện tư thế và sức mạnh trung tâm.",
                "pic_3_2",
                170,
                "18 phút",
                getCoreLessons()
        ));

        // Bài tập Cardio đốt mỡ
        list.add(new Workout(
                "Cardio đốt mỡ",
                "Cardio nhịp tim ổn định giúp đốt calo, tăng sức bền.",
                "pic_2",
                240,
                "25 phút",
                getCardioLessons()
        ));

        // Bài tập Pilates nhẹ
        list.add(new Workout(
                "Pilates nhẹ",
                "Chuỗi Pilates thân thiện cho người mới, tập trung kiểm soát nhịp thở và độ linh hoạt.",
                "pic_2_2",
                140,
                "22 phút",
                getPilatesLessons()
        ));

        // Bài tập Sức mạnh toàn thân
        list.add(new Workout(
                "Sức mạnh toàn thân",
                "Kết hợp nhóm cơ lớn giúp tăng sức mạnh tổng quát và đốt calo hiệu quả.",
                "pic_1",
                260,
                "30 phút",
                getFullBodyStrengthLessons()
        ));

        // ========== THÊM BÀI TẬP MỚI TẠI ĐÂY ==========
        // Ví dụ: Thêm bài tập Plank
        // list.add(new Workout(
        //         "Plank",
        //         "Mô tả bài tập Plank...",
        //         "pic_4",  // Tên file ảnh trong drawable
        //         120,       // Kcal
        //         "15 phút", // Thời gian
        //         getPlankLessons()
        // ));

        return list;
    }

    /**
     * Danh sách bài học cho bài tập Chạy bộ
     */
    private static ArrayList<Lession> getRunningLessons() {
        ArrayList<Lession> list = new ArrayList<>();

        list.add(new Lession("Bài học 1", "03:46 ", "HBPMvFkpNgE", "pic_1_1"));
        list.add(new Lession("Bài học 2", "03:41 ", "K6I24WgiiPw", "pic_1_2"));
        list.add(new Lession("Bài học 3", "01:57 ", "Zc08v4YYOeA", "pic_1_3"));

        // ========== THÊM BÀI HỌC MỚI TẠI ĐÂY ==========
        // list.add(new Lession("Bài học 4", "04:00 ", "VIDEO_ID_YOUTUBE", "pic_1_4"));

        return list;
    }

    /**
     * Danh sách bài học cho bài tập Kéo giãn
     */
    private static ArrayList<Lession> getStretchingLessons() {
        ArrayList<Lession> list = new ArrayList<>();

        list.add(new Lession("Bài học 1", "20:23 ", "L3eImBAXT7I", "pic_3_1"));
        list.add(new Lession("Bài học 2", "18:27 ", "47ExgzO7FlU", "pic_3_2"));
        list.add(new Lession("Bài học 3", "32:25 ", "OmLx8tmaQ-4", "pic_3_3"));
        list.add(new Lession("Bài học 4", "07:52 ", "w86EalEoFRY", "pic_3_4"));

        // ========== THÊM BÀI HỌC MỚI TẠI ĐÂY ==========

        return list;
    }

    private static ArrayList<Lession> getHiitLessons() {
        ArrayList<Lession> list = new ArrayList<>();
        list.add(new Lession("Khởi động toàn thân", "03:00", "ml6cT4AZdqI", "pic_3_4"));
        list.add(new Lession("Burpee & Jump Squat", "06:00", "odWsPx1v9mE", "pic_2_4"));
        list.add(new Lession("Mountain Climber & High Knees", "06:00", "kZDvg92tTMc", "pic_1_3"));
        return list;
    }

    private static ArrayList<Lession> getArmStrengthLessons() {
        ArrayList<Lession> list = new ArrayList<>();
        list.add(new Lession("Push Up hẹp tay", "05:00", "IODxDxX7oi4", "pic_1_2"));
        list.add(new Lession("Diamond Push Up", "05:00", "tF6FQDVN2J8", "pic_1_1"));
        list.add(new Lession("Dips ghế", "05:00", "0326dy_-CzM", "pic_2_1"));
        list.add(new Lession("Shoulder Taps", "05:00", "By-3JQFj0Zs", "pic_3_1"));
        return list;
    }

    private static ArrayList<Lession> getCoreLessons() {
        ArrayList<Lession> list = new ArrayList<>();
        list.add(new Lession("Plank cơ bản", "04:00", "y1tEvYnxV6A", "pic_3_2"));
        list.add(new Lession("Crunch chéo", "04:00", "9KgBt7y6ekM", "pic_3_3"));
        list.add(new Lession("Leg Raise", "04:00", "JB2oya8hN9k", "pic_1_1"));
        list.add(new Lession("Bicycle Crunch", "06:00", "1we3bh9uhqY", "pic_1_3"));
        return list;
    }

    private static ArrayList<Lession> getCardioLessons() {
        ArrayList<Lession> list = new ArrayList<>();
        list.add(new Lession("Chạy tại chỗ nâng cao gối", "06:00", "soN7Mv2VzzI", "pic_2"));
        list.add(new Lession("Jumping Jack", "05:00", "c4DAnQ6DtF8", "pic_2_4"));
        list.add(new Lession("Skater & Lunge", "07:00", "7d6wZ2c8Q3k", "pic_3_4"));
        list.add(new Lession("Cool down nhẹ", "07:00", "w86EalEoFRY", "pic_3_1"));
        return list;
    }

    private static ArrayList<Lession> getPilatesLessons() {
        ArrayList<Lession> list = new ArrayList<>();
        list.add(new Lession("Breathing & Roll Down", "05:00", "9Kd0o3nKqqM", "pic_2_2"));
        list.add(new Lession("Hundred", "05:00", "9ck3pK0LHAE", "pic_3"));
        list.add(new Lession("Leg Circle", "06:00", "2L2lnxIcNmo", "pic_2_3"));
        list.add(new Lession("Spine Stretch", "06:00", "qtgVvG6E3b0", "pic_3_2"));
        return list;
    }

    private static ArrayList<Lession> getFullBodyStrengthLessons() {
        ArrayList<Lession> list = new ArrayList<>();
        list.add(new Lession("Squat & Row", "06:00", "U3HlEF_E9fo", "pic_1"));
        list.add(new Lession("Lunge với tạ tay", "06:00", "QOVaHwm-Q6U", "pic_2"));
        list.add(new Lession("Push Up + Shoulder Tap", "06:00", "IODxDxX7oi4", "pic_1_2"));
        list.add(new Lession("Deadlift nhẹ", "06:00", "r4MzxtBKyNE", "pic_2_1"));
        list.add(new Lession("Cool down & giãn cơ", "06:00", "L3eImBAXT7I", "pic_3_4"));
        return list;
    }

    /**
     * Danh sách bài học cho bài tập Yoga
     */
    private static ArrayList<Lession> getYogaLessons() {
        ArrayList<Lession> list = new ArrayList<>();

        list.add(new Lession("Bài học 1", "23:00 ", "v7AYKMP6rOE", "pic_3_1"));
        list.add(new Lession("Bài học 2", "27:00 ", "Eml2xnoLpYE", "pic_3_2"));
        list.add(new Lession("Bài học 3", "25:00 ", "v7SN-d4qXx0", "pic_3_3"));
        list.add(new Lession("Bài học 4", "21:00 ", "LqXZ628YNj4", "pic_3_4"));

        // ========== THÊM BÀI HỌC MỚI TẠI ĐÂY ==========

        return list;
    }

    // ========== THÊM METHOD MỚI CHO BÀI TẬP MỚI ==========
    // Ví dụ: Method cho bài tập Plank
    // private static ArrayList<Lession> getPlankLessons() {
    //     ArrayList<Lession> list = new ArrayList<>();
    //     list.add(new Lession("Bài học 1", "05:00 ", "VIDEO_ID_1", "pic_4_1"));
    //     list.add(new Lession("Bài học 2", "05:30 ", "VIDEO_ID_2", "pic_4_2"));
    //     return list;
    // }
}

