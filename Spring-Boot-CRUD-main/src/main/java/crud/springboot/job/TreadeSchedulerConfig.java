package crud.springboot.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import crud.springboot.service.TradesService;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class TreadeSchedulerConfig {
	
	
	@Autowired
	TradesService tradesService;
	
	  @Scheduled(cron = "${interval-in-cron}")
	  public void computePrice() throws InterruptedException {
		  tradesService.schduleTradeExpiredChange();
	  }

}
