package com.example.postgresdemo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RestController
public class SampleWebUIController {

    @Autowired
    ValueRepository repository;

    public SampleWebUIController() {

    }

    @GetMapping(value = "/put/{item}")
    public String putitem(@PathVariable String item)   {
        
    	
    	repository.save(new Value(item));
        return "The item " + item +" was put in";
    }
    
    @GetMapping(value = "/")
  	 public String  getAllValues() {

    	StringBuffer sb = new StringBuffer();
    	List<Value> values = (List<Value>) repository.findAll();
    	
    	 for (Value v : values) {
             System.out.println(sb.append("id="+v.getId() +" value = " + v.getValue()+"<br>"));
         }
    	 if (values==null || values.isEmpty()) {
    		 sb.append("there are no value");
    	 }
    	
    	 sb.append("Put in values by using /put/{value}");
  		return sb.toString();
  	}

  

}
