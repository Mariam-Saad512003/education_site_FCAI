package college.College.Controller;

import college.College.Model.AdminClass;
import college.College.Model.DoctorClass;
import college.College.Model.LoginClass;
import college.College.Model.StudentClass;
import college.College.Reprositry.AdminJpa;
import college.College.Reprositry.CourceJpa;
import college.College.Reprositry.DoctorJpa;
import college.College.Reprositry.StudentJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

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
    public String loginForm(@Validated @RequestBody LoginClass data){
        String email = data.getUsername();
        String password = data.getPassword();
       StudentClass s =  studentJpa.findByEmail(email);
        DoctorClass d =  doctorJpa.findByEmail(email);
        AdminClass A =  adminJpa.findByEmail(email);


        if(s != null){
           if(Objects.equals(password, s.getPassword())){
               return "welcome student"; // student account

           };
        }

        if(A != null){
            if(Objects.equals(password, A.getPassword())){
                return "welcome Admin"; // student account

            };
        }
        if(d != null){
            if(Objects.equals(password, d.getPassword())){
                return "welcome doctor"; // doctor account

            };
        }
        return "invalid Email or Password";
    }





}
