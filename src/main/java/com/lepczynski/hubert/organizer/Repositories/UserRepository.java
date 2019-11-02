package com.lepczynski.hubert.organizer.Repositories;

import com.lepczynski.hubert.organizer.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{

}
