package com.lepczynski.hubert.organizer.Models.DTOs;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class UserDTO extends RepresentationModel<UserDTO>
{
    Long id;
    String email;
    String username;
}
