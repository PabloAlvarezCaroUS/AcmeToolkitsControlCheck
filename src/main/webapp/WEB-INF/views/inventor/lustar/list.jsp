<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.lustar.list.label.subject" path="subject" />
	<acme:list-column code="inventor.lustar.list.label.creationMoment"
		path="creationMoment" />
	<acme:list-column code="inventor.lustar.list.label.income"
		path="income" />
</acme:list>

<acme:button code="inventor.lustar.list.button.create"
	action="/inventor/lustar/create" />