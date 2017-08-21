<!DOCTYPE html>
<%@ page import="java.text.*,java.util.*" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello Table</h1>
        <table>
            <tr>
                <th>id</th>
                <th>name</th> 
            </tr>
            <c:forEach items="${contacts}" var="contact">  
                <tr>
                    <td>${contact.id}</td>
                    <td>${contact.name}</td> 
                </tr>
            </c:forEach>
        </table>
        <a href="<c:url value="/hello/contacts?nameFilter=^(?!A).*$"/>">Без буквы А</a>

        ${message}
    </body>
</html>
