package com.huhoot.admin.manage.host;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/test")
public class Test2Controller {
    @GetMapping("/huynek")
    public ResponseEntity<String> huynek(){
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("Chung ta cua hien tai");
    }
}
