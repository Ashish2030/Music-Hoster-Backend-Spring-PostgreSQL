package com.project.MusicHoster.service;
import com.project.MusicHoster.model.Login;
import com.project.MusicHoster.model.Music;
import com.project.MusicHoster.model.User;
import com.project.MusicHoster.repository.MusicRepository;
import com.project.MusicHoster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserRepository userrepository;
    @Autowired
    private MusicRepository musicrepository;
    public boolean addUser(User data)
    {
        try
        {
            this.userrepository.save(data);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public boolean login(Login data)
    {
        String name = data.getUsername();
        String password = data.getPassword();
        User user = this.userrepository.findByUsername(name);
        if (user.getPassword().equals(data.getPassword()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean insert(Music data)
    {
        try
        {
            this.musicrepository.save(data);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

}
