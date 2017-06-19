package com.karoljanowski.service.UserServiceImpl;

import com.karoljanowski.dao.AppointmentDao;
import com.karoljanowski.domain.Appointment;
import com.karoljanowski.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Karol Janowski on 2017-06-19.
 */
@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    AppointmentDao appointmentDao;

    public Appointment createAppointment(Appointment appointment){
        return appointmentDao.save(appointment);
    }

    public List<Appointment> findAll(){
        return appointmentDao.findAll();
    }

    public Appointment findAppointment(Long id){
        return appointmentDao.findOne(id);
    }

    public void confirmAppointment(Long id){
        Appointment appointment = findAppointment(id);
        appointment.setConfirmed(true);
        appointmentDao.save(appointment);
    }
}


















