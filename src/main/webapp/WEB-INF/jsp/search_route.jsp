<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="resources/js/autoddl/jquery-1.8.3-min.js"></script>
<jsp:include page="header.jsp"></jsp:include>
<div id="GPS_View_container">
    <div id="GPS_View_menu"><jsp:include page="admin_menu.jsp"></jsp:include></div>
    <div id="GPS_View_table">

	<table class="margin_table">
      		
			
      		<tr>
        		<td valign="top" align="left">
			        <div class="headings altheading">
			          <h2> Route Information</h2>
			          <div class="searchdiv">
                        <a href="#" class="searchpressable" onclick="toggle(this,'div');return false">
                          Open Search
                        </a></div>
			        </div>						
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
    	
<tr>

<td>
<div style="display:none" id="div"><div class="searchpanel">
<form action="findroute" method="GET">
							 
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
							  <tr>
							  <td align="left" valign="middle" width="10%" class="input_txtlabel"><span style="line-height:8px;">&nbsp; Organization Name &nbsp;<br/><font color="#ccc">.</font></span><br/>
							    <select   id="e1"style="width:300px;" name="org_name">
							 	<option value="">Select None</option>     
							   <c:forEach items="${routeViewForm.route_views}" var="route1" varStatus="status">
							    <option value="${route1.org_name}">${route1.org_name}</option>
							    </c:forEach>
							    </select></td>
							  <td align="left" valign="middle" width="10%" class="input_txtlabel"><span style="line-height:8px;">&nbsp;Bus Registration No &nbsp;<br/><font color="#ccc">.</font></span><br/>
							    <select   id="e3"style="width:300px;" name="vechicle_reg_no">
							 	<option value="">Select None</option>     
							   <c:forEach items="${routeViewForm.route_views}" var="route1" varStatus="status">
							    <option value="${route1.bus_reg_no}">${route1.bus_reg_no}</option>
							    </c:forEach>
							    </select></td>
							  <td align="left" valign="middle" width="10%" class="input_txtlabel"><span style="line-height:8px;">&nbsp; Branch &nbsp;<br/><font color="#ccc">.</font></span><br/>
							    <select   id="e2"style="width:300px;" name="branch">
							 	<option value="">Select None</option>     
							   <c:forEach items="${routeViewForm.route_views}" var="route1" varStatus="status">
							    <option value="${route1.branch}">${route1.branch}</option>
							    </c:forEach>
							    </select></td>
							  
							  </tr><tr><td><font color="#ccc">.</font></td></tr>
							  <tr>
							  <td align="left" valign="middle" width="10%" class="input_txtlabel"><span style="line-height:8px;">&nbsp; Rout No &nbsp;<br/><font color="#ccc">.</font></span><br/>
							    <select   id="e4"style="width:300px;" name="route_no">
							 	<option value="">Select None</option>     
							   <c:forEach items="${routeViewForm.route_views}" var="route1" varStatus="status">
							    <option value="${route1.route_no}">${route1.route_no}</option>
							    </c:forEach>
							    </select></td>
							    
							    <td align="left" valign="middle" width="10%" class="input_txtlabel"><span style="line-height:8px;">&nbsp; Trip &nbsp;<br/><font color="#ccc">.</font></span><br/>
							    <select   id="e5"style="width:300px;" name="trip">
							 	<option value="">Select None</option>     
							   <c:forEach items="${routeViewForm.route_views}" var="route1" varStatus="status">
							    <option value="${route1.trip}">${route1.trip}</option>
							    </c:forEach>
							    </select></td>
							    
							    
							    
							    <td align="right" valign="middle" width="20%"><br>
							    <input type="submit" class="btn" value="Search" ></td>
							    <td width="10%"><font color="#ccc" >.</font></td>
							 
							  </tr>
							</table>
							</form>
							</div></div>
							</td>
							</tr>
							</table>
							<div style="display:none" id="divfilter">
							<div id="filter_box">

	<div class="wrapperFilter">
		<img src="resources/images/filter.png" width="25" height="27"title="Search" align="center"/>
	<input type="text" id="search" name="search" placeholder="Enter Text To Filter" class="light-table-filter" data-table="order-table" placeholder="Filter"/>
	</div>
</div></div>
							
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr class="title">
									<!-- 	<td valign="top" align="left" width="18%"> Bus Id</td> -->
					         	<td valign="top" align="left" width="17.5%"> 	Organization Name</td>
					         	<td valign="top" align="left" width="12%">Branch</td>
					       	 	<td valign="top" align="left" width="10%"> Bus Reg No</td>
					       	 	<td valign="top" align="left" width="13%"> 	Route No</td>
          						<td valign="top" align="left" width="10%"> Trip</td>
          						<td valign="top" align="left" width="25%"> Bus Stop Address</td>
          						<td valign="top" align="left" width="10%"> Action</td>
          						
          						
        					</tr></table>
							
							<div class="Panel_One_Inner">
							
				        <table cellpadding="0" cellspacing="0" border="0" width="100%"style="table-layout: fixed;width:100%" class="order-table table">
							
        					<c:if test="${fn:length(routeViewForm.route_views) gt 0 }">
        					<c:forEach items="${routeViewForm.route_views}" var="route" varStatus="status">
        					
        				       				<tr class="row1">
        				            		<td valign="middle" align="left"style="overflow:hidden;" width="17%">${route.org_name}</td>
					     		     		<td valign="top" align="left" style="overflow:hidden;"width="12%">${route.branch}</td> 
											<td valign="top" align="left" style="overflow:hidden;"width="10%">${route.bus_reg_no}</td>
											<td valign="top" align="left" style="overflow:hidden;"width="13%">${route.route_no}</td>
											<td valign="top" align="left" style="overflow:hidden;"width="10%"><c:choose>
											<c:when test="${route.trip==0}">
											<c:out value="Pick Up" ></c:out>
											</c:when>
											<c:when test="${route.trip==1}">
											<c:out value="Drop" ></c:out>
											</c:when>
											<c:otherwise>
											<c:out value="KG Drop"></c:out>
											</c:otherwise>
											</c:choose>
</td>
											<td valign="top" align="left"style="overflow:hidden;" width="25%" title="${route.bus_stop_address}">${route.bus_stop_address}</td>
											<td valign="top" align="left" style="overflow:hidden;"width="10%">	
										    <a href="<c:out value="editroute?route_no=${route.route_no}"/>"><img src="resources/images/edit-29.png" width="20"height="18"alt="Edit" title="Edit"/></a>|
											<a href="<c:out value="deleteuser?route_no=${route.route_no}"/>" onclick="return confirmation()"><img src="resources/images/del.png" alt="Delete" width="20"height="18" title="Delete"/></a>
									
											</td>
								</tr>
							    	</c:forEach>
							    </c:if>
							    
							     <c:if test="${fn:length(routeViewForm.route_views) == 0}">	
							    	<tr class="row1">
							    	<td colspan="7" width="100%"><center><b>No Routes Found!!!</b></center></td>
							    	</tr>
							    	</c:if>
        				</table>
        				</div>
        				<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr class="title">
								
          						<td valign="top" align="right" width="100%"> &#9660;</td>
          						
          						
        					</tr></table>
        				</td>
        				</tr>
        				</table>
        			
					       				
     </div></div>				
<script>

function confirmation() {
	var answer = confirm("Are you Sure You Want to Delete the Route ?");
	if (answer){
		return true;
	}
	else{
		return false;
	}
}
</script>

<script language="javascript">

function selectall(field)
{

	//field.getElementByTagName('checkbox');
	if(document.grid.checkall.checked==true)
	{
		for (i = 0; i < field.length; i++)
			field[i].checked = true ;
	}
	else
	{
		for(i = 0; i < field.length; i++)
			field[i].checked = false;
	}
}
</script>
  		 
<jsp:include page="footer.jsp"></jsp:include>
<a href="#x" class="overlay" id="join_form"></a>
        <div class="popup">
            <h2>Student Details</h2>
<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr class="titlepop">
									<!-- 	<td valign="top" align="left" width="18%"> Bus Id</td> -->
					         	<td valign="top" align="left" width="15%"> 	Org Name</td>
					         	<td valign="top" align="left" width="15%">Branch</td>
					       	 	<td valign="top" align="left" width="10%"> Bus Reg No</td>
					       	 	<td valign="top" align="left" width="10%"> 	Route No</td>
          						<td valign="top" align="left" width="10%"> Trip</td>
          						<td valign="top" align="left" width="25%"> Bus_Stop_Address</td>
          						<td valign="top" align="left" width="25%"> Operations</td>
          						
          						
        					</tr></table>
							
							<div class="Panel_One_Inner">
							
				        <table cellpadding="0" cellspacing="0" border="0" width="100%" class="order-table table">
							
        					<c:if test="${fn:length(routeViewForm.route_views) gt 0 }">
        					<c:forEach items="${routeViewForm.route_views}" var="route" varStatus="status">
        					
        				       					<tr class="row1">
        				            		<td valign="center" align="center" width="15%">${route.org_name}</td>
					     		     	<td valign="top" align="left" width="15%">${route.branch}</td> 
											<td valign="top" align="left" width="10%">${route.bus_reg_no}</td>
											<td valign="top" align="left" width="10%"><a href="#join_form" id="join_pop" class="iconbutton star" >${route.route_no}</a></td>
											<td valign="top" align="left" width="10%"><c:choose><c:when test="${route.trip==0}">
											<c:out value="Pick Up"></c:out>
											</c:when>
											<c:when test="${route.trip==1}">
											<c:out value="Drop"></c:out>
											</c:when>
											<c:otherwise>
											<c:out value="KG Drop"></c:out>
											</c:otherwise>
											</c:choose>
</td>
											<td valign="top" align="left" width="25%">${route.bus_stop_address}</td>
											<td valign="top" align="left" width="25%">				
										
										  <a href="#" title="" id="addScnt"><img src="resources/images/icons/icon_edit.png" alt="Edit" /></a>
										    <a href="<c:out value="editroute?route_no=${route.route_no}"/>" style="padding-right:10px;">Edit</a>
											
											<a href="#" title=""><img src="resources/images/icons/icon_delete.png" alt="Delete" /></a><a href="<c:out value="deleteuser?route_no=${route.route_no}"/>" onclick="return confirmation('Are you sure want to Delete?')">Remove</a>
									
											</td>
								</tr>
							    	</c:forEach>
							    </c:if>
							     <c:if test="${fn:length(routeViewForm.route_views) == 0}">	
							    	<tr class="row1">
							    	<td colspan="7" width="100%"><center><b>No Routes Found!!!</b></center></td>
							    	</tr>
							    	</c:if>
        				</table>
        				</div>
        				<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr class="title">
								
          						<td valign="top" align="right" width="100%"> &#9660;</td>
          						
          						
        					</tr></table>
        				<a class="close" href="#close"></a>
        </div>
        <script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
<script type="text/javascript">
//This example adds a search box to a map, using the Google Place Autocomplete
//feature. People can enter geographical searches. The search box will return a
//pick list containing a mix of places and predicted search terms.
$(function() {
	
        var scntDiv = $('#p_scents');
        var i = $('#p_scents p').size();
        var j=0;
        $('#addScnt').live('load', function() {
        	//alert(i);
        	$('<p style="border:solid 1px grey;padding:5px;margin-top:5px;width:400px;background-color:#E5E5E5;"><label for="p_scnts"><input type="text"  size="20" class="input_txtbx_height" id="stop_info_'+i+'" name="stop['+i+']" value="" placeholder="Stop Location" /></label><br/><br/><input type="radio"  name="stop_pick['+i+']" id="stop_pick_'+i+'" value="0" checked>Pick up<input type="radio"  name="stop_pick['+i+']" value="1" id="stop_drop_'+i+'">Drop<input type="radio"  name="stop_pick['+i+']" value="2" id="stop_both_'+i+'">KG Drop<br/><br/><input type="text" name="particular_stop_pickup_time['+i+']" id="datepicker" placeholder="Bus Arrival time" class="input_txtbx_height" style="width=100px; value=""/><br/><br/>&nbsp;&nbsp;&nbsp;<a href="#" id="remScnt">Remove</a></p>').appendTo(scntDiv);
        	document.getElementById("number_of_stops").value=i;
        	var autocomplete_stop = new google.maps.places.Autocomplete(document.getElementById("stop_info_"+i));
            i++;
            return false;
    });
    
    $('#remScnt').live('click', function() { 
               $(this).parents('p').remove();
                    i--;
           
		});
});

function initialize() {

	var autocomplete_stop_address = new  google.maps.places.Autocomplete(document.getElementById('stop_address'));
	var autocomplete_route_stop = new google.maps.places.Autocomplete(document.getElementById('route_stop'));
	
}
google.maps.event.addDomListener(window, 'load', initialize);
</script>