package com.example.demo.controller;


import com.example.demo.entity.BatchQueryInfo;
import com.example.demo.entity.QueryInfo;
import com.example.demo.entity.User;
import com.example.demo.service.FileService;
import com.example.demo.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping()
public class QueryController {
    @Autowired
    private FileService fileService;
    @Autowired
    private QueryService queryService;

    @RequestMapping(value = "/api/user/get", method = RequestMethod.POST)
//    @PostMapping("/get")
    @CrossOrigin
    public User queryUser(@RequestBody QueryInfo queryInfo) {
        User result = queryService.getUser(queryInfo);
        return result;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @CrossOrigin
    public User queryUser_test(QueryInfo queryInfo) {
        System.out.println(queryInfo.getCustId());
        User result = queryService.getUser(queryInfo);
        return result;
    }
    @RequestMapping(value = "/api/user/export", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<Map<String,String>> generateZipFile(@RequestBody BatchQueryInfo batchQueryInfo) {
//        Map<String, String> response1 = new HashMap<>();
//        response1.put("url", "/download/custInfo.zip");
//        return ResponseEntity.ok(response1);
        try {
            String[] ps = batchQueryInfo.getProvince().split(",");
            batchQueryInfo.setProvinces(ps);
            List<String> data = queryService.getCustIdBatch(batchQueryInfo);
//            Collections.sort(data);
            // Generate the zip file and get the URL
            String fileUrl = fileService.generateZipFile(data);
            Map<String, String> response = new HashMap<>();
            response.put("url", fileUrl);

            // Return the URL to the client
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<FileSystemResource> getFile(@PathVariable String fileName) {
        FileSystemResource file = fileService.getFile(fileName);
        if (file.exists()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
