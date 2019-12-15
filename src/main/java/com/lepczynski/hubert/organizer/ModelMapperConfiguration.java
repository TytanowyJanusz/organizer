package com.lepczynski.hubert.organizer;

import com.lepczynski.hubert.organizer.Models.DTOs.TaskDTO;
import com.lepczynski.hubert.organizer.Models.Task;
import com.lepczynski.hubert.organizer.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Autowired
    UserRepository userRepository;

    @Bean
    public ModelMapper modelMapper(UserRepository userRepository) {
        this.userRepository = userRepository;

        ModelMapper modelMapper = new ModelMapper();


        //Explicit mappings
        //http://modelmapper.org/user-manual/property-mapping/
        PropertyMap<Task, TaskDTO> taskToDTOMap = new PropertyMap<Task, TaskDTO>() {
            protected void configure() {
                map().setUserId(source.getUser().getId());
            }
        };
        PropertyMap<TaskDTO, Task> DTOtoTaskMap = new PropertyMap<TaskDTO, Task>() {
            protected void configure() {
                map().setUser(userRepository.getOne(source.getUserId()));
            }
        };

        modelMapper.addMappings(taskToDTOMap);
        return modelMapper;
    }

}
