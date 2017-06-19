package com.karoljanowski.service;

import com.karoljanowski.domain.Appointment;

import java.util.List;

/**
 * Created by Karol Janowski on 2017-06-19.
 */
public interface AppointmentService {
     Appointment createAppointment(Appointment appointment);
     List<Appointment> findAll();
     Appointment findAppointment(Long id);
     void confirmAppointment(Long id);
}
