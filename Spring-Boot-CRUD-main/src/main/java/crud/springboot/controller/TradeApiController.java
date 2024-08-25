package crud.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import crud.springboot.model.TradeStore;
import crud.springboot.service.TradesService;

@Controller
public class TradeApiController {

	@Autowired
	private TradesService tradeService;

	// get your trade

	@GetMapping("/trade")
	public String getTradeByID(@RequestParam("tradeId") Optional<String> tradeIdOp, Model model) {

		List<TradeStore> listTrades = new ArrayList<>();
		TradeStore trade = null;
		String tradeId = "";

		// get trade from the service
		if (tradeIdOp.isPresent()) {

			tradeId = tradeIdOp.get();

			try {
				trade = tradeService.getTradeById(Long.parseLong(tradeId));
			} catch (NumberFormatException nfe) {

			}
		}
		if (trade != null)
			listTrades.add(trade);
		model.addAttribute("listTrades", listTrades);
		return "index";
	}

	// display list of trades
	@GetMapping("/")
	public String viewHomePage(Model model) {

		List<TradeStore> listTrades = null;

		listTrades = tradeService.getAllTrades();

		model.addAttribute("listTrades", listTrades);
		return "index";

	}

	@GetMapping("/showNewTradeForm")
	public String showTradeForm(Model model) {
		// link back form fill page
		TradeStore trade = new TradeStore();
		model.addAttribute("trade", trade);
		return "new_trade";
	}

	@PostMapping("/saveTrade")
	public String saveTrade(@ModelAttribute("trade") TradeStore trade, Model model) {
		// save trade to database
		Optional<TradeStore> optional = Optional.ofNullable(tradeService.getTradeById(trade.getTradeId()));
		if (optional.isPresent()) {
			TradeStore oldtrade = optional.get();
			if (oldtrade.getVersion() > trade.getVersion()) {
				model.addAttribute("trade", trade);
				model.addAttribute("message", "Version should be greater then or equal:" + oldtrade.getVersion());

				return "update_trade";
			}
		}
		tradeService.saveTrade(trade);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

		// get trade from the service
		TradeStore trade = tradeService.getTradeById(id);

		// set trade as a model attribute to pre-populate the form
		model.addAttribute("message", null);
		model.addAttribute("trade", trade);
		return "update_trade";
	}

	@GetMapping("/deleteTrade/{tradeId}")
	public String deleteTrade(@PathVariable(value = "tradeId") long id) {

		// call delete trade method
		this.tradeService.deleteTradeById(id);
		return "redirect:/";
	}

}
