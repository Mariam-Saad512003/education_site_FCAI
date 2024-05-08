package college.college.Controller;

import college.college.Model.*;
import college.college.Reprositry.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    StudentJpa studentJpa;
    @Autowired
    DoctorJpa doctorJpa;
    @Autowired
    CourceJpa courceJpa;

//GET COURSE FOR DOCTOR BY ID
    @GetMapping("/{doctorId}/courses")
    public ResponseEntity<?> getCoursesForDoctor(@PathVariable int doctorId) {
        DoctorClass doctor = doctorJpa.findById(doctorId).orElse(null);
        if(doctor == null){
            return ResponseEntity.badRequest().body("Doctor id is invalid or not present");

        }

        return ResponseEntity.ok(doctor.getCourses());
    }

//VIEW DOCTOR PROFILE
    @GetMapping("/profile/{id}")
    public Optional<DoctorClass> userProfile(@PathVariable int id) {
        return doctorJpa.findById(id);
    }

//UPDATE PROFILE DOCTOR
    @PutMapping("/updateDoctor/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Integer id,  @RequestBody DoctorPeofileUpdateRequest request) {
        Optional<DoctorClass> doctor = doctorJpa.findById(id);
        if (doctor.isEmpty()) {
            return ResponseEntity.badRequest().body("Doctor ID " + id + " is invalid or not present");
        }

        DoctorClass Doctor = doctor.get();

        // Validate input data
        // Validate age
        if (request.getAge() < 0) {
            return ResponseEntity.badRequest().body("Invalid date ");
        }

        // Validate email format


        // Validate password length
        if (request.getPassword().length() < 6) {
            return ResponseEntity.badRequest().body("password is too short ");
        }

        // Update DOCTOR details
            Doctor.setName(request.getName());
            Doctor.setAge(request.getAge());
            Doctor.setPassword(request.getPassword());

            doctorJpa.save(Doctor);

        return ResponseEntity.ok("Profile updated successfully");
    }

}
