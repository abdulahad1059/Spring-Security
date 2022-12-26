package com.springlearning.springlearning.dao;

import com.springlearning.springlearning.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findOneByUsername(String username);
}
