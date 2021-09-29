package cubahomes.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cubahomes.services.StorageService;

@Controller
@RequestMapping("/file")
public class FileHandlingController {
	
	@Autowired
	private StorageService storage;
	
	@PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String uploadAnounceImages(@RequestParam MultipartFile file) {
		
//		storage.store(file);
//		System.out.println("stored");
		return "redirect:/file";
	}
}
