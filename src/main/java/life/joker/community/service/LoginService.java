package life.joker.community.service;

import life.joker.community.mapper.LoginMapper;
import life.joker.community.model.Login;
import life.joker.community.model.LoginExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author joker
 * @date 2023/02/28 17:29
 **/
@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;

    public void createOrUpdate(Login login) {
        LoginExample loginexample = new LoginExample();
        loginexample.createCriteria().andAccountIdEqualTo(login.getAccountId());
        List<Login> logins = loginMapper.selectByExample(loginexample);
        if (logins.size() == 0) {
            //插入
            login.setGmtCreate(System.currentTimeMillis());
            login.setGmtModified(login.getGmtCreate());
            loginMapper.insertSelective(login);
        } else {
            //更新
            Login dblogin = logins.get(0);
            dblogin.setGmtModified(System.currentTimeMillis());
            dblogin.setBio(login.getBio());
            dblogin.setName(login.getName());
            dblogin.setAvatarUrl(login.getAvatarUrl());
            dblogin.setToken(login.getToken());
            loginMapper.updateByPrimaryKeySelective(dblogin);
        }
    }
}