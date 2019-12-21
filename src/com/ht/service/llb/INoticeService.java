package com.ht.service.llb;

import com.ht.vo.NoticeVO;

import java.util.List;
import java.util.Map;

public interface INoticeService {
    Integer countNotice(Integer noticeType);
    List<NoticeVO> pageList(Integer noticeType,int page, int limit);

    List<NoticeVO> allNotice();
    List allNoticeBySql();

    void addNotice(NoticeVO noticeVO);
    void delNotice(NoticeVO noticeVO);
    void updNotice(NoticeVO noticeVO);
    NoticeVO selNoticeById(Integer noticeId);

    Map selNoticeByIdToMap(Integer noticeId);
}
