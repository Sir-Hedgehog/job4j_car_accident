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
                margin: 1.5em 0;
                text-transform: uppercase;
                text-align: center;
            }

            body {
                background: rgba(44, 3, 171, 0.12);
                display: flex;
                flex-direction: column;
            }

            .container {
                max-width: 90%;
            }

            p {
                color: red;
                font-size: larger;
            }

            section {
                font-size: large;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
            }

            .form-group {
                width: 50%;
            }

            #buttons {
                margin-top: 0.8em;
                display: flex;
                justify-content: space-between;
            }

            #sendId {
                margin-left: 2em;
            }

            input.btn {
                background-color: #fff;
                color: #8080ab;
                border-color: #8080ab;
            }

            input.btn:hover {
                background-color: #8080ab;
            }
        </style>
    </head>
    <%--@elvariable id="error" type="java.lang.String"--%>
    <%--@elvariable id="id" type="java.lang.Integer"--%>
    <body>
        <div class="container">
            <div class="row pt-2" id="blockOfHeader">
                <h3 id="header">Для редактирования заявления необходимо указать свой идентификатор!</h3>
            </div>
            <form action="<c:url value='/edit'/>" method="GET">
                <section>
                    <c:if test="${error != ''}">
                        <p>
                            <c:out value="${error}"/>
                        </p>
                    </c:if>
                    <div class="form-group">
                        <label for="id">Введите идентификатор заявления: </label>
                        <input type="text" class="form-control" id="id" name="id"/>
                    </div>
                    <div id="buttons">
                        <input id="sendId" type="submit" name="save" class="btn btn-outline-dark" value="Отправить"/>
                    </div>
                </section>
            </form>
        </div>
    </body>
</html>