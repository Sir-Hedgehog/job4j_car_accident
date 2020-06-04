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
                color: #fff;
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
                margin-top: 1.5em;
                font-size: large;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
            }

            .table-bordered {
                border: 3px solid #fff;
                border-spacing: 0;
                border-collapse: collapse;
            }

            th.contained {
                background-color: #8080ab;
            }

            td.data {
                background-color: #a09dab;
            }

            table.table-bordered tr td.data, th.contained {
                vertical-align: middle;
                text-align: center;
                border: 2px solid #fff;
            }

            #buttons {
                margin-top: 0.8em;
                display: flex;
                justify-content: space-between;
            }

            #createButton {
                margin-left: 2em;
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
    <%--@elvariable id="accidents" type="java.util.Map"--%>
    <body>
        <div class="container">
            <div class="row pt-2" id="blockOfHeader">
                <h1 id="header">Список заявок по ДТП</h1>
            </div>
            <section>
                <table class="table table-bordered">
                    <tr>
                        <th class="contained">Имя водителя</th>
                        <th class="contained">Описание</th>
                        <th class="contained">Адрес происшествия</th>
                    <tr>
                    <c:forEach items="${accidents}" var="accident">
                        <tr>
                            <td class="data"><c:out value="${accident.value.name}"/></td>
                            <td class="data"><c:out value="${accident.value.text}"/></td>
                            <td class="data"><c:out value="${accident.value.address}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </section>
            <div id="buttons">
                <form action="<c:url value='/create'/>" method='POST'>
                    <input id="createButton" type="submit" name="create" class="btn btn-outline-dark" value="Создать заявление"/>
                </form>
                <form action="<c:url value='/checkId'/>" method='POST'>
                    <input id="updateButton" type="submit" name="update" class="btn btn-outline-dark" value="Редактировать заявление"/>
                </form>
            </div>
        </div>
    </body>
</html>
