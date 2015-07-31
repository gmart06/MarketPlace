package fr.m2i.stage.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.m2i.stage.marketplace.domain.entity.Merchant;
import fr.m2i.stage.marketplace.domain.repository.MerchantRepository;

@Service
public class MerchantService {
	
	private MerchantRepository merchantRepository;

	@Autowired
	public MerchantService(MerchantRepository merchantRepository) {
		this.merchantRepository = merchantRepository;
	}
	
	public boolean hasMerchant(Long id) {
		return merchantRepository.exists(id);
	}
	
	public Merchant findById(Long id) {
		return merchantRepository.findOne(id);
	}
	
	public Merchant findByIdWithAllCatlalog(Long id) {
		return merchantRepository.findById(id);
	}
	
	@Transactional
	public void add(Merchant merchant) {
		merchantRepository.save(merchant);
	}

}
