package com.xiangxue.googleproject.request;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.xiangxue.googleproject.data.database.Student;
import com.xiangxue.googleproject.data.repository.local.LocalRoomRequestManager;

import java.util.List;

// 真正的获取 仓库
public class RequestCollectViewModel extends AndroidViewModel {

    private Application application;

    public RequestCollectViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<List<Student>> getStudentsLive() {
        return LocalRoomRequestManager.getInstance(application).getStudentsLive();
    }

    public void touchOffInsertStudents(Student... students) {
        LocalRoomRequestManager.getInstance(application).insertStudnets(students);
    }

    // 触发仓库  --  DB
    public void touchOffUpdateWords(Student... students) {
        LocalRoomRequestManager.getInstance(application).updateStudnets(students);
    }

    // 触发仓库  --  DB
    public void touchOffDeleteStudents(Student... students) {
        LocalRoomRequestManager.getInstance(application).deleteStudents(students);
    }

    // 触发仓库  --  DB
    public void touchOffDeleteAllWords() {
        LocalRoomRequestManager.getInstance(application).deleteAllStudnets();
    }

}
