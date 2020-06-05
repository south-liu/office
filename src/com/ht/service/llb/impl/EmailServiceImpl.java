package com.ht.service.llb.impl;

import com.ht.dao.llb.IEmailDao;
import com.ht.service.llb.IEmailService;
import com.ht.vo.EmailReceiverVO;
import com.ht.vo.EmailVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmailServiceImpl implements IEmailService {
    @Resource
    private IEmailDao emailDao;

    @Override
    public void addEmail(EmailVO emailVO) {
        emailDao.addEmail(emailVO);
    }

    @Override
    public void addEmailReceiver(EmailReceiverVO emailReceiverVO) {
        emailDao.addEmailReceiver(emailReceiverVO);
    }

    @Override
    public int countReceiveEmail(Integer receiver) {
        return emailDao.countReceiveEmail(receiver);
    }

    @Override
    public int countMySendEmail(Integer empId) {
        return emailDao.countMySendEmail(empId);
    }

    @Override
    public List allReceiveEmail(Integer receiver, int page, int limit) {
        return emailDao.allReceiveEmail(receiver,page,limit);
    }

    @Override
    public List<EmailReceiverVO> allReceiveEmail(Integer receiver) {
        return emailDao.allReceiveEmail(receiver);
    }

    @Override
    public List allMySendEmail(Integer empId, int page, int limit) {
        return emailDao.allMySendEmail(empId,page,limit);
    }

    @Override
    public EmailVO selEmailById(Integer emailId) {
        return emailDao.selEmailById(emailId);
    }

    @Override
    public void updIsRead(Integer emailReceiverId, Integer isRead) {
        emailDao.updIsRead(emailReceiverId,isRead);
    }

    @Override
    public EmailReceiverVO selEmailReceiverById(Integer emailReceiverId) {
        return emailDao.selEmailReceiverById(emailReceiverId);
    }

    @Override
    public void delEmail(Integer emailReceiverId) {
        emailDao.delEmail(emailReceiverId);
    }
}
