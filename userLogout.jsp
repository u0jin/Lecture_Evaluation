
<%-- 로그인이 된 사용자가 로그아웃을 요청할때 처리 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"

	pageEncoding="UTF-8"%>

<%@ page import="java.io.PrintWriter"%>

<%

	session.invalidate();
// 모든 세션정보 파기

%>

<script>

	location.href = 'index.jsp';

</script>
