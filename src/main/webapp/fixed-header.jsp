<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="page-header">
    <nav class="navbar navbar-expand-lg justify-content-around">
        <a href="<c:url value="/"/>" class="navbar-brand main-logo">
            Zaplanuj <span>Jedzonko</span>
        </a>
        <ul class="nav nounderline text-uppercase">
            <li class="nav-item ml-4">
                <a class="nav-link color-header" href="<c:url value="/login"/>">logowanie</a>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link color-header" href="<c:url value="/register"/>">rejestracja</a>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link" href="<c:url value="/about"/>">o aplikacji</a>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link" href="<c:url value="/recipes"/>">Przepisy</a>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link disabled" href="<c:url value="/contact"/>">Kontakt</a>
            </li>
        </ul>
    </nav>
</header>