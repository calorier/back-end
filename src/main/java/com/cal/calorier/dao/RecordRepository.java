package com.cal.calorier.dao;

import com.cal.calorier.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecordRepository extends JpaRepository<Record, Integer>, JpaSpecificationExecutor<Record> {
    List<Record> findById(int id);
    List<Record> findAll();
}
