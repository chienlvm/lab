package chienlvm.fsoft.vn.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class MyScheduler {

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier(value = "importBookToEls")
	private Job fetchEls;
	
	@Autowired
	@Qualifier(value = "sendMail")
	private Job aaa;
	@Scheduled(cron="*/10 * * * * *")
	public void myScheduler(){
		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
		
		try {
			JobExecution jobExecution = jobLauncher.run(fetchEls, jobParameters);
			System.out.println("Job's Status fetchEls :::"+jobExecution.getStatus());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}
	
	@Scheduled(cron="*/10 * * * * *")
	public void sendMail(){
		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
		
		try {
			JobExecution jobExecution = jobLauncher.run(aaa, jobParameters);
			System.out.println("Job's Status sendMail:::"+jobExecution.getStatus());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}
}
