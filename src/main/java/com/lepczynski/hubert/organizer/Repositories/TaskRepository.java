package com.lepczynski.hubert.organizer.Repositories;

import com.lepczynski.hubert.organizer.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin
public interface TaskRepository extends JpaRepository<Task, Long>
{
    List<Task> findByUserId(Long userId);
}
