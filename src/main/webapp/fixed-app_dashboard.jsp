<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul class="nav flex-column long-bg">
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/app/dashboard"/>">
            <span>Pulpit</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/app/recipe"/>">
            <span>Przepisy</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
<%--        <a class="nav-link" href="<c:url value="/app/plan"/>">--%>
            <span>Plany</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/appEditUserData">
            <span>Edytuj dane</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link disabled" href="/appEditPassword">
            <span>Zmień hasło</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/superAdminUsers.jsp">
            <span>Użytkownicy</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
</ul>