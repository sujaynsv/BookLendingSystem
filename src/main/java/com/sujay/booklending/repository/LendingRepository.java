package com.sujay.booklending.repository;

import com.sujay.booklending.model.LendingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LendingRepository extends JpaRepository<LendingRecord, Long> {
}
