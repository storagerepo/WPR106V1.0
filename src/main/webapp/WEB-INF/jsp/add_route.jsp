<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="resources/js/autoddl/jquery-1.8.3-min.js"></script>
<jsp:include page="header.jsp"></jsp:include>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">


<script>
$(function() {
$( "#datepicker" ).datepicker({dateFormat:'yy-mm-dd'});
});

$('.datetimeinput').timepicker();
</script>

<!-- <script type='text/javascript'
	src='http://code.jquery.com/jquery-1.4.3.min.js'></script>
	<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.ui.timepicker.js" />"></script>
	<link href="<c:url value="/resources/css/jquery.ui.timepicker.css" />"
	rel="stylesheet" type="text/css" />
	<script type="text/javascript">
	 $(document).ready(function() {
		 $('#timepicker').timepicker({
			    showPeriod: true,
			    showLeadingZero: true
			});
     });
	
	</script> -->
	
	
	<script type="text/javascript">
function doAjaxPost() {  
	/* alert("hi"); */
	var orgname = $('#orgid').val();
	/* alert(orgname); */
	 $.ajax({  
		    type: "POST",  
		    url: "/BusTrackingApp/route_reg_ajax",  
		    data: "org_name=" + orgname,
		    success: function(response){  
		    	
		    	
/* document.getElementById("branch").value=response; */
$('#info').html(response);
/* var select = document.getElementById("bid");
var option = document.createElement('option');
option.text = option.value = response;
select.add(option, 0); */
/* alert("shgjasgdjs"); */
   },  
		    error: function(e){  
		      alert('Error: ' + e);  
		    }  
		  });  
		}  
		</script>
<style>
#map-canvas {
	height: 100%;
	width: 90%;
	margin: 0px auto;
	padding: 40px;
	align:center;
}

#panel {
	position: absolute;
	top: 5px;
	left: 50%;
	margin-left: -180px;
	z-index: 5;
	background-color: #fff;
	padding: 5px;
	border: 1px solid #999;
}

* {
	font-family: Arial;
}

h2 {
	padding: 0 0 5px 5px;
}

h2 a {
	color: #224f99;
}

a {
	color: #999;
	text-decoration: none;
}

a:hover {
	color: #802727;
}

p {
	padding: 0 0 5px 0;
}

</style>

<div id="GPS_View_container">
    <div id="GPS_View_menu"><jsp:include page="admin_menu.jsp"></jsp:include></div>
    <div id="GPS_View_table">
<form action="insert_route_stop" method="post">

	<table class="margin_table">
		<c:if test="${Success=='true'}">
		<tr>
			<td valign="top" align="left" style="padding: 5px 0 10px 0;">
				<div class="status success">
					<p class="closestatus">
						<a title="#" href="">x</a>
					</p>
					<p>
						<img alt="Success" src="images/icons/icon_success.png"><span>
						</span> .
					</p>
				</div></td>
		</tr>
		</c:if>
		<tr>
			<td valign="top" align="left">
				<div>
					<div class="headings altheading">
						<h2> Route Registration</h2>
					</div>
					

						<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr class="row1">
								<td valign="middle" align="right" class="input_txt" width="30%"><span
									class="err">*</span> Organization Name :</td>
								<td valign="top" align="left" class="input_txt" width="70%">

								<select name="org_name" class="input_txtbx_height" id="orgid" onchange="doAjaxPost()" onblur="Validate('orgid')">
								<option value="Selected">-- Select Organization--</option>
								<c:forEach items="${orgname}" var="orgname" varStatus="status">
        				        <option value="${orgname}" <%-- <c:if test="${adminuser.org_id==OrgRegistration.org_id}"><c:out value="Selected"/></c:if> --%>>${orgname}</option>
			                  </c:forEach>
								
								</select>
								</td>
							</tr>
							<tr class="row1">
								<td valign="middle" align="right" class="input_txt" width="30%"><span
									class="err">*</span> Branch :</td>
								<td valign="top" align="left" class="input_txt" width="70%">
					  <div id="info" style="height:8px; " >
								<select name="branch" class="input_txtbx_height" id="bid" onchange="doAjaxPost1()" onblur="Validate1('bid')"> 
								<option value="Selected">-- Select branch--</option>
								<%-- <c:forEach items="${orgRegistrationForm.orgregistration}" var="OrgRegistration" varStatus="status">
        				        <option value="${OrgRegistration.branch}">${OrgRegistration.branch}</option>
			                  </c:forEach> --%>

								</select></div>
								<br />
								</td>
							</tr>
							<!-- <tr class="row1">
								<td valign="middle" align="right" class="input_txt" width="30%"><span
									class="err">*</span> Stop ID :</td>
								<td valign="top" align="left" class="input_txt" width="70%"><input
									type="text" name="stop_id" id="stop_id"
									class="input_txtbx_height" id="inp_id3" value=""
									placeholder="Stop ID" /><br />
								</td>
							</tr> -->
							<tr class="row2">
								<td valign="middle" align="right" class="input_txt" width="30%"><span
									class="err">*</span> Route No :</td>
								<td valign="top" align="left" class="input_txt" width="70%" >
								<div id="info1"><select class="input_txtbx_height" name="route_no" id="imei" onblur="Validate2('imei')">
							    <option value="" selected>-- Select Route No--</option>
							     <%-- <c:forEach items="${busregistrationform.busregistration}" var="BusRegistration" varStatus="status">
        				        <option value="${BusRegistration.route_no}">${BusRegistration.route_no}</option>
			                  </c:forEach> --%>
			                  </select>
			                  </div>
									&nbsp;&nbsp;<br />
								</td>
							</tr>
							<%-- <tr class="row1">
								<td valign="middle" align="right" class="input_txt" width="30%"><span
									class="err">*</span> Trip :</td>
								<td valign="top" align="left" class="input_txt" width="70%">
								<select name="trip" class="input_txtbx_height">
								<option value="Pick Up"> Pick Up</option>
								<option value="Drop"> Drop</option>
								<option value="KG Drop"> KG Drop</option>
								</select><br />
								</td>
							</tr>
							<tr class="row2" style="margin-top:20px;">
								<td valign="middle" align="right" class="input_txt"><span
									class="err">*</span> Bus Registration No :</td>
								<td valign="top" align="left" class="input_txt" width="70%">
								<select name="vechicle_reg_no" class="input_txtbx_height">
								<option value="">-- Select Registration No--</option>
								<c:forEach items="${busregistrationform.busregistration}" var="BusRegistration" varStatus="status">
        				        <option value="${BusRegistration.vechicle_reg_no}">${BusRegistration.vechicle_reg_no}</option>
			                  </c:forEach>

								</select>
									&nbsp;&nbsp;<br />
								</td>
							</tr> 
							
							<tr class="row2" style="margin-top:20px;">
								<td valign="middle" align="right" class="input_txt"><span
									class="err">*</span> Bus Stop Address  :</td>
								<td valign="top" align="left" class="input_txt" width="70%"  >
								<input type="text" name="stopaddress" class="input_txtbx_height" id="stop_address" onblur="check()" value="" />
									&nbsp;&nbsp;<br />
								</td>
							</tr>
							--%>
							<tr class="row2" style="margin-top:20px;">
								<td valign="middle" align="right" class="input_txt" ><span
									class="err">*</span>&nbsp;&nbsp;<a href="" id="addScnt" style="padding:3px;border:2px inset #9fb7cd;border-radius:5px;text-decoration:none;"> Add Bus Stops :</a><input type="hidden" id="number_of_stops" name="number_of_stops"></td>
								<td valign="top" align="left" class="input_txt" width="70%" id="p_scents">
								
								
								</td>
								</tr>
							
							<!--  <tr class="row2" style="margin-top:20px;">
								<td valign="middle" align="right" class="input_txt"><span
									class="err">*</span> Arrival Time :</td>
								<td valign="top" align="left" class="input_txt" width="70%">
								<input type="text" id="datepicker" name="route_from" class="input_txtbx_height"  value="" />
									&nbsp;&nbsp;<br />
								</td>								
								
							</tr> -->
									
							<tr class="row1">
                  
                  <td valign="top" align="left"></td>
                 
                  <td valign="top" align="left">
                  <table cellpadding="0" cellspacing="0" border="0">
                  <tr>
                  <td><input type="submit" class="submit_btn" value="Register" ></td>
                  
                  <td> 
                   <input type="button" class="submit_btn" onclick="window.location.href='welcome'" value="Cancel">
                  </td>
                  </tr>
                  </table>
                  </td>
                </tr>

						</table>
					</div>
				</td>
		</tr>
	</table>

</form>
	</div></div>				
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
<script language="JavaScript">
//This example adds a search box to a map, using the Google Place Autocomplete
//feature. People can enter geographical searches. The search box will return a
//pick list containing a mix of places and predicted search terms.
$(function() {
	
        var scntDiv = $('#p_scents');
        var i = $('#p_scents p').size();
        var j=0;
        $('#addScnt').live('click', function() {
        	alert(i);
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

<script>

function Validate(orgid)
{
var e = document.getElementById("orgid");
var strUser = e.options[e.selectedIndex].value;

var strUser1 = e.options[e.selectedIndex].text;
if(strUser==0)
{
alert("Please Select a Organization");
}
}

function Validate1(bid)
{
var e = document.getElementById("bid");
var strUser = e.options[e.selectedIndex].value;

var strUser1 = e.options[e.selectedIndex].text;
if(strUser==0)
{
alert("Please Select a branch");
}
}

function check(){
if(document.getElementById("stop_address").value==''){
	alert("Address is required");
	return false;
}
}

function routecheck()
{
	if(document.getElementById("route_id").value==''){
		alert("Route no is required");
		return false;

	}	
}

function validateorg()
{
	if(document.getElementById("route_id").value==''){
		alert("Route no is required");
		return false;

	}	
}

</script>

<script type="text/javascript">
 function doAjaxPost1() {  
	/* alert("hi"); */
	var orgname = $('#orgid').val();
	var branch=$('#bid').val();
	/* alert(orgname); */
	 $.ajax({  
		    type: "POST",  
		    url: "/BusTrackingApp/route_ajax",  
		    data: "org_name="+orgname+"&branch="+branch,
		    
		    success: function(response){  
		
$('#info1').html(response);

   },  
		    error: function(e){  
		      alert('Error: ' + e);  
		    }  
		  });  
		}  
		</script>
<jsp:include page="footer.jsp"></jsp:include>