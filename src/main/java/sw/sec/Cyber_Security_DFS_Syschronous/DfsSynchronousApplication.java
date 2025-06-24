package sw.sec.Cyber_Security_DFS_Syschronous;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DfsSynchronousApplication {
	private static final Logger log = LoggerFactory.getLogger(DfsSynchronousApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(DfsSynchronousApplication.class, args);
		log.info("Starting DFS Synchronous Application...");
		log.info("___________________________________________");
	}

}
