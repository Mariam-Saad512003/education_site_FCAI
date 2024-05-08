package college.college.Reprositry;

import college.college.Model.DoctorClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorJpa extends JpaRepository<DoctorClass,Integer> {
    DoctorClass findByEmail(String email);

}
