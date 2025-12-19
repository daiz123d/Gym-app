package com.uilover.project1932.Data;

import com.uilover.project1932.Helper.ExerciseImageUrls;

import java.util.ArrayList;

public class ExerciseData {
    
    public static ArrayList<ExerciseItem> getBicepsExercises() {
        ArrayList<ExerciseItem> exercises = new ArrayList<>();
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp tay trước hai tay luân phiên nâng tạ đơn và xoay tay khi ngồi trên ghế",
                ExerciseImageUrls.BICEPS_CURL,
                "Bài tập bắp tay với tạ đơn, luân phiên hai tay và xoay cổ tay khi ngồi",
                "1. Ngồi trên ghế, lưng thẳng\n" +
                "2. Cầm tạ đơn bằng cả hai tay\n" +
                "3. Luân phiên nâng từng tay lên, xoay cổ tay khi nâng\n" +
                "4. Hạ tạ từ từ về vị trí ban đầu\n" +
                "5. Lặp lại với tay còn lại",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp tay trước hai tay luân phiên nâng tạ đơn và xoay tay khi đứng",
                ExerciseImageUrls.BICEPS_CURL,
                "Bài tập bắp tay với tạ đơn khi đứng, luân phiên và xoay cổ tay",
                "1. Đứng thẳng, hai chân rộng bằng vai\n" +
                "2. Cầm tạ đơn bằng cả hai tay\n" +
                "3. Luân phiên nâng từng tay, xoay cổ tay khi nâng\n" +
                "4. Giữ cơ thể ổn định, không đung đưa\n" +
                "5. Hạ tạ từ từ về vị trí ban đầu",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp tay trước hai tay nâng tạ đơn khi đứng",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay trước hai tay nâng tạ đơn khi đứng"),
                "Bài tập bắp tay cơ bản với tạ đơn khi đứng",
                "1. Đứng thẳng, hai chân rộng bằng vai\n" +
                "2. Cầm tạ đơn bằng cả hai tay, lòng bàn tay hướng về phía trước\n" +
                "3. Nâng cả hai tay cùng lúc lên ngang ngực\n" +
                "4. Giữ 1 giây ở vị trí cao nhất\n" +
                "5. Hạ tạ từ từ về vị trí ban đầu",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp tay trước hai tay nâng tạ đơn luân phiên khi ngồi trên ghế",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay trước hai tay nâng tạ đơn luân phiên khi ngồi trên ghế"),
                "Bài tập bắp tay với tạ đơn khi ngồi, luân phiên hai tay",
                "1. Ngồi trên ghế, lưng thẳng\n" +
                "2. Cầm tạ đơn bằng cả hai tay\n" +
                "3. Luân phiên nâng từng tay lên ngang ngực\n" +
                "4. Giữ 1 giây ở vị trí cao nhất\n" +
                "5. Hạ tạ từ từ, lặp lại với tay còn lại",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp tay trước hai tay nâng tạ đơn luân phiên khi đứng",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau"),
                "Bài tập bắp tay với tạ đơn khi đứng, luân phiên",
                "1. Đứng thẳng, hai chân rộng bằng vai\n" +
                "2. Cầm tạ đơn bằng cả hai tay\n" +
                "3. Luân phiên nâng từng tay lên ngang ngực\n" +
                "4. Giữ cơ thể ổn định\n" +
                "5. Hạ tạ từ từ, lặp lại với tay còn lại",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp tay trước ngồi trên ghế hai tay nâng tạ đơn với lòng bàn tay hướng vào cơ thể",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau"),
                "Hammer curl khi ngồi, lòng bàn tay hướng vào nhau",
                "1. Ngồi trên ghế, lưng thẳng\n" +
                "2. Cầm tạ đơn, lòng bàn tay hướng vào nhau\n" +
                "3. Nâng cả hai tay cùng lúc lên ngang ngực\n" +
                "4. Giữ 1 giây ở vị trí cao nhất\n" +
                "5. Hạ tạ từ từ về vị trí ban đầu",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp tay trước nâng tạ đơn bằng một tay",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay trước nâng tạ đơn bằng một tay"),
                "Bài tập bắp tay với một tay, tập trung vào từng bên",
                "1. Ngồi hoặc đứng, giữ tạ đơn bằng một tay\n" +
                "2. Nâng tạ lên ngang ngực, giữ khuỷu tay cố định\n" +
                "3. Giữ 1 giây ở vị trí cao nhất\n" +
                "4. Hạ tạ từ từ về vị trí ban đầu\n" +
                "5. Hoàn thành số lần lặp, đổi tay",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập Zottman Curl",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập Zottman Curl"),
                "Bài tập bắp tay và cẳng tay với kỹ thuật Zottman",
                "1. Đứng thẳng, cầm tạ đơn bằng cả hai tay\n" +
                "2. Nâng tạ lên với lòng bàn tay hướng lên\n" +
                "3. Ở vị trí cao nhất, xoay cổ tay để lòng bàn tay hướng xuống\n" +
                "4. Hạ tạ từ từ với lòng bàn tay hướng xuống\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp tay trước đứng kéo cáp với tay nắm thuận",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay trước đứng kéo cáp với tay nắm thuận"),
                "Bài tập bắp tay với máy cáp, tay nắm thuận",
                "1. Đứng trước máy cáp, điều chỉnh độ cao phù hợp\n" +
                "2. Cầm tay cầm với lòng bàn tay hướng lên\n" +
                "3. Kéo tay cầm lên ngang ngực\n" +
                "4. Giữ 1 giây, cảm nhận cơ bắp tay co lại\n" +
                "5. Từ từ hạ tay cầm về vị trí ban đầu",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp tay trước đứng kéo dây cáp bằng một tay",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay trước đứng kéo dây cáp bằng một tay"),
                "Bài tập bắp tay với máy cáp, một tay",
                "1. Đứng trước máy cáp, cầm tay cầm bằng một tay\n" +
                "2. Kéo tay cầm lên ngang ngực\n" +
                "3. Giữ khuỷu tay cố định, chỉ di chuyển cẳng tay\n" +
                "4. Giữ 1 giây ở vị trí cao nhất\n" +
                "5. Từ từ hạ về, đổi tay",
                60
        ));
        
        return exercises;
    }
    
    public static ArrayList<ExerciseItem> getTricepsExercises() {
        ArrayList<ExerciseItem> exercises = new ArrayList<>();
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp tay sau đứng đẩy cáp xuống",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau đứng đẩy cáp xuống"),
                "Triceps pushdown với máy cáp",
                "1. Đứng trước máy cáp, cầm tay cầm\n" +
                "2. Giữ khuỷu tay sát người\n" +
                "3. Đẩy tay cầm xuống dưới\n" +
                "4. Giữ 1 giây, cảm nhận cơ tay sau co lại\n" +
                "5. Từ từ đưa tay về vị trí ban đầu",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp tay sau nằm đẩy tạ đòn",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau nằm đẩy tạ đòn"),
                "Close-grip bench press cho triceps",
                "1. Nằm trên ghế, cầm tạ đòn với tay hẹp\n" +
                "2. Hạ tạ xuống ngực\n" +
                "3. Đẩy tạ lên, tập trung vào cơ tay sau\n" +
                "4. Giữ 1 giây ở vị trí cao nhất\n" +
                "5. Lặp lại động tác",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp tay sau đứng duỗi tay sau đầu",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau đứng duỗi tay sau đầu"),
                "Overhead triceps extension",
                "1. Đứng thẳng, cầm tạ đơn hoặc tạ đòn phía sau đầu\n" +
                "2. Giữ khuỷu tay cố định\n" +
                "3. Duỗi tay lên trên\n" +
                "4. Giữ 1 giây, cảm nhận cơ tay sau co lại\n" +
                "5. Từ từ hạ về vị trí ban đầu",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp tay sau hít xà khuỷu tay",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau hít xà khuỷu tay"),
                "Triceps dips",
                "1. Ngồi trên ghế, tay đặt sau lưng\n" +
                "2. Hạ người xuống, gập khuỷu tay\n" +
                "3. Đẩy người lên bằng cơ tay sau\n" +
                "4. Giữ 1 giây ở vị trí cao nhất\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp tay sau duỗi tay một tay",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau"),
                "Single-arm triceps extension",
                "1. Đứng, cầm tạ đơn bằng một tay phía sau đầu\n" +
                "2. Duỗi tay lên trên\n" +
                "3. Giữ khuỷu tay cố định\n" +
                "4. Giữ 1 giây\n" +
                "5. Hạ tạ xuống, đổi tay",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp tay sau đẩy cáp bằng dây thừng",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau"),
                "Rope triceps pushdown",
                "1. Đứng trước máy cáp, cầm dây thừng\n" +
                "2. Đẩy dây xuống, tách hai đầu dây ra\n" +
                "3. Giữ 1 giây\n" +
                "4. Từ từ đưa dây lên\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp tay sau nằm duỗi tay",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau nằm duỗi tay"),
                "Lying triceps extension",
                "1. Nằm trên ghế, cầm tạ đòn phía sau đầu\n" +
                "2. Duỗi tay lên trên\n" +
                "3. Giữ khuỷu tay cố định\n" +
                "4. Giữ 1 giây\n" +
                "5. Hạ tạ về phía sau đầu",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp tay sau đẩy cáp một tay",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau đẩy cáp một tay"),
                "Single-arm cable pushdown",
                "1. Đứng trước máy cáp, cầm tay cầm bằng một tay\n" +
                "2. Đẩy tay cầm xuống\n" +
                "3. Giữ khuỷu tay sát người\n" +
                "4. Giữ 1 giây\n" +
                "5. Từ từ đưa tay lên, đổi tay",
                60
        ));
        
        return exercises;
    }
    
    public static ArrayList<ExerciseItem> getChestExercises() {
        ArrayList<ExerciseItem> exercises = new ArrayList<>();
        
        exercises.add(new ExerciseItem(
                "Bài tập ngực nằm đẩy tạ đòn",
                ExerciseImageUrls.CHEST_BENCH_PRESS,
                "Bench press cơ bản",
                "1. Nằm trên ghế, cầm tạ đòn\n" +
                "2. Hạ tạ xuống ngực\n" +
                "3. Đẩy tạ lên mạnh mẽ\n" +
                "4. Giữ 1 giây ở vị trí cao nhất\n" +
                "5. Lặp lại động tác",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập ngực nằm đẩy tạ đơn",
                ExerciseImageUrls.CHEST_BENCH_PRESS,
                "Dumbbell bench press",
                "1. Nằm trên ghế, cầm tạ đơn bằng cả hai tay\n" +
                "2. Hạ tạ xuống ngực\n" +
                "3. Đẩy tạ lên, giữ cân bằng\n" +
                "4. Giữ 1 giây ở vị trí cao nhất\n" +
                "5. Lặp lại động tác",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập ngực đẩy tạ đòn dốc lên",
                ExerciseImageUrls.CHEST_BENCH_PRESS,
                "Incline bench press",
                "1. Nằm trên ghế dốc 30-45 độ\n" +
                "2. Cầm tạ đòn, hạ xuống ngực trên\n" +
                "3. Đẩy tạ lên mạnh mẽ\n" +
                "4. Tập trung vào cơ ngực trên\n" +
                "5. Lặp lại động tác",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập ngực mở tạ đơn",
                ExerciseImageUrls.CHEST_FLYES,
                "Dumbbell flyes",
                "1. Nằm trên ghế, cầm tạ đơn\n" +
                "2. Mở rộng tay ra hai bên\n" +
                "3. Khép tay lại như ôm ngực\n" +
                "4. Giữ 1 giây ở vị trí cao nhất\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập ngực hít đất",
                ExerciseImageUrls.CHEST_PUSH_UP,
                "Push-ups",
                "1. Vào tư thế plank\n" +
                "2. Hạ người xuống, gần chạm sàn\n" +
                "3. Đẩy người lên mạnh mẽ\n" +
                "4. Giữ cơ thể thẳng\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập ngực đẩy tạ đòn dốc xuống",
                ExerciseImageUrls.CHEST_DECLINE,
                "Decline bench press",
                "1. Nằm trên ghế dốc xuống\n" +
                "2. Cầm tạ đòn, hạ xuống ngực dưới\n" +
                "3. Đẩy tạ lên mạnh mẽ\n" +
                "4. Tập trung vào cơ ngực dưới\n" +
                "5. Lặp lại động tác",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập ngực kéo cáp chéo",
                ExerciseImageUrls.CHEST_CABLE,
                "Cable crossover",
                "1. Đứng giữa máy cáp\n" +
                "2. Kéo tay cầm từ hai bên vào giữa\n" +
                "3. Khép tay lại trước ngực\n" +
                "4. Giữ 1 giây, cảm nhận cơ ngực co lại\n" +
                "5. Từ từ mở tay về vị trí ban đầu",
                60
        ));
        
        return exercises;
    }
    
    public static ArrayList<ExerciseItem> getBackExercises() {
        ArrayList<ExerciseItem> exercises = new ArrayList<>();
        
        exercises.add(new ExerciseItem(
                "Bài tập lưng kéo xà đơn",
                ExerciseImageUrls.BACK_PULL_UP,
                "Pull-ups cho cơ lưng",
                "1. Treo người trên xà đơn\n" +
                "2. Kéo người lên cho đến khi cằm qua xà\n" +
                "3. Giữ 1 giây ở vị trí cao nhất\n" +
                "4. Từ từ hạ người xuống\n" +
                "5. Lặp lại động tác",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập lưng kéo tạ đòn",
                ExerciseImageUrls.BACK_ROW,
                "Barbell row",
                "1. Đứng, cúi người, cầm tạ đòn\n" +
                "2. Kéo tạ lên ngang bụng\n" +
                "3. Giữ 1 giây, cảm nhận cơ lưng co lại\n" +
                "4. Từ từ hạ tạ xuống\n" +
                "5. Lặp lại động tác",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập lưng kéo cáp rộng",
                ExerciseImageUrls.BACK_LAT_PULLDOWN,
                "Wide-grip lat pulldown",
                "1. Ngồi trên máy, cầm thanh kéo rộng\n" +
                "2. Kéo thanh xuống ngang ngực\n" +
                "3. Giữ 1 giây, cảm nhận cơ lưng rộng co lại\n" +
                "4. Từ từ đưa thanh lên\n" +
                "5. Lặp lại động tác",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập lưng kéo tạ đơn một tay",
                ExerciseImageUrls.BACK_ONE_ARM_ROW,
                "One-arm dumbbell row",
                "1. Đặt một tay và đầu gối lên ghế\n" +
                "2. Cầm tạ đơn bằng tay còn lại\n" +
                "3. Kéo tạ lên ngang bụng\n" +
                "4. Giữ 1 giây, cảm nhận cơ lưng co lại\n" +
                "5. Hạ tạ xuống, đổi tay",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập lưng kéo tạ chết",
                ExerciseImageUrls.BACK_DEADLIFT,
                "Deadlift",
                "1. Đứng, hai chân rộng bằng vai\n" +
                "2. Cúi xuống, cầm tạ đòn\n" +
                "3. Đứng thẳng lên, kéo tạ lên\n" +
                "4. Giữ 1 giây ở vị trí đứng\n" +
                "5. Hạ tạ xuống từ từ",
                120
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập lưng kéo cáp hẹp",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau"),
                "Close-grip lat pulldown",
                "1. Ngồi trên máy, cầm thanh kéo hẹp\n" +
                "2. Kéo thanh xuống ngang ngực\n" +
                "3. Tập trung vào cơ lưng giữa\n" +
                "4. Giữ 1 giây\n" +
                "5. Từ từ đưa thanh lên",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập lưng kéo ngược",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập lưng kéo ngược"),
                "Reverse grip lat pulldown",
                "1. Ngồi trên máy, cầm thanh kéo ngược\n" +
                "2. Kéo thanh xuống ngang ngực\n" +
                "3. Tập trung vào cơ lưng dưới\n" +
                "4. Giữ 1 giây\n" +
                "5. Từ từ đưa thanh lên",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập lưng kéo tạ đòn ngồi",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập lưng kéo tạ đòn ngồi"),
                "Seated cable row",
                "1. Ngồi trên máy, chân đặt trên bàn đạp\n" +
                "2. Kéo tay cầm về phía bụng\n" +
                "3. Giữ lưng thẳng, khuỷu tay sát người\n" +
                "4. Giữ 1 giây, cảm nhận cơ lưng co lại\n" +
                "5. Từ từ đưa tay về vị trí ban đầu",
                90
        ));
        
        return exercises;
    }
    
    public static ArrayList<ExerciseItem> getLegExercises() {
        ArrayList<ExerciseItem> exercises = new ArrayList<>();
        
        exercises.add(new ExerciseItem(
                "Bài tập chân ngồi xổm",
                ExerciseImageUrls.LEGS_SQUAT,
                "Squats cơ bản",
                "1. Đứng thẳng, hai chân rộng bằng vai\n" +
                "2. Hạ người xuống như ngồi xổm\n" +
                "3. Đẩy người lên về vị trí ban đầu\n" +
                "4. Giữ lưng thẳng trong suốt động tác\n" +
                "5. Lặp lại động tác",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập chân đẩy chân trên máy",
                ExerciseImageUrls.LEGS_LEG_PRESS,
                "Leg press",
                "1. Ngồi trên máy, đặt chân lên bàn đạp\n" +
                "2. Hạ bàn đạp xuống\n" +
                "3. Đẩy bàn đạp lên mạnh mẽ\n" +
                "4. Giữ 1 giây ở vị trí cao nhất\n" +
                "5. Lặp lại động tác",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập chân ngồi xổm với tạ đòn",
                ExerciseImageUrls.LEGS_SQUAT,
                "Barbell squats",
                "1. Đặt tạ đòn lên vai sau\n" +
                "2. Đứng thẳng, hai chân rộng bằng vai\n" +
                "3. Hạ người xuống như ngồi xổm\n" +
                "4. Đẩy người lên mạnh mẽ\n" +
                "5. Lặp lại động tác",
                120
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập chân chùng chân",
                ExerciseImageUrls.LEGS_LUNGE,
                "Lunges",
                "1. Đứng thẳng, bước một chân về phía trước\n" +
                "2. Hạ người xuống, đầu gối sau gần chạm sàn\n" +
                "3. Đẩy người lên, bước chân về vị trí ban đầu\n" +
                "4. Đổi chân\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập chân gập chân trên máy",
                ExerciseImageUrls.LEGS_LEG_CURL,
                "Leg curl",
                "1. Nằm sấp trên máy, đặt chân dưới thanh đỡ\n" +
                "2. Gập chân lên về phía mông\n" +
                "3. Giữ 1 giây, cảm nhận cơ đùi sau co lại\n" +
                "4. Từ từ hạ chân xuống\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập chân duỗi chân trên máy",
                ExerciseImageUrls.LEGS_LEG_EXTENSION,
                "Leg extension",
                "1. Ngồi trên máy, đặt chân dưới thanh đỡ\n" +
                "2. Duỗi chân lên thẳng\n" +
                "3. Giữ 1 giây, cảm nhận cơ đùi trước co lại\n" +
                "4. Từ từ hạ chân xuống\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập chân chùng chân Bulgaria",
                ExerciseImageUrls.LEGS_LUNGE,
                "Bulgarian split squats",
                "1. Đặt một chân lên ghế phía sau\n" +
                "2. Hạ người xuống, đầu gối trước gập 90 độ\n" +
                "3. Đẩy người lên mạnh mẽ\n" +
                "4. Đổi chân\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập chân nâng bắp chân",
                ExerciseImageUrls.CALVES_RAISE,
                "Calf raises",
                "1. Đứng thẳng, hai chân rộng bằng vai\n" +
                "2. Nâng gót chân lên cao\n" +
                "3. Giữ 1 giây ở vị trí cao nhất\n" +
                "4. Từ từ hạ gót chân xuống\n" +
                "5. Lặp lại động tác",
                45
        ));
        
        return exercises;
    }
    
    public static ArrayList<ExerciseItem> getShoulderExercises() {
        ArrayList<ExerciseItem> exercises = new ArrayList<>();
        
        exercises.add(new ExerciseItem(
                "Bài tập vai đẩy tạ đòn lên trên",
                ExerciseImageUrls.SHOULDER_PRESS,
                "Overhead press",
                "1. Đứng thẳng, cầm tạ đòn ngang vai\n" +
                "2. Đẩy tạ lên trên đầu\n" +
                "3. Giữ 1 giây ở vị trí cao nhất\n" +
                "4. Từ từ hạ tạ về ngang vai\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập vai nâng tạ đơn ngang",
                ExerciseImageUrls.SHOULDER_LATERAL_RAISE,
                "Lateral raises",
                "1. Đứng thẳng, cầm tạ đơn bằng cả hai tay\n" +
                "2. Nâng tay ngang ra hai bên\n" +
                "3. Giữ 1 giây ở vị trí cao nhất\n" +
                "4. Từ từ hạ tay xuống\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập vai nâng tạ đơn trước",
                ExerciseImageUrls.SHOULDER_FRONT_RAISE,
                "Front raises",
                "1. Đứng thẳng, cầm tạ đơn bằng cả hai tay\n" +
                "2. Nâng tay lên trước mặt\n" +
                "3. Giữ 1 giây ở vị trí cao nhất\n" +
                "4. Từ từ hạ tay xuống\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập vai mở tạ đơn sau",
                ExerciseImageUrls.SHOULDER_REAR_DELT,
                "Rear delt flyes",
                "1. Cúi người, cầm tạ đơn\n" +
                "2. Mở tay ra hai bên\n" +
                "3. Tập trung vào cơ vai sau\n" +
                "4. Giữ 1 giây ở vị trí cao nhất\n" +
                "5. Từ từ hạ tay xuống",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập vai đẩy tạ đơn ngồi",
                ExerciseImageUrls.SHOULDER_PRESS,
                "Seated dumbbell press",
                "1. Ngồi trên ghế, cầm tạ đơn ngang vai\n" +
                "2. Đẩy tạ lên trên đầu\n" +
                "3. Giữ 1 giây ở vị trí cao nhất\n" +
                "4. Từ từ hạ tạ về ngang vai\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập vai nâng tạ đơn một tay",
                ExerciseImageUrls.SHOULDER_LATERAL_RAISE,
                "Single-arm lateral raise",
                "1. Đứng thẳng, cầm tạ đơn bằng một tay\n" +
                "2. Nâng tay ngang ra một bên\n" +
                "3. Giữ 1 giây ở vị trí cao nhất\n" +
                "4. Từ từ hạ tay xuống\n" +
                "5. Đổi tay, lặp lại",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập vai đẩy Arnold",
                ExerciseImageUrls.SHOULDER_PRESS,
                "Arnold press",
                "1. Ngồi, cầm tạ đơn, lòng bàn tay hướng vào người\n" +
                "2. Xoay cổ tay và đẩy tạ lên\n" +
                "3. Ở vị trí cao nhất, lòng bàn tay hướng ra trước\n" +
                "4. Hạ tạ xuống, xoay cổ tay về vị trí ban đầu\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        return exercises;
    }
    
    public static ArrayList<ExerciseItem> getAbsExercises() {
        ArrayList<ExerciseItem> exercises = new ArrayList<>();
        
        exercises.add(new ExerciseItem(
                "Bài tập bụng gập bụng",
                ExerciseImageUrls.ABS_CRUNCH,
                "Crunches cơ bản",
                "1. Nằm ngửa, co gối\n" +
                "2. Gập bụng lên\n" +
                "3. Giữ 1 giây ở vị trí cao nhất\n" +
                "4. Từ từ hạ xuống\n" +
                "5. Lặp lại động tác",
                45
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bụng nâng chân",
                ExerciseImageUrls.ABS_LEG_RAISE,
                "Leg raises",
                "1. Nằm ngửa, tay đặt dưới mông\n" +
                "2. Nâng chân lên vuông góc với sàn\n" +
                "3. Giữ 1 giây\n" +
                "4. Từ từ hạ chân xuống\n" +
                "5. Lặp lại động tác",
                45
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bụng plank",
                ExerciseImageUrls.ABS_PLANK,
                "Plank",
                "1. Vào tư thế plank, chống bằng khuỷu tay\n" +
                "2. Giữ cơ thể thẳng từ đầu đến chân\n" +
                "3. Giữ cơ bụng căng\n" +
                "4. Giữ 30-60 giây\n" +
                "5. Nghỉ và lặp lại",
                0
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bụng gập bụng chéo",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bụng gập bụng chéo"),
                "Oblique crunches",
                "1. Nằm ngửa, co gối\n" +
                "2. Gập bụng lên, xoay người sang một bên\n" +
                "3. Tập trung vào cơ bụng chéo\n" +
                "4. Đổi bên\n" +
                "5. Lặp lại động tác",
                45
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bụng gập bụng ngược",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau"),
                "Reverse crunches",
                "1. Nằm ngửa, co gối\n" +
                "2. Nâng đầu gối lên về phía ngực\n" +
                "3. Giữ 1 giây\n" +
                "4. Từ từ hạ chân xuống\n" +
                "5. Lặp lại động tác",
                45
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bụng gập bụng với tạ",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau"),
                "Weighted crunches",
                "1. Nằm ngửa, giữ tạ trước ngực\n" +
                "2. Gập bụng lên\n" +
                "3. Giữ 1 giây ở vị trí cao nhất\n" +
                "4. Từ từ hạ xuống\n" +
                "5. Lặp lại động tác",
                45
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bụng gập bụng trên bóng",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bụng gập bụng trên bóng"),
                "Stability ball crunches",
                "1. Nằm trên bóng, lưng cong\n" +
                "2. Gập bụng lên\n" +
                "3. Giữ 1 giây\n" +
                "4. Từ từ hạ xuống\n" +
                "5. Lặp lại động tác",
                45
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bụng gập bụng kiểu xe đạp",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bụng gập bụng kiểu xe đạp"),
                "Bicycle crunches",
                "1. Nằm ngửa, tay sau đầu\n" +
                "2. Co gối, đạp như đạp xe\n" +
                "3. Xoay người, khuỷu tay chạm đầu gối đối diện\n" +
                "4. Đổi bên liên tục\n" +
                "5. Lặp lại động tác",
                45
        ));
        
        return exercises;
    }
    
    public static ArrayList<ExerciseItem> getForearmExercises() {
        ArrayList<ExerciseItem> exercises = new ArrayList<>();
        
        exercises.add(new ExerciseItem(
                "Bài tập cẳng tay cuốn tạ đòn",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập cẳng tay cuốn tạ đòn"),
                "Wrist curls",
                "1. Ngồi, đặt cẳng tay lên đùi\n" +
                "2. Cầm tạ đòn, cuốn cổ tay lên\n" +
                "3. Giữ 1 giây\n" +
                "4. Từ từ hạ xuống\n" +
                "5. Lặp lại động tác",
                45
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập cẳng tay cuốn tạ đòn ngược",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập cẳng tay cuốn tạ đòn ngược"),
                "Reverse wrist curls",
                "1. Ngồi, đặt cẳng tay lên đùi\n" +
                "2. Cầm tạ đòn, lòng bàn tay hướng xuống\n" +
                "3. Cuốn cổ tay lên\n" +
                "4. Giữ 1 giây\n" +
                "5. Từ từ hạ xuống",
                45
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập cẳng tay cuốn tạ đơn",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập cẳng tay cuốn tạ đơn"),
                "Dumbbell wrist curls",
                "1. Ngồi, đặt cẳng tay lên đùi\n" +
                "2. Cầm tạ đơn, cuốn cổ tay lên\n" +
                "3. Giữ 1 giây\n" +
                "4. Từ từ hạ xuống\n" +
                "5. Đổi tay, lặp lại",
                45
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập cẳng tay bóp bóng",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập cẳng tay bóp bóng"),
                "Grip strength",
                "1. Cầm bóng bóp tay\n" +
                "2. Bóp mạnh và giữ 3-5 giây\n" +
                "3. Thả lỏng\n" +
                "4. Lặp lại nhiều lần\n" +
                "5. Đổi tay",
                30
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập cẳng tay treo người",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau"),
                "Farmer's walk",
                "1. Cầm tạ nặng bằng cả hai tay\n" +
                "2. Đi bộ với tạ\n" +
                "3. Giữ tạ càng lâu càng tốt\n" +
                "4. Tập trung vào sức bám\n" +
                "5. Đặt tạ xuống khi mệt",
                60
        ));
        
        return exercises;
    }
    
    public static ArrayList<ExerciseItem> getCardioExercises() {
        ArrayList<ExerciseItem> exercises = new ArrayList<>();
        
        exercises.add(new ExerciseItem(
                "Chạy bộ",
                ExerciseImageUrls.getImageUrlForExercise("Chạy bộ"),
                "Cardio cơ bản",
                "1. Khởi động 5 phút\n" +
                "2. Chạy với tốc độ vừa phải\n" +
                "3. Duy trì nhịp tim ổn định\n" +
                "4. Giảm tốc độ 5 phút cuối\n" +
                "5. Giãn cơ sau khi chạy",
                0
        ));
        
        exercises.add(new ExerciseItem(
                "Nhảy dây",
                ExerciseImageUrls.getImageUrlForExercise("Nhảy dây"),
                "Cardio với dây nhảy",
                "1. Cầm dây nhảy\n" +
                "2. Nhảy liên tục\n" +
                "3. Duy trì nhịp độ ổn định\n" +
                "4. Nghỉ giữa các hiệp\n" +
                "5. Lặp lại",
                60
        ));
        
        return exercises;
    }
    
    public static ArrayList<ExerciseItem> getStretchingExercises() {
        ArrayList<ExerciseItem> exercises = new ArrayList<>();
        
        exercises.add(new ExerciseItem(
                "Bài tập dựa tường kéo giãn bắp chân",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập dựa tường kéo giãn bắp chân"),
                "Wall calf stretch",
                "1. Đứng cách tường một bước chân\n" +
                "2. Đặt tay lên tường\n" +
                "3. Đưa một chân ra sau, giữ thẳng\n" +
                "4. Đẩy hông về phía trước, cảm nhận căng ở bắp chân\n" +
                "5. Giữ 30 giây, đổi chân",
                0
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập kéo giãn cơ đùi sau",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập kéo giãn cơ đùi sau"),
                "Hamstring stretch",
                "1. Ngồi trên sàn, một chân duỗi thẳng\n" +
                "2. Cúi người về phía chân duỗi\n" +
                "3. Giữ 30 giây, cảm nhận căng ở đùi sau\n" +
                "4. Đổi chân\n" +
                "5. Lặp lại",
                0
        ));
        
        return exercises;
    }
    
    public static ArrayList<ExerciseItem> getFunctionalExercises() {
        ArrayList<ExerciseItem> exercises = new ArrayList<>();
        
        exercises.add(new ExerciseItem(
                "Burpees",
                ExerciseImageUrls.getImageUrlForExercise("Burpees"),
                "Bài tập chức năng toàn thân",
                "1. Đứng thẳng\n" +
                "2. Ngồi xổm, đặt tay xuống sàn\n" +
                "3. Nhảy chân ra sau, vào tư thế plank\n" +
                "4. Nhảy chân về, đứng lên\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Mountain Climbers",
                ExerciseImageUrls.getImageUrlForExercise("Mountain Climbers"),
                "Bài tập chức năng cardio",
                "1. Vào tư thế plank\n" +
                "2. Luân phiên đưa chân lên\n" +
                "3. Duy trì tốc độ ổn định\n" +
                "4. Giữ cơ bụng căng\n" +
                "5. Lặp lại",
                45
        ));
        
        exercises.add(new ExerciseItem(
                "Kettlebell Swing",
                ExerciseImageUrls.getImageUrlForExercise("Kettlebell Swing"),
                "Bài tập chức năng với tạ ấm",
                "1. Đứng, cầm tạ ấm bằng hai tay\n" +
                "2. Hạ tạ xuống giữa hai chân\n" +
                "3. Đẩy hông và đưa tạ lên ngang ngực\n" +
                "4. Hạ tạ xuống\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Box Jumps",
                ExerciseImageUrls.getImageUrlForExercise("Box Jumps"),
                "Nhảy lên hộp",
                "1. Đứng trước hộp hoặc bục\n" +
                "2. Nhảy lên hộp bằng cả hai chân\n" +
                "3. Đứng thẳng trên hộp\n" +
                "4. Nhảy xuống\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        return exercises;
    }
    
    // Cơ mông
    public static ArrayList<ExerciseItem> getGlutesExercises() {
        ArrayList<ExerciseItem> exercises = new ArrayList<>();
        
        exercises.add(new ExerciseItem(
                "Bài tập mông đẩy hông",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập mông đẩy hông"),
                "Hip thrust",
                "1. Ngồi trên sàn, lưng dựa vào ghế\n" +
                "2. Đặt tạ đòn lên hông\n" +
                "3. Đẩy hông lên cao\n" +
                "4. Giữ 1 giây ở vị trí cao nhất\n" +
                "5. Hạ hông xuống",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập mông kéo tạ chết Romania",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập mông kéo tạ chết Romania"),
                "Romanian deadlift",
                "1. Đứng, cầm tạ đòn\n" +
                "2. Hạ tạ xuống, giữ chân thẳng\n" +
                "3. Cảm nhận căng ở mông và đùi sau\n" +
                "4. Đứng thẳng lên\n" +
                "5. Lặp lại động tác",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập mông cầu mông",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập mông cầu mông"),
                "Glute bridge",
                "1. Nằm ngửa, co gối\n" +
                "2. Đẩy hông lên cao\n" +
                "3. Giữ 1 giây ở vị trí cao nhất\n" +
                "4. Hạ hông xuống\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập mông chùng chân Bulgaria",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập mông chùng chân Bulgaria"),
                "Bulgarian split squats",
                "1. Đặt một chân lên ghế phía sau\n" +
                "2. Hạ người xuống, tập trung vào mông\n" +
                "3. Đẩy người lên mạnh mẽ\n" +
                "4. Đổi chân\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập mông đá chân sau",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau"),
                "Donkey kicks",
                "1. Quỳ trên sàn, chống bằng tay và đầu gối\n" +
                "2. Đá một chân lên sau, giữ thẳng\n" +
                "3. Giữ 1 giây ở vị trí cao nhất\n" +
                "4. Hạ chân xuống\n" +
                "5. Đổi chân, lặp lại",
                45
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập mông mở chân nằm",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau"),
                "Clamshells",
                "1. Nằm nghiêng, co gối\n" +
                "2. Mở đầu gối trên lên\n" +
                "3. Giữ 1 giây\n" +
                "4. Hạ đầu gối xuống\n" +
                "5. Đổi bên, lặp lại",
                30
        ));
        
        return exercises;
    }
    
    // Cơ đùi trước
    public static ArrayList<ExerciseItem> getQuadricepsExercises() {
        ArrayList<ExerciseItem> exercises = new ArrayList<>();
        
        exercises.add(new ExerciseItem(
                "Bài tập đùi trước ngồi xổm với tạ",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập đùi trước ngồi xổm với tạ"),
                "Barbell squats",
                "1. Đặt tạ đòn lên vai sau\n" +
                "2. Đứng thẳng, hai chân rộng bằng vai\n" +
                "3. Hạ người xuống như ngồi xổm\n" +
                "4. Đẩy người lên mạnh mẽ\n" +
                "5. Lặp lại động tác",
                120
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập đùi trước duỗi chân trên máy",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập đùi trước duỗi chân trên máy"),
                "Leg extension",
                "1. Ngồi trên máy, đặt chân dưới thanh đỡ\n" +
                "2. Duỗi chân lên thẳng\n" +
                "3. Giữ 1 giây, cảm nhận cơ đùi trước co lại\n" +
                "4. Từ từ hạ chân xuống\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập đùi trước chùng chân",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập đùi trước chùng chân"),
                "Lunges",
                "1. Đứng thẳng, bước một chân về phía trước\n" +
                "2. Hạ người xuống, đầu gối trước gập 90 độ\n" +
                "3. Đẩy người lên, bước chân về\n" +
                "4. Đổi chân\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập đùi trước đẩy chân trên máy",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập đùi trước đẩy chân trên máy"),
                "Leg press",
                "1. Ngồi trên máy, đặt chân lên bàn đạp\n" +
                "2. Hạ bàn đạp xuống\n" +
                "3. Đẩy bàn đạp lên mạnh mẽ\n" +
                "4. Tập trung vào cơ đùi trước\n" +
                "5. Lặp lại động tác",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập đùi trước ngồi xổm một chân",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau"),
                "Pistol squats",
                "1. Đứng trên một chân\n" +
                "2. Hạ người xuống, chân kia duỗi thẳng\n" +
                "3. Đẩy người lên\n" +
                "4. Đổi chân\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        return exercises;
    }
    
    // Cơ đùi sau
    public static ArrayList<ExerciseItem> getHamstringsExercises() {
        ArrayList<ExerciseItem> exercises = new ArrayList<>();
        
        exercises.add(new ExerciseItem(
                "Bài tập đùi sau gập chân trên máy",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập đùi sau gập chân trên máy"),
                "Leg curl",
                "1. Nằm sấp trên máy, đặt chân dưới thanh đỡ\n" +
                "2. Gập chân lên về phía mông\n" +
                "3. Giữ 1 giây, cảm nhận cơ đùi sau co lại\n" +
                "4. Từ từ hạ chân xuống\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập đùi sau kéo tạ chết Romania",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập đùi sau kéo tạ chết Romania"),
                "Romanian deadlift",
                "1. Đứng, cầm tạ đòn\n" +
                "2. Hạ tạ xuống, giữ chân thẳng\n" +
                "3. Cảm nhận căng ở đùi sau\n" +
                "4. Đứng thẳng lên\n" +
                "5. Lặp lại động tác",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập đùi sau gập chân đứng",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập đùi sau gập chân đứng"),
                "Standing leg curl",
                "1. Đứng trên máy, đặt một chân dưới thanh đỡ\n" +
                "2. Gập chân lên về phía mông\n" +
                "3. Giữ 1 giây\n" +
                "4. Hạ chân xuống\n" +
                "5. Đổi chân, lặp lại",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập đùi sau gập chân ngồi",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập đùi sau gập chân ngồi"),
                "Seated leg curl",
                "1. Ngồi trên máy, đặt chân dưới thanh đỡ\n" +
                "2. Gập chân lên về phía mông\n" +
                "3. Giữ 1 giây\n" +
                "4. Từ từ hạ chân xuống\n" +
                "5. Lặp lại động tác",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập đùi sau kéo tạ chết một chân",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau"),
                "Single-leg Romanian deadlift",
                "1. Đứng trên một chân, cầm tạ đơn\n" +
                "2. Hạ tạ xuống, chân kia duỗi ra sau\n" +
                "3. Cảm nhận căng ở đùi sau\n" +
                "4. Đứng thẳng lên\n" +
                "5. Đổi chân, lặp lại",
                60
        ));
        
        return exercises;
    }
    
    // Bắp chân
    public static ArrayList<ExerciseItem> getCalvesExercises() {
        ArrayList<ExerciseItem> exercises = new ArrayList<>();
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp chân nâng gót chân đứng",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp chân nâng gót chân đứng"),
                "Standing calf raises",
                "1. Đứng thẳng, hai chân rộng bằng vai\n" +
                "2. Nâng gót chân lên cao\n" +
                "3. Giữ 1 giây ở vị trí cao nhất\n" +
                "4. Từ từ hạ gót chân xuống\n" +
                "5. Lặp lại động tác",
                45
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp chân nâng gót chân với tạ",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp chân nâng gót chân với tạ"),
                "Weighted calf raises",
                "1. Đứng, cầm tạ đơn hoặc đặt tạ đòn lên vai\n" +
                "2. Nâng gót chân lên cao\n" +
                "3. Giữ 1 giây\n" +
                "4. Từ từ hạ gót chân xuống\n" +
                "5. Lặp lại động tác",
                45
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp chân nâng gót chân ngồi",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp chân nâng gót chân ngồi"),
                "Seated calf raises",
                "1. Ngồi trên máy, đặt đầu gối dưới thanh đỡ\n" +
                "2. Nâng gót chân lên\n" +
                "3. Giữ 1 giây\n" +
                "4. Từ từ hạ gót chân xuống\n" +
                "5. Lặp lại động tác",
                45
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp chân nâng gót chân một chân",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp chân nâng gót chân một chân"),
                "Single-leg calf raises",
                "1. Đứng trên một chân\n" +
                "2. Nâng gót chân lên cao\n" +
                "3. Giữ 1 giây\n" +
                "4. Hạ gót chân xuống\n" +
                "5. Đổi chân, lặp lại",
                45
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập bắp chân nhảy",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau"),
                "Calf jumps",
                "1. Đứng thẳng\n" +
                "2. Nhảy lên cao, nâng gót chân\n" +
                "3. Tiếp đất nhẹ nhàng\n" +
                "4. Lặp lại liên tục\n" +
                "5. Duy trì tốc độ ổn định",
                30
        ));
        
        return exercises;
    }
    
    // Cơ xô (Lats)
    public static ArrayList<ExerciseItem> getLatsExercises() {
        ArrayList<ExerciseItem> exercises = new ArrayList<>();
        
        exercises.add(new ExerciseItem(
                "Bài tập xô kéo xà đơn",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập xô kéo xà đơn"),
                "Pull-ups",
                "1. Treo người trên xà đơn\n" +
                "2. Kéo người lên cho đến khi cằm qua xà\n" +
                "3. Giữ 1 giây\n" +
                "4. Từ từ hạ người xuống\n" +
                "5. Lặp lại động tác",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập xô kéo cáp rộng",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập xô kéo cáp rộng"),
                "Wide-grip lat pulldown",
                "1. Ngồi trên máy, cầm thanh kéo rộng\n" +
                "2. Kéo thanh xuống ngang ngực\n" +
                "3. Giữ 1 giây\n" +
                "4. Từ từ đưa thanh lên\n" +
                "5. Lặp lại động tác",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập xô kéo tạ đòn",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập xô kéo tạ đòn"),
                "Barbell row",
                "1. Đứng, cúi người, cầm tạ đòn\n" +
                "2. Kéo tạ lên ngang bụng\n" +
                "3. Tập trung vào cơ xô\n" +
                "4. Giữ 1 giây\n" +
                "5. Hạ tạ xuống",
                90
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập xô kéo tạ đơn một tay",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập xô kéo tạ đơn một tay"),
                "One-arm dumbbell row",
                "1. Đặt một tay và đầu gối lên ghế\n" +
                "2. Cầm tạ đơn bằng tay còn lại\n" +
                "3. Kéo tạ lên ngang bụng\n" +
                "4. Tập trung vào cơ xô\n" +
                "5. Đổi tay, lặp lại",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập xô kéo cáp một tay",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau"),
                "Single-arm cable row",
                "1. Ngồi trên máy, cầm tay cầm bằng một tay\n" +
                "2. Kéo tay cầm về phía bụng\n" +
                "3. Tập trung vào cơ xô\n" +
                "4. Giữ 1 giây\n" +
                "5. Đưa tay về, đổi tay",
                60
        ));
        
        return exercises;
    }
    
    // Cơ cầu vai (Traps)
    public static ArrayList<ExerciseItem> getTrapsExercises() {
        ArrayList<ExerciseItem> exercises = new ArrayList<>();
        
        exercises.add(new ExerciseItem(
                "Bài tập cầu vai nhún vai với tạ đòn",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập cầu vai nhún vai với tạ đòn"),
                "Barbell shrugs",
                "1. Đứng thẳng, cầm tạ đòn\n" +
                "2. Nhún vai lên cao\n" +
                "3. Giữ 1 giây ở vị trí cao nhất\n" +
                "4. Từ từ hạ vai xuống\n" +
                "5. Lặp lại động tác",
                45
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập cầu vai nhún vai với tạ đơn",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập cầu vai nhún vai với tạ đơn"),
                "Dumbbell shrugs",
                "1. Đứng thẳng, cầm tạ đơn bằng cả hai tay\n" +
                "2. Nhún vai lên cao\n" +
                "3. Giữ 1 giây\n" +
                "4. Từ từ hạ vai xuống\n" +
                "5. Lặp lại động tác",
                45
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập cầu vai kéo tạ đòn cao",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập cầu vai kéo tạ đòn cao"),
                "Upright row",
                "1. Đứng thẳng, cầm tạ đòn\n" +
                "2. Kéo tạ lên ngang ngực\n" +
                "3. Khuỷu tay cao hơn cổ tay\n" +
                "4. Giữ 1 giây\n" +
                "5. Hạ tạ xuống",
                60
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập cầu vai kéo tạ đòn chết",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập cầu vai kéo tạ đòn chết"),
                "Deadlift",
                "1. Đứng, cầm tạ đòn\n" +
                "2. Cúi xuống, kéo tạ lên\n" +
                "3. Tập trung vào cơ cầu vai\n" +
                "4. Giữ 1 giây ở vị trí đứng\n" +
                "5. Hạ tạ xuống",
                120
        ));
        
        exercises.add(new ExerciseItem(
                "Bài tập cầu vai nhún vai với máy cáp",
                ExerciseImageUrls.getImageUrlForExercise("Bài tập bắp tay sau"),
                "Cable shrugs",
                "1. Đứng trước máy cáp, cầm tay cầm\n" +
                "2. Nhún vai lên cao\n" +
                "3. Giữ 1 giây\n" +
                "4. Từ từ hạ vai xuống\n" +
                "5. Lặp lại động tác",
                45
        ));
        
        return exercises;
    }
    
    /**
     * Lấy tất cả bài tập từ tất cả các nhóm cơ
     */
    public static ArrayList<ExerciseItem> getAllExercises() {
        ArrayList<ExerciseItem> allExercises = new ArrayList<>();
        
        // Thêm tất cả exercises từ các nhóm cơ
        allExercises.addAll(getBicepsExercises());
        allExercises.addAll(getTricepsExercises());
        allExercises.addAll(getChestExercises());
        allExercises.addAll(getBackExercises());
        allExercises.addAll(getLegExercises());
        allExercises.addAll(getShoulderExercises());
        allExercises.addAll(getAbsExercises());
        allExercises.addAll(getForearmExercises());
        allExercises.addAll(getCardioExercises());
        allExercises.addAll(getStretchingExercises());
        allExercises.addAll(getFunctionalExercises());
        allExercises.addAll(getGlutesExercises());
        allExercises.addAll(getQuadricepsExercises());
        allExercises.addAll(getHamstringsExercises());
        allExercises.addAll(getCalvesExercises());
        allExercises.addAll(getLatsExercises());
        allExercises.addAll(getTrapsExercises());
        
        return allExercises;
    }
}

