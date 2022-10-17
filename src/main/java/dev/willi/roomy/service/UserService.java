package dev.willi.roomy.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.willi.roomy.model.SecurityUser;
import dev.willi.roomy.model.entity.ConfirmationToken;
import dev.willi.roomy.model.entity.User;
import dev.willi.roomy.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	// attributes
	
	private final static String USER_NOT_FOUND_MSG = "The email adress: %s, could not been found!";
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenService confirmationTokenService;
	
	//constructors
	
	public UserService(UserRepository userRepository, 
					   BCryptPasswordEncoder bBCryptPasswordEncoder, 
					   ConfirmationTokenService confirmationTokenService) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bBCryptPasswordEncoder;
		this.confirmationTokenService = confirmationTokenService;
	}
	
	//methods
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email)
				.map(SecurityUser::new)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
	}

	public String signUpUser(User user) {
		boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
		
		if (userExists) {
			throw new IllegalStateException("This email adress is already in use!");
		}
		
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);
		
		userRepository.save(user);
		
		String token = UUID.randomUUID().toString();
		
		ConfirmationToken confirmationToken = new ConfirmationToken(token, 
																	LocalDateTime.now(), 
																	LocalDateTime.now().plusMinutes(1440), //24h
																	user);
		
		confirmationTokenService.saveConfirmationToken(confirmationToken);
		
		return token;
	}
	
	public User getUserByMail(String email) {
		Optional<User> result = userRepository.findByEmail(email);
		
		User user = null;
		
		if (result.isPresent()) {
			user = result.get();
		} else {
			throw new UsernameNotFoundException(USER_NOT_FOUND_MSG);
		}
		
		return user;
	}
	
	public String deleteUserByMail(String email) {
		userRepository.delete(getUserByMail(email));
		
		return "Deleted user with email adress: " + email;
	}

	public String updateUser(User user) {
		userRepository.save(user);
		
		return "Updated user: " + user;
	}

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

}
