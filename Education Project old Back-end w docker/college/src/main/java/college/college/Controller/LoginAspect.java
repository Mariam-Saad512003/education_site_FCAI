package college.college.Controller;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspect {

    @Before("execution(* college.college.Controller.AdminController.*.*(..))")
    public void beforeAdminMethodExecution(JoinPoint joinPoint) {
        System.out.println("Executing method in Admin package: " + joinPoint.getSignature());
    }

    @Before("execution(* college.college.Controller.DoctorController.*.*(..))")
    public void beforeDoctorMethodExecution(JoinPoint joinPoint) {
        System.out.println("Executing method in Doctor package: " + joinPoint.getSignature());
    }

    @Before("execution(* college.college.Controller.StudentController.*.*(..))")
    public void beforeStudentMethodExecution(JoinPoint joinPoint) {
        System.out.println("Executing method in Student package: " + joinPoint.getSignature());
    }

    @Before("execution(* college.college.Controller.*.*(..))")
    public void testAspectExecution(JoinPoint joinPoint) {
        System.out.println("Aspect method executed: " + joinPoint.getSignature());
    }
}
