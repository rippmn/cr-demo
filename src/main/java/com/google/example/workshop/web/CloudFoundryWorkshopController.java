package com.google.example.workshop.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.example.workshop.entity.Attendee;
import com.google.example.workshop.repository.AttendeeRepository;

@Controller
public class CloudFoundryWorkshopController {

	private static final Logger logger = LoggerFactory.getLogger(CloudFoundryWorkshopController.class);

	@Autowired
	private AttendeeRepository attendeeRepo;

	@RequestMapping("/")
	public String index(Model model) throws Exception {
		for (String key : System.getenv().keySet()) {
			System.out.println(key + ":" + System.getenv(key));
		}

		
		return "index";
	}

	
	@RequestMapping(value = "/addAttendee", method = RequestMethod.GET)
	public String addAttendee() {
		return "addAttendee";
	}

	@RequestMapping(value = "/addAttendee", method = RequestMethod.POST)
	public String addAttendee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("emailAddress") String emailAddress, Model model) throws Exception {

		Attendee attendee = new Attendee();
		attendee.setFirstName(firstName);
		attendee.setLastName(lastName);
		attendee.setEmailAddress(emailAddress);

		attendeeRepo.save(attendee);

		return this.attendees(model);
	}
	
	/**
	 * Action to get a list of all attendees.
	 * 
	 * @param model
	 *            The model for this action.
	 * @return The path to the view.
	 */
	@RequestMapping(value = "/attendees", method = RequestMethod.GET)
	public String attendees(Model model) throws Exception{

		Iterable<Attendee> attendees = attendeeRepo.findAll();

		model.addAttribute("attendees", attendees);
		return "attendees";
	}

}