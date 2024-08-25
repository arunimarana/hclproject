package crud.springboot.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import crud.springboot.model.TradeStore;
import crud.springboot.repository.TradesRepository;

@Service
public class TradesServiceImpl implements TradesService {

	@Autowired
	private TradesRepository tradeRepository;

	@Override
	public List<TradeStore> getAllTrades() {
		return tradeRepository.findAll();
	}

	@Override
	public void saveTrade(TradeStore trade) {
		trade.setCreatedDate(LocalDate.now());
		this.tradeRepository.save(trade);
	}

	@Override
	public TradeStore getTradeById(long id) {
		Optional<TradeStore> optional = tradeRepository.findById(id);
		TradeStore trade = null;
		if (optional.isPresent()) {
			trade = optional.get();
		} else {
			return null;
			//throw new RuntimeException(" Trade not found for id :: " + id);
			
		}
		return trade;
	}

	@Override
	public void deleteTradeById(long id) {
		this.tradeRepository.deleteById(id);
	}


//scheduler method, get all expired trades then change the column
	@Override
	public void schduleTradeExpiredChange() {
		List<TradeStore> tradelist = this.tradeRepository.findAllExpired();
		System.out.println(tradelist);
		tradelist.stream().forEach(trade->{
			trade.setExpired(true);
			this.tradeRepository.save(trade);
		});
	}
}
