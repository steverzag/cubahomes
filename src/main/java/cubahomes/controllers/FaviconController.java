package cubahomes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("favicon.ico")
public class FaviconController {

	@GetMapping
	@ResponseBody
	public void retunrnNoFavicon() {
		
	}
}
