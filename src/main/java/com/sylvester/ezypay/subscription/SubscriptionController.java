package com.sylvester.ezypay.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/subscription")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ResponseEntity<SubscriptionBean> submit(@RequestBody SubscriptionBean requestBean)
            throws Exception {

        SubscriptionBean subscriptionBean = subscriptionService.submit(requestBean);
        
        return new ResponseEntity<SubscriptionBean>(subscriptionBean, HttpStatus.OK);
    }

}
