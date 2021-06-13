package com.assigned.githubassignment.viewmodels;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.assigned.githubassignment.database.UserDao;
import com.assigned.githubassignment.database.UserDatabase;
import com.assigned.githubassignment.model.UserModel;

import java.util.List;

public class UserRepository {


    private UserDatabase database;

    private LiveData<List<UserModel>> getAllCrews;

    public UserRepository(Application application) {

        database = UserDatabase.Companion.getDatabase(application.getApplicationContext());
        getAllCrews = database.UserDao().getAllCrews();

    }

    public void insert(List<UserModel> crewList) {

        new InsertAsyncTask(database).execute(crewList);
    }

    public LiveData<List<UserModel>> getAllCrews() {
        return getAllCrews;
    }

    private static class InsertAsyncTask extends AsyncTask<List<UserModel>, Void, Void> {

        private UserDao crewDao;

        InsertAsyncTask(UserDatabase crewDatabase) {
            crewDao = crewDatabase.UserDao();
        }

        @Override
        protected Void doInBackground(List<UserModel>... lists) {


                for (int i = 0; i < lists.length; i++) {
                    crewDao.insert(lists[0]);
                }

            return null;
        }
    }
}
