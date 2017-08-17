package com.cinek.edziennik.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cinek.edziennik.exception.StudentNoSuchCourseException;
import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Grade;
import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.service.CourseService;
import com.cinek.edziennik.service.StudentService;
import com.cinek.edziennik.service.UserService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	UserService userService;
	@Autowired
	CourseService courseService;
	@Autowired
	StudentService studentService;

	@RequestMapping(value = "/setGrade/{courseId}/{studentId}/{grade:.+}", method = RequestMethod.GET)
	public String setGrade(Model model, @PathVariable Long studentId, @PathVariable Long courseId,
			@PathVariable double grade) {
		try {
			courseService.setGradeFromCourse(studentId, courseId, grade);
		} catch (StudentNoSuchCourseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/showStudents/" + courseId;

	}

	@RequestMapping("/showCourses")
	public String showCoursesTaught(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		Map<Course,Integer> coursesMap = courseService.getCoursesTeacherTeachesWithSize(username);
		model.addAttribute("coursesMap", coursesMap);
		return "teacherCourses";

	}

	@RequestMapping("/showStudents/{courseId}")
	public String showStudentsAttendingCourse(Model model, @PathVariable Long courseId) {
		List<Student> students = courseService.getStudentsAttendingCourse(courseId);
		model.addAttribute("courseId", courseId);
		// TODO zrób mapê która robi pare (student,ocena z kursu zeby bylo
		// latwiej to wyswietlic,
		// jak nie ma oceny to zmapuj null)
		Map<Student, String> studentGradeMap = makeMap(students, courseId);
		model.addAttribute("studentsMap", studentGradeMap);
		return "showStudents";
	}

	private Map<Student, String> makeMap(List<Student> students, Long courseId) {
		Map<Student, String> map = new HashMap<Student, String>();
		for (Student student : students) {
			Long studentId = student.getId();
			Grade grade = studentService.findStudentsGradeById(studentId, courseId);
			if (grade != null) {
				map.put(student,  String.valueOf(grade.getGrade()));
			} else {
				map.put(student, "---");
			}

		}
		return map;
	}

}
