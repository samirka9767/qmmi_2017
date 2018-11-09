package dao;

import domain.*;

import java.util.List;
import java.util.Map;

/**
 * Created by a.ulviyya on 30.06.2016.
 */
public interface userDao {
public usersInfo login(String username,String password) throws Exception;
    public List<roles> userAccess(long uID) throws Exception;

    Map<Long , Resource> getResources() throws Exception;

    Map<Long, String> getConstants() throws Exception;


    usersInfo getUser(Long userId, String userName, String password, Long currLang) throws Exception;
}
