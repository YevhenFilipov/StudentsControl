<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<nav class="navigation">
    <a href="/${CONTEXT}/${CURRENT_MAPPING}/home.html">На
        главную
    </a>
    <a href="/${CONTEXT}/${CURRENT_MAPPING}${BACK_PAGE}">Назад
    </a>
</nav>


<h2>
    <c:choose>
        <c:when test="${STUDENT_BUTTON eq 1}">
            Для создания студетна заполните все поля и нажмите кнопку "Создать".
        </c:when>
        <c:otherwise>
            Для модификации введите новые значения и нажмите кнопку "Применить".
        </c:otherwise>
    </c:choose>

</h2>

<c:choose>
    <c:when test="${STUDENT_BUTTON eq 1}">
        <form id="studentForm" class="newStudent" action="${CONTEXT }/${CURRENT_MAPPING}/studentCreating.html"
        method="POST" <%--onsubmit="verifyNewStudentForm()"--%>>
    </c:when>
    <c:otherwise>
        <form id="studentForm" class="newStudent" action="${CONTEXT }/${CURRENT_MAPPING}/studentModifying.html"
        method="POST">
    </c:otherwise>
</c:choose>

<input type="hidden" name="id" value="${student.id }"/>

<label>
            <span>
                Фамилия
            </span>
    <input id="first_name" type="text" size="20" name="first_name" value="${student.lastName}" maxlength="24">
</label>
<label>
            <span>
                Имя
            </span>
    <input id="name" type="text" size="20" name="name" value="${student.name }" maxlength="24">
</label>

<label>
            <span>
                Группа
            </span>
    <input id="group" type="text" size="20" name="group" value="${student.group }" maxlength="24">
</label>

<label>
            <span>
                Дата поступления
            </span>

    <input id="datepicker" type="text" size="20" name="date" value="${student.date }" readonly>

</label>

<c:choose>
    <c:when test="${STUDENT_BUTTON eq 1}">
        <input type="button" value="Создать" id="button" onclick="verifyNewStudentForm()">
    </c:when>
    <c:otherwise>
        <input type="button" value="Применить" id="button" onclick="verifyNewStudentForm()">
    </c:otherwise>
</c:choose>
</form>


<jsp:include page="/WEB-INF/JSP/modules/validationMessage.jsp"/>

