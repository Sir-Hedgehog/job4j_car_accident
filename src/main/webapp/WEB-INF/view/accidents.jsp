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
            }

            body {
                background: #577655;
                display: flex;
                flex-direction: column;
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

            col {
                width: 11em;
            }

            .table-bordered {
                border: 3px solid #fff;
                border-spacing: 0;
                border-collapse: collapse;
            }

            .data, th.contained {
                background-color: #ab8a64;
            }

            table.table-bordered tr td.data, th.contained {
                vertical-align: middle;
                text-align: center;
                border: 3px solid #fff;
            }

            #picture, #big-border {
                border-right: 3px solid #fff;
            }
        </style>
    </head>
    <body>
    <div class="container">
        <div class="row pt-2" id="blockOfHeader">
            <h2 id="header">Список заявок по ДТП</h2>
        </div>
        <section>
            <table class="table table-bordered">
                <colgroup>
                    <col span="1">
                </colgroup>
                <tr>
                    <th class="contained">Время создания</th>
                    <th class="contained">Имя водителя</th>
                    <th class="contained" id="big-border">Фотография</th>
                    <th class="contained">Номер автомобиля</th>
                    <th class="contained">Адрес происшествия</th>
                    <th class="contained">Описание</th>
                <tr>
                <c:forEach items="${accidents}" var="accident">
                    <tr>
                        <td class="data"><c:out value="${accident.createDateTime}"/></td>
                        <td class="data"><c:out value="${accident.name}"/></td>
                        <td class="data" id="picture"><c:out value="${accident.image}"/></td>
                        <td class="data"><c:out value="${accident.number}"/></td>
                        <td class="data"><c:out value="${accident.address}"/></td>
                        <td class="data"><c:out value="${accident.text}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </div>
    </body>
</html>
