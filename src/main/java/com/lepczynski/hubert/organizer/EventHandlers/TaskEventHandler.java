package com.lepczynski.hubert.organizer.EventHandlers;

import com.lepczynski.hubert.organizer.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class TaskEventHandler
{
    @Autowired
    UserRepository userRepository;
/*
    @HandleAfterSave
    public void handleTaskSave(Task task)
    {
        //linking task to user? TODO make sure this is correct
        userRepository.findById(task.getUser().getId()).get().AddTask(task);
    }

 */
}
