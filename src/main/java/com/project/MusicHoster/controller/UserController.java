package com.project.MusicHoster.controller;
import com.project.MusicHoster.model.Login;
import com.project.MusicHoster.model.Music;
import com.project.MusicHoster.model.User;
import com.project.MusicHoster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserController
{
    @Autowired
    private UserService userservice;
    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public String addUser(@RequestBody User user)
    {
        if(this.userservice.addUser(user))
        {
            return "done";
        }
        else
        {
            return "Not done";
        }
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody Login data)
    {
        if(this.userservice.login(data))
        {
            return "Logged In";
        }
        else
        {
            return "Account not found";
        }
    }
    @RequestMapping(value = "/value",method = RequestMethod.POST)
    public String insert(@RequestBody Music data)
    {
        if(this.userservice.insert(data))
        {
            return "Logged In";
        }
        else
        {
            return "Account not found";
        }
    }
}
