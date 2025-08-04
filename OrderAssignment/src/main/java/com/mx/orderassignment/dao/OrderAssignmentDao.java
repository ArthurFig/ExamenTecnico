package com.mx.orderassignment.dao;

import com.mx.orderassignment.entity.OrderAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderAssignmentDao extends JpaRepository<OrderAssignment,Integer> {
}
