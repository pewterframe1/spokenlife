<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap core CSS -->
    
    <link rel="stylesheet" href="css/style2.css">

   
    <title>Login</title>
</head>
<body>
   <!-- ***** Header Area Start ***** -->
    <header class="header-area header-sticky">
      <div class="container">
          <div class="row">
              <div class="col-12">
                  <nav class="main-nav">
                      <!-- ***** Logo Start ***** -->
                      <a href="index.html" class="logo">
                          <img src="assets/images/Punto e virgola2.png" alt="" style="max-width: 180px; margin-top: -15px;">
                      </a>
                      <!-- ***** Logo End ***** -->
                  </nav>
              </div>
          </div>
      </div>
    </header>
    <!-- ***** Header Area End ***** -->


    <div class="container">
<div class="login-left">
    <div class="login-header">
        <h1>Benvenuto viaggiatore</h1>
        <p>Per favore accedi</p>
    </div>
    <form class="accedi-form" action="login" method="post">
        <div class="login-form-content">
            <div class="form-item">
            	<%if(request.getParameter("singup") == null){%>
            		<label for="username">Inserisci username</label>
               		<input id="username" type="text" name="username">
            	<%}else if(request.getParameter("singup").equals("true")){ %>
            	<p style="color:green">Registrazione avvenuta con successo</p>
            		<label for="username">Inserisci username</label>
               		<input id="username" type="text" name="username" value="<%=request.getParameter("username")%>">
            	<%} %>
            </div>
            <div class="form-item">
                <label for="password">Inserisci password</label>
                <input id="password" type="password" name="password">
            </div>
            <button type="submit">accedi</button>
            
            <script type="text/javascript">
                const button= document.querySelectorAll('button');
                button.forEach(btn=> {
                    btn.addEventListener('click', function(e) {
                        let x = e.clientX - e.target.offsetLeft;
                        let y = e.clientY - e.target.offsetTop;

                        let ripples = document.createElement('span');
                        ripples.style.left = x + 'px';
                        ripples.style.top = y + 'px';
                        this.appendChild(ripples);

                        setTimeout(() => {
                            ripples.remove()
                        },1000);

                    })
                })
            </script>
            <a href="registrazione.jsp">Non hai un account? Iscriviti!</a>
           
        <div class="login-form-footer">
            <a href="https://it-it.facebook.com/">
                <img width="30" src="https://upload.wikimedia.org/wikipedia/commons/0/05/Facebook_Logo_%282019%29.png" alt=""> Facebook login
            </a>
            <a href="https://www.google.com/intl/it/gmail/about/">
                <img width="30" src="https://upload.wikimedia.org/wikipedia/commons/5/53/Google_%22G%22_Logo.svg" alt=""> Google login
        </div>
        <br>
        
        </form>
</div>
</div>
</body>
</html>