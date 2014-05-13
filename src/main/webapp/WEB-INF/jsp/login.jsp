<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title> 
<link href="<c:url value="/resources/css/newstyles/5grid/core.css" />" rel="stylesheet"  type="text/css" />
<link href="<c:url value="/resources/css/newstyles/5grid/core-desktop.css" />" rel="stylesheet"  type="text/css" />
<link href="<c:url value="/resources/css/newstyles/5grid/core-1200px.css" />" rel="stylesheet"  type="text/css" />
<link href="<c:url value="/resources/css/newstyles/5grid/core-noscript.css" />" rel="stylesheet"  type="text/css" />
<link href="<c:url value="/resources/css/newstyles/style.css" />" rel="stylesheet"  type="text/css" />
<link href="<c:url value="/resources/css/newstyles/style-desktop.css" />" rel="stylesheet"  type="text/css" />
<link href="<c:url value="/resources/css/newstyles/5grid/jquery.js" />" rel="stylesheet"  type="text/css" />
<link href="<c:url value="/resources/css/newstyles/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none" />" rel="stylesheet"  type="text/css" />
<%-- <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet"  type="text/css" />
 --%>
 <!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
 
<style>
.errorblock {
	color: #ff0000;
	/* background-color: #ffEEEE; */
	border: 2px solid #ff0000;
	padding: 8px;
	margin: 16px;
	border-radius:15px;
	
	
}
.txtfld{
	outline: none;
	padding: 10px;
	height:25px;
	width: 190px;
	font-family: Helvetica, Arial, "Sans Serif";
	font-size: 13px;
	color: #666;
	border: 1px solid #ccc;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	-webkit-box-shadow: inset 0px 0px 10px 0px #eee;
	-moz-box-shadow: inset 0px 0px 10px 0px #eee;
	box-shadow: inset 0px 0px 10px 0px #eee;
	background:#fff;
}

.txtfld:focus {
	border-color: #3bb9ff;
	-webkit-box-shadow: inset 0px 0px 10px 0px #ddd;
	-moz-box-shadow: inset 0px 0px 10px 0px #ddd;
	box-shadow: inset 0px 0px 10px 0px #86D4FE;
}

.pass{
	outline: none;
	padding-left: 10px;
	height:25px;
	width: 190px;
	font-family: Helvetica, Arial, "Sans Serif";
	font-size: 13px;
	color: #666;
	border: 1px solid #ccc;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	-webkit-box-shadow: inset 0px 0px 10px 0px #eee;
	-moz-box-shadow: inset 0px 0px 10px 0px #eee;
	box-shadow: inset 0px 0px 10px 0px #eee;
	background:#fff;
}

.pass:focus {
	border-color: #3bb9ff;
	-webkit-box-shadow: inset 0px 0px 10px 0px #ddd;
	-moz-box-shadow: inset 0px 0px 10px 0px #ddd;
	box-shadow: inset 0px 0px 10px 0px #86D4FE;
}
</style>
</head>
<body onload='document.f.j_username.focus();' >
<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
<div id="header-wrapper">
	<header id="header" class="5grid-layout" >
		
				<form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
	<div class="Login_panel" style="margin-top:10px;padding-top:15px;color:#fff;">
	<table cellpadding="3" cellspacing="0" border="0" width="100%"><tr>
	<td  align="right" width="65%"></td><td width="5%" align="left" style="color:#fff;">
Email </td><td style="color:#fff;"> Password</td><td></td><td></td></tr>
<tr>
<td  align="right"></td>
<td align="left" width="15%"><input type="text" class="txtfld"value="" name="j_username" /></td>
<td><input type="password" value=""class="pass" name="j_password"/></td><td>
<input type="submit" class="submit_btn" value="Login"/></td><td>
<input type="reset" class="submit_btn" value="Reset"/></td></tr>
<tr><td  align="right"></td><td style="color:#fff;"><input type="checkbox" value="1" class="txtfld" name="" />keep me logged in </td>
<td >
<a href="#"  ><font color="white">Forgot your password?</font></a></td>
<td></td><td></td></tr></table>
</div>
<div class="Login_side_contact_outline" id="contact-form">
<h1 >
   <img src="resources/images/contact.png" width="360" height="35"/>
</h1>
<br/>
			<input type="text" name="name" id="fname" placeholder="First Name" autocomplete="off" tabindex="1" class="txtinput_small">
			<input type="text" name="name" id="lname" placeholder="Last Name" autocomplete="off" tabindex="1" class="txtinput_small_two">
			<input type="email" name="email" id="email" placeholder="Your e-mail address" autocomplete="off" tabindex="2" class="txtinput">
			<input type="text" name="name" id="oname" placeholder="Organization Name" autocomplete="off" tabindex="1" class="txtinput">
			<input type="text" name="telephone" id="telephone" placeholder="Mobile Number" autocomplete="off" tabindex="3" class="txtinput">
			<input type="tel" name="country" id="country" placeholder="Country" tabindex="4" class="txtinput">
			<textarea name="message" id="message" placeholder="Comments/Questions" tabindex="5" class="txtblock"></textarea>
			<input type="submit" value="Submit" class="contact_submit_btn"><input type="reset" value="Reset" class="contact_submit_btn">
</div>
</form>
<div class="syrees_copy_rights">� Copyright 2014, All rights reserved,�Syrees Inc.<br/>
Phone:�00 91 96327 19800�-�Fax:�000-111-0000</div>
<div class="Login_page_footer_link"><center>Contact Us | Terms of Use | Privacy Policy | Email-Us | Directions | Site Map</center></div>

</header>
</div>


<%-- <div id="wrap_page"><c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	
	<form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
	<div class="test1">
<div class="label_align">Email <span style="margin: 0 0 0 30% ;"> Password</span></div>

<input type="text" value="" name="j_username" class="text_align"/>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" value="" name="j_password"/>
<input type="submit" class="button_submit"/>
<input type="reset" class="button_submit"/><br/>
<span style="margin:0 0 0 64.7%;"><input type="checkbox" value="1" class="checkbox_login" name="" />keep me logged in <a href="#" style="color:#fff; text-decoration: none;margin:0 0 0 3.59% ;" >forgot your password?</a></span>

</div>
<div class="Login_side_contact_outline">


</div>
</form>
</div>	
 --%><%-- 
<div class="main">
<div class="header-top">
<div class="right login-top"><a href="#">Login</a> | <a href="#">Forgot Password</a></div>
  </div>
  <div class="clearfix">&nbsp;</div>
  <div class="header menu">
   <a href="#">Home</a> | <a href="#">About Us</a>  |  <a href="#"> GuideLines </a>   
        
    </div><!-- header ends here  -->
    <!-- middle content starts here  -->
    
    <div class="inner-content">
      
       <div class="inner">  
	<form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
		     <h1>User Login</h1>
	 	<table class="center"> 
	 	<tr>
				<td>Organisation ID:</td>
				<td class="td1"> <input type="text"  class="inputbx" name="org_id" value=''></td>
				<p><font color="Red"></font></p>
		     </tr>	
	 		<tr>
				<td>User Name:</td>
				<td class="td1"> <input type="text"  class="inputbx" name="j_username" value=''></td>
				<p><font color="Red"></font></p>
		     </tr>
			<tr>
				<td>Password:</td>
				<td class="td1"><input type="password" class="inputbx" name="j_password"></td>
				
		      </tr>
		      <tr>
		      
	    		<td><input type="submit" name="enter_button1" value="Login"/></td>	
	   	 		<td><input type="button" name="enter_button2" value="Cancel" onclick="login.jsp"/></td>
	   
	   		</tr>
		       </table>
		    
	</form>
	</div>
	</div>
	
		  <div class="login-bottom"></div>
		</div> --%>
</body>
</html>