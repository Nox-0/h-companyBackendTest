package com.halter.backendTest.repository;

import com.halter.backendTest.models.Cow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CowRepository extends JpaRepository<Cow, Integer> {

    public Cow findById(int id);


}
