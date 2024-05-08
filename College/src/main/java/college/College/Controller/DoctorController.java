package college.College.Controller;

import college.College.Model.CourceClass;
import college.College.Model.DoctorClass;
import college.College.Model.GradeClass;
import college.College.Model.StudentClass;
import college.College.Reprositry.CourceJpa;
import college.College.Reprositry.DoctorJpa;
import college.College.Reprositry.StudentJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    StudentJpa studentJpa;
    @Autowired
    DoctorJpa doctorJpa;
    @Autowired
    CourceJpa courceJpa;


    @GetMapping("/{doctorId}/courses")
    public ResponseEntity<?> getCoursesForDoctor(@PathVariable int doctorId) {
        DoctorClass doctor = doctorJpa.findById(doctorId).orElse(null);
        if(doctor == null){
            return ResponseEntity.badRequest().body("Doctor id is invalid or not present");

        }

        return ResponseEntity.ok(doctor.getCourses());
    }


    @GetMapping("/profile/{id}")
    public Optional<DoctorClass> userProfile(@PathVariable int id) {
        return doctorJpa.findById(id);
    }
/*
    @GetMapping("/GetstudentGrades/{id}")
    public ResponseEntity<?> getGradesForDoctor(@PathVariable int id) {
        try {
            // Step 1: Find the doctor by ID
            Optional<DoctorClass> doctorOptional = doctorJpa.findById(id);

            // Check if the doctor exists
            if (doctorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            DoctorClass doctor = doctorOptional.get();

            // Step 2: Get Doctor's Course Subject IDs
            List<Integer> subjectIds = doctor.getCourses().stream()
                    .map(CourceClass::getSubjectId)
                    .collect(Collectors.toList());

            // Step 3: Get Students Assigned to Matching Courses
            List<StudentClass> students = studentJpa.findByEnrolledCoursesSubjectIdIn(subjectIds);

            // Step 4: Retrieve GPA of Each Student
            List<Float> studentGPAs = students.stream()
                    .map(StudentClass::getAccumulator_gpa)
                    .collect(Collectors.toList());

            // Return the list of student GPAs
            return ResponseEntity.ok(studentGPAs);
        } catch (Exception e) {
            // If an error occurs during the process, return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching grades.");
        }
    }
    */


}
