package com.uilover.project1932.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.uilover.project1932.Adapter.HandbookListAdapter;
import com.uilover.project1932.Data.HandbookItem;
import com.uilover.project1932.Helper.NavigationHelper;
import com.uilover.project1932.databinding.ActivityNutritionBinding;

import java.util.ArrayList;

public class NutritionActivity extends AppCompatActivity {
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

        setupNavigation();
        setupBackButton();
        loadNutritionItems();
        setupSearch();
    }

    private void setupNavigation() {
        NavigationHelper.setupBottomNavigation(
                binding.homeTab,
                binding.favoriteTab,
                binding.cartTab,
                binding.profileTab,
                this
        );
    }

    private void setupBackButton() {
        if (binding.backBtn != null) {
            binding.backBtn.setOnClickListener(v -> finish());
        }
    }

    private void loadNutritionItems() {
        allItems = new ArrayList<>();
        
        // Protein
        allItems.add(new HandbookItem(
                "Protein",
                "pic_1",
                "Whey Protein là nguồn protein chất lượng cao, dễ hấp thu",
                "Whey Protein là một trong những loại protein bổ sung phổ biến nhất trong thể thao. " +
                "Nó được chiết xuất từ sữa và chứa tất cả 9 axit amin thiết yếu. " +
                "Whey Protein giúp:\n\n" +
                "• Phục hồi và xây dựng cơ bắp sau tập luyện\n" +
                "• Tăng cường sức mạnh và hiệu suất\n" +
                "• Hỗ trợ giảm cân bằng cách tăng cảm giác no\n" +
                "• Cải thiện hệ miễn dịch\n\n" +
                "Liều dùng khuyến nghị: 20-30g sau khi tập luyện hoặc giữa các bữa ăn."
        ));
        
        // Gainer
        allItems.add(new HandbookItem(
                "Gainer",
                "pic_2",
                "Mass Gainer giúp tăng cân và xây dựng cơ bắp cho người gầy",
                "Mass Gainer là sản phẩm bổ sung dinh dưỡng dành cho người muốn tăng cân và xây dựng cơ bắp. " +
                "Sản phẩm này chứa hàm lượng cao carbohydrate và protein.\n\n" +
                "Thành phần chính:\n" +
                "• Protein (20-30g mỗi khẩu phần)\n" +
                "• Carbohydrate phức hợp (50-80g)\n" +
                "• Chất béo lành mạnh\n" +
                "• Vitamin và khoáng chất\n\n" +
                "Cách sử dụng: Uống 1-2 lần/ngày, tốt nhất sau khi tập luyện hoặc giữa các bữa ăn."
        ));
        
        // Creatine
        allItems.add(new HandbookItem(
                "Creatine",
                "pic_3",
                "Creatine giúp tăng sức mạnh và hiệu suất tập luyện",
                "Creatine là một hợp chất tự nhiên được tìm thấy trong cơ bắp. " +
                "Nó giúp tạo ra năng lượng cho các hoạt động cường độ cao.\n\n" +
                "Lợi ích:\n" +
                "• Tăng sức mạnh và sức bền\n" +
                "• Cải thiện hiệu suất tập luyện\n" +
                "• Tăng khối lượng cơ bắp\n" +
                "• Hỗ trợ phục hồi nhanh hơn\n\n" +
                "Liều dùng: 3-5g mỗi ngày, có thể dùng trước hoặc sau khi tập."
        ));
        
        // Axit amin
        allItems.add(new HandbookItem(
                "Axit amin",
                "pic_1_1",
                "BCAA và EAA hỗ trợ phục hồi và xây dựng cơ bắp",
                "Axit amin là các khối xây dựng của protein. Có 20 loại axit amin, trong đó 9 loại là thiết yếu.\n\n" +
                "BCAA (Branched-Chain Amino Acids):\n" +
                "• Leucine, Isoleucine, Valine\n" +
                "• Giúp giảm mệt mỏi khi tập luyện\n" +
                "• Hỗ trợ phục hồi cơ bắp\n\n" +
                "EAA (Essential Amino Acids):\n" +
                "• Chứa tất cả 9 axit amin thiết yếu\n" +
                "• Tối ưu hóa tổng hợp protein\n" +
                "• Hỗ trợ tăng trưởng cơ bắp\n\n" +
                "Liều dùng: 5-10g trước hoặc trong khi tập luyện."
        ));
        
        // Sản phẩm giảm cân
        allItems.add(new HandbookItem(
                "Sản phẩm giảm cân",
                "pic_2_1",
                "Hỗ trợ đốt cháy mỡ và tăng cường trao đổi chất",
                "Các sản phẩm giảm cân thể thao thường chứa các thành phần như:\n\n" +
                "• Caffeine: Tăng cường trao đổi chất và đốt cháy mỡ\n" +
                "• L-Carnitine: Vận chuyển axit béo vào ty thể để đốt cháy\n" +
                "• Green Tea Extract: Chứa EGCG giúp đốt cháy mỡ\n" +
                "• CLA (Conjugated Linoleic Acid): Giảm mỡ cơ thể\n\n" +
                "Lưu ý: Sử dụng kết hợp với chế độ ăn uống và tập luyện phù hợp."
        ));
        
        // L-Carnitine
        allItems.add(new HandbookItem(
                "L-Carnitine",
                "pic_3_1",
                "Vận chuyển axit béo để đốt cháy năng lượng",
                "L-Carnitine là một axit amin được sản xuất tự nhiên trong cơ thể. " +
                "Nó đóng vai trò quan trọng trong việc chuyển hóa chất béo thành năng lượng.\n\n" +
                "Lợi ích:\n" +
                "• Đốt cháy mỡ thừa hiệu quả\n" +
                "• Tăng cường năng lượng khi tập luyện\n" +
                "• Cải thiện hiệu suất tim mạch\n" +
                "• Hỗ trợ phục hồi sau tập luyện\n\n" +
                "Liều dùng: 1-3g trước khi tập luyện 30-60 phút."
        ));
        
        // Vitamin và khoáng chất
        allItems.add(new HandbookItem(
                "Vitamin và khoáng chất",
                "pic_1_2",
                "Bổ sung vi chất dinh dưỡng thiết yếu cho vận động viên",
                "Vận động viên cần nhiều vitamin và khoáng chất hơn người bình thường do cơ thể tiêu hao nhiều năng lượng.\n\n" +
                "Vitamin quan trọng:\n" +
                "• Vitamin D: Hấp thu canxi, sức khỏe xương\n" +
                "• Vitamin B: Chuyển hóa năng lượng\n" +
                "• Vitamin C: Chống oxy hóa, phục hồi\n\n" +
                "Khoáng chất quan trọng:\n" +
                "• Sắt: Vận chuyển oxy\n" +
                "• Canxi: Sức khỏe xương\n" +
                "• Magie: Co cơ và thư giãn\n" +
                "• Kẽm: Tổng hợp protein\n\n" +
                "Nên bổ sung đa vitamin dành cho vận động viên."
        ));

        filteredItems = new ArrayList<>(allItems);
        
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HandbookListAdapter(filteredItems, NutritionDetailActivity.class);
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

