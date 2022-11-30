package com.akash.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
