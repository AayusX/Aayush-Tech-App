package com.aayushtech07.aayushtech.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aayushtech07.aayushtech.R;
import com.aayushtech07.aayushtech.models.Tool;

import java.util.List;

public class ToolsAdapter extends RecyclerView.Adapter<ToolsAdapter.ViewHolder> {

    private List<Tool> tools;
    private OnToolClickListener listener;

    public interface OnToolClickListener {
        void onToolClick(Tool tool);
    }

    public ToolsAdapter(List<Tool> tools, OnToolClickListener listener) {
        this.tools = tools;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tool, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tool tool = tools.get(position);
        holder.bind(tool, listener);
    }

    @Override
    public int getItemCount() {
        return tools.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconImageView;
        private TextView nameTextView;
        private TextView descriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.toolIconImageView);
            nameTextView = itemView.findViewById(R.id.toolNameTextView);
            descriptionTextView = itemView.findViewById(R.id.toolDescriptionTextView);
        }

        public void bind(Tool tool, OnToolClickListener listener) {
            iconImageView.setImageResource(tool.getIconResId());
            nameTextView.setText(tool.getName());
            descriptionTextView.setText(tool.getDescription());

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onToolClick(tool);
                }
            });
        }
    }
}
