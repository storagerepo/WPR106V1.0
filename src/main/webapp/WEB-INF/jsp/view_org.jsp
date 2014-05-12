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
			          <h2>Organization Information<br/></h2><div class="searchdiv">
                        <a href="#" class="searchpressable" onclick="toggle(this,'div');return false">
                          Open Search
                        </a></div>
			        </div>
			    
			       
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
    	<tr>

<td>
<div style="display:none" id="div"><div class="searchpanel">
						
 
<form action="findorg" method="GET">
							 
							<table width="100%" border="0" cellspacing="0" cellpadding="0" >
							
							  <tr>
							    <td align="left" valign="middle" width="15%" class="input_txtlabel">&nbsp;Organization Name &nbsp;<br/>
							    <select name="org_name" id="e1"style="width:300px;">
							    <option value="">Select None</option>
							    <c:forEach items="${orgregistrationform.orgregistration}" var="orgRegistration1" varStatus="status">
							    <option value="${orgRegistration1.org_name}">${orgRegistration1.org_name}</option>
							    </c:forEach>
							    </select></td>
							    
							    <td align="left" valign="middle" width="10%" class="input_txtlabel">Branch  &nbsp;<br/>
							    <select name="branch" id="e2"style="width:300px;">
							    <option value="">Select None</option>
							    <c:forEach items="${orgregistrationform.orgregistration}" var="orgRegistration1" varStatus="status">
							    <option value="${orgRegistration1.branch}">${orgRegistration1.branch}</option>
							    </c:forEach>
							    </select></td>
							    
							    <td align="left" valign="middle" width="10%" class="input_txtlabel">&nbsp;City&nbsp;<br/>
							    <select name="city" id="e3"style="width:300px;">
							    <option value="">Select None</option>
							    <c:forEach items="${orgregistrationform.orgregistration}" var="orgRegistration1" varStatus="status">
							    <option value="${orgRegistration1.city}">${orgRegistration1.city}</option>
							    </c:forEach>
							    </select></td>
							    
                                 </tr><tr>							   
							    <td align="left" valign="middle" width="10%" class="input_txtlabel">&nbsp;Country&nbsp;<br/>
							    <select name="country" id="e4"style="width:300px;">
							    <option value="">Select None</option>
							    <c:forEach items="${orgregistrationform.orgregistration}" var="orgRegistration1" varStatus="status">
							    <option value="${orgRegistration1.country}">${orgRegistration1.country}</option>
							    </c:forEach>
							    </select></td>
							    
							    
							    <td align="center" valign="middle" width="20%">
							    <input type="submit" class="pressableButton blue" value="Search" ></td>
							
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
							<table cellpadding="0" cellspacing="0" border="1" width="100%">
							<tr class="title">
								<!-- <td valign="middle" align="center" width="15%"><input type="checkbox" onclick="selectall(this.form)" value="" name="checkall"></td> -->
         						<td valign="top" align="left" width="19.8%"> Organization Name </td>					         	
					         	<td valign="top" align="left" width="15%"> Branch</td>
          						<td valign="top" align="left" width="15%"> City</td>
          						<td valign="top" align="left" width="15%"> Country</td>
          						<td valign="top" align="left" width="12.5%"> Org Type</td>
          						<td valign="top" align="left" width="1%"> Status</td>
          						<td valign="top" align="left" width="9.5%"> Action</td>
          						
          						
        					</tr></table>
							<div class="Panel_One_Inner">
							
							
				        <table cellpadding="0" cellspacing="0" border="1" width="100%" class="order-table table">
							
        					<c:if test="${fn:length(orgregistrationform.orgregistration) gt 0 }">
        					<c:forEach items="${orgregistrationform.orgregistration}" var="orgRegistration" varStatus="status">
        				       					<tr class="row1">
							       		<%-- <td valign="middle" align="center" width="15%"><input type="checkbox" value="${orgRegistration.org_name}" name="chkUser"></td> --%>
					     		     	<td valign="top" align="left" width="20%">${orgRegistration.org_name}</td>					     		     
					     		     
											<td valign="top" align="left" width="15%">${orgRegistration.branch}</td>
											<td valign="top" align="left" width="15%">${orgRegistration.city}</td>
											<td valign="top" align="left" width="15%">${orgRegistration.country}</td>
											<td valign="top" align="left" width="13%">${orgRegistration.type_of_organization}</td>
											<td valign="top" align="left" width="5.8%">
											<c:choose>
											<c:when test="${orgRegistration.is_active==1}">
											<c:out value="Active"></c:out>
											</c:when>
											<c:when test="${orgRegistration.is_active==0}">
											<c:out value="Not Active"></c:out>
											</c:when>
											<c:otherwise>
											<c:out value=""></c:out>
											</c:otherwise>
											</c:choose>
											</td>
											
											<td valign="top" align="left" width="8.6%">
										
												<a href="<c:out value="editorg?org_name=${orgRegistration.org_name}&branch=${orgRegistration.branch}"/>" style="padding-right:10px;"><img src="resources/images/edit-29.png" width="20"height="18"alt="Edit" title="Edit"/></a>
												
											<a href="<c:out value="deleteorg?org_id=${orgRegistration.org_id}"/>" onclick="return confirmation()"><img src="resources/images/del.png" alt="Delete" width="20"height="18" title="Delete"/></a>
									
											</td>
								</tr>
							    	</c:forEach>
							    </c:if>
							    <c:if test="${fn:length(orgregistrationform.orgregistration) == 0}">	
							    	<tr class="row1">
							    	<td colspan="7" width="100%"><center><b>No Organization Found!!!</b></center></td>
							    	</tr>
							    	</c:if>
        				</table>
        				</div>
        				<table cellpadding="0" cellspacing="0" border="0" width="101%">
							<tr class="title">
								<!-- <td valign="middle" align="center" width="15%"><input type="checkbox" onclick="selectall(this.form)" value="" name="checkall"></td> -->
         						<td valign="top" align="right" width="100%">&#9660;</td>					         	
					         	
          						
          						
        					</tr></table>
        				</td>
        				</tr>
        				</table>
						</div></div>
						
						

<jsp:include page="footer.jsp"></jsp:include>