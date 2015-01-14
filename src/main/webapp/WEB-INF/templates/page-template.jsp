<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf8"/>
    <meta name="description" content="My web application"/>
    <meta name="keywords" content="jee, java, web, demo, webtasks"/>
    <meta name="author" content="fIL"/>
    <title>Demo web application</title>

    <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${CONTEXT }/resources/css/jquery-ui-1.10.4.custom.css"/>
    <link rel="stylesheet" type="text/css"
          href="${CONTEXT }/resources/css/myStyle.css"/>
    <script type="text/javascript"
            src="${CONTEXT }/resources/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript"
            src="${CONTEXT }/resources/js/webtasks.js"></script>
    <script type="text/javascript"
            src="${CONTEXT }/resources/js/jquery-ui-1.10.4.custom.js"></script>

    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script>
        $(function () {
            $("#datepicker").datepicker({
                dateFormat: "dd.mm.yy",
                changeMonth: true,
                changeYear: true
            });
        });

        var context = "${CONTEXT}";
        var mapping = "${CURRENT_MAPPING}";
    </script>


</head>

<body>
<div class="container">
    <header>
        <h1>
            Система управления студентами и их успеваемостью
        </h1>
    </header>
    <div class="logout">
        <a href="/logout.php">Logout</a>
    </div>
    <section class="section">
        <jsp:include page="${currentPage}" flush="true"/>
    </section>
</div>
</body>
</html>