package com.assigned.githubassignment.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.assigned.githubassignment.model.UserModel;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

        private UserRepository crewRepository;
        private LiveData<List<UserModel>> getAllCrews;

    public UserViewModel(@NonNull Application application) {
            super(application);
            crewRepository=new UserRepository(application);
            getAllCrews=crewRepository.getAllCrews();
        }

        public void insert(List<UserModel> list)
        {
            crewRepository.insert(list);
        }

        public LiveData<List<UserModel>> getAllCrew(){
            return getAllCrews;
        }

    }
