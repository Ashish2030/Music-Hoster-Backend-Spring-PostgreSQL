package com.project.MusicHoster.repository;
import com.project.MusicHoster.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MusicRepository extends JpaRepository<Music,Integer>
{

}
