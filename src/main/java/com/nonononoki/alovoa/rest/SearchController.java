package com.nonononoki.alovoa.rest;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nonononoki.alovoa.model.AlovoaException;
import com.nonononoki.alovoa.service.AuthService;
import com.nonononoki.alovoa.service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@Autowired
	private AuthService authService;

	@GetMapping("/users/{latitude}/{longitude}/{distance}/{search}")
	public String getUsers(Model model, @PathVariable Double latitude, @PathVariable Double longitude,
			@PathVariable int distance, @PathVariable int search)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, AlovoaException {
		model.addAttribute("dto", searchService.search(latitude, longitude, distance, search));
		model.addAttribute("currUser", authService.getCurrentUser());
		return "fragments :: search-users";
	}
	
	@GetMapping("/users/default")
	public String getUsersDefault(Model model)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, AlovoaException {
		model.addAttribute("dto", searchService.searchDefault());
		model.addAttribute("currUser", authService.getCurrentUser());
		return "fragments :: search-users";
	}
}
