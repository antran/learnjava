package petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import petclinic.model.Person;
import petclinic.model.Specialty;

/**
 * Created by antt on 6/30/15.
 */
@Controller
public class TestController {
    @RequestMapping(value="/testBinding", method = RequestMethod.POST)
    public @ResponseBody String testBinding(@ModelAttribute Person p, @ModelAttribute Specialty s ) {
        String result = "Empty";
        if (p != null)
            result += " " + p;
        if (s != null)
            result += " " + s;
        return result;
    }
    
    @RequestMapping(value="/getPerson", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Person getPerson() {
        Person p = new Person();
        p.setId(1);
        p.setFirstName("an");
        p.setLastName("tran");
        return p;
    }

    @RequestMapping(value="/getSpecial", method = RequestMethod.GET)
    public Specialty getSpecial() {
        Specialty p = new Specialty();
        p.setId(1);
        p.setName("Magic Power");
        return p;
    }


}
