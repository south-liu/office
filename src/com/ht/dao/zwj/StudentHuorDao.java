package com.ht.dao.zwj;

import com.ht.vo.StudentFloorVO;
import com.ht.vo.StudentHuorVO;

import java.util.List;

/**
 * 学生宿舍管理
 * 数据表名称：studentHuor
 */
public interface StudentHuorDao {
    String currentTableName = "studentHuor";// 当前模块的表名称
    String getCurrentTableId = "hourId";// 当前模块的主键ID
    String floorTableName = "studentFloor";// 需要连接的表[楼栋表]

    List<StudentHuorVO> allData(Integer page, Integer limit);

    long getTotality();

    /**
     * 通过楼栋id获取对象
     *
     * @param floorId
     * @return
     */
    StudentFloorVO obtainStudentFloorByFloorId(Integer floorId);

    int add(StudentHuorVO studentHuorVO);

    StudentHuorVO getStudentHuorByHuorName(String huorName);

    /**
     * 查询所有楼栋信息
     *
     * @return
     */
    List<StudentFloorVO> allFloorData();

    StudentHuorVO getStudentHuorById(int hourId);

    void updateStudentHuor(StudentHuorVO studentHuor);

    void delete(StudentHuorVO studentHuorById);

    List<StudentHuorVO> allData();

    List<StudentHuorVO> allDataByFloorId(Integer floorId);

    List<StudentHuorVO> queryStudentHuorNotHuorid(Integer hourId, String huorName);

    /**
     * 将宿舍房号【beforeHourid】内的所有学生，设置新宿舍房号【afterHourid】
     *
     * @param afterHourid
     * @param beforeHourid
     * @return 受影响的行数
     */
    int updateStudentHuorIdByHuorId(int afterHourid, int beforeHourid);
}
