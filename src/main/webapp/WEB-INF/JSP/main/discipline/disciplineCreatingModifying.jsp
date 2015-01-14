<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<nav class="navigation">
    <a href="${CONTEXT}${CURRENT_MAPPING}/home.php">На
        главную
    </a>
    <a href="${CONTEXT}${CURRENT_MAPPING}${BACK_PAGE}">Назад
    </a>
</nav>


<h2>
    <c:choose>
        <c:when test="${DISCIPLINE_BUTTON eq 1}">
            Для того, чтобы создать новую дисциплину, заполните поле и нажмите кнопку "Создать":
        </c:when>
        <c:otherwise>
            Для того, чтобы модифицировать дисциплину, введите новое значение поля и нажмите кнопку "Применить":
        </c:otherwise>
    </c:choose>

</h2>

<c:choose>
    <c:when test="${DISCIPLINE_BUTTON eq 1}">
        <form id="disciplineForm" class="newDiscipline" action="${CONTEXT }${CURRENT_MAPPING}/disciplineCreating.php"
        method="POST">
    </c:when>
    <c:otherwise>
        <form id="disciplineForm" class="newDiscipline" action="${CONTEXT }${CURRENT_MAPPING}/disciplinesModifying.php"
        method="POST">
    </c:otherwise>
</c:choose>

<input type="hidden" name="id" value="${discipline.id }"/>

<label>
            <span>
                Название
            </span>
    <input id="discipline_name" type="text" size="20" name="discipline_name" value="${discipline.name}" maxlength="24">
</label>

<div class="rightAlignInTable">

    <c:choose>
        <c:when test="${DISCIPLINE_BUTTON eq 1}">
            <input type="button" value="Создать" id="button" onclick="verifyDisciplineForm()">
        </c:when>
        <c:otherwise>
            <input type="button" value="Применить" id="button" onclick="verifyDisciplineForm()">
        </c:otherwise>
    </c:choose>
</div>
</form>


<jsp:include page="/WEB-INF/JSP/modules/validationMessage.jsp"/>

