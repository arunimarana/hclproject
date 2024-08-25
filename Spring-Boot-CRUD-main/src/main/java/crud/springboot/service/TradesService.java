package crud.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import crud.springboot.model.TradeStore;

public interface TradesService {
	 List<TradeStore> getAllTrades();
	void saveTrade(TradeStore  trade);
	TradeStore getTradeById(long id);
	void deleteTradeById(long id);
	
	void schduleTradeExpiredChange();
}
