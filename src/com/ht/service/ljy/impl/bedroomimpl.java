package com.ht.service.ljy.impl;

import com.ht.dao.ljy.ljyDao;
import com.ht.service.ljy.bedroomService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JY on 2019/12/19.
 */
@Service
public class bedroomimpl extends ljyDao implements bedroomService {


    @Override
    public List bedroom_list(int page, int limit) {
        return pagelistbysql("select stu.huor,stuhuor.hourId,stucls.className,stuflo.floorName,stuhuor.huorName,stuhuor.address,stuhuor.count,stuhuor.numberBeds from student stu left join studentClass stucls on stu.clazz=stucls.classId left join studentHuor stuhuor on stu.huor=stuhuor.hourId left join studentFloor stuflo on stuflo.floorId=stuhuor.floorId", page, limit);
    }

    @Override
    public int bedroom_count() {
        return selTotalRow("select count(*) from student stu left join studentClass stucls on stu.clazz=stucls.classId left join studentHuor stuhuor on stu.huor=stuhuor.hourId left join studentFloor stuflo on stuflo.floorId=stuhuor.floorId");
    }

    @Override
    public List bedroom_search_list(int page, int limit, Integer clazz, Integer floor, Integer huor) {
        String sql = "select stu.huor,stucls.className,stuflo.floorName,stuhuor.huorName,stuhuor.address,stuhuor.count,stuhuor.numberBeds,stuhuor.hourId from student stu left join studentClass stucls on stu.clazz = stucls.classId left join studentHuor stuhuor on stu.huor = stuhuor.hourId left join studentFloor stuflo on stuflo.floorId = stuhuor.floorId where 1=1";
        if (clazz != null && !"".equals(clazz)) {
            sql += " and stu.clazz = " + clazz;
        }
        if (floor != null && !"".equals(floor)) {
            sql += " and stuflo.floorId =" + floor;
        }
        if (huor != null && !"".equals(huor)) {
            sql += " and stu.huor =" + huor;
        }
        System.out.println("sql" + sql);
        return pagelistbysql(sql, page, limit);
    }

    @Override
    public int bedroom_search_count(Integer clazz, Integer floor, Integer huor) {
        String sql = "select count(*) from student stu left join studentClass stucls on stu.clazz = stucls.classId left join studentHuor stuhuor on stu.huor = stuhuor.hourId left join studentFloor stuflo on stuflo.floorId = stuhuor.floorId where 1=1";
        if (clazz != null && !"".equals(clazz)) {
            sql += " and stu.clazz=" + clazz;
        }
        if (floor != null && !"".equals(floor)) {
            sql += " and stuflo.floorId=" + floor;
        }
        if (huor != null && !"".equals(huor)) {
            sql += " and stu.huor=" + huor;
        }
        System.out.println("sql:" + sql);
        return selTotalRow(sql);
    }

    @Override
    public List huor_stuName(int page, int limit, int huor) {
        return pagelistbysql("select * from student where huor=" + huor, page, limit);
    }

    @Override
    public int huor_stuName_count(int huor) {
        return selTotalRow("select count(*) from student where huor=" + huor);
    }
}
