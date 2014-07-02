package bustracking.controllers;


import java.util.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import bustracking.dao.BusRegistrationDAO;
import bustracking.dao.MainDAO;
import bustracking.dao.ReportsDAO;

import bustracking.forms.ReportForm;
import bustracking.model.Report;



@Controller
@SessionAttributes({"student_roll_no","from_date","to_date","date","vechicle_reg_no","from_time","to_time"})
public class ReportsController{
	
	
	@Autowired
	BusRegistrationDAO busDAO;
	
	@Autowired
	MainDAO mainDAO;
	
	@Autowired
	ReportsDAO reportsDAO;

	
	// Admin Side Reports For SMS
	
		@RequestMapping(value="/admin_reports", method=RequestMethod.GET)
		public String adminreports(HttpSession session,ModelMap model) {
			
			session.removeAttribute("org_name");
			session.removeAttribute("branch");
			session.removeAttribute("student_roll_no");
			session.removeAttribute("date");
			
			List <String> orgname_for_school=new ArrayList<String>();
			orgname_for_school=busDAO.getorgname_for_school();
			model.addAttribute("orgname_for_school",orgname_for_school);
			
			ReportForm reportForm=new ReportForm();
			reportForm.setReports(reportsDAO.getsmsreport_for_download());
			model.addAttribute("reportForm",reportForm);
			
			return "admin_sms_report";
		}
	
		@RequestMapping(value="/tracksms", method = RequestMethod.GET)
		public String clienttracksms(HttpSession session,HttpServletRequest request,ModelMap model, Principal principal ) {
			
		    session.removeAttribute("student_roll_no");
		    session.removeAttribute("from_date");
		    session.removeAttribute("to_date");
			
			ReportForm reportForm=new ReportForm();
			reportForm.setReports(reportsDAO.getTracksmsreport(mainDAO.getOrg_id(principal.getName())));
			model.addAttribute("reportForm",reportForm);
			
			ReportForm reportForm1=new ReportForm();
			reportForm1.setReports(reportsDAO.getStudent_roll_no_for_clienttrack_sms(mainDAO.getOrg_id(principal.getName())));
			model.addAttribute("reportForm1",reportForm1);
			
			return "tracksms";
		}
		
		
		@RequestMapping(value="/clientsmstrack", method = RequestMethod.GET)
		public String clienttracksms(@RequestParam("fromdate") String fromdate,@RequestParam("todate") String todate,@RequestParam("student_roll_no") String student_roll_no,HttpServletRequest request,HttpSession session,ModelMap model, Principal principal ) {
			
			
			session.setAttribute("from_date", fromdate);
			session.setAttribute("to_date", todate);
			session.setAttribute("student_roll_no",student_roll_no);
			
			if(student_roll_no=="" && fromdate=="" && todate=="")
			{
				ReportForm reportForm=new ReportForm();
				reportForm.setReports(reportsDAO.getTracksmsreport(mainDAO.getOrg_id(principal.getName())));
				model.addAttribute("reportForm",reportForm);
				
				ReportForm reportForm1=new ReportForm();
				reportForm1.setReports(reportsDAO.getStudent_roll_no_for_clienttrack_sms(mainDAO.getOrg_id(principal.getName())));
				model.addAttribute("reportForm1",reportForm1);
				
			}
			else{
				
			ReportForm reportForm=new ReportForm();
			reportForm.setReports(reportsDAO.getclientsmsreport_search(mainDAO.getOrg_id(principal.getName()), student_roll_no, fromdate, todate));
			model.addAttribute("reportForm",reportForm);
			
			ReportForm reportForm1=new ReportForm();
			reportForm1.setReports(reportsDAO.getStudent_roll_no_for_clienttrack_sms(mainDAO.getOrg_id(principal.getName())));
			model.addAttribute("reportForm1",reportForm1);
			
			}
			return "tracksms";
		}
		
		
		// SMS Report Generate in Admin Side
		
		@RequestMapping(value="/adminsmsreport", method = RequestMethod.GET)
		public String admintracksms(HttpSession session,@RequestParam("org_name") String org_name,@RequestParam("branch") String branch,@RequestParam("from_date") String fromdate,@RequestParam("to_date") String todate,@RequestParam("student_roll_no") String student_roll_no,@ModelAttribute("sms_report") Report sms_report,HttpServletRequest request,ModelMap model, Principal principal ) {
			
			session.setAttribute("date", sms_report);
			session.setAttribute("org_name", org_name);
			session.setAttribute("branch", branch);
			session.setAttribute("student_roll_no", student_roll_no);
			
			
			List <String> orgname_for_school=new ArrayList<String>();
			orgname_for_school=busDAO.getorgname_for_school();
			model.addAttribute("orgname_for_school",orgname_for_school);
			model.addAttribute("branch_array",busDAO.getBus_id(org_name));
			model.addAttribute("roll_array", reportsDAO.get_student_roll_no(org_name, branch));
			
			if(org_name=="" && branch=="" && student_roll_no=="" && fromdate=="" && todate=="")
			{
				ReportForm reportForm=new ReportForm();
				reportForm.setReports(reportsDAO.getsmsreport_for_download());
				model.addAttribute("reportForm",reportForm);
				
				return "admin_sms_report";
				
			}
			else{
				
			ReportForm reportForm=new ReportForm();
			reportForm.setReports(reportsDAO.getsmsreport(org_name, branch,student_roll_no,fromdate, todate));
			model.addAttribute("reportForm",reportForm);
			
			ReportForm reportForm1=new ReportForm();
			reportForm1.setReports(reportsDAO.getsmsreport_for_download());
			model.addAttribute("reportForm",reportForm);
			
			return "admin_sms_report";
			
			}
			
		}
		
		// Get Student Roll No
		

		@RequestMapping(value="/student_roll_no",method=RequestMethod.POST)
		public @ResponseBody String trip(@RequestParam("org_name") String org_name,@RequestParam("branch") String branch,ModelMap model, Principal principal) {
			
			String returnText="";
			List <String> student_roll_no=new ArrayList<String>();
			student_roll_no=reportsDAO.get_student_roll_no(org_name, branch);
			
			
			returnText=returnText+"<script id='script_bid'> $(document).ready(function() {$('#student_roll_no').select2(); });</script><select name='student_roll_no' id='student_roll_no'>";
			returnText+="<option value='none' selected>--Select Student--</option>";
			for(String student_no:student_roll_no)
			{
				 
				returnText+="<option value='"+student_no+"'>"+student_no+"</option>";
			}			
			
			returnText+="</select>";
			return returnText;
			
		
		}
		

		@RequestMapping(value="/clientdriverlistreport",method=RequestMethod.GET)
		public String Driverreport(ModelMap model,Principal principal){
		
		
		
			return "client_driverlist_report";
		}
	
	
		@RequestMapping(value="/clientpickupreport", method = RequestMethod.GET)
		public String client_pickup_report(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
			return "client_pickup_report";
		}
	



		@RequestMapping(value="/clientdropreport", method = RequestMethod.GET)
		public String clientdropreport(HttpServletRequest request,ModelMap model, Principal principal ) {
		
		
			return "client_drop_report";
		}
	
		/*
		 * Over Speed Report In Client Side
		 * 
		 */
		
		@RequestMapping(value="/clientoverspeedingreport", method = RequestMethod.GET)
		public String clientoverspeedingreport(HttpSession session,HttpServletRequest request,ModelMap model, Principal principal ) {
		
			
			session.removeAttribute("vechicle_reg_no");
			session.removeAttribute("from_date");
			session.removeAttribute("from_time");
			session.removeAttribute("to_date");
			session.removeAttribute("to_time");
			
			ReportForm reportForm=new ReportForm();
			reportForm.setReports(reportsDAO.getoverspeedreport(mainDAO.getOrg_id(principal.getName())));
			model.addAttribute("reportForm",reportForm);
			
			ReportForm reportForm1=new ReportForm();
			reportForm1.setReports(reportsDAO.getvechicle_reg_no_for_over_speed_report(mainDAO.getOrg_id(principal.getName())));
			model.addAttribute("reportForm1",reportForm1);
			
			return "client_overspeeding_report";
		}
		
		/*
		 * Search in Over Speed Report
		 * 
		 */
		
		@RequestMapping(value="/searchoverspeedreport", method = RequestMethod.GET)
		public String searchclientoverspeedingreport(HttpSession session,HttpServletRequest request,@RequestParam("vechicle_reg_no") String vechicle_reg_no,@RequestParam("from_date") String from_date,@RequestParam("from_time") String from_time,@RequestParam("to_date") String to_date,@RequestParam("to_time") String to_time,ModelMap model, Principal principal ) {
		
			session.setAttribute("vechicle_reg_no", vechicle_reg_no);
			session.setAttribute("from_date", from_date);
			session.setAttribute("from_time", from_time);
			session.setAttribute("to_date", to_date);
			session.setAttribute("to_time", to_time);
			
			ReportForm reportForm=new ReportForm();
			reportForm.setReports(reportsDAO.search_over_speed_report_(mainDAO.getOrg_id(principal.getName()), vechicle_reg_no, from_date, from_time, to_date, to_time));
			model.addAttribute("reportForm",reportForm);
			
			ReportForm reportForm1=new ReportForm();
			reportForm1.setReports(reportsDAO.getvechicle_reg_no_for_over_speed_report(mainDAO.getOrg_id(principal.getName())));
			model.addAttribute("reportForm1",reportForm1);
			
			return "client_overspeeding_report";
		}
		
		
		//Download admin sms tracking 
		@RequestMapping(value="/export_adminsmstrack", method = RequestMethod.POST)
		public ModelAndView adminsmstrack_export(HttpServletResponse response,HttpServletRequest request,ModelMap model, Principal principal ) {
		
			
			
			//Field going to be used in the document
			String[] fields={"Organisation","Branch","Student Roll no","Mobile number","Date/Time","Status"};
			
			List<Report> reports=new ArrayList<Report>();
			
			//Must set this line to download
			response.setHeader("Content-Disposition","attachment;filename=AdminSMSReport.xls");
			
			//Fill the report
			reports=reportsDAO.getsmsreport_for_download();
			
			ModelAndView modelAndView=new ModelAndView("reportsDAO","reports",reports);
			
			modelAndView.addObject("fields",fields);
			modelAndView.addObject("content","admin_sms_track");
		
			return modelAndView ;
			
			
		}
		
		/*
		 * Export Client SMS Track Report 
		 * 
		 */
		
		@RequestMapping(value="/export_clientsmstrack", method = RequestMethod.POST)
		public ModelAndView clientsmstrack_export(HttpServletResponse response,HttpServletRequest request,ModelMap model, Principal principal ) {
		
			
			
			//Field going to be used in the document
			String[] fields={"Student Roll no","Mobile number","Date/Time","Status"};
			
			List<Report> reports=new ArrayList<Report>();
			
			//Must set this line to download
			response.setHeader("Content-Disposition","attachment;filename=ClientSMSReport.xls");
			
			//Fill the report
			reports=reportsDAO.getTracksmsreport(mainDAO.getOrg_id(principal.getName()));
			
			ModelAndView modelAndView=new ModelAndView("reportsDAO","reports",reports);
			
			modelAndView.addObject("fields",fields);
			modelAndView.addObject("content","client_sms_track");
		
			return modelAndView ;
			
			
		}
		
		
		/*
		 * Export Client Side Over Speed Report 
		 * 
		 */
		
		@RequestMapping(value="/export_overspeedreport", method = RequestMethod.POST)
		public ModelAndView clientoverspeed_export(HttpServletResponse response,HttpServletRequest request,ModelMap model, Principal principal ) {
		
			
			
			//Field going to be used in the document
			String[] fields={"Vechicle_Reg_No","Driver Name","Over Speed Count","Date/Time"};
			
			List<Report> reports=new ArrayList<Report>();
			
			//Must set this line to download
			response.setHeader("Content-Disposition","attachment;filename=OverSpeedReport.xls");
			
			//Fill the report
			reports=reportsDAO.export_overspeedreport(mainDAO.getOrg_id(principal.getName()));
			
			ModelAndView modelAndView=new ModelAndView("reportsDAO","reports",reports);
			
			modelAndView.addObject("fields",fields);
			modelAndView.addObject("content","over_speed_report");
		
			return modelAndView ;
			
			
		}
		
	
}