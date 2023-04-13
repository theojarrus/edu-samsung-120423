package com.example.hackatonapplication.feature.profile.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.hackatonapplication.databinding.FragmentProfileBinding;
import com.example.hackatonapplication.domain.model.User;
import com.example.hackatonapplication.feature.profile.presentation.ProfileStatus;
import com.example.hackatonapplication.feature.profile.presentation.ProfileViewModel;

public class ProfileFragment extends Fragment {


    private ProfileViewModel viewModel;
    private FragmentProfileBinding binding;
    private ProfileFragmentArgs args;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        args = ProfileFragmentArgs.fromBundle(requireArguments());
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.status.observe(getViewLifecycleOwner(), this::renderStatus);
        viewModel.user.observe(getViewLifecycleOwner(), this::renderUser);
        binding.toolbar.setNavigationOnClickListener(v ->  Navigation.findNavController(binding.getRoot()).navigateUp());
        binding.delete.setOnClickListener(v -> viewModel.delete(args.getId()));
        if (savedInstanceState == null) viewModel.load(args.getId());
    }

    private void renderStatus(ProfileStatus status) {
        switch (status) {
            case LOADING:
                binding.imageLayout.setVisibility(View.INVISIBLE);
                binding.name.setVisibility(View.INVISIBLE);
                binding.email.setVisibility(View.INVISIBLE);
                binding.delete.setVisibility(View.INVISIBLE);
                binding.error.setVisibility(View.INVISIBLE);
                binding.progress.setVisibility(View.VISIBLE);
                break;
            case LOADED:
                binding.imageLayout.setVisibility(View.VISIBLE);
                binding.name.setVisibility(View.VISIBLE);
                binding.email.setVisibility(View.VISIBLE);
                binding.delete.setVisibility(View.VISIBLE);
                binding.error.setVisibility(View.INVISIBLE);
                binding.progress.setVisibility(View.INVISIBLE);
                break;
            case FAILURE:
                binding.imageLayout.setVisibility(View.INVISIBLE);
                binding.name.setVisibility(View.INVISIBLE);
                binding.email.setVisibility(View.INVISIBLE);
                binding.delete.setVisibility(View.INVISIBLE);
                binding.error.setVisibility(View.VISIBLE);
                binding.progress.setVisibility(View.INVISIBLE);
                break;
            case DELETED:
                Navigation.findNavController(binding.getRoot()).navigateUp();
                break;
        }
    }

    private void renderUser(User user) {
        Glide.with(binding.getRoot()).load(user.getPhotoUrl()).into(binding.image);
        binding.name.setText(user.getName());
        binding.email.setText(user.getEmail());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
