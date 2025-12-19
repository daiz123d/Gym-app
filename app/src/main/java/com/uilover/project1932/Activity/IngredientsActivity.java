package com.uilover.project1932.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.uilover.project1932.Adapter.HandbookListAdapter;
import com.uilover.project1932.Data.HandbookItem;
import com.uilover.project1932.databinding.ActivityNutritionBinding;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {
    ActivityNutritionBinding binding;
    private HandbookListAdapter adapter;
    private ArrayList<HandbookItem> allItems;
    private ArrayList<HandbookItem> filteredItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNutritionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setupBackButton();
        loadIngredientsItems();
        setupSearch();
    }

    private void setupBackButton() {
        if (binding.backBtn != null) {
            binding.backBtn.setOnClickListener(v -> finish());
        }
    }

    private void loadIngredientsItems() {
        allItems = new ArrayList<>();
        
        // Sữa và các sản phẩm từ sữa
        allItems.add(new HandbookItem(
                "Sữa và các sản phẩm từ sữa, sữa chua, phô mai cottage",
                "pic_1",
                "Nguồn protein và canxi tuyệt vời cho cơ bắp và xương",
                "Sữa và các sản phẩm từ sữa là nguồn dinh dưỡng quan trọng:\n\n" +
                "Giá trị dinh dưỡng (100g):\n" +
                "• Sữa tươi: 61 Kcal, 3.2g protein, 4.8g carb, 3.3g fat\n" +
                "• Sữa chua: 59 Kcal, 10g protein, 3.6g carb, 0.4g fat\n" +
                "• Phô mai cottage: 98 Kcal, 11g protein, 3.4g carb, 4.3g fat\n\n" +
                "Lợi ích:\n" +
                "• Protein chất lượng cao (casein và whey)\n" +
                "• Canxi cho xương chắc khỏe\n" +
                "• Probiotic từ sữa chua\n" +
                "• Hỗ trợ phục hồi sau tập luyện\n\n" +
                "Khuyến nghị: 200-300ml sữa hoặc 150-200g sữa chua mỗi ngày."
        ));
        
        // Ngũ cốc
        allItems.add(new HandbookItem(
                "Ngũ cốc, cháo, khoai tây chiên",
                "pic_2",
                "Nguồn carbohydrate phức hợp cho năng lượng bền vững",
                "Ngũ cốc và tinh bột là nguồn năng lượng chính:\n\n" +
                "Giá trị dinh dưỡng (100g):\n" +
                "• Yến mạch: 389 Kcal, 17g protein, 66g carb, 7g fat\n" +
                "• Gạo lứt: 111 Kcal, 2.6g protein, 23g carb, 0.9g fat\n" +
                "• Khoai tây: 77 Kcal, 2g protein, 17g carb, 0.1g fat\n" +
                "• Khoai tây chiên: 312 Kcal, 3.4g protein, 41g carb, 15g fat\n\n" +
                "Lợi ích:\n" +
                "• Năng lượng bền vững cho tập luyện\n" +
                "• Chất xơ hỗ trợ tiêu hóa\n" +
                "• Vitamin B cho trao đổi chất\n\n" +
                "Khuyến nghị: 150-200g ngũ cốc mỗi bữa chính."
        ));
        
        // Trứng và phô mai
        allItems.add(new HandbookItem(
                "Trứng, phô mai, phô mai chế biến",
                "pic_3",
                "Protein hoàn chỉnh với tất cả axit amin thiết yếu",
                "Trứng là thực phẩm hoàn hảo cho người tập gym:\n\n" +
                "Giá trị dinh dưỡng:\n" +
                "• Trứng gà (1 quả ~50g): 72 Kcal, 6.3g protein, 0.4g carb, 4.8g fat\n" +
                "• Phô mai (100g): 113 Kcal, 25g protein, 1g carb, 1g fat\n" +
                "• Phô mai chế biến (100g): 264 Kcal, 16g protein, 4g carb, 21g fat\n\n" +
                "Lợi ích:\n" +
                "• Protein hoàn chỉnh (PDCAAS = 1.0)\n" +
                "• Choline cho sức khỏe não bộ\n" +
                "• Vitamin D tự nhiên\n" +
                "• Lutein và zeaxanthin cho mắt\n\n" +
                "Khuyến nghị: 2-4 quả trứng mỗi ngày, 50-100g phô mai."
        ));
        
        // Thịt gà
        allItems.add(new HandbookItem(
                "Thịt gà và thịt gia cầm khác, thịt gà xay, phụ phẩm",
                "pic_1_1",
                "Nguồn protein nạc hàng đầu cho cơ bắp",
                "Thịt gà là lựa chọn số 1 của người tập gym:\n\n" +
                "Giá trị dinh dưỡng (100g thịt gà ức nấu chín):\n" +
                "• Calo: 165 Kcal\n" +
                "• Protein: 31g\n" +
                "• Carb: 0g\n" +
                "• Fat: 3.6g\n\n" +
                "Các phần khác:\n" +
                "• Đùi gà: 209 Kcal, 26g protein, 10.9g fat\n" +
                "• Cánh gà: 203 Kcal, 30g protein, 8.1g fat\n" +
                "• Gan gà: 167 Kcal, 24.5g protein, 6.3g fat\n\n" +
                "Lợi ích:\n" +
                "• Protein nạc, ít mỡ\n" +
                "• Dễ tiêu hóa\n" +
                "• Giá cả phải chăng\n" +
                "• Linh hoạt trong chế biến\n\n" +
                "Khuyến nghị: 150-200g thịt gà mỗi bữa, 2-3 bữa/ngày."
        ));
        
        // Rau củ quả
        allItems.add(new HandbookItem(
                "Rau củ quả",
                "pic_2_1",
                "Nguồn vitamin, khoáng chất và chất xơ thiết yếu",
                "Rau củ quả cung cấp vi chất dinh dưỡng quan trọng:\n\n" +
                "Giá trị dinh dưỡng (100g):\n" +
                "• Bông cải xanh: 34 Kcal, 2.8g protein, 7g carb, 0.4g fat\n" +
                "• Cà rốt: 41 Kcal, 0.9g protein, 10g carb, 0.2g fat\n" +
                "• Cà chua: 18 Kcal, 0.9g protein, 3.9g carb, 0.2g fat\n" +
                "• Rau bina: 23 Kcal, 2.9g protein, 3.6g carb, 0.4g fat\n" +
                "• Chuối: 89 Kcal, 1.1g protein, 23g carb, 0.3g fat\n\n" +
                "Lợi ích:\n" +
                "• Vitamin và khoáng chất\n" +
                "• Chất chống oxy hóa\n" +
                "• Chất xơ cho tiêu hóa\n" +
                "• Hỗ trợ phục hồi\n\n" +
                "Khuyến nghị: 400-500g rau củ quả mỗi ngày, đa dạng màu sắc."
        ));

        filteredItems = new ArrayList<>(allItems);
        
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HandbookListAdapter(filteredItems, IngredientsDetailActivity.class);
        binding.recyclerView.setAdapter(adapter);
        
        // Update title
        binding.titleText.setText("Danh sách các nguyên liệu và lượng calo");
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

