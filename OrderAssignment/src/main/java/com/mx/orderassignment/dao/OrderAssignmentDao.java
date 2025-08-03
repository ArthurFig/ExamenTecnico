package com.mx.orderassignment.dao;

import com.mx.orderassignment.entity.OrderAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAssignmentDao extends JpaRepository<OrderAssignment,Integer> {
}
