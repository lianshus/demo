package com.student.system.service.impl;

import com.student.system.domain.UserDO;
import com.student.system.service.SessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 待完善
 *
 * @author student
 */
@Service
public class SessionServiceImpl implements SessionService {
    private final SessionDAO sessionDAO;

    @Autowired
    public SessionServiceImpl(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    @Override
    public List<UserDO> listOnlineUser() {
        List<UserDO> list = new ArrayList<>();
        UserDO userDO;
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            SimplePrincipalCollection principalCollection = new SimplePrincipalCollection();
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
                continue;
            } else {
                principalCollection = (SimplePrincipalCollection) session
                        .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                userDO = (UserDO) principalCollection.getPrimaryPrincipal();
                list.add(userDO);
            }
        }
        return list;
    }

    @Override
    public Collection<Session> sessionList() {
        return sessionDAO.getActiveSessions();
    }

    @Override
    public boolean forceLogout(String sessionId) {
        Session session = sessionDAO.readSession(sessionId);
        session.setTimeout(0);
        return true;
    }
}
