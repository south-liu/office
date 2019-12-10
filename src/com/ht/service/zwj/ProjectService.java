package com.ht.service.zwj;

import com.ht.vo.ProjectVO;

import java.util.List;

// 答辩项目Service
public interface ProjectService {
    List<ProjectVO> allData(Integer page, Integer limit);

    List<ProjectVO> allData();

    long getTotality();

    void delete(int projectId);

    int add(ProjectVO projectVO);

    ProjectVO getProjectByProjectName(String projectName);

    ProjectVO getProjectById(int projectId);

    void updateProject(ProjectVO project);

    void deleteMultiProject(Integer[] projectIds);
}
