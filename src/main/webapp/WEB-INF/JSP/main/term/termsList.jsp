<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<nav class="navigation">

    <a href="${CONTEXT}${CURRENT_MAPPING}/home.html">На
        главную
    </a>
    <a href="${CONTEXT}${CURRENT_MAPPING}${BACK_PAGE}">Назад
    </a>

</nav>

<div class="content">
    <div class="leftAlign">

        <form action="${CONTEXT}${CURRENT_MAPPING}/termsList.html"
              method="GET">

            <div class="highLightBlock">
                <h3>Выбрать семестр</h3>

                <select id="term_list_select" name="selectTerm">
                    <c:forEach
                            var="term" items="${terms}">
                        <c:choose>
                            <c:when test="${term.id  eq idCurrTerm }">
                                <option selected="selected" value="${term.id }">Семестр
                                        ${term.id }</option>
                            </c:when>

                            <c:otherwise>
                                <option value="${term.id }">Семестр ${term.id }</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <input type="submit" name="selectTerm" value="Выбрать">

                <h3>Длительность семестра: ${termDuration } недели</h3>
            </div>
        </form>


    </div>


    <div class="leftColumnBlock">
        <h3>Список дисциплин семестра</h3>

        <table>
            <tr>
                <th>Наименование дисциплины</th>
            </tr>
            <c:forEach var="discipline" items="${disciplines }">
                <tr>
                    <td> ${discipline }</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="rightColumnBlock">
        <c:if test="${CURRENT_ROLE eq 1}">
            <input class="button" type="button" name="createTerm"
                   value="Создать семестр" onclick="location.href='${CONTEXT}${CURRENT_MAPPING}/termCreating.html'">
            <input class="button" type="button"
                   value="Модифицировать текущий семестр..." id="disciplineModifyButton"
                   name="modifying" onclick="modifyTerm()">
            <input class="button" type="button" name="studentDeleteButton"
                   value="Удалить текущий семестр" id="disciplineDeleteButton" onclick="deleteTerm()">
        </c:if>
    </div>

</div>