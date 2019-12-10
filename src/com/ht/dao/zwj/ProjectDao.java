package com.ht.dao.zwj;

import com.ht.vo.ProjectVO;

import java.util.List;

// 答辩项目DAO
public interface ProjectDao {
    String currentTableName = "project";
    String currentTableId = "projectId";

    List<ProjectVO> allData(Integer page, Integer limit);

    List<ProjectVO> allData();

    long getTotality();

    void delete(int projectId);

    int add(ProjectVO projectVO);

    ProjectVO getProjectByProjectName(String projectName);

    ProjectVO getProjectById(int projectId);

    void updateProject(ProjectVO project);
}
