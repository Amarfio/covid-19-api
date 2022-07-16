package com.work.covid19api.repository;

import com.work.covid19api.model.CovidTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CovidTestRepository extends JpaRepository<CovidTest, Long> {
}
