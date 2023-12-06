package esprit.exam.datacenter.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class Logging {
    private long t1, t2;

    @Before("execution(* esprit.exam.datacenter.service.*.*(..))") //PointCut
    public void avant(JoinPoint thisJoinPoint) {
        t1 = System.currentTimeMillis();
        log.info("In the method"+ thisJoinPoint.getSignature().getName());
    }

    @After("execution(* esprit.exam.datacenter.service.*.*(..))")
    public void apres(JoinPoint thisJoinPoint) {
        t2 = System.currentTimeMillis();
        log.info("Exuction Time of methode " + thisJoinPoint.getSignature() + " is  " + (t2 - t1) + " ms");
        log.info("Out of the method (After)"+ thisJoinPoint.getSignature().getName());
    }


}
