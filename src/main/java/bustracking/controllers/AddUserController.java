package bustracking.controllers;




import java.io.IOException;
import java.security.Principal;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import bustracking.model.*;
import bustracking.dao.AddUserDAO;
import bustracking.dao.OrgRegistrationDAO;
import bustracking.forms.AddUserForm;
import bustracking.forms.OrgRegistrationForm;
import bustracking.forms.StudentRegistrationForm;
import bustracking.dao.BusRegistrationDAO;
import bustracking.forms.BusRegistrationForm;



@Controller
@SessionAttributes({"adminuser"})
public class AddUserController{
	
	@Autowired
	AddUserDAO userDAO;
	
	@Autowired
	OrgRegistrationDAO orgRegistrationDAO;
	
	@Autowired  
	BusRegistrationDAO busDAO; 
	
	
	@RequestMapping(value="/businessrulesregistration", method=RequestMethod.GET)
	public String businessrulesregistration(HttpSession session,ModelMap model, Principal principal){
		
		
	return "add_businessRules_registration";
	}
	

	@RequestMapping(value="/changepassword", method=RequestMethod.GET)
	public String changepassword(HttpSession session,ModelMap model, Principal principal){
		
		
	return "add_device_setup";
	}
	
	@RequestMapping(value="/adduser", method=RequestMethod.GET)
	public String printWelcome(HttpSession session,ModelMap model, Principal principal){
		session.removeAttribute("adminuser");
		
		
		List <String> orgname=new ArrayList<String>();
		orgname=busDAO.getorgname();
		OrgRegistrationForm orgRegistrationForm=new OrgRegistrationForm();
		orgRegistrationForm.setOrgregistration(orgRegistrationDAO.getOrgregistration());
		model.addAttribute("orgRegistrationForm",orgRegistrationForm);
		model.addAttribute("orgname",orgname);
		
	return "add_adminuser";
	}
	
	@RequestMapping(value="/add_user_ajax",method=RequestMethod.POST)
	public @ResponseBody String bus_reg(@RequestParam("org_name") String org_name,ModelMap model ) {
	
		String returnText="";
			List <String> branch=new ArrayList<String>();
		branch=busDAO.getBus_id(org_name);
		
		returnText=returnText+"<select id='bid' name='branch'  class='org_input_cmbbx'>";
		returnText+="<option value='' selected>--Select Branch--</option>";
		for(String bname:branch)
		{
			returnText+="<option value='"+bname+"'>"+bname+"</option>";
			}			
		  
	   returnText=returnText+"</select>";		 
	return returnText;
	}
	
@RequestMapping(value="/adduser", method=RequestMethod.POST)
public String adduser(HttpServletRequest request,HttpSession session,@ModelAttribute("AddUser") @Valid AddUser user, BindingResult result,ModelMap model){
    session.setAttribute("adminuser", user);
    
    OrgRegistrationForm orgRegistrationForm=new OrgRegistrationForm();
	orgRegistrationForm.setOrgregistration(orgRegistrationDAO.getOrgregistration());
	model.addAttribute("orgRegistrationForm",orgRegistrationForm);
	
	user.setOrg_id(orgRegistrationDAO.getOrg_id(request.getParameter("org_name"),request.getParameter("branch")));
	
	if(result.hasErrors()){
		AddUserForm adduserform=new AddUserForm();
		model.addAttribute("adduserform",adduserform);
		return "add_adminuser";
	}
	else{
		if(userDAO.check_user_name(user))		
			userDAO.insert_user(user);
		else
		{
			model.addAttribute("userexists","Username already exists!");
			return "add_adminuser";
		}
		AddUserForm adduserform=new AddUserForm();
		adduserform.setAdduser(userDAO.getAdduser());
		model.addAttribute("adduserform",adduserform);
    return "view_adminuser";
	}
}

@RequestMapping(value="/viewuser", method=RequestMethod.GET)
public String viewuser(ModelMap model, Principal principal){
	
	AddUserForm adduserform=new AddUserForm();
	adduserform.setAdduser(userDAO.getAdduser());
	model.addAttribute("adduserform",adduserform);
	return "view_adminuser";
}

@RequestMapping(value="/edituser",method=RequestMethod.GET)
public String edituser(HttpServletRequest request,@RequestParam("org_id")String org_id,ModelMap model,OrgRegistration orgregistration)
{
	OrgRegistrationForm orgRegistrationForm=new OrgRegistrationForm();
	orgRegistrationForm.setOrgregistration(orgRegistrationDAO.getOrgregistration());
	model.addAttribute("orgRegistrationForm",orgRegistrationForm);
	
	
	AddUserForm adduserform=new AddUserForm();
	adduserform.setAdduser(userDAO.getUsers(org_id));
	model.addAttribute("adduserform",adduserform);
	return "edit_user";
	
}

@RequestMapping(value="/updateuser",method=RequestMethod.POST)

public String updateuser(HttpServletRequest request,@ModelAttribute ("AddUser") AddUser user,ModelMap model)


{
	OrgRegistrationForm orgRegistrationForm=new OrgRegistrationForm();
	orgRegistrationForm.setOrgregistration(orgRegistrationDAO.getOrgregistration());
	model.addAttribute("orgRegistrationForm",orgRegistrationForm);
	
		int status = userDAO.updateUser(user);
		System.out.println(status);
		if(status==1)
		{
			
			AddUserForm adduserform=new AddUserForm();
			adduserform.setAdduser(userDAO.getAdduser());
			model.addAttribute("adduserform",adduserform);
		}
	
		return "view_adminuser";
		
}


@RequestMapping(value="/deleteuser", method=RequestMethod.GET)
public String removeStudent(@RequestParam("org_id") String org_id, ModelMap model, Principal principal)
{

	int status=userDAO.deleteUser(org_id);
	
	if(status==1)
	{
	
	
	AddUserForm adduserform=new AddUserForm();
	adduserform.setAdduser(userDAO.getAdduser());
	model.addAttribute("adduserform",adduserform);
	}
	
	return "view_adminuser";

}

@RequestMapping(value="/finduser",method=RequestMethod.GET)
public String findorg(HttpServletRequest request,@RequestParam("org_id") String org_id, @RequestParam("admin_id")String admin_id,@RequestParam("name") String name,ModelMap model)
{
	if( org_id== " " && admin_id== " " && name==" ")
	{
		AddUserForm adduserform=new AddUserForm();
		adduserform.setAdduser(userDAO.getAdduser());
		model.addAttribute("adduserform",adduserform);
        
		return "search_user";
	}
	else
	{
	
		AddUserForm adduserform=new AddUserForm();
		adduserform.setAdduser(userDAO.finduser(org_id, admin_id, name));
		model.addAttribute("adduserform",adduserform);

	 return "search_user";
	}
}
	
	
@RequestMapping(value="/userdetails", method=RequestMethod.GET)
	public String userdetails(HttpServletRequest request,@RequestParam("admin_reg_no") String admin_reg_no,ModelMap model,AddUser adduser)
	{
		
		
	AddUserForm adduserform=new AddUserForm();
	adduserform.setAdduser(userDAO.getUsers(admin_reg_no));
	model.addAttribute("adduserform",adduserform);
		
		
		return "user_details";
	}

/*@RequestMapping(value="/smsreport", method=RequestMethod.GET)
public String smsreport(ModelMap model, Principal principal){
	
	return "sms_report";
}*/
}