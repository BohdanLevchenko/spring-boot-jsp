package com.example.jsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.HEAD;

@SpringBootApplication
@Controller
@RequestMapping({""})
public class JspApplication {
  private static final Logger LOGGER = LoggerFactory.getLogger(JspApplication.class);

	public static void main(String[] args) throws UnknownHostException {
    SpringApplication app = new SpringApplication(JspApplication.class);
    Environment env = app.run(args).getEnvironment();
    LOGGER.info("\n----------------------------------------------------------\n\t" +
            "Application '{}' is running! Access URLs:\n\t" +
            "Local: \t\thttp://127.0.0.1:{}\n\t" +
            "External: \thttp://{}:{}\n----------------------------------------------------------",
        env.getProperty("spring.application.name"),
        env.getProperty("server.port"),
        InetAddress.getLocalHost().getHostAddress(),
        env.getProperty("server.port"));
	}

  @RequestMapping(method = {GET, HEAD})
  String index(Model model) {
    model.addAttribute("time", LocalDateTime.now());
    return "index";
  }
}
