package fr.m2i.stage.marketplace.domain.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import fr.m2i.stage.marketplace.domain.entity.Merchant;

public interface MerchantRepository extends CrudRepository<Merchant, Long> {
	
	@EntityGraph(value="Merchant.Catalog")
	Merchant findById(Long id);

}
