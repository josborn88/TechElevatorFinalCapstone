package com.techelevator.controller;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.Vulnerability;
import com.techelevator.model.dao.VulnerabilityDao;
import com.techelevator.model.dao.jdbc.Report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiController
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private AuthProvider authProvider;
    @Autowired 
    private VulnerabilityDao vulnerabilityDao;

//    @RequestMapping(path = "/", method = RequestMethod.GET)
//    public String authorizedOnly() throws UnauthorizedException {
//        /*
//        You can lock down which roles are allowed by checking
//        if the current user has a role.
//        
//        In this example, if the user does not have the admin role
//        we send back an unauthorized error.
//        */
//        if (!authProvider.userHasRole(new String[] { "admin" })) {
//            throw new UnauthorizedException();
//        }
//        return "Success";
//    }
    
    @GetMapping
	public List<Vulnerability> list() {
    	System.out.println("get reports");
		return vulnerabilityDao.viewAllVulnerabilityReports();
	}
    

    @PostMapping("/report")
    @ResponseStatus(HttpStatus.CREATED)
    public void addVulnerability(@RequestBody Report report) {
    	System.out.println("report request went through");
    	vulnerabilityDao.reportVulnerability(report);
    }
    
    @GetMapping("/report")
    public String reportPage() {
    	
    	return "report";
    }
    
    // Update Priority // 
    
    @PutMapping("/updatePriority")
    @ResponseStatus(HttpStatus.OK)
    public void updatePriority(@RequestBody Vulnerability vulnerability) {

    	vulnerabilityDao.updateReportPriority(vulnerability, vulnerability.getPriority());
    	
    }
    
    // Update is Repaired // 
    
    @PutMapping("/updateIsRepaired")
    @ResponseStatus(HttpStatus.OK)
    public void updateIsRepaired(@RequestBody Vulnerability vulnerability) {
    	vulnerabilityDao.updateIsRepaired(vulnerability);
    }
    
    // Update Is Duplicate // 
    @PutMapping("/updateDuplicate")
    @ResponseStatus(HttpStatus.OK)
    public void updateIsDuplicate(@RequestBody Vulnerability vulnerability) {

    	vulnerabilityDao.updateIsDuplicate(vulnerability);
    }
    
    @PutMapping("/updateRepairDate")
    @ResponseStatus(HttpStatus.OK)
    public void updateRapairDate(@RequestBody Vulnerability vulnerability) {
    	System.out.println(vulnerability.getRepairDate());
    	vulnerabilityDao.updateRepairDate(vulnerability, vulnerability.getRepairDate());
    }
    
  
}