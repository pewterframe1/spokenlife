<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/reg.css" rel="stylesheet" type="text/css">
    <script src="https://kit.fontawesome.com/19e9dbc6b8.js" crossorigin="anonymous"></script>
    <title>Dati</title>
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
                          <img src="assets/images/Punto e virgola2.png" alt="" style="max-width: 180px; margin-top: -15px">
                      </a>
                      <!-- ***** Logo End ***** -->
                  </nav>
              </div>
          </div>
      </div>
    </header>
    <!-- ***** Header Area End ***** -->


    <div class="container">
        <div class="registrati-left">
            <div class="registrati-header">
                <h1>Eccoci qui!</h1>
                <p>Compila tutti i campo per registrarti!</p>
                <%if(request.getParameter("singup") == null){ %>
                <%}else if(request.getParameter("singup").equals("false")){%>
                	<p style="color:red">Le password non combaciano!</p>
                <%} %>
            </div>
            <form class="registrati-form" action="registrazione" method="post">
                <div class="registrati-form-content">
                    <div class="form-item">
                        <label for="name">Inserisci nome</label>
                        <input type="text" id="name" name="name" required>
                    </div>
 
                    <div class="form-item">
                        <label for="surname">Inserisci cognome</label>
                        <input type="text" id="surname" name="surname" required>
                    </div>
                    <div class="form-item">
                        <label for="email">Inserisci email</label>
                        <input type="email" id="email" name="email" required>
                        <br>
                        <br>
                        <br>
                        <div class="form-item">
                            <label for="username">Inserisci nome utente</label>
                            <input type="text" id="username" name="username" required>
                            <br>
                            <br>
                            <br>
                            <div class="form-item">
                                <label for="cf">Inserisci codice fiscale</label>
                                <input type="text" id="cf" name="cf" minlength="16" maxlength="16"
                                required>
                            </div>
                            <br>
                            <br>
                            <div class="form-item">
                                <label for="phone">Inserisci num. telefono</label>
                                <input type="text" id="phone" name="phone" required>
                            </div>
                            <br>
                            <br>
                            <!--SERVE PER VEDERE SE LE PASSWORD COINCIDONO-->
<script>
    var check = function() {
        if (document.getElementById('password').value ==
          document.getElementById('confirm_password').value) {
          document.getElementById('message').style.color = 'green';
          document.getElementById('message').innerHTML = 'matching';
        } else {
          document.getElementById('message').style.color = 'red';
          document.getElementById('message').innerHTML = 'not matching';
        }
      }
    </script>
    
    <!--SERVE PER VEDERE SE LE PASSWORD COINCIDONO-->
                            <div class="form-item">
                                <label for="password">Inserisci password</label>
                                <input type="password" id="password" name="password" minlength="8"
                                required  onkeyup='check();'  >
<!--img src="../assets/images/occhiopassw.png" id="mostrapassword" alt="show/hide"-->
                               


                            </div>
                            <br>
                            <br>
                            <div class="form-item">
                                <label for="password">Conferma password</label>
                                <input type="password" id="confirm_password" name="confirm_password" minlength="8"
                                required  onkeyup='check();'>
                                <span id='message'></span>
                            </div>
                            
                            <input type="checkbox" class="fa-solid fa-eye" onclick="showPwd()" name="show/hide">Mostra/nascondi password
                            
                            <script>
                                  function showPwd() {
                                    var input = document.getElementById('password');
                                    var input2 = document.getElementById('confirm_password');
                                    if (input.type === "password") {
                                      input.type = "text";
                                    } else {
                                      input.type = "password";
                                    }
                                    if (input2.type === "password") {
                                      input2.type = "text";
                                    } else {
                                      input2.type = "password";
                                    }
                                  }
                            </script>
                            <br>
                            <br>
                            
                                <fieldset>
                                    <legend>Inserisci genere</legend>
                                    <input type="radio" name="gender" value="maschio" />Maschio<br>
                                    <input type="radio" name="gender" value="femmina" />Femmina<br>
                                    <input type="radio" name="gender" value="femmina" />Non-binario<br>
                                    <input type="radio" name="gender" value="preferisco non specificarlo" checked/>Preferisco
                                    non specificarlo
                                </fieldset>
                            
                        </div>
                    </div>
                    <button type="submit">Registrati</button>
                    </div>
            </form>
            </div>
</div>
</body>

</html>