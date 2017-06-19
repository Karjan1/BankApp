package com.karoljanowski.dao;

import com.karoljanowski.domain.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Karol Janowski on 2017-06-19.
 */
public interface AppointmentDao  extends CrudRepository<Appointment, Long> {
    List<Appointment> findAll();
}
