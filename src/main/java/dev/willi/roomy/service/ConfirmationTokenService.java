package dev.willi.roomy.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.willi.roomy.model.entity.ConfirmationToken;
import dev.willi.roomy.repository.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenService {
	
	//attributes

	private final ConfirmationTokenRepository confirmationTokenRepository;

	//constructors

	public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
		super();
		this.confirmationTokenRepository = confirmationTokenRepository;
	}
	
	//methods

	public void saveConfirmationToken(ConfirmationToken token) {
		confirmationTokenRepository.save(token);
	}
	
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
	
}
