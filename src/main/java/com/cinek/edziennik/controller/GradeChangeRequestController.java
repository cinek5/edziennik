package com.cinek.edziennik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cinek.edziennik.model.GradeChangeRequest;
import com.cinek.edziennik.service.GradeChangeRequestService;
import com.cinek.edziennik.service.UserService;

@Controller
public class GradeChangeRequestController {
	@Autowired
	GradeChangeRequestService gradeChangeRequestService;
    @Autowired
    UserService userService;
	@RequestMapping(value = "student/gradeChangeRequest/create/{teacherId}/{studentId}/{gradeId}/{requestedGrade:.+}", method = RequestMethod.GET)
	public String createNewGradeChangeRequest(@PathVariable Long teacherId, @PathVariable Long studentId,
			@PathVariable Long gradeId,@PathVariable Double requestedGrade) {
        gradeChangeRequestService.createNewRequest(teacherId, studentId, gradeId, requestedGrade);
		return "redirect:/student/myGrades";
	}
	@RequestMapping(value = "teacher/gradeChangeRequest/accept/{gradeChangeRequestId}", method = RequestMethod.GET )
    public String acceptGradeChangeRequest(@PathVariable Long gradeChangeRequestId) {
		gradeChangeRequestService.accept(gradeChangeRequestId);
		return "redirect:/teacher/gradeChangeRequest/showAll";
	}
	@RequestMapping(value = "teacher/gradeChangeRequest/reject/{gradeChangeRequestId}", method = RequestMethod.GET )
    public String rejectGradeChangeRequest(@PathVariable Long gradeChangeRequestId) {
		gradeChangeRequestService.delete(gradeChangeRequestId);
		return "redirect:/teacher/gradeChangeRequest/showAll";
	}
	@RequestMapping(value = "teacher/gradeChangeRequest/showAll", method = RequestMethod.GET) 
    public String showGradeChangeRequests(Model model)  {
		User userauth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userauth.getUsername();
        com.cinek.edziennik.model.User  user = userService.findByUsername(username);
		List<GradeChangeRequest> gradeChangeRequests = gradeChangeRequestService.findByTeacherId(user.getId());
		model.addAttribute("gradeChangeRequests", gradeChangeRequests);
		return "showGradeChangeRequests";
	}
}
