<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pl">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Zaplanuj Jedzonko</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head>
body>
<%@ include file="fixed-app_header.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@ include file="fixed-app_dashboard.jsp" %>

        <div class="m-4 p-3 width-medium ">
            <div class="dashboard-content border-dashed p-3 m-4">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href="#" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <div class="schedules-content-header">
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">Plan jarski na bezmięsny tydzień</p>
                            </div>
                        </div>
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">
                                    Pojęcie kuchnia wegetariańska określa pożywienie, które ani nie zawiera mięsa, ani nie zostało przygotowane na bazie pochodzącej z mięsa (np. na rosole drobiowym). Laktoowowegetarianie (najczęściej spotykany typ wegetarian w zachodnim świecie) spożywają nabiał, laktowegetarianie wykluczają jaja, ale nie inne produkty nabiałowe.
                                </p>
                            </div>
                        </div>
                    </div>

                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">Poniedziałek</th>
                            <th class="col-7"></th>
                            <th class="col-1"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                        <tbody class="text-color-lighter">
                        <tr class="d-flex">
                            <td class="col-2">śniadanie</td>
                            <td class="col-7">płatki owsiane z jagodami i komosą ryżową</td>
                            <td class="col-1 center">
                                <a href="<c:url value="/app/removeRecipeFromPlan"/>" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                            </td>
                            <td class="col-2 center">
                                <a href="app-details-schedules.html" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </tr>
                        <tr class="d-flex">
                            <td class="col-2">śniadanie</td>
                            <td class="col-7">płatki owsiane z jagodami i komosą ryżową</td>
                            <td class="col-1 center">
                                <a href="#" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                            </td>
                            <td class="col-2 center">
                                <a href="app-details-schedules.html" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </tr>
                        <tr class="d-flex">
                            <td class="col-2">śniadanie</td>
                            <td class="col-7">płatki owsiane z jagodami i komosą ryżową</td>
                            <td class="col-1 center">
                                <a href="#" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                            </td>
                            <td class="col-2 center">
                                <a href="app-details-schedules.html" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">Poniedziałek</th>
                            <th class="col-7"></th>
                            <th class="col-1"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                        <tbody class="text-color-lighter">
                        <tr class="d-flex">
                            <td class="col-2">śniadanie</td>
                            <td class="col-7">płatki owsiane z jagodami i komosą ryżową</td>
                            <td class="col-1 center">
                                <a href="#" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                            </td>
                            <td class="col-2 center">
                                <a href="app-details-schedules.html" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </tr>
                        <tr class="d-flex">
                            <td class="col-2">śniadanie</td>
                            <td class="col-7">płatki owsiane z jagodami i komosą ryżową</td>
                            <td class="col-1 center">
                                <a href="#" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                            </td>
                            <td class="col-2 center">
                                <a href="app-details-schedules.html" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </tr>
                        <tr class="d-flex">
                            <td class="col-2">śniadanie</td>
                            <td class="col-7">płatki owsiane z jagodami i komosą ryżową</td>
                            <td class="col-1 center">
                                <a href="#" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                            </td>
                            <td class="col-2 center">
                                <a href="app-details-schedules.html" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="fixed-bootstrap.jsp" %>
</body>
</html>