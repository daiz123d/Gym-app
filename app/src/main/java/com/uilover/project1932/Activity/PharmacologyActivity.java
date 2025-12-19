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

public class PharmacologyActivity extends AppCompatActivity {
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
        loadPharmacologyItems();
        setupSearch();
    }

    private void setupBackButton() {
        if (binding.backBtn != null) {
            binding.backBtn.setOnClickListener(v -> finish());
        }
    }

    private void loadPharmacologyItems() {
        allItems = new ArrayList<>();
        
        // Hormone tăng trưởng
        allItems.add(new HandbookItem(
                "Hormone tăng trưởng",
                "pic_1",
                "Growth Hormone (GH) - Hormone quan trọng cho tăng trưởng và phục hồi",
                "Growth Hormone (GH) là hormone peptide được sản xuất bởi tuyến yên.\n\n" +
                "Chức năng:\n" +
                "• Kích thích tăng trưởng tế bào\n" +
                "• Tăng tổng hợp protein\n" +
                "• Đốt cháy mỡ\n" +
                "• Tăng cường phục hồi\n\n" +
                "Lưu ý: Chỉ sử dụng dưới sự giám sát y tế. Có thể gây tác dụng phụ nghiêm trọng nếu sử dụng không đúng cách."
        ));
        
        // Peptide
        allItems.add(new HandbookItem(
                "Peptide",
                "pic_2",
                "Peptide là chuỗi axit amin ngắn, có nhiều ứng dụng trong thể thao",
                "Peptide là các phân tử được tạo thành từ 2-50 axit amin. Trong thể thao, peptide được sử dụng để:\n\n" +
                "Các loại peptide phổ biến:\n" +
                "• BPC-157: Phục hồi chấn thương\n" +
                "• TB-500: Tăng cường phục hồi\n" +
                "• GHRP-6: Kích thích GH\n" +
                "• Ipamorelin: Tăng GH tự nhiên\n\n" +
                "Lưu ý: Cần có kiến thức chuyên sâu và tư vấn y tế trước khi sử dụng."
        ));
        
        // Tiêm
        allItems.add(new HandbookItem(
                "Tiêm",
                "pic_3",
                "Các sản phẩm tiêm trong thể thao - Cần kiến thức và kỹ thuật đúng",
                "Các sản phẩm tiêm trong thể thao bao gồm:\n\n" +
                "1. Vitamin B12\n" +
                "   • Tăng cường năng lượng\n" +
                "   • Hỗ trợ trao đổi chất\n\n" +
                "2. Testosterone\n" +
                "   • Tăng cường sức mạnh\n" +
                "   • Xây dựng cơ bắp\n\n" +
                "3. HGH (Human Growth Hormone)\n" +
                "   • Tăng trưởng cơ bắp\n" +
                "   • Đốt cháy mỡ\n\n" +
                "CẢNH BÁO: Chỉ sử dụng dưới sự giám sát của bác sĩ. Tự tiêm có thể gây nguy hiểm."
        ));
        
        // Sản phẩm uống
        allItems.add(new HandbookItem(
                "Sản phẩm uống",
                "pic_1_1",
                "Các sản phẩm dược phẩm dạng uống trong thể thao",
                "Các sản phẩm uống phổ biến trong thể thao:\n\n" +
                "1. Prohormones\n" +
                "   • Chuyển đổi thành hormone trong cơ thể\n" +
                "   • Cần chu kỳ và PCT đúng cách\n\n" +
                "2. SARMs (Selective Androgen Receptor Modulators)\n" +
                "   • Tác động chọn lọc lên thụ thể androgen\n" +
                "   • Ít tác dụng phụ hơn steroid\n\n" +
                "3. Aromatase Inhibitors\n" +
                "   • Ngăn chặn chuyển đổi testosterone thành estrogen\n" +
                "   • Sử dụng trong PCT\n\n" +
                "Lưu ý: Tất cả các sản phẩm này đều cần kiến thức chuyên sâu và có thể có tác dụng phụ."
        ));

        filteredItems = new ArrayList<>(allItems);
        
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HandbookListAdapter(filteredItems, PharmacologyDetailActivity.class);
        binding.recyclerView.setAdapter(adapter);
        
        // Update title
        binding.titleText.setText("Dược lý học");
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

