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
    
    @RequestMapping("/hooo")
    public String index(HttpServletResponse response) throws Exception {

        response.setContentType("application/pdf");

        raportBuilder.buildPdfReport(response.getOutputStream());

        return "Raportti hukassa???";
    }
    
}
