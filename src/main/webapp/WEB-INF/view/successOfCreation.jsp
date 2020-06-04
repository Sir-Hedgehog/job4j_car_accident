<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset='utf-8'/>
        <title>Список ДТП</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9.10.12/dist/sweetalert2.all.min.js" crossorigin="anonymous"></script>
        <style>
            html, body {
                height: 100%;
                color: #8080ab;
                font-family: Arial, Tahoma, sans-serif;
            }

            #blockOfHeader {
                display: inline;
            }

            #header {
                margin-top: 1.4em;
                text-transform: uppercase;
                text-align: center;
                color: #8080ab;
            }

            body {
                background: rgba(44, 3, 171, 0.12);
                display: flex;
                flex-direction: column;
            }

            .container {
                max-width: 90%;
            }

            section {
                margin-top: 2em;
                font-size: large;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
            }

            p {
                font-size: larger;
                margin-bottom: 2.5rem;
            }

            a {
                color: #8080ab;
                border: 2px solid #8080ab;
                padding: 5px;
                text-decoration: none;
                background-color: white;
                border-radius: 8px;
                transition: 0.4s;
            }

            a:hover {
                background-color: #8080ab;
                color: white;
                border: 2px solid #8080ab;
                text-decoration: none;
            }
        </style>
    </head>
    <%--@elvariable id="id" type="java.lang.Integer"--%>
    <body>
        <div class="container">
            <div class="row pt-2" id="blockOfHeader">
                <h1 id="header">Заявление успешно зарегистрировано!</h1>
            </div>
            <section>
                <p>Идентификатор Вашего заявления: <c:out value="${id}"/></p>
                <a href="<c:url value='/'/>">Перейти к таблице с существующими правонарушениями</a>
            </section>
        </div>
    </body>
</html>
