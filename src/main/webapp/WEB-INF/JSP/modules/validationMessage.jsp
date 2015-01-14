<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:if test="${VALIDATION_MESSAGE ne null }">
    <table id="table_for_error_message">
        <tr>
            <td class="error_message"><c:choose>

                <c:when test="${VALIDATION_MESSAGE eq 'CanNotCreateStudent'}">
                    Не удалось внести данные нового студента
                </c:when>
                <c:when test="${VALIDATION_MESSAGE eq 'CanNotModifyStudent'}">
                    Не удалось изменить данные студента
                </c:when>
                <c:when test="${VALIDATION_MESSAGE eq 'CanNotCreateDiscipline'}">
                    Не удалось внести новую дисциплину
                </c:when>

                <c:when test="${VALIDATION_MESSAGE eq 'CanNotDeleteDiscipline'}">
                    Не удалось удалить дисциплину
                </c:when>

                <c:when test="${VALIDATION_MESSAGE eq 'CanNotModifyDiscipline'}">
                    Не удалось изменить дисциплину
                </c:when>

                <c:when test="${VALIDATION_MESSAGE eq 'canNotCreateNewTerm'}">
                    Не удалось создать новый семестр
                </c:when>

                <c:when test="${VALIDATION_MESSAGE eq 'canNotAddDisciplinesOfNewTerm'}">
                    Не удалось добавить дисциплины семестра
                </c:when>

                <c:when test="${VALIDATION_MESSAGE eq 'CanNotModifyTerm'}">
                    Не удалось изменить семестр
                </c:when>

                <c:when test="${VALIDATION_MESSAGE eq 'canNotDeleteDisciplinesOfTerm'}">
                    Не удалось удалить дисциплины семестра
                </c:when>

                <c:when test="${VALIDATION_MESSAGE eq 'canNotDeleteTerm'}">
                    Не удалось удалить семестр
                </c:when>

                <c:when test="${VALIDATION_MESSAGE eq 'CantSetMark'}">
                    Не удалось добавить оценку
                </c:when>

                <c:when test="${VALIDATION_MESSAGE eq 'CantDeleteMarks'}">
                    Не удалось удалить оценки
                </c:when>


            </c:choose></td>
        </tr>
    </table>
</c:if>





