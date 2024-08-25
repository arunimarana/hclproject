package crud.springboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mock.Strictness;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import crud.springboot.model.TradeStore;
import crud.springboot.repository.TradesRepository;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = org.mockito.quality.Strictness.LENIENT)
class TradesServiceImplTest {

	

	
	  @InjectMocks
	  TradesServiceImpl service;

	  @Mock
	  TradesRepository dao;
	  
	   private TradeStore trade;
	   
	   List<TradeStore> list;

	    @BeforeEach
	    public void setup(){	
	    	list = new ArrayList<TradeStore>();
	    	trade =new TradeStore();
	    	trade.setTradeId(1);
	    	trade.setBookId("B1");
	    	trade.setCounterPartyId("C1");
	    	trade.setMaturityDate(LocalDate.now());
	    	trade.setVersion(1);
	    	trade.setCreatedDate(LocalDate.now());
			 TradeStore trdOne = new TradeStore();
			 TradeStore trdTwo = new TradeStore();
			 TradeStore trdThree = new TradeStore();
			    list.add(trdOne);
			    list.add(trdTwo);
			    list.add(trdThree);
	    }
	  
	@Test
	void testGetAllTrades() {
		
		 

		    when(dao.findAll()).thenReturn(list);

		    //test
		    List<TradeStore> tradeList = service.getAllTrades();

		    assertEquals(3, tradeList.size());
		    verify(dao, times(1)).findAll();
	}

	@Test
	void testSaveTrade() {
		
    	
    	
		service.saveTrade(trade);
        
	    verify(dao, times(1)).save(trade);
		
	}

	@Test
	void testGetTradeById() {
		   // given
        when(dao.findById(1L)).thenReturn(Optional.of(trade));

        // when
        TradeStore savedTreade = service.getTradeById(trade.getTradeId());

        // then
        assertThat(savedTreade).isNotNull();
	}

	@Test
	void testDeleteTradeById() {
		 // given - precondition or setup
        long tradeId = 1L;

        doNothing().when(dao).deleteById(tradeId);

        // when -  action or the behaviour that we are going test
        service.deleteTradeById(tradeId);

        // then - verify the output
        verify(dao, times(1)).deleteById(tradeId);
	}

	@Test
	void testSchduleTradeExpiredChange() {
		
		  when(dao.findAllExpired()).thenReturn(list);
		  
		    service.schduleTradeExpiredChange();
		    assertEquals(3, list.size());
		    verify(dao, times(1)).findAllExpired();
        
      
	}
	



	@Test
	void testCommon()
	{
		TradeStore t=new TradeStore();
		trade.equals(t);
		t.setBookId(trade.getBookId());
		t.setTradeId(trade.getTradeId());
		t.setCounterPartyId(trade.getCounterPartyId());
		t.setExpired(trade.isExpired());
		t.setCreatedDate(trade.getCreatedDate());
		t.setMaturityDate(trade.getMaturityDate());
		t.setVersion(trade.getVersion());
		trade.equals(t);
	}
	
}
