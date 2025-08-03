package com.mx.drivers.dao;

import com.mx.drivers.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverDao extends JpaRepository<Driver,Integer> {
}
