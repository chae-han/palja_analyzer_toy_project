package com.chan.paljachance.webBackend.user.repository;

import com.chan.paljachance.webBackend.user.dto.UserDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface UserRepository extends CrudRepository<UserDto, Integer> {
    @Query("SELECT COUNT(*) FROM UserDto")
    int countAll();

//    @Query("SELECT COUNT(*) FROM car WHERE brand = :brand")
//    int countByBrand(@Param("brand") String brand);
}