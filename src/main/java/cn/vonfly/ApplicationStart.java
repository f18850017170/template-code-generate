package cn.vonfly;

import cn.vonfly.execute.CodeGenerateExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Feng
 */
@SpringBootApplication
public class ApplicationStart implements CommandLineRunner {
    @Autowired
    private CodeGenerateExecutor codeGenerateExecutor;
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        codeGenerateExecutor.exec();
    }
}
