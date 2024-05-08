package college.college.Reprositry;

import college.college.Model.StudentClass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentJpa extends JpaRepository<StudentClass,Integer> {
    StudentClass findByEmail(String email);

}
