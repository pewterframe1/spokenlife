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
		<title>My Profile</title>

    <!-- Stylesheets
    ================================================= -->
		<link rel="stylesheet" href="cssapp/bootstrap.min.css" />
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
	  String paramuser = request.getParameter("username");
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
                  <img src="<%=acc.selectByUsername(paramuser).getProf_image() %>" alt="" class="img-responsive profile-photo" />
                  <h3><%=acc.selectByUsername(paramuser).getUsername() %></h3>
                  <% 
                  int role = acc.selectByUsername(paramuser).getRole();
                  if (role == 0){%>
                  	<p class="text-muted">Utente</p>
                  <%}else if(role==1){ %>
                  	<p class="text-muted">Amministratore</p>
                  <%}%>
                </div>
              </div>
              <div class="col-md-9">
                <ul class="list-inline profile-menu">
                  <li><a href="timline.jsp?username=<%=request.getParameter("username")%>" class="active">Profilo</a></li>
                </ul>
                <form action="follow?username=<%=request.getParameter("username")%>" method="post">
	                <ul class="follow-me list-inline">
	                  <%if(!(user.getUsername().equals(paramuser))){%>
		                  <li><%=Integer.toString(acc.selectFollowers(request.getParameter("username"))) %> followers</li>
		                  <%if(acc.checkFollow(user.getUsername(), request.getParameter("username"))){ %>
		                  	<li><button type="submit" class="btn-primary">Smetti di seguire</button></li>
		                  <%}else{ %>
		                  	<li><button type="submit" class="btn-primary">Segui</button></li>
		                  <%} %>
	                  <%} else{%>
	                  	  <li><%=Integer.toString(acc.selectFollowers(request.getParameter("username"))) %> persone ti seguono</li>
	                  <%}%>
	                </ul>
                </form>
              </div>
            </div>
          </div><!--Timeline Menu for Large Screens End-->

          <!--Timeline Menu for Small Screens-->
          <div class="navbar-mobile hidden-lg hidden-md">
            <div class="profile-info">
              <img src="<%=acc.selectByUsername(paramuser).getProf_image() %>" alt="" class="img-responsive profile-photo" />
                  <h3><%=acc.selectByUsername(paramuser).getUsername() %></h3>
                  <% 
                  role = acc.selectByUsername(paramuser).getRole();
                  if (role == 0){%>
                  	<p class="text-muted">User</p>
                  <%}else if(role==1){ %>
                  	<p class="text-muted">Admin</p>
                  <%}%>
            </div>
            <div class="mobile-menu">
              <ul class="list-inline">
                <li><a href="timline.jsp?username=saibucomputer" class="active">Profilo</a></li>
              </ul>
 
              <form action="follow?username=<%=request.getParameter("username")%>" method="post">
	                <ul class="follow-me list-inline">
	                  <%if(!(user.getUsername().equals(request.getParameter("username")))){%>
		                  <%if(acc.checkFollow(user.getUsername(), request.getParameter("username"))){ %>
		                  	<li><button type="submit" class="btn-primary">Smetti di seguire</button></li>
		                  <%}else{ %>
		                  	<li><button type="submit" class="btn-primary">Segui</button></li>
		                  <%} %>
	                  <%} else{%>
	                  <%}%>
	                </ul>
                </form>
            </div>
            </div><!--Timeline Menu for Small Screens End-->

        </div>
        <div id="page-contents">
          <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-7">

             <!-- Post Create Box
            ================================================= -->
            <%if(user.getUsername().equals(request.getParameter("username"))){%>
            	<form action = "insert_post" method="post">
	            <div class="create-post">
	            	<div class="row">
	            		<div class="col-md-7 col-sm-7">
	                  <div class="form-group">
	                    <img src="<%=user.getProf_image() %>" alt="" class="profile-photo-md" />
	                    <textarea name="texts" id="exampleTextarea" cols="30" rows="1" class="form-control" placeholder="Scrivi i tuoi pensieri qui.." style="width:100%"></textarea>
	                  </div>
	                </div>
	            		<div class="col-md-3 col-sm-3">
	                  <div class="tools">
	 
	                    <button type="submit" class="btn btn-primary pull-right">Pubblica</button>
	                  </div>
	                </div>
	            	</div>
	            </div>
            </form><!-- Post Create Box End-->
            <%}else{ %>
            	<form action = "insert_message?dest=<%=request.getParameter("username") %>" method="post">
	            <div class="create-post">
	            	<div class="row">
	            		<div class="col-md-7 col-sm-7">
	                  <div class="form-group">
	                    <img src="<%=user.getProf_image() %>" alt="" class="profile-photo-md" />
	                    <textarea name="text_mess" id="exampleTextarea" cols="30" rows="1" class="form-control" placeholder="Manda un messaggio privato" style="width:100%"></textarea>
	                  </div>
	                </div>
	            		<div class="col-md-3 col-sm-3">
	                  <div class="tools">
	 
	                    <button type="submit" class="btn btn-primary pull-right">Invia</button>
	                  </div>
	                </div>
	            	</div>
	            </div>
            </form>
            
            <%} %>
            

              <!-- Post Content
              ================================================= -->
              
              <%
              	ArrayList<Post> listapost = new ArrayList<Post>();
              	listapost = acc.selectPostByUsername(request.getParameter("username"));
              	for(int i = 0; i < listapost.size(); i++){
              %>
              	<div class="post-content">

                <div class="post-container">
                  <img src="<%=acc.selectByUsername(listapost.get(i).getUsername()).getProf_image() %>" alt="user" class="profile-photo-md pull-left" />
                  <div class="post-detail">
                    <div class="user-info">
                      <% String paramusername = listapost.get(i).getUsername(); %>
                      <h5><a href="timeline.jsp?username=<%=paramusername %>" class="profile-link"><%=listapost.get(i).getUsername() %></a></h5>
                      <p class="text-grey"><%=listapost.get(i).getTopic() %></p>
                      <p class="text-muted"><%=listapost.get(i).getPostdate() %></p>
                    </div>
                    <div class="reaction">
                      <%
                      	String currentidpost = Integer.toString(listapost.get(i).getIdpost());
                      	String currentpostlike = Integer.toString(acc.selectLikes(listapost.get(i).getIdpost()));
                      %>
                      <form action="insert_like_timeline" method="post">
                        <button type="submit" value="<%=currentidpost%>" name="likebutton" class="btn text-green"><i class="icon ion-thumbsup"></i><%=currentpostlike %></button>
                        <input type="text" style="visibility:hidden" name="redirect" value="<%=request.getParameter("username")%>">
                      </form>
                    </div>
                    <div class="line-divider"></div>
                    <div class="post-text">
                      <p><%=listapost.get(i).getText() %><i class="em em-anguished"></i> <i class="em em-anguished"></i> <i class="em em-anguished"></i></p>
                    </div>
                    <div class="line-divider"></div>
                    <%
                    ArrayList<Comment> listacomment = new ArrayList<Comment>();
                  	listacomment = acc.SELECT_COMMENTS(listapost.get(i).getIdpost());
                  	for(int j = 0; j < listacomment.size(); j++){
                    %>
                    <div class="post-comment">
                      <img src="<%=acc.selectByUsername(listacomment.get(j).getUsername_comment()).getProf_image() %>" alt="" class="profile-photo-sm" />
                      <p><a href="timeline.jsp?username=<%=acc.selectByUsername(listacomment.get(j).getUsername_comment()).getUsername() %>" class="profile-link"><%=listacomment.get(j).getUsername_comment() %></a><i> : </i><%=listacomment.get(j).getText_comment() %></p>
                    </div>
               		<% } %>
               
               
                    <div class="post-comment">
                    <form action="insert_comment" method="post" style="width:100%">
                    <div style="width:10%!important; display:inline-block; padding-left:10px">
                      <img src="<%=user.getProf_image() %>" alt="" class="profile-photo-sm" />
                    </div>
                    <div style="width:70%!important; display:inline-block;">
                      <input type="text" name="textscomment" class="form-control" style="width:100%" placeholder="Post a comment">
                    </div>
                    <div style="width:20%!important; display:inline-block; padding: 5px 0px 0px 5px">
                      <button type ="submit" name="currentbutton" value="<%=listapost.get(i).getIdpost()%>" class="btn btn-primary pull-right">Commenta</button>
                    </div>
                    </form>
                    </div>
                  </div>
                </div>
              </div>
              <%	
              	}
              %>
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