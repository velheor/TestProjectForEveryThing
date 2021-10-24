package ru.example.annotations;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import ru.example.repositories.BusinessIdRepository;

import javax.persistence.Entity;
import javax.transaction.Transactional;
import java.lang.reflect.Field;

@Aspect
@Configuration
public class AspectDefinition {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private BusinessIdRepository businessIdRepository;

    @Before("execution(* org.springframework.data.repository.CrudRepository.save(..))")
    public void generateSequence(JoinPoint joinPoint) {
        Object[] argumentList = joinPoint.getArgs();
        for (Object arg : argumentList) {
            if (arg.getClass().isAnnotationPresent(Entity.class)) {
                Field[] fields = arg.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(InjectSequenceValue.class)) {
                        field.setAccessible(true);
                        try {
                            if (field.get(arg) == null) {
                                String name = field.getAnnotation(InjectSequenceValue.class).sequencename();
                                long nextval = getNextValue(name);
                                System.out.println("Next value :" + nextval);
                                field.set(arg, nextval);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

    public long getNextValue(String name) {
        int sequenceNextVal = 0;
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("VALUES NEXT VALUE FOR ACTUALENTITY;");
        while (sqlRowSet.next()) {
            sequenceNextVal = sqlRowSet.getInt("C1");
        }
        return sequenceNextVal;
    }
}
