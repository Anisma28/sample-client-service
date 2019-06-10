package com.example.sampleclientservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ClientController {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/ping")
	public List<ServiceInstance> ping(){
		List<ServiceInstance> instances = discoveryClient.getInstances("CLIENT-SERVICE");
		instances.stream().forEach(it -> log.info("INSTANCE: ID= {}, port={}", it.getInstanceId(), it.getPort()));
		log.info("Instances: count= {}", instances.size());
		
		return instances;
	}

}
