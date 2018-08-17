package com.shashank.phhub.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shashank.phhub.hib.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
}
