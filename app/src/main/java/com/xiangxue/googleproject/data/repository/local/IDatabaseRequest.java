package com.xiangxue.googleproject.data.repository.local;

import com.xiangxue.googleproject.data.database.Student;

/**
 * 为了扩展，这样写（在仓库里面的）
 * 数据库获取标准接口（在仓库里面） 也就是数据库的数据读取（包括数据库数据，等）
 * 只为 LocalRoomRequestManager 服务
 */
public interface IDatabaseRequest {

    void insertStudnets(Student... students);

    void updateStudnets(Student... students);

    void deleteStudents(Student... students);

    void deleteAllStudnets();

    // TODO 可扩展 ...

}
