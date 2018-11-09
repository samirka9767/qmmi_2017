package service;

import domain.*;

import java.util.List;
import java.util.Map;

/**
 * Created by a.ulviyya on 30.06.2016.
 */
public interface UserService {
    public Map<Long,String> getConstants() throws Exception;
    public Map<Long , Resource> getResources() throws Exception;
    public usersInfo login(String username,String password) throws Exception;
    public List<roles> userAccess(long uID) throws Exception;
    public usersInfo getUser(Long userId, String userName, String password,Long currLang) throws Exception;
}
