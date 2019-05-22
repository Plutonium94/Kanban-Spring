package fr.unice.kanban.repositories;

import fr.unice.kanban.entities.*;
import fr.unice.kanban.repositories.*;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Component
public class UserConverter implements Converter<String, KUser> {

	@Autowired
	private UserRepository userRepo;

	public UserConverter() {

	}

	@Override
	public KUser convert(String username) {
		Optional<KUser> optUser = userRepo.findById(username);
		if(optUser.isPresent()) {
			return optUser.get();
		} else {
			return null;
		}
	}


}