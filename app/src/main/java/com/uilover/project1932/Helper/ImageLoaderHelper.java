package com.uilover.project1932.Helper;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Helper class to load images from URLs or drawable resources
 */
public class ImageLoaderHelper {
    
    /**
     * Load image from URL or drawable resource
     * @param context Context
     * @param imageSource Can be a URL (http/https) or drawable resource name
     * @param imageView Target ImageView
     * @param defaultResId Default drawable resource ID if image fails to load
     */
    public static void loadImage(Context context, String imageSource, ImageView imageView, int defaultResId) {
        if (imageSource == null || imageSource.isEmpty()) {
            if (defaultResId != 0) {
                imageView.setImageResource(defaultResId);
            }
            return;
        }
        
        // Check if it's a URL (starts with http:// or https://)
        if (imageSource.startsWith("http://") || imageSource.startsWith("https://")) {
            // Load from URL
            Glide.with(context)
                    .load(imageSource)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(defaultResId != 0 ? defaultResId : android.R.drawable.ic_menu_gallery)
                    .error(defaultResId != 0 ? defaultResId : android.R.drawable.ic_menu_gallery)
                    .into(imageView);
        } else {
            // Load from drawable resource
            int resId = context.getResources().getIdentifier(imageSource, "drawable", context.getPackageName());
            if (resId != 0) {
                Glide.with(context)
                        .load(resId)
                        .into(imageView);
            } else {
                // Fallback to default image
                if (defaultResId != 0) {
                    imageView.setImageResource(defaultResId);
                } else {
                    imageView.setImageResource(android.R.drawable.ic_menu_gallery);
                }
            }
        }
    }
    
    /**
     * Load image from URL or drawable resource (with default placeholder)
     * @param context Context
     * @param imageSource Can be a URL (http/https) or drawable resource name
     * @param imageView Target ImageView
     */
    public static void loadImage(Context context, String imageSource, ImageView imageView) {
        loadImage(context, imageSource, imageView, android.R.drawable.ic_menu_gallery);
    }
    
    /**
     * Load image with custom options
     * @param context Context
     * @param imageSource Can be a URL (http/https) or drawable resource name
     * @param imageView Target ImageView
     * @param options RequestOptions for Glide
     */
    public static void loadImageWithOptions(Context context, String imageSource, ImageView imageView, RequestOptions options) {
        if (imageSource == null || imageSource.isEmpty()) {
            return;
        }
        
        if (imageSource.startsWith("http://") || imageSource.startsWith("https://")) {
            Glide.with(context)
                    .load(imageSource)
                    .apply(options)
                    .into(imageView);
        } else {
            int resId = context.getResources().getIdentifier(imageSource, "drawable", context.getPackageName());
            if (resId != 0) {
                Glide.with(context)
                        .load(resId)
                        .apply(options)
                        .into(imageView);
            }
        }
    }
}

