package service;

import dao.*;
import domain.*;

import java.util.List;
import java.util.Map;

/**
 * Created by a.ulviyya on 30.06.2016.
 */
public class UserServiceImpl implements UserService {
   private userDao _userDao=  new userDaoImpl();
@Override
    public usersInfo login(String username,String password) throws Exception {
      return _userDao.login(username,password);
    }
    @Override
    public List<roles> userAccess(long uID) throws Exception {
        return _userDao.userAccess(uID);
    }
    @Override
    public Map<Long , Resource> getResources() throws Exception {
        return _userDao.getResources();
    }
    @Override
    public Map<Long, String> getConstants() throws Exception {
        return _userDao.getConstants();
    }

    @Override

    public usersInfo getUser(Long userId, String userName, String password,Long currLang) throws Exception {
        return _userDao.getUser(userId,userName,password,currLang);
    }
}
