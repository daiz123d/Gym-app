package com.uilover.project1932.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.uilover.project1932.Adapter.EncyclopediaAdapter;
import com.uilover.project1932.Data.HandbookItem;
import com.uilover.project1932.databinding.ActivityEncyclopediaBinding;

import java.util.ArrayList;

public class EncyclopediaActivity extends AppCompatActivity {
    ActivityEncyclopediaBinding binding;
    private EncyclopediaAdapter adapter;
    private ArrayList<HandbookItem> allItems;
    private ArrayList<HandbookItem> filteredItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEncyclopediaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setupBackButton();
        loadEncyclopediaItems();
        setupSearch();
    }

    private void setupBackButton() {
        if (binding.backBtn != null) {
            binding.backBtn.setOnClickListener(v -> finish());
        }
    }

    private void loadEncyclopediaItems() {
        allItems = new ArrayList<>();
        
        // Anabolic Steroid
        allItems.add(new HandbookItem(
                "Anabolic Steroid (Steroid đồng hóa) là gì?",
                "pic_1",
                "Tìm hiểu về steroid đồng hóa và tác động của chúng",
                "Anabolic Steroid (Steroid đồng hóa) là các hormone tổng hợp tương tự testosterone.\n\n" +
                "Cơ chế hoạt động:\n" +
                "• Kích thích tổng hợp protein trong cơ bắp\n" +
                "• Tăng cường sức mạnh và sức bền\n" +
                "• Tăng khối lượng cơ bắp\n" +
                "• Giảm thời gian phục hồi\n\n" +
                "Tác dụng phụ:\n" +
                "• Tổn thương gan\n" +
                "• Vấn đề tim mạch\n" +
                "• Rối loạn hormone\n" +
                "• Tâm lý bất ổn\n\n" +
                "CẢNH BÁO: Sử dụng steroid không được kiểm soát có thể gây nguy hiểm nghiêm trọng đến sức khỏe."
        ));
        
        // Bài tập khu cơ
        allItems.add(new HandbookItem(
                "Bài tập khu cơ (Bài tập chuyên biệt)",
                "pic_2",
                "Các bài tập tập trung vào từng nhóm cơ cụ thể",
                "Bài tập khu cơ là các bài tập được thiết kế để tác động vào một nhóm cơ cụ thể.\n\n" +
                "Lợi ích:\n" +
                "• Phát triển cân đối các nhóm cơ\n" +
                "• Khắc phục điểm yếu\n" +
                "• Tăng cường sức mạnh cụ thể\n" +
                "• Cải thiện hình dáng cơ thể\n\n" +
                "Ví dụ:\n" +
                "• Ngực: Bench Press, Flyes\n" +
                "• Lưng: Pull-ups, Rows\n" +
                "• Chân: Squats, Leg Press\n" +
                "• Vai: Overhead Press, Lateral Raises\n\n" +
                "Lưu ý: Kết hợp bài tập khu cơ với bài tập tổng hợp để có kết quả tốt nhất."
        ));
        
        // Béo bụng
        allItems.add(new HandbookItem(
                "Béo bụng",
                "pic_3",
                "Nguyên nhân và cách giảm mỡ bụng hiệu quả",
                "Béo bụng là vấn đề phổ biến, đặc biệt ở nam giới.\n\n" +
                "Nguyên nhân:\n" +
                "• Chế độ ăn không lành mạnh\n" +
                "• Ít vận động\n" +
                "• Căng thẳng (cortisol)\n" +
                "• Di truyền\n" +
                "• Tuổi tác\n\n" +
                "Cách giảm mỡ bụng:\n" +
                "1. Chế độ ăn: Calo thâm hụt, nhiều protein, ít carb tinh chế\n" +
                "2. Tập luyện: Cardio + Strength training\n" +
                "3. Ngủ đủ: 7-9 giờ mỗi đêm\n" +
                "4. Quản lý căng thẳng: Thiền, yoga\n" +
                "5. Tránh rượu và đồ uống có đường\n\n" +
                "Lưu ý: Không thể giảm mỡ tại một điểm cụ thể. Cần giảm mỡ toàn thân."
        ));
        
        // Body types
        allItems.add(new HandbookItem(
                "Bạn thuộc tạng người nào: Ectomorph (gầy), Mesomorph (cơ bắp), Endomorph (béo)?",
                "pic_1_1",
                "Hiểu về các tạng người và cách tập luyện phù hợp",
                "Có 3 tạng người chính:\n\n" +
                "1. ECTOMORPH (Gầy)\n" +
                "   Đặc điểm:\n" +
                "   • Người gầy, khó tăng cân\n" +
                "   • Trao đổi chất nhanh\n" +
                "   • Xương nhỏ, vai hẹp\n\n" +
                "   Cách tập:\n" +
                "   • Tập 3-4 lần/tuần, 45-60 phút\n" +
                "   • Tập compound movements\n" +
                "   • Ăn nhiều calo, nhiều carb\n\n" +
                "2. MESOMORPH (Cơ bắp)\n" +
                "   Đặc điểm:\n" +
                "   • Dễ tăng cơ, dễ giảm mỡ\n" +
                "   • Trao đổi chất tốt\n" +
                "   • Xương to, vai rộng\n\n" +
                "   Cách tập:\n" +
                "   • Tập 4-5 lần/tuần\n" +
                "   • Kết hợp strength và hypertrophy\n" +
                "   • Chế độ ăn cân bằng\n\n" +
                "3. ENDOMORPH (Béo)\n" +
                "   Đặc điểm:\n" +
                "   • Dễ tăng cân, khó giảm mỡ\n" +
                "   • Trao đổi chất chậm\n" +
                "   • Xương to, dễ tích mỡ\n\n" +
                "   Cách tập:\n" +
                "   • Tập 5-6 lần/tuần, nhiều cardio\n" +
                "   • Chế độ ăn ít carb, nhiều protein\n" +
                "   • Calo thâm hụt\n\n" +
                "Lưu ý: Hầu hết mọi người là sự kết hợp của các tạng người."
        ));
        
        // Carbohydrate
        allItems.add(new HandbookItem(
                "Carbohydrate đơn giản và phức hợp",
                "pic_2_1",
                "Hiểu về carb và vai trò trong dinh dưỡng thể thao",
                "Carbohydrate là nguồn năng lượng chính cho cơ thể.\n\n" +
                "CARB ĐƠN GIẢN:\n" +
                "• Đường, mật ong, trái cây\n" +
                "• Hấp thu nhanh, tăng đường huyết nhanh\n" +
                "• Tốt cho: Sau tập luyện, năng lượng tức thì\n" +
                "• Ví dụ: Bánh mì trắng, kẹo, nước ngọt\n\n" +
                "CARB PHỨC HỢP:\n" +
                "• Yến mạch, gạo lứt, khoai lang\n" +
                "• Hấp thu chậm, năng lượng bền vững\n" +
                "• Tốt cho: Bữa chính, năng lượng lâu dài\n" +
                "• Ví dụ: Gạo lứt, yến mạch, đậu\n\n" +
                "Khuyến nghị cho người tập gym:\n" +
                "• Trước tập: Carb phức hợp (1-2 giờ trước)\n" +
                "• Sau tập: Carb đơn giản (trong 30 phút)\n" +
                "• Tổng lượng: 3-5g/kg thể trọng/ngày\n\n" +
                "Lưu ý: Điều chỉnh theo mục tiêu (tăng cơ hay giảm mỡ)."
        ));

        filteredItems = new ArrayList<>(allItems);
        
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EncyclopediaAdapter(filteredItems, EncyclopediaDetailActivity.class);
        binding.recyclerView.setAdapter(adapter);
    }

    private void setupSearch() {
        if (binding.searchBar != null) {
            binding.searchBar.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    filterItems(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }
    }

    private void filterItems(String query) {
        filteredItems.clear();
        if (query.isEmpty()) {
            filteredItems.addAll(allItems);
        } else {
            String lowerQuery = query.toLowerCase();
            for (HandbookItem item : allItems) {
                if (item.getTitle().toLowerCase().contains(lowerQuery) ||
                    item.getDescription().toLowerCase().contains(lowerQuery)) {
                    filteredItems.add(item);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}

