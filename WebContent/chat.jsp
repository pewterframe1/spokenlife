<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="model.*, data.*"%>
	<%@page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="This is social network html5 template available in themeforest......" />
		<meta name="keywords" content="Social Network, Social Media, Make Friends, Newsfeed, Profile Page" />
		<meta name="robots" content="index, follow" />
		<title>Chatroom | Send and Receive Messages</title>
		

    <!-- Stylesheets
    ================================================= -->
		<link rel="stylesheet" href="cssapp/bootstrap.min.css" />
    <link rel="stylesheet" href="cssapp/jquery.scrollbar.css" />
		<link rel="stylesheet" href="cssapp/style.css" />
		<link rel="stylesheet" href="cssapp/ionicons.min.css" />
    <link rel="stylesheet" href="cssapp/font-awesome.min.css" />
    <link href="cssapp/emoji.css" rel="stylesheet">
    
    <!--Google Font-->
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,400i,700,700i" rel="stylesheet">
    
    <!--Favicon-->
    <link rel="shortcut icon" type="image/png" href="images/fav.png"/>
	</head>
  <body style="overflow-x:hidden">
  
  <%
	  User user = (User)request.getSession().getAttribute("auth");
	  UserDAO acc = new UserDAO();
	  request.getSession().setMaxInactiveInterval(1800); 
	  request.getSession().setAttribute("auth", user);
	  %>

   <!-- Header
    ================================================= -->
		<header id="header">
      <nav class="navbar navbar-default navbar-fixed-top menu">
        <div class="container"  >

          <!-- Brand and toggle get grouped for better mobile display -->
          <div class="navbar-header" style="padding-left: 4%">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="home.jsp"><img src="images/definitivo3333.png" alt="logo" /></a>
          </div>

          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="padding-right: 4%">
            <ul class="nav navbar-nav navbar-right main-menu" style="padding-top:3px!important">
            <li class="dropdown">
                <a href="home.jsp" class="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Home</a>
              </li>
              <li class="dropdown">
                <a href="timeline.jsp?username=<%=user.getUsername() %>" class="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Profilo</a>
              </li>
              <li class="dropdown">
                <a href="chat.jsp" class="Home">Chatroom <span></span></a>
              </li>
              <li class="dropdown"><a href="contact.html">Contatti</a></li>
            </ul>
            <form class="navbar-form navbar-right hidden-sm" action="logout" method="post">
            	<div class="form-group">
                	<button type="submit" class="btn-primary" name="usernamelogout" value="<%=user.getUsername()%>">Esci</button>
              </div>
            </form>
          </div><!-- /.navbar-collapse -->
        </div><!-- /.container -->
      </nav>
    </header>
    <!--Header End-->

    <div id="page-contents">
    	<div class="container">
    		<div class="row">

    			<!-- Newsfeed Common Side Bar Left
          ================================================= -->
    			<div class="col-md-3 static">
            <div class="profile-card">
            	<img src="<%=user.getProf_image() %>" alt="user" class="profile-photo" />
            	<h5><a href="timeline.html" class="text-white"><%=user.getUsername() %></a></h5>
            	<a href="#" class="text-white"><i class="ion ion-android-person-add"></i> <%=Integer.toString(acc.selectFollowers(user.getUsername()))%> followers</a>
            </div><!--profile card ends-->
            <ul class="nav-news-feed">
              <li><i class="icon ion-ios-paper"></i><div><a href="index.jsp">Il mio Newsfeed</a></div></li>
            </ul><!--news-feed links ends-->
            <div id="chat-block">
              <div class="title">Chat online</div>
              <ul class="online-users list-inline">
              <%
            	  ArrayList<User> onlineuser = new ArrayList<User>();
              	  onlineuser = acc.selectOnlineUser();
              	  for(int m = 0; m < onlineuser.size(); m++){%>
              	  	<li><a href="timeline.jsp?username=<%=onlineuser.get(m).getUsername() %>" title="<%=onlineuser.get(m).getUsername() %>"><img src="<%=onlineuser.get(m).getProf_image() %>" alt="user" class="img-responsive profile-photo" /><span class="online-dot"></span></a></li>
              <%  } %>
              </ul>
            </div><!--chat block ends-->
          </div>
          
    	<div class="col-md-7">

            <!-- Chat Room
            ================================================= -->
            <div class="chat-room">
              <div  class="row">
                <div class="col-md-5">

                  <!-- Contact List in Left-->
                  <ul class="nav nav-tabs contact-list scrollbar-wrapper scrollbar-outer">
                  <%
                  ArrayList<Message> listamess = new ArrayList<Message>();
                  ArrayList<String> listamit = new ArrayList<String>();
                  listamess = acc.selectMessagesByDest(user.getUsername());
                  listamit = acc.selectOnlyMit(user.getUsername());
                  for(int l = 0; l < listamit.size(); l++){
                  	if(l == 0){%>
                  		<li class="active">
                  	<%}else{%>
                  		<li>
                  	<%}
                  	String param = acc.selectByUsername(listamit.get(l)).getUsername();
                  	%>
                      <a href="#<%=acc.selectByUsername(listamit.get(l)).getUsername()%>" data-toggle="tab">
                        <div class="contact">
                        	<img src="<%=acc.selectByUsername(listamit.get(l)).getProf_image() %>" alt="" class="profile-photo-sm pull-left"/>
                        	<div class="msg-preview">
                        		<h6><%=acc.selectByUsername(listamit.get(l)).getUsername() %></h6>
                            	<div class="chat-alert"><%=acc.selectMessagesNotificationByMit(listamit.get(l), user.getUsername())%></div>
                        	</div>
                        </div>
                      </a>
                      
                    </li>
                  <%}%>
                  </ul>
                  </div>
                            <!-- 
                            	<div class="seen"><i class="icon ion-checkmark-round"></i></div>
                            	<div class="replied"><i class="icon ion-reply"></i></div>
                             -->
                  <div class="col-md-7">
                  	<div class="tab-content scrollbar-wrapper wrapper scrollbar-outer">
                  <%
                  ArrayList<Message> listamessmitdest = new ArrayList<Message>();
                  for(int y = 0; y < listamit.size(); y++){ 
                  	listamessmitdest = acc.selectMessagesByMitDest(listamit.get(y), user.getUsername());%>
                  		
          			<%if(y == 0){ %>
          				<div class="tab-pane active" id="<%=listamit.get(y) %>">
          			<%}else{ %>
          				<div class="tab-pane" id="<%=listamit.get(y) %>">
          			<%} %>	
          				
          			<div class="chat-body">
              			<ul class="chat-message">
	                  	<%for(int h = 0; h < listamessmitdest.size(); h++){
	                  		if(listamessmitdest.get(h).getUsername_mit_mess().equals(user.getUsername())){%>
	                  			<li class="right">
	                  				<img src="<%=acc.selectByUsername(listamessmitdest.get(h).getUsername_mit_mess()).getProf_image() %>" alt="" class="profile-photo-sm pull-right" />
	                  		<%}else{%>
	                  			<li class="left">
		                  			<img src="<%=acc.selectByUsername(listamessmitdest.get(h).getUsername_mit_mess()).getProf_image() %>" alt="" class="profile-photo-sm pull-left" />
		                  		<%} %>
			                  		<div class="chat-item">
			                  			<div class="chat-item-header">
		                              		<h5><%=acc.selectByUsername(listamessmitdest.get(h).getUsername_mit_mess()).getUsername() %></h5>
		                              	</div>
		                              	<p><%=listamessmitdest.get(h).getText_mess() %></p>
		                            </div>
                            	</li>
	                  		
	                  	<%} %>
	                  	</ul>
	                 </div>
	                 
	                 
                  </div>
                  </div>
                  <div class="send-message">
                    <div class="input-group">
	                    <form action="insert_message?chat=yes" method="post">
	                      <input type="text" class="form-control" name="text_mess" placeholder="Scrivi il tuo messaggio">
	                      <span class="input-group-btn">
	                        <button class="btn btn-default" name="dest" value="<%=acc.selectByUsername(listamit.get(y)).getUsername() %>" type="submit">Invia</button>
	                        
	                      </span>
	                    </form>
                    </div>
                  </div>
                  <%} %>
                  
                </div>
                <div class="clearfix"></div>
              </div>
            </div>
          </div>
    		</div>
    	</div>
    </div>

   <!-- Footer
    ================================================= -->
    <footer id="footer">
      <div class="container">
      	<div class="row">
          <div class="footer-wrapper">
            <div class="col-md-3 col-sm-3">
              <h5>Contattaci</h5>
              <ul class="contact">
                <li><a href="tel:+393517489411"><i class="icon ion-ios-telephone-outline" style="color: blueviolet;"></i>+39 351 748 9411</li></a>
                <li><a href="mailto:spokenlife70@gmail.com"><i class="icon ion-ios-email-outline" style="color: blueviolet;"></i>spokenlife70@gmail.com</li></a>
                <li><a href="map:1200 19th Street,NW
                  Suite 1110 Washington, DC 20036"><i class="icon ion-ios-location-outline" style="color: blueviolet;"></i>1200 19th Street,<br>
                  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; NW
                  Suite 1110
                  <br>
                  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Washington, DC 20036</li></a>
              </ul>
            </div>
            <div class="col-md-3 col-sm-3">
              <h5>Ecco dove puoi trovarci</h5>
              <ul class="list-inline social-icons">
              	<li><a href="#"><i class="fa fa-spotify" style="color: rgb(56, 218, 50);">&nbsp;SpokenLifeMusic</i></a></li><br>
              	<li><a href="#"><i class="fa fa-instagram" style="color: rgb(220, 20, 113);">&nbsp;spokeenlife_</i></a></li><br>
              	<li><a href="#"><i class="fa fa-linkedin" style="color: darkcyan;">&nbsp;SpokenLife</i></a></li>
              </ul>
            </div>
            <div class="col-md-2 col-sm-2">
              <h5>About</h5>
              <ul class="footer-links">
                <li><a href="../sito/html/about.html">About us</a></li>
                <li><a href="../applicazione/contact.html">Contact us</a></li>
                <li><a href="../sito/html/index.html">Help</a></li>
              </ul>
            </div>
          </div>
      	</div>
      </div>
      <div class="copyright">
        <p>SpokenLife © 2023. All rights reserved</p>
      </div>
		</footer>
    
    <!--preloader-->
    <div id="spinner-wrapper">
      <div class="spinner"></div>
    </div>

    <!-- Scripts
    ================================================= -->
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.sticky-kit.min.js"></script>
    <script src="js/jquery.scrollbar.min.js"></script>
    <script src="js/script.js"></script>

  </body>
</html>
