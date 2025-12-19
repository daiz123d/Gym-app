package com.uilover.project1932.Helper;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.uilover.project1932.Activity.CartActivity;
import com.uilover.project1932.Activity.ExercisesActivity;
import com.uilover.project1932.Activity.FavoritesActivity;
import com.uilover.project1932.Activity.MainActivity;
import com.uilover.project1932.Activity.ProfileActivity;
import com.uilover.project1932.Activity.TrainingActivity;
import com.uilover.project1932.Activity.TrainingScheduleActivity;
import com.uilover.project1932.R;

public class NavigationHelper {
    
    public static void setupBottomNavigation(View homeTab, View favoriteTab, View cartTab, View profileTab, Object currentActivity) {
        // Reset all tabs first
        resetTabColors(homeTab, favoriteTab, cartTab, profileTab);
        
        // Highlight current tab
        if (currentActivity instanceof MainActivity) {
            highlightTab(homeTab);
        } else if (currentActivity instanceof ExercisesActivity || currentActivity instanceof TrainingActivity
                || currentActivity instanceof FavoritesActivity) {
            highlightTab(favoriteTab);
        } else if (currentActivity instanceof TrainingScheduleActivity) {
            highlightTab(cartTab);
        } else if (currentActivity instanceof ProfileActivity) {
            highlightTab(profileTab);
        }
        
        if (homeTab != null) {
            homeTab.setOnClickListener(v -> {
                if (!(currentActivity instanceof MainActivity)) {
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(intent);
                }
            });
        }

        if (favoriteTab != null) {
            favoriteTab.setOnClickListener(v -> {
                if (!(currentActivity instanceof ExercisesActivity)
                        && !(currentActivity instanceof FavoritesActivity)
                        && !(currentActivity instanceof TrainingActivity)) {
                    Intent intent = new Intent(v.getContext(), ExercisesActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(intent);
                }
            });
        }

        if (cartTab != null) {
            cartTab.setOnClickListener(v -> {
                if (!(currentActivity instanceof TrainingScheduleActivity)) {
                    Intent intent = new Intent(v.getContext(), TrainingScheduleActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(intent);
                }
            });
        }

        if (profileTab != null) {
            profileTab.setOnClickListener(v -> {
                if (!(currentActivity instanceof ProfileActivity)) {
                    Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
    
    private static void resetTabColors(View homeTab, View favoriteTab, View cartTab, View profileTab) {
        resetTabColor(homeTab);
        resetTabColor(favoriteTab);
        resetTabColor(cartTab);
        resetTabColor(profileTab);
    }
    
    private static void resetTabColor(View tab) {
        if (tab == null) return;
        
        try {
            // Find icon and text based on the tab's ID
            ImageView icon = null;
            TextView text = null;
            
            int tabId = tab.getId();
            if (tabId == R.id.homeTab) {
                icon = tab.findViewById(R.id.homeIcon);
                text = tab.findViewById(R.id.homeText);
            } else if (tabId == R.id.favoriteTab) {
                icon = tab.findViewById(R.id.favoriteIcon);
                text = tab.findViewById(R.id.favoriteText);
            } else if (tabId == R.id.cartTab) {
                icon = tab.findViewById(R.id.cartIcon);
                text = tab.findViewById(R.id.cartText);
            } else if (tabId == R.id.profileTab) {
                icon = tab.findViewById(R.id.profileIcon);
                text = tab.findViewById(R.id.profileText);
            }
            
            if (icon != null) {
                icon.clearColorFilter();
                icon.setColorFilter(ContextCompat.getColor(tab.getContext(), R.color.white));
            }
            if (text != null) {
                text.setTextColor(ContextCompat.getColor(tab.getContext(), R.color.white));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void highlightTab(View tab) {
        if (tab == null) return;
        
        try {
            // Find icon and text based on the tab's ID
            ImageView icon = null;
            TextView text = null;
            
            int tabId = tab.getId();
            if (tabId == R.id.homeTab) {
                icon = tab.findViewById(R.id.homeIcon);
                text = tab.findViewById(R.id.homeText);
            } else if (tabId == R.id.favoriteTab) {
                icon = tab.findViewById(R.id.favoriteIcon);
                text = tab.findViewById(R.id.favoriteText);
            } else if (tabId == R.id.cartTab) {
                icon = tab.findViewById(R.id.cartIcon);
                text = tab.findViewById(R.id.cartText);
            } else if (tabId == R.id.profileTab) {
                icon = tab.findViewById(R.id.profileIcon);
                text = tab.findViewById(R.id.profileText);
            }
            
            if (icon != null) {
                icon.clearColorFilter();
                icon.setColorFilter(ContextCompat.getColor(tab.getContext(), R.color.orange));
            }
            if (text != null) {
                text.setTextColor(ContextCompat.getColor(tab.getContext(), R.color.orange));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

