package com.schrodingdong.backend.controller;

import com.schrodingdong.backend.model.ResponseModel;
import com.schrodingdong.backend.model.UserModel;
import com.schrodingdong.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("")
@RestController
@AllArgsConstructor
public class Controller {
    private final UserService userService;


    @RequestMapping("/no-cache/get")
    public ResponseEntity<?> index(@RequestParam("uuid") String uuid) {
        Date start = new Date();
        UserModel user =  userService.getUser(uuid);
        if(user == null) return ResponseEntity.notFound().build();

        Date end = new Date();
        Date timeForRequest = new Date(end.getTime() - start.getTime());
        ResponseModel response = new ResponseModel(user, timeForRequest.getTime());
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping("/memory-cache/get")
    public ResponseEntity<?> indexCache(@RequestParam("uuid") String uuid) {
        Date start = new Date();
        UserModel user =  userService.getUserMemoryCache(uuid);
        if(user == null) return ResponseEntity.notFound().build();

        Date end = new Date();
        Date timeForRequest = new Date(end.getTime() - start.getTime());
        ResponseModel response = new ResponseModel(user, timeForRequest.getTime());
        return ResponseEntity.ok().body(response);
    }
}
