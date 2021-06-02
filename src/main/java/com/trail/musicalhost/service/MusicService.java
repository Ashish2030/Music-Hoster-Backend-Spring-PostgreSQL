package com.trail.musicalhost.service;
import com.trail.musicalhost.model.Music;
import com.trail.musicalhost.model.User;
import com.trail.musicalhost.model.detail;
import com.trail.musicalhost.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
@Service
public class MusicService {
    @Autowired
    public MusicRepository musicRepository;
    @Autowired
    UserService userService;
    //--------------------store data
    public Music store(MultipartFile file, detail details) throws IOException
    {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Music FileDB = new Music(fileName, file.getContentType(), file.getBytes());
        User user = userService.getCurrentLoggedINUser();
        FileDB.setUser(user);
        FileDB.setDescription(details.getDescription());
        FileDB.setTag(details.getName());
        System.out.println(user.getId());

        return musicRepository.save(FileDB);
    }

    public Music getFile(String id) {
        return musicRepository.findById(id).get();
    }
    public Stream<Music> getAllFiles() {
        return musicRepository.findAll().stream();
    }

    public List<Music> getAllMusic(){
        return (List<Music>) musicRepository.findAll();
    }

    public Music getMusic(String id){
        Optional<Music> music = musicRepository.findById(id);
        if(music.isPresent())
        {
            return music.get();
        }
        else
            {
            return null;
        }
    }
    @Transactional
    public List<Music> getPostByUserID(Integer id){
        return this.musicRepository.findAllMusicByUserId(id);
    }
    public void deleteMusic(String  id){
        this.musicRepository.deleteById(id);
    }
    // Method to add a post
    public void addMusic(Music music){
        this.musicRepository.save(music);
    }
    public boolean updateMusic(String id,  MultipartFile file)throws IOException
    {
        User user = userService.getCurrentLoggedINUser();

        if(this.musicRepository.existsById(id)){
            Optional<Music> existMusic = musicRepository.findById(id);
            Music music1 = existMusic.get();

            //----------------------------------------------------------------------------------
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Music FileDB = new Music(fileName, file.getContentType(), file.getBytes());

            FileDB.setMusicId(music1.getMusicId());

            FileDB.setUser(user);
             musicRepository.save(FileDB);
            return true;
        }else {
            System.out.println("not found");
            return false;
        }
    }

}
