package college.college.Controller;

import college.college.Model.*;
import college.college.Reprositry.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;


@RestController
public class LoginController {
    @Autowired
    StudentJpa studentJpa;
    @Autowired
    DoctorJpa doctorJpa;
    @Autowired
    AdminJpa adminJpa;
    @Autowired
    CourceJpa courceJpa;

    @PostMapping("/login")
    public ResponseEntity<?> loginForm(@Validated @RequestBody LoginClass data) {
        String email = data.getUsername();
        String password = data.getPassword();

        StudentClass student = studentJpa.findByEmail(email);
        DoctorClass doctor = doctorJpa.findByEmail(email);
        AdminClass admin = adminJpa.findByEmail(email);

        if (student != null && Objects.equals(password, student.getPassword())) {
            return ResponseEntity.ok(Map.of("id", student.getId(), "role", "student"));
        } else if (doctor != null && Objects.equals(password, doctor.getPassword())) {
            return ResponseEntity.ok(Map.of("id", doctor.getId(), "role", "doctor"));
        } else if (admin != null && Objects.equals(password, admin.getPassword())) {
            return ResponseEntity.ok(Map.of("id", admin.getId(), "role", "admin"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
    
}
