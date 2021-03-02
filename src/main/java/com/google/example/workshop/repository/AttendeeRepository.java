package com.google.example.workshop.repository;

import org.springframework.data.repository.CrudRepository;

import com.google.example.workshop.entity.Attendee;

/**
 * JPA repository for the Attendee entity.
 * 
 * @author Brian Jimerson
 *
 */
public interface AttendeeRepository extends CrudRepository<Attendee, Long> {

}
