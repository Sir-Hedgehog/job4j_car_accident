<%--@elvariable id="accident" type="ru.job4j.accidents.model.Accident"--%>
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
                margin-top: 1em;
                margin-bottom: 0.8em;
                text-transform: uppercase;
                text-align: center;
            }

            body {
                background: rgba(44, 3, 171, 0.12);
                display: flex;
                flex-direction: column;
            }

            .form-group {
                width: 50%;
            }

            .container {
                max-width: 90%;
            }

            section {
                font-size: large;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
            }

            p {
                color: red;
                font-size: larger;
            }

            #buttons {
                margin-top: 0.8em;
                display: flex;
                justify-content: space-between;
            }

            #updateButton {
                margin-right: 2em;
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
    <body>
        <div class="container">
            <div class="row pt-2" id="blockOfHeader">
                <h1 id="header">Редактирование заявления</h1>
            </div>
            <form action="<c:url value='/update'/>" method="POST">
                <section>
                    <c:if test="${error != ''}">
                        <p>
                            <c:out value="${error}"/>
                        </p>
                    </c:if>
                    <input type="hidden" name="id" value="${accident.id}"/>
                    <div class="form-group">
                        <label for="name">Введите Ваше ФИО: </label>
                        <input type="text" class="form-control" id="name" name="name" value="${accident.name}"/>
                    </div>
                    <div class="form-group">
                        <label for="address">Введите адрес происшествия: </label>
                        <input type="text" class="form-control" id="address" name="address" value="${accident.address}"/>
                    </div>
                    <div class="form-group">
                        <label for="text">Составьте описание происшествия: </label>
                        <textarea class="form-control" id="text" name="text">${accident.text}</textarea>
                    </div>
                    <div id="buttons">
                        <input id="updateButton" type="submit" name="save" class="btn btn-outline-dark" value="Обновить заявление"/>
                    </div>
                </section>
            </form>
        </div>
    </body>
</html>
