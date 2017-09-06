<!doctype html>
<%@ page import="java.text.*,java.util.*" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Simple TODO List</title>

        <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    </head>
    <body>
        <div class="logout-div">
            <a class="logout-button" href="/portal/user/logout">Logout</a>
        </div>
        <div id="header-div">

        </div>

        <div   class="div-button-add"  align="center" class="add-todo">
            <button class="button-add add-task" type="button" onclick="">Add TODO List</button>
        </div>
        <script src="<c:url value="/js/libs/jquery/jquery.min.js" />"></script>
        <script src="<c:url value="/js/libs/bootstrap.min.js" />"></script>
        <script>
            var deleteTask = function (id, idTodo) {
                $.post('${pageContext.servletContext.contextPath}/portal/user/delete', {id: id, idTodo: idTodo}, function (data) {
                    if (data === "ok") {
                        $("#task-tr-"+id).remove();
                    }
                });
            };
//            $(document).on('click', '#save-button', function (e) {
            var saveTask = function (id) {
                var formURL = "<c:url value="/portal/user/addTask"/>";
                var task = $("#value-task-" + id).val();
                var posting = $.post(formURL, $("#save-form-" + id).serialize());
                posting.done(function (data) {
                    console.log(data);
                    console.log($('#task-table-' + id).length);
                    $('#task-table-' + id + ' tbody  tr:last').after('<tr id="task-tr-' + data.id + '">\n\
    <td><input type="checkbox"  id="' + data.id + '"></td>\n\
    <td id="task-' + data.id + '">' + data.task + '</td>\n\
    \n\
    <td  id="edit-' + data.id + '"><a href="#" class="btn-lg" \n\
data-toggle="modal" data-target="#myModal"  data-id="' + data.id + '"> Edit </a></td>\n\
    <td> <a href="#" onclick="deleteTask(' + data.id+ ', '+id+')";>Delete</a></td>\n\
    </tr>');
                });
            };


            $(document).on('click', '.button-add', function (e) {
                var formURL = "<c:url value="/portal/user/add"/>";
                var posting = $.post(formURL);
                posting.done(function (data) {
//                    console.log(data);insertBefore();
                    console.log(data.id);
                    console.log(data.name);
                    $('#header-div').append('<div class="table" id="' + data.id + '">\n\
    <div class="table-header">\n\
    <span class="fa fa-calendar" aria-hidden="true"></span><span>Complete the task ssdsdfsdfasd</span><span  class="fa fa-calendar" aria-hidden="true"></span><span  class="fa fa-calendar" aria-hidden="true"></span>\n\
    \n\
    </div> \n\
  <form  name="form" id="save-form-' + data.id + '"><div class="heder-add"><input type="hidden" name = "todoListId" value="' + data.id + '"><input class="input-task" type="text" name="task" id="value-task-' + data.id + '"/> <button class="add-task save-button" id="' + data.id + '" onclick="saveTask(' + data.id + ')" href="#" type="button">Add task</button> </div> </form>\n\
<table class="simple-little-table" id="task-table-' + data.id + '">\n\
  <tr>\n\
<th style="width: 10%"> </th>\n\
<th ></th>\n\
 <th style="width: 10%"></th>\n\
 <th style="width: 10%"></th>\n\
</tr>\n\
</table>\n\
</div>\n\
   \n\
    ');

                });
            });
        </script>
    </body>
</html>
