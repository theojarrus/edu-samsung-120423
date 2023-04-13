package com.example.hackatonapplication.feature.dashboard.ui.recycler;

import static java.lang.Math.max;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.hackatonapplication.databinding.ItemUserBinding;
import com.example.hackatonapplication.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends Adapter<UserViewHolder> {

    private List<User> items = new ArrayList<>();
    private final UserClickListener listener;

    public UserAdapter(UserClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UserViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<User> items) {
        int count = getItemCount();
        this.items = new ArrayList<>(items);
        notifyItemRangeChanged(0, max(count, getItemCount()));
    }
}
