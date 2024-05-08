package college.college.Reprositry;

import college.college.Model.AdminClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminJpa extends JpaRepository<AdminClass , Integer> {

    AdminClass findByEmail(String email);

}
