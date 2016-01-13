package test.test.test.test.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MrLog {

    private static final Logger log = LoggerFactory.getLogger(MrLog.class);
    
    public static void main(String[] args) {
        
        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            log.error("hello {}", "world", e);
        }
        
    }
}
