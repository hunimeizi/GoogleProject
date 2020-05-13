package com.xiangxue.googleproject.data.repository.local;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.xiangxue.googleproject.data.database.Student;
import com.xiangxue.googleproject.data.database.StudentDao;
import com.xiangxue.googleproject.data.database.StudentDatabase;

import java.util.List;

// 仓库角色 本地/DB
public class LocalRoomRequestManager implements ILocalRequest, IDatabaseRequest {

    // TODO 可扩展 ...

    /** TODO ********************** 下面这一系列都是 本地相关的 ************************/

    private static LocalRoomRequestManager INSTANCE;
    private StudentDao studentDao;
    private LiveData<List<Student>> studentsLive;

    private LocalRoomRequestManager(Context context) {
        StudentDatabase studentDatabase = StudentDatabase.getDatabase(context.getApplicationContext());
        studentDao = studentDatabase.getStudnetDao();
        studentsLive = studentDao.getAllStudentLive();
    }

    public static LocalRoomRequestManager getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (LocalRoomRequestManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalRoomRequestManager(context);
                }
            }
        }
        return INSTANCE;
    }

    public LiveData<List<Student>> getStudentsLive() {
        return studentsLive;
    }

    @Override
    public void insertStudnets(Student... students) {
        new InsertAsyncTask(studentDao).execute(students);
    }

    @Override
    public void updateStudnets(Student... students) {
        new UpdateAsyncTask(studentDao).execute(students);
    }

    @Override
    public void deleteStudents(Student... students) {
        new DeleteAsyncTask(studentDao).execute(students);
    }

    @Override
    public void deleteAllStudnets() {
        new DeleteAllAsyncTask(studentDao).execute();
    }

    static class InsertAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        InsertAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insertWords(students); // 插入成功
            return null;
        }

    }

    static class UpdateAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        UpdateAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.updateWords(students);
            return null;
        }

    }

    static class DeleteAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        DeleteAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.deleteWords(students);
            return null;
        }

    }

    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private StudentDao studentDao;

        DeleteAllAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentDao.deleteAllWords();
            return null;
        }

    }
}
