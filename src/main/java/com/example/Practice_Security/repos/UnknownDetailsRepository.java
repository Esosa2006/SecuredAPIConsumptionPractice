package com.example.Practice_Security.repos;

import com.example.Practice_Security.dtos.unknownData.UnknownData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnknownDetailsRepository extends JpaRepository<UnknownData, Long> {
}
