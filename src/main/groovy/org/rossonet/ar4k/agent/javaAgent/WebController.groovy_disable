package org.rossonet.ar4k.agent.javaAgent


import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.client.RestTemplate

@Controller
public class WebController {

	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

	@RequestMapping("/google")
	@ResponseBody
	public String proxy(@RequestBody String body, HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException {
		String server = "news.google.it";
		int port = 8080;

		Anima.stampa("body: "+body+", request: "+request.toString())

		URI uri = new URI("https", null, server, port, request.getRequestURI(), request.getQueryString(), null);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity =
				restTemplate.exchange(uri, method, new HttpEntity<String>(body), String.class);

		return responseEntity.getBody();
	}
}
