<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<nav class="navigation">

    <a href="${CONTEXT}/${CURRENT_MAPPING}/home.php">На
        главную
    </a>
    <a href="${CONTEXT}/${CURRENT_MAPPING}${BACK_PAGE}">Назад
    </a>

</nav>


<div class="content">
    <div class="fullWidthBlock">
        <div class="leftColumnBlock">
            <h3>
                Список дисциплин
            </h3>
        </div>
    </div>
    <div class="leftColumnBlock">
        <table>
            <tr>
                <c:if test="${CURRENT_ROLE eq 1}">
                    <th></th>
                </c:if>
                <th>Наименование дисциплины</th>
            </tr>
            <c:forEach var="discipline" items="${disciplines }">
                <tr>
                    <c:if test="${CURRENT_ROLE eq 1}">
                        <td><input type="checkbox" id="${discipline.id }"></td>
                    </c:if>
                    <td> ${discipline.name }</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="rightColumnBlock">

        <c:if test="${CURRENT_ROLE eq 1}">
            <input class="button" type="button" id="disciplineCreateButton" name="createDiscipline"
                   value="Создать дисциплину"
                   onclick="location.href='${CONTEXT}/${CURRENT_MAPPING}/disciplineCreating.php'">

            <input class="button" type="button"
                   value="Модифицировать выбранную дисциплину..." id="disciplineModifyButton"
                   name="modifying" onclick="modifyingDiscipline()">

            <input class="button" type="button" onclick="deleteDiscipline()" name="studentDeleteButton"
                   value="Удалить выбранную дисциплину" id="disciplineDeleteButton">
        </c:if>
    </div>

</div>