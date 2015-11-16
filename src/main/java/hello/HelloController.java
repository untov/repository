package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController {

    @Autowired
    private RaportBuilder raportBuilder;
    
    @RequestMapping("/hoo")
    public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String stop = "here";

        response.setContentType("application/pdf");

        //RaportBuilder raportBuilder = new RaportBuilder();
        raportBuilder.buildPdfReport(response.getOutputStream());

        return "Greetings from Spring Boot! ??";
    }
    
}
