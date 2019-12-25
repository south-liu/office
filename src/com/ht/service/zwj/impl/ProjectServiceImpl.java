package com.ht.service.zwj.impl;

import com.ht.dao.zwj.ProjectDao;
import com.ht.service.zwj.ProjectService;
import com.ht.vo.ProjectVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Resource
    private ProjectDao projectDao;

    @Override
    public List<ProjectVO> allData(Integer page, Integer limit) {
        return projectDao.allData(page, limit);
    }

    @Override
    public List<ProjectVO> allData() {
        return projectDao.allData();
    }

    @Override
    public long getTotality() {
        return projectDao.getTotality();
    }

    @Override
    public void delete(int projectId) {
        projectDao.delete(projectId);
    }

    @Override
    public int add(ProjectVO projectVO) {
        return projectDao.add(projectVO);
    }

    @Override
    public ProjectVO getProjectByProjectName(String projectName) {
        return projectDao.getProjectByProjectName(projectName);
    }

    @Override
    public ProjectVO getProjectById(int projectId) {
        return projectDao.getProjectById(projectId);
    }

    @Override
    public void updateProject(ProjectVO project) {
        projectDao.updateProject(project);
    }

    @Override
    public void deleteMultiProject(Integer[] projectIds) {
        if (projectIds == null) {
            return;
        }
        for (Integer projectId : projectIds) {
            delete(projectId);
        }
    }
}
