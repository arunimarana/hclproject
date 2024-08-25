package crud.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import crud.springboot.model.TradeStore;

@Repository
public interface TradesRepository extends JpaRepository<TradeStore, Long>{
	
	@Query("SELECT e FROM TradeStore e WHERE e.maturityDate < CURRENT_DATE and e.expired=false")
	List<TradeStore> findAllExpired();

}
