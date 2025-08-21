package com.aayushtech07.aayushtech.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aayushtech07.aayushtech.R;
import com.aayushtech07.aayushtech.models.AppShortcut;

import java.util.List;

public class AppShortcutAdapter extends RecyclerView.Adapter<AppShortcutAdapter.ViewHolder> {

    private List<AppShortcut> shortcuts;
    private OnShortcutClickListener listener;

    public interface OnShortcutClickListener {
        void onShortcutClick(AppShortcut shortcut);
    }

    public AppShortcutAdapter(List<AppShortcut> shortcuts, OnShortcutClickListener listener) {
        this.shortcuts = shortcuts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_app_shortcut, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppShortcut shortcut = shortcuts.get(position);
        holder.bind(shortcut, listener);
    }

    @Override
    public int getItemCount() {
        return shortcuts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconImageView;
        private TextView nameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.iconImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }

        public void bind(AppShortcut shortcut, OnShortcutClickListener listener) {
            iconImageView.setImageResource(shortcut.getIconResId());
            nameTextView.setText(shortcut.getName());

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onShortcutClick(shortcut);
                }
            });
        }
    }
}
