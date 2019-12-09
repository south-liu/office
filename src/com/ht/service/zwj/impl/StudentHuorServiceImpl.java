package com.ht.service.zwj.impl;

import com.ht.dao.zwj.StudentHuorDao;
import com.ht.service.zwj.StudentHuorService;
import com.ht.vo.StudentFloorVO;
import com.ht.vo.StudentHuorVO;
import com.mchange.v2.beans.BeansUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.IntrospectionException;
import java.util.*;

@Service
public class StudentHuorServiceImpl implements StudentHuorService {
    @Resource
    private StudentHuorDao studentHuorDao;

    @Override
    public List<Map<String, Object>> allData(Integer page, Integer limit) {
        List<StudentHuorVO> allData = studentHuorDao.allData(page, limit);

        List<Map<String, Object>> list = new ArrayList<>();
        if (list == null) {
            return null;
        }
        for (StudentHuorVO element : allData) {
            Map<String, Object> map = new LinkedHashMap<>();
            try {
                BeansUtils.extractAccessiblePropertiesToMap(map, element);
                if (element.getFloorId() != null) {
                    if (studentHuorDao.obtainStudentFloorByFloorId(element.getFloorId()) != null) {
                        map.put("floorName", studentHuorDao.obtainStudentFloorByFloorId(element.getFloorId()).getFloorName());
                    }
                }
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
            list.add(map);
        }


        return list;
    }

    @Override
    public long getTotality() {
        return studentHuorDao.getTotality();
    }

    @Override
    public int add(StudentHuorVO studentHuorVO) {
        return studentHuorDao.add(studentHuorVO);
    }

    @Override
    public StudentHuorVO getStudentHuorByHuorName(String huorName) {
        return studentHuorDao.getStudentHuorByHuorName(huorName);
    }

    @Override
    public List<StudentFloorVO> allFloorData() {
        return studentHuorDao.allFloorData();
    }

    @Override
    public StudentHuorVO getStudentHuorById(int hourId) {
        return studentHuorDao.getStudentHuorById(hourId);
    }

    @Override
    public void updateStudentHuor(StudentHuorVO studentHuor) {
        studentHuorDao.updateStudentHuor(studentHuor);
    }

    @Override
    public void deleteMultiStudentHuor(Integer[] ids) {
        if (ids == null) {
            return;
        }
        for (Integer id : ids) {
            delete(id);
        }
    }

    @Override
    public void delete(int hourId) {
        StudentHuorVO studentHuorById = getStudentHuorById(hourId);
        studentHuorDao.delete(studentHuorById);
    }
}
