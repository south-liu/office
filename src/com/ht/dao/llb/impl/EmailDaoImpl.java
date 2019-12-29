package com.ht.dao.llb.impl;

import com.ht.dao.BaseDao;
import com.ht.dao.llb.IEmailDao;
import com.ht.vo.EmailReceiverVO;
import com.ht.vo.EmailVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmailDaoImpl extends BaseDao implements IEmailDao {

    @Override
    public List allReceiveEmail(Integer receiver, int page, int limit) {
        return pageListBySql("select emailReceiver.emailReceiverId,emailReceiver.receiver,emailReceiver.isRead,email.*,emp.empName from emailReceiver left join email on emailReceiver.emailId = email.emailId left join emp on email.empId = emp.empId \n" +
                "where emailReceiver.receiver =  "+receiver+" order by emailReceiver.emailReceiverId desc",page,limit);
    }

    @Override
    public List<EmailReceiverVO> allReceiveEmail(Integer receiver) {
        return findAllByHql("from EmailReceiverVO where receiver = "+receiver);
    }

    @Override
    public List allMySendEmail(Integer empId, int page, int limit) {
        return pageListBySql("select emailReceiver.emailReceiverId,emailReceiver.receiver,emailReceiver.isRead,email.*,emp.empName from emailReceiver LEFT JOIN email on emailReceiver.emailId = email.emailId left join emp on emp.empId = emailReceiver.receiver where email.empId = "+empId+" order by emailReceiver.emailReceiverId desc",page,limit);
    }

    @Override
    public void addEmail(EmailVO emailVO) {
        saveObject(emailVO);
    }

    @Override
    public void addEmailReceiver(EmailReceiverVO emailReceiverVO) {
        saveObject(emailReceiverVO);
    }

    @Override
    public EmailVO selEmailById(Integer emailId) {
        return (EmailVO) findOneByHql("from EmailVO where emailId = "+emailId);
    }

    @Override
    public void updIsRead(Integer emailReceiverId, Integer isRead) {
        executeSQL("update emailReceiver set isRead = "+isRead+" where emailReceiverId = "+emailReceiverId);
    }

    @Override
    public EmailReceiverVO selEmailReceiverById(Integer emailReceiverId) {
        return (EmailReceiverVO) findOneByHql("from EmailReceiverVO where emailReceiverId = "+emailReceiverId);
    }

    @Override
    public void delEmail(Integer emailReceiverId) {
        executeSQL("delete from emailReceiver where emailReceiverId = "+emailReceiverId);
    }
}
