package bustracking.controllers;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bustracking.dao.MainDAO;
import bustracking.dao.OrgRegistrationDAO;
import bustracking.dao.RouteDAO;
import bustracking.dao.StudentRegistrationDAO;
import bustracking.forms.AddUserForm;
import bustracking.forms.BusDeviceRegistrationForm;
import bustracking.forms.BusRegistrationForm;
import bustracking.forms.OrgRegistrationForm;
import bustracking.forms.RouteForm;
import bustracking.forms.RouteViewForm;
import bustracking.forms.StudentRegistrationForm;
import bustracking.model.AddUser;
import bustracking.model.OrgRegistration;
import bustracking.model.Route;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;

import bustracking.model.OrgRegistration;
import bustracking.forms.OrgRegistrationForm;
import bustracking.dao.OrgRegistrationDAO;
import bustracking.dao.BusRegistrationDAO;
import bustracking.forms.BusRegistrationForm;



@Controller
public class RouteController
{
	@Autowired
	RouteDAO routeDAO;
	
	@Autowired  
		MainDAO mainDAO; 
		
	@Autowired
	
	BusRegistrationDAO busDAO;

	@Autowired  
	StudentRegistrationDAO studentDAO;
	
	@Autowired
	OrgRegistrationDAO orgRegistrationDAO;
	

	static int stop_id=10001;
	

	@RequestMapping(value="/insert_route",method=RequestMethod.GET)
	public String getroute(ModelMap model,Route route, Principal principal){
		
		OrgRegistrationForm orgRegistrationForm=new OrgRegistrationForm();
		orgRegistrationForm.setOrgregistration(orgRegistrationDAO.getOrgregistration());
		model.addAttribute("orgRegistrationForm",orgRegistrationForm);
		
		BusRegistrationForm busregistrationform= new BusRegistrationForm();
		busregistrationform.setBusregistration(busDAO.getBusregistration());
		model.addAttribute("busregistrationform",busregistrationform);
		
		
		
		List <String> orgname=new ArrayList<String>();
		orgname=busDAO.getorgname();
		model.addAttribute("orgname",orgname);
		
		return "add_route";
	}
	
	@RequestMapping(value="/route_ajax",method=RequestMethod.POST)
	public @ResponseBody String sr_reg(@RequestParam("org_name") String org_name, @RequestParam("branch") String branch ,ModelMap model) {
		
		String returnText="";
		List <String> studroute=new ArrayList<String>();
		studroute=studentDAO.getStud_route(org_name,branch);
		
		returnText=returnText+"<select name='route_no' id='imei' class='input_txtbx_height'>";
		returnText+="<option value='' selected>--Select Route No--</option>";
		for(String sr:studroute)
		{
			returnText+="<option value='"+sr+"'>"+sr+"</option>";
			}			
		  
	   returnText=returnText+"</select>";	
	   System.out.println(returnText);
		
		return returnText;
	
	}
	
	@RequestMapping(value="/viewroute", method=RequestMethod.GET)
	public String viewroute(HttpServletRequest request,ModelMap model, Principal principal){
		
		RouteViewForm routeViewForm=new RouteViewForm();
		routeViewForm.setRoute_views(routeDAO.getRoutes());
		model.addAttribute("routeViewForm",routeViewForm);
		
		return "view_route";
	}


	@RequestMapping(value="/clientviewroutedetails", method = RequestMethod.GET)
	public String driverlist(HttpServletRequest request,ModelMap model, Principal principal ) {
		RouteViewForm routeViewForm=new RouteViewForm();
		routeViewForm.setRoute_views(routeDAO.getRoute_by_org_id(mainDAO.getOrg_id(principal.getName())));
		model.addAttribute("routeViewForm",routeViewForm);
		
		return "client_view_route_details";
	}
	
	/*@RequestMapping(value="/insert_route_stop", method = RequestMethod.POST)
	public String insert_route(Route route,ModelMap model, Principal principal)
	{
	
		RouteForm routeForm=new RouteForm();
		routeForm.setRoutes(routeDAO.insert_route(route));
		model.addAttribute("routeForm",routeForm);
		return "view_route";
	}
	*/
	
	/*@RequestMapping(value="/addroute", method=RequestMethod.GET)
	public String printWelcome(HttpSession session,ModelMap model, Principal principal){
		
		
		
		OrgRegistrationForm orgRegistrationForm=new OrgRegistrationForm();
		orgRegistrationForm.setOrgregistration(orgRegistrationDAO.getOrgregistration());
		model.addAttribute("orgRegistrationForm",orgRegistrationForm);
		
		
	return "add_route";
	}*/
	
@RequestMapping(value="/insert_route_stop", method = RequestMethod.POST)

	public String inserting_route(HttpServletRequest request,ModelMap model,Route route, Principal principal ) {
		
	
	   route.setOrg_id(orgRegistrationDAO.getOrg_id(request.getParameter("org_name"),request.getParameter("branch")));
		
		//this.setInfo(route, route.getRoute_from(),"both",request.getParameter("route_pick_time"),request.getParameter("route_drop_time"));
		System.out.println(request.getParameter("org_name"));
		System.out.println(request.getParameter("branch"));
		System.out.println(request.getParameter("stop[]"));
		String[] stop_locations=new String[100];
		String[] stop_pick_drop=new String[100];
		String[] stop_timings=new String[100];
		int stop_count=Integer.parseInt(request.getParameter("number_of_stops"));
		System.out.println(stop_count);
		stop_locations=request.getParameterValues("stop[0]");
		stop_pick_drop=request.getParameterValues("stop_pick[0]");
		stop_timings=request.getParameterValues("particular_stop_pickup_time[0]");
	
		//setting the route values
		  route.setOrg_id(orgRegistrationDAO.getOrg_id(request.getParameter("org_name"),request.getParameter("branch")));
		  
		String stop_location;
		for (int i=0;i<=stop_count;i++)
		{
			System.out.println("request.getParameter(stop["+i+"])");
			if(!request.getParameter("stop["+i+"]").equals(""))
			{
				stop_location=request.getParameter("stop["+i+"]");
				route.setStop_id("S"+i);
				route.setAddress(stop_location);
				route.setLatitude(getLat(stop_location));
				route.setLongitude(getLong(stop_location));
				route.setTrip(request.getParameter("stop_pick["+i+"]"));
				route.setBus_arrival_time(request.getParameter("particular_stop_pickup_time["+i+"]"));
				routeDAO.insert_route(route);
			}
			System.out.println(route);
			 
				
		}
		/*
			if(request.getParameter("stop_pick["+k+"]").equals("0"))
				System.out.println(request.getParameter("stop_pick["+k+"]"));
			this.setInfo(route, stop_location);
		if(request.getParameter("stop_pick["+k+"]").equals("1"))
			this.setInfo(route, stop_location);
		if(request.getParameter("stop_pick["+k+"]").equals("2"))
			this.setInfo(route, stop_location);
		k++;
		*/
	
		//System.out.println(request.getParameter("stop_pick["+k+"]"));
		//route.setStop_id("S"+(k+1));
		//this.setInfo(route, route.getRoute_to());
		
		//model.addAttribute("org_id",orgRegistrationDAO.getOrg_id(request.getParameter("org_name"),request.getParameter("branch")));
		
		RouteViewForm routeViewForm=new RouteViewForm();
		routeViewForm.setRoute_views(routeDAO.getRoutes());
		model.addAttribute("routeViewForm",routeViewForm);
		
		
		return "view_route";
	}
	
	
	public String getLat(String Stoplocation)
	{
		final Geocoder geocoder = new Geocoder();		
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(Stoplocation).setLanguage("en").getGeocoderRequest();
		GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
	
		return geocoderResponse.getResults().get(0).getGeometry().getLocation().getLat().toString();
	}
	public String getLong(String Stoplocation)
	{
		final Geocoder geocoder = new Geocoder();		
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(Stoplocation).setLanguage("en").getGeocoderRequest();
		GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
	
		return geocoderResponse.getResults().get(0).getGeometry().getLocation().getLng().toString();
	}
	
	public void setInfo(Route route,String Stoplocation)
	{
	
		
		route.setAddress(Stoplocation);	
		route.setBus_arrival_time("2038-01-19 03:14:07");
		route.setLatitude(this.getLat(Stoplocation));
		
		
		route.setLongitude(this.getLong(Stoplocation));
		//route.setStop_drop_time("2038-01-19 03:14:07");
		
		System.out.println(Stoplocation);
		System.out.println(this.getLat(Stoplocation));
		//System.out.println(this.getLong(Stoplocation));
		stop_id=stop_id+3;
		
		
	}
	
	
	//Ajax for org name and branch
	
	@RequestMapping(value="/route_reg_ajax",method=RequestMethod.POST)
	public @ResponseBody String bus_reg(@RequestParam("org_name") String org_name,ModelMap model) {
	
		String returnText="";
		List <String> branch=new ArrayList<String>();
		branch=busDAO.getBus_id(org_name);
		
		returnText=returnText+"<select name='branch' id='bid' onchange='doAjaxPost1()' class='input_txtbx_height'>";
		returnText+="<option value='' selected>--Select Branch--</option>";
		for(String bname:branch)
		{
			returnText+="<option value='"+bname+"'>"+bname+"</option>";
			}			
		  
	   returnText=returnText+"</select>";	
	   System.out.println(returnText);
	return returnText;
	}
	
	
	/*
@RequestMapping(value="/viewroute", method=RequestMethod.GET)
public String viewuser(ModelMap model, Principal principal){
	
	System.out.println("view route!!!!");
	
	
	RouteForm routeForm=new RouteForm();
	routeForm.setRoutes(routeDAO.getRoutes());
	model.addAttribute("routeForm",routeForm);
	
	return "view_route";
}


@RequestMapping(value="/editroute",method=RequestMethod.GET)
public String edituser(HttpServletRequest request,@RequestParam("route_id")String route_id,ModelMap model,OrgRegistration orgregistration)
{
	BusDeviceRegistrationForm busDeviceRegistrationForm= new BusDeviceRegistrationForm();
	busDeviceRegistrationForm.setBusDeviceRegistrations(routeDAO.getBusids());
	model.addAttribute("busDeviceRegistrationForm",busDeviceRegistrationForm);
	
	RouteForm routeForm= new RouteForm();
	routeForm.setRoutes(routeDAO.getRoutesView(route_id));
	model.addAttribute("routeForm",routeForm);
	
	return "edit_route";
	
}

@RequestMapping(value="/updateroute",method=RequestMethod.POST)

public String updateuser(HttpServletRequest request,@ModelAttribute ("Route") Route route,ModelMap model)


{
		int status = routeDAO.updateRoute(route);
		System.out.println(status);
		if(status==1)
		{
			
			RouteForm routeForm=new RouteForm();
			routeForm.setRoutes(routeDAO.getRoutes());
			model.addAttribute("routeForm",routeForm);
		}
	
		return "view_route";
		
}




@RequestMapping(value="/routedetails", method=RequestMethod.GET)
	public String userdetails(HttpServletRequest request,@RequestParam("route_id") String route_id,ModelMap model,AddUser adduser)
	{
		
		
	RouteForm routeForm=new RouteForm();
	routeForm.setRoutes(routeDAO.getRoutesView(route_id));
	model.addAttribute("routeForm",routeForm);
		
		
		return "route_details";
	}
*/

}

