package bustracking.controllers;
 
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.aspectj.weaver.ltw.LTWWorld;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;


import bustracking.dao.BusDeviceRegistrationDAO;
import bustracking.dao.BusRegistrationDAO;
import bustracking.dao.ClientHomeDAO;
import bustracking.dao.FleetHomepageDAO;
import bustracking.dao.MainDAO;
import bustracking.dao.OrgBusinessRuleDAO;
import bustracking.dao.TrackingInfoDAO;
import bustracking.forms.BusDeviceRegistrationForm;
/*import bustracking.forms.DeviceFailForm;*/
import bustracking.forms.BusRegistrationForm;
import bustracking.forms.ClientHomeForm;
import bustracking.forms.FleetHomepageForm;
import bustracking.forms.OrgBusinessRuleForm;

import bustracking.forms.LatLongForm;
import bustracking.model.*;


import bustracking.model.XMLWriter;
@Controller
@SessionAttributes({"sample","listsize","menu","role"})
public class MainController {
	
	@Autowired  
	MainDAO mainDAO; 
	@Autowired  
	ClientHomeDAO clientHomeDAO; 
	
	
	@Autowired
	OrgBusinessRuleDAO businessRuleDAO;
 
	@Autowired
	TrackingInfoDAO trackingInfoDAO;
	
	@Autowired
	BusDeviceRegistrationDAO busDeviceRegistrationDAO;
	
	@Autowired
	BusRegistrationDAO busRegistrationDAO;
	
	
	
	@Autowired
	FleetHomepageDAO fleetHomepageDAO;
	
	@RequestMapping(value={"/", "/welcome"}, method = RequestMethod.GET)
	public String printWelcome(HttpSession session,ModelMap model, Principal principal ) {
	
		String role=mainDAO.getRole(principal.getName());
		session.setAttribute("menu",mainDAO.getRole(principal.getName()));
		
		if(role.equals("ROLE_SUPERADMIN"))
			return "superadmin_homepage";
		else if(role.equals("ROLE_FCLIENT"))
		{
		    FleetHomepageForm fleetHomepageForm=new FleetHomepageForm();
		    fleetHomepageForm.setFleetHomepages(fleetHomepageDAO.getFleethomedetails());
		    model.addAttribute("fleetHomepageForm",fleetHomepageForm);
			return "fleetclient_homepage";
			
		}
else{
			
			ClientHomeForm clientHomeForm= new ClientHomeForm();
			clientHomeForm.setClienthome(clientHomeDAO.getClienthome(mainDAO.getOrg_id(principal.getName())));
			model.addAttribute("clientHomeForm",clientHomeForm);
			return "client_home";
		}
 
		
 
	}

	@RequestMapping(value="/adminmenu", method = RequestMethod.GET)
	public String adminmenu(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "admin_menu";
	}
	@RequestMapping(value="/demo", method = RequestMethod.GET)
	public String demo_page(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		System.out.println("came!!!");
		
		System.out.println("latitude:"+request.getParameter("lat"));
		System.out.println("longitude:"+request.getParameter("long"));
		
		
		
		return "demo";
	}
	@RequestMapping(value="/viewbusinessrules", method = RequestMethod.GET)
	public String viewbusinessrules(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "view_business_rules";
	}

	/*Dummy Dependent DDL */

	@RequestMapping(value="/DDDL", method = RequestMethod.GET)
	public String Dependent1(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "Dependent1";
	}

	@RequestMapping(value="/DDDL2", method = RequestMethod.GET)
	public String Dependent2(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "Dependent2";
	}
	

	@RequestMapping(value="/DDDL3", method = RequestMethod.GET)
	public String Dependent3(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "DependentOrg";
	}
	/*Client Dependent actions ::::GPS*/
	@RequestMapping(value="/clienthome", method = RequestMethod.GET)
	public String clienthome(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "client_home";
	}
	
	
	/*@RequestMapping(value="/clientviewroutedetails", method = RequestMethod.GET)
	public String clientviewroutedetails(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "client_view_route_details";
	}
	*/
	@RequestMapping(value="/clientbusnodetails", method = RequestMethod.GET)
	public String clientbusnodetails(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "client_busno_details";
	}
	
	/*@RequestMapping(value="/clientviewstudent", method = RequestMethod.GET)
	public String clientviewstudent(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "client_view_student";
	}
	
	
	@RequestMapping(value="/clientsmsoption", method = RequestMethod.GET)
	public String clientsmsoption(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "client_sms_option";
	}
	
	@RequestMapping(value="/clientreport", method = RequestMethod.GET)
	public String clientreport(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "client_report_page";
	}
	

	@RequestMapping(value="/adminreport", method = RequestMethod.GET)
	public String adminreport(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "view_report";
	}
	
	

	@RequestMapping(value="/adminholiday", method = RequestMethod.GET)
	public String adminholiday(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "view_holiday";
	}
	*/
	

	@RequestMapping(value="/trackinghistory", method = RequestMethod.GET)
	public String trackinghistory(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "admin_tracking_history";
	}
	

	@RequestMapping(value="/routeinfo", method = RequestMethod.GET)
	public String routeinfo(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		

		return "client_view_routes";
	}
	
	

	@RequestMapping(value="/driverlist", method = RequestMethod.GET)
	public String driverlist(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		BusRegistrationForm busRegistrationForm=new BusRegistrationForm();
		busRegistrationForm.setBusregistration(busRegistrationDAO.getBusregistration_by_org_id(mainDAO.getOrg_id(principal.getName())));
		model.addAttribute("busRegistrationForm",busRegistrationForm);
		return "client_driver_list";
	}
	
	


	@RequestMapping(value="/clientstudentlist", method = RequestMethod.GET)
	public String clientstudentlist(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "client_student_list";
	}
	



	@RequestMapping(value="/clientstudentdata", method = RequestMethod.GET)
	public String clientstudentdata(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "client_student_data";
	}
	



	@RequestMapping(value="/clientstudentroutemap", method = RequestMethod.GET)
	public String clientstudentroutemap(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "client_student_routemap";
	}
	



	@RequestMapping(value="/clientsmsparent", method = RequestMethod.GET)
	public String clientsmsparent(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "client_sms_parent";
	}
	



	@RequestMapping(value="/clienttracksms", method = RequestMethod.GET)
	public String clienttracksms(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "client_track_sms";
	}
	



	@RequestMapping(value="/clientbusinessrules", method = RequestMethod.GET)
	public String clientbusinessrules(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "client_business_rules";
	}
	



	@RequestMapping(value="/clientholiday", method = RequestMethod.GET)
	public String clientholiday(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "client_holiday";
	}
	



	@RequestMapping(value="/clientpickupreport", method = RequestMethod.GET)
	public String client_pickup_report(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "client_pickup_report";
	}
	



	@RequestMapping(value="/clientdropreport", method = RequestMethod.GET)
	public String clientdropreport(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "client_drop_report";
	}

	@RequestMapping(value="/clientoverspeedingreport", method = RequestMethod.GET)
	public String clientoverspeedingreport(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "client_overspeeding_report";
	}

	@RequestMapping(value="/clientdeviceview", method = RequestMethod.GET)
	public String clientdeviceview(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "client_device_view";
	}
	@RequestMapping(value="/autocompleteSearch_org", method = RequestMethod.GET)
	public String autocompleteSearch(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
		return "autocomplete_Search_org";
	}
		

	
	
	
	
	
	@RequestMapping(value="/calculate", method = RequestMethod.POST)
	public String calculate(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		System.out.println("calculate came!!!");
		
		
		Double doub=new Double("99999.0");
		@SuppressWarnings("static-access")
		double lat1=doub.parseDouble(request.getParameter("lat1"));
		@SuppressWarnings("static-access")
		double long1=doub.parseDouble(request.getParameter("long1"));
		@SuppressWarnings("static-access")
		double lat2=doub.parseDouble(request.getParameter("lat2"));
		@SuppressWarnings("static-access")
		double long2=doub.parseDouble(request.getParameter("long2"));
		@SuppressWarnings("static-access")
		double speed=doub.parseDouble(request.getParameter("speed"));
		
		
		
		//distance(lat1, lat2, long1, long2,speed);
		
		
		
		return "demo";
	}
	
	@RequestMapping(value="/getlocationdevice", method = RequestMethod.POST)
	public String getlocation_device(HttpSession session,HttpServletRequest request,ModelMap model, Principal principal ) {
		
		System.out.println(request.getParameter("device_id"));
		BusDeviceRegistrationForm busDeviceRegistrationForm=new BusDeviceRegistrationForm();
		busDeviceRegistrationForm.setBusDeviceRegistrations(busDeviceRegistrationDAO.getBusdeviceregistration());
		model.addAttribute("busDeviceRegistrationForm",busDeviceRegistrationForm);
	
		
		
		
		
		
		return "view_map";
		
		
		//return "view_map";
	}
	
	
	
	
	
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
 
	}
	/*@RequestMapping(value="/insert_organisation", method = RequestMethod.GET)
	public String insert_org(ModelMap model) {
		return "insert_organisation";
 
	}*/
	@RequestMapping(value="/add_adminuser", method = RequestMethod.GET)
	public String add_adminuser(ModelMap model) {
		return "add_adminuser";
 
	}
	
	@RequestMapping(value="/viewmap", method = RequestMethod.GET)
	public String view_map(HttpSession session,ModelMap model) {
		
	
	
		BusDeviceRegistrationForm busDeviceRegistrationForm=new BusDeviceRegistrationForm();
		busDeviceRegistrationForm.setBusDeviceRegistrations(busDeviceRegistrationDAO.getBusdeviceregistration());
		model.addAttribute("busDeviceRegistrationForm",busDeviceRegistrationForm);			
		
		LatLongForm latLongForm=new LatLongForm();
		//latLongForm.setLatLongs(trackingInfoDAO.getTrackingInfo("359710042476300"),'');		
		model.addAttribute("latLongForm",latLongForm);	
		model.addAttribute("device_id","");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    TimeZone.setDefault(TimeZone.getTimeZone("IST"));
	    Date date=new Date();
	    
	    model.addAttribute("click_time",sdf.format(date));
		return "view_map";
 
	}
	
	
	@RequestMapping(value="/live_track", method = RequestMethod.GET)
	public String Live_track(@RequestParam("id") String device_id,HttpSession session,ModelMap model) {
		
		BusDeviceRegistrationForm busDeviceRegistrationForm=new BusDeviceRegistrationForm();
		busDeviceRegistrationForm.setBusDeviceRegistrations(busDeviceRegistrationDAO.getBusdeviceregistration());
		model.addAttribute("busDeviceRegistrationForm",busDeviceRegistrationForm);		
		
		model.addAttribute("device_id",device_id);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    TimeZone.setDefault(TimeZone.getTimeZone("IST"));
	    Date date=new Date();
	    
	    model.addAttribute("click_time",sdf.format(date));
		return "view_map";
 
	}
	
	@RequestMapping(value="/view_map_history", method = RequestMethod.GET)
	public String view_map_history(HttpSession session,ModelMap model) {
		
	
	
		BusDeviceRegistrationForm busDeviceRegistrationForm=new BusDeviceRegistrationForm();
		busDeviceRegistrationForm.setBusDeviceRegistrations(busDeviceRegistrationDAO.getBusdeviceregistration());
		model.addAttribute("busDeviceRegistrationForm",busDeviceRegistrationForm);		
		
		
		LatLongForm latLongForm=new LatLongForm();
		//latLongForm.setLatLongs(trackingInfoDAO.getTrackingInfo("359710042476300"),'');
		
		model.addAttribute("latLongForm",latLongForm);
		model.addAttribute("first",1);
		return "view_map_history";
 
	}
	
	
	
	@RequestMapping(value="/viewmap_with_id", method = RequestMethod.GET)
	public String view_map_reload(@RequestParam("id") String device,HttpSession session,ModelMap model) {
		
	
		System.out.println("came");
		BusDeviceRegistrationForm busDeviceRegistrationForm=new BusDeviceRegistrationForm();
		busDeviceRegistrationForm.setBusDeviceRegistrations(busDeviceRegistrationDAO.getBusdeviceregistration());
		model.addAttribute("busDeviceRegistrationForm",busDeviceRegistrationForm);		
		
		
		LatLongForm latLongForm=new LatLongForm();
		//latLongForm.setLatLongs(trackingInfoDAO.getTrackingInfo("359710042476300"),'');
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    TimeZone.setDefault(TimeZone.getTimeZone("IST"));
	    Date date=new Date();
	    
	    model.addAttribute("click_time",sdf.format(date));
		model.addAttribute("latLongForm",latLongForm);
		model.addAttribute("device_id","");
		model.addAttribute("id",device);
		
		return "view_map";
 
	}
	
	
	@RequestMapping(value="/viewmap", method = RequestMethod.POST)
	public String view_particular_device(HttpServletRequest request,HttpSession session,ModelMap model) {
		
	
	
		BusDeviceRegistrationForm busDeviceRegistrationForm=new BusDeviceRegistrationForm();
		busDeviceRegistrationForm.setBusDeviceRegistrations(busDeviceRegistrationDAO.getBusdeviceregistration());
		model.addAttribute("busDeviceRegistrationForm",busDeviceRegistrationForm);		
		
		
		LatLongForm latLongForm=new LatLongForm();
		System.out.println("Selected Date:"+request.getParameter("date").toString());
		latLongForm.setLatLongs(trackingInfoDAO.getTrackingInfo(request.getParameter("device_id"),request.getParameter("date")));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    TimeZone.setDefault(TimeZone.getTimeZone("IST"));
	    Date date=new Date();
	    
	    model.addAttribute("click_time",sdf.format(date));
		model.addAttribute("latLongForm",latLongForm);
		model.addAttribute("first",2);
		model.addAttribute("device",request.getParameter("device_id"));
		model.addAttribute("date",request.getParameter("date"));
		return "view_map_history";
 
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "login";
 
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "login";
 
	}
	
	@RequestMapping(value="/json", method = RequestMethod.POST)
	public @ResponseBody LatLongForm json(@RequestParam("device_id") String device,@RequestParam("click_time") String click_time,ModelMap model) {
		
		System.out.println(device);
		
		LatLongForm latLongForm=new LatLongForm();
		
		
		latLongForm.setLatLongs(trackingInfoDAO.getLiveTrackingInfo(device,click_time));
		
		return latLongForm;
	
 
	}
	/*@RequestMapping(value="/createuser", method=RequestMethod.GET)
	public String createSpitterProfile(Model model) {
		model.addAttribute(new UserProfile());
	return "edit";
	}
	
	@RequestMapping(value="/submituser", method=RequestMethod.POST)
	public String addUserProfileFromForm(UserProfile userProfile) {
		
		System.out.println("Save User" + userProfile.getFullName());
		return "/welcome";
	}
	*/
	@RequestMapping(value="/showaddparticipants", method=RequestMethod.GET)
	public String showAddParticipants(ModelMap model) {
		//model.addAttribute(new UserProfile());
		return "addparticipants";
	}
	@RequestMapping(value="/viewparticipants", method=RequestMethod.GET)
	public String viewParticipants(ModelMap model) {
		
		return "viewparticipants";
	}
	@RequestMapping(value="/showaddparticipantgroups", method=RequestMethod.GET)
	public String showAddParticipantGroups(ModelMap model) {
		
		return "addparticipantgroups";
	}
	@RequestMapping(value="/viewparticipantgroups", method=RequestMethod.GET)
	public String viewParticipantGroups(ModelMap model) {
		
		return "viewparticipantgroups";
	}
	
	@RequestMapping(value="/showaddadminuser", method=RequestMethod.GET)
	public String showAddadminUser(ModelMap model) {
		
		return "addadminuser";
	}
	@RequestMapping(value="/viewadminuser", method=RequestMethod.GET)
	public String viewAdminUser(ModelMap model) {
		
		return "viewadminuser";
	}
	
	@RequestMapping(value="/activityofadmin", method=RequestMethod.GET)
	public String activityOfAdmin(ModelMap model) {
		
		return "activityofadmin";
	}
	
	/*@RequestMapping(value="/devicefail", method=RequestMethod.GET)
	public String fail_note(DeviceFail deviceFail,ModelMap model) {
		
		DeviceFailForm deviceFailForm=new DeviceFailForm();
		deviceFailForm.setDeviceFails(mainDAO.getdevicefails());
		model.addAttribute("deviceFailForm",deviceFailForm);
		return "device_fail_status";
	}*/
	
	
	@RequestMapping(value="/textmsgsettings", method=RequestMethod.GET)
	public String textMsgSettings(ModelMap model) {
		
		return "textmsg";
	}
	
	@RequestMapping(value="/route_stop", method=RequestMethod.GET)
	public String stop_route(ModelMap model) {
		
		return "Route_stop";
	}
	/*
	@RequestMapping(value="/insert_route", method=RequestMethod.GET)
	public String insert_route(ModelMap model) {
		
		return "add_route";
	}*/
	@RequestMapping(value="/admin_settings", method=RequestMethod.GET)
	public String admin_settings(ModelMap model) {
		
		return "admin_settings";
	}
	
  }