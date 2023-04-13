package com.example.hackatonapplication.feature.dashboard.ui.recycler;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.example.hackatonapplication.databinding.ItemUserBinding;
import com.example.hackatonapplication.domain.model.User;

public class UserViewHolder extends ViewHolder {

    private final ItemUserBinding binding;
    private final UserClickListener listener;

    public UserViewHolder(ItemUserBinding binding, UserClickListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    public void bind(User item) {
        Glide.with(binding.getRoot()).load(item.getPhotoUrl()).into(binding.image);
        binding.name.setText(item.getName());
        binding.email.setText(item.getEmail());
        binding.getRoot().setOnClickListener(v -> listener.onClick(item.getId()));
    }
}
