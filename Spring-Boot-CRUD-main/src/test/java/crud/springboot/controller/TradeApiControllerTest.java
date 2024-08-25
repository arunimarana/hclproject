/**
 * 
 */
package crud.springboot.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import crud.springboot.model.TradeStore;
import crud.springboot.service.TradesService;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = org.mockito.quality.Strictness.LENIENT)
class TradeApiControllerTest {
	

    @Mock
    private TradesService tradeService;
    
    
    private Model model;
    private TradeStore trade;
	List<TradeStore> list;
    
    @BeforeEach
    public void setup(){
        
    	model=new ConcurrentModel();
    	
    	trade = new TradeStore();
    	trade.setTradeId(1);
    	trade.setBookId("B1");
    	
    	list = new ArrayList<TradeStore>();
		 TradeStore trdOne = new TradeStore();
		 TradeStore trdTwo = new TradeStore();
		 TradeStore trdThree = new TradeStore();
		    list.add(trdOne);
		    list.add(trdTwo);
		    list.add(trdThree);
    }
    
    @InjectMocks
    TradeApiController controller;
	/**
	 * Test method for {@link crud.springboot.controller.TradeApiController#getTradeByID(java.util.Optional, org.springframework.ui.Model)}.
	 */
	@Test
	void testGetTradeByID() {
		when(tradeService.getTradeById(11)).thenReturn(trade);
		Optional<String> tradeIdOp=Optional.of("TradeId");
		controller.getTradeByID(tradeIdOp, model);
		  
	}
	
	@Test
	void testSaveTrade() {
		when(tradeService.getTradeById(11)).thenReturn(trade);
		controller.saveTrade(trade, model);
		
	}

	/**
	 * Test method for {@link crud.springboot.controller.TradeApiController#showFormForUpdate(long, org.springframework.ui.Model)}.
	 */
	@Test
	void testShowFormForUpdate() {
		controller.showFormForUpdate(11, model);
	}
	
	/**
	 * Test method for {@link crud.springboot.controller.TradeApiController#deleteTrade(long)}.
	 */
	@Test
	void testDeleteTrade() {
		 doNothing().when(tradeService).deleteTradeById(1);
		controller.deleteTrade(11);
	}
	

	
}
