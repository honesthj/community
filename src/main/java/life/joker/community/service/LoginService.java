package life.joker.community.service;

import life.joker.community.mapper.LoginMapper;
import life.joker.community.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author joker
 * @date 2023/02/28 17:29
 **/
@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;

    public void createOrUpdate(Login login) {
        Login dblogin = loginMapper.findByAccountId(login.getAccountId());
        if (dblogin == null) {
            //插入
            login.setGmtCreate(System.currentTimeMillis());
            login.setGmtModified(login.getGmtCreate());
            loginMapper.insert(login);
        } else {
            //更新
            dblogin.setGmtModified(System.currentTimeMillis());
            dblogin.setBio(login.getBio());
            dblogin.setName(login.getName());
            dblogin.setAvatarUrl(login.getAvatarUrl());
            dblogin.setToken(login.getToken());
            loginMapper.update(dblogin);
        }
    }
}