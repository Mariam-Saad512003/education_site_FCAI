package college.College.Controller;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspect {

    @Before("execution(* college.College.Controller.Admin.*.*(..))")
    public void beforeAdminMethodExecution(JoinPoint joinPoint) {
        System.out.println("Executing method in Admin package: " + joinPoint.getSignature());
    }

    @Before("execution(* college.College.Controller.Doctor.*.*(..))")
    public void beforeDoctorMethodExecution(JoinPoint joinPoint) {
        System.out.println("Executing method in Doctor package: " + joinPoint.getSignature());
    }

    @Before("execution(* college.College.Controller.Student.*.*(..))")
    public void beforeStudentMethodExecution(JoinPoint joinPoint) {
        System.out.println("Executing method in Student package: " + joinPoint.getSignature());
    }
}
