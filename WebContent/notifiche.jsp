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
		<title>Notifiche</title>

    <!-- Stylesheets
    ================================================= -->
		<link rel="stylesheet" href="cssapp/bootstrap.min.css" />
		<link rel="stylesheet" href="cssapp/style.css" />
		<link rel="stylesheet" href="cssapp/ionicons.min.css" />
    <link rel="stylesheet" href="cssapp/font-awesome.min.css" />
    
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

    <div class="container">

      <!-- Timeline
      ================================================= -->
      <div class="timeline">
        <div class="timeline-cover">

          <!--Timeline Menu for Large Screens-->
          <div class="timeline-nav-bar hidden-sm hidden-xs">
            <div class="row">
              <div class="col-md-3">
                <div class="profile-info">
                  <img src="<%=user.getProf_image() %>" alt="" class="img-responsive profile-photo" />
                  <h3><%=user.getUsername() %></h3>
                </div>
              </div>
              <div class="col-md-9">
                <ul class="list-inline profile-menu">
                  <li><a href="timeline.jsp?username=<%=user.getUsername() %>">Profilo</a></li>
                </ul>
              </div>
            </div>
          </div><!--Timeline Menu for Large Screens End-->
  
          <!--Timeline Menu for Small Screens-->
          <div class="navbar-mobile hidden-lg hidden-md">
            <div class="profile-info">
              <img src="<%=user.getProf_image() %>" alt="" class="img-responsive profile-photo" />
              <h4>Utente</h4>
              <p class="text-muted">Scrivi qualcosa in bio</p>
            </div>
            <div class="mobile-menu">
              <ul class="list-inline">
                <li><a href="timeline-about.html">Profilo</a></li>
                <li><a href="timeline.html">I miei post</a></li>
                <li><a href="timeline-friends.html">Amici</a></li>
              </ul>
  
              <button class="btn-primary">Aggiungi post</button>
            </div>
          </div><!--Timeline Menu for Small Screens End-->

        </div>
        <div id="page-contents">
          <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-7">

              <!-- About
              ================================================= -->
              <div class="about-profile">
                <!-- <div class="about-content-block">
                  <h4 class="grey"><i class="ion-ios-information-outline icon-in-title"></i>Personal Information</h4>
                  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur</p>
                </div>-->
                <div class="about-content-block">
                 <!--<div class="organization">
                    <img src="../HTML/images/ny1.jpg" alt="" class="pull-left img-org" />
                     <div class="work-info">
                      <h5>NY</h5>
                      <p>Pubblicato ieri <br>
                        <span class="text-grey">Sono felice perch� mi sono ricordata che la scorsa primavera sono andata a New York</span></p>
                    </div>
                  </div>
                  <div class="organization">
                    <img src="images/envato.png" alt="" class="pull-left img-org" />
                    <div class="work-info">
                      <h5>Envato</h5>
                      <p>Seller - <span class="text-grey">1 February 2013 to present</span></p>
                    </div>
                  </div>
                  <div class="organization">
                    <img src="images/envato.png" alt="" class="pull-left img-org" />
                    <div class="work-info">
                      <h5>Envato</h5>
                      <p>Seller - <span class="text-grey">1 February 2013 to present</span></p>
                    </div>
                  </div>--> 
              
             
                  <h4>Le tue notifiche</h4>
                  <%
                  ArrayList<Follower_Notification> listanotifiche = new ArrayList<Follower_Notification>();
                  listanotifiche = acc.selectNotificationByUsername(user.getUsername());
        		  for(int i = 0; i < listanotifiche.size(); i++){
                  %>
                  	<div class="feed-item">
                    <div class="live-activity">
                      <p><a href="timelile.jsp?username=<%=listanotifiche.get(i).getUsername_mit() %>" class="profile-link"><%=listanotifiche.get(i).getUsername_mit() %></a> ha iniziato a seguirti</p>
                    </div>
                  </div>
                  <%} %>
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
        <p>SpokenLife � 2023. All rights reserved</p>
      </div>
		</footer>
    
    <!--preloader-->
    <div id="spinner-wrapper">
      <div class="spinner"></div>
    </div>

    <!-- Scripts
    ================================================= -->
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCTMXfmDn0VlqWIyoOxK8997L-amWbUPiQ&callback=initMap"></script>
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.sticky-kit.min.js"></script>
    <script src="js/jquery.scrollbar.min.js"></script>
    <script src="js/script.js"></script>
    
  </body>
</html>
