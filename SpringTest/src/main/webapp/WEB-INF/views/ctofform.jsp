<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<!--  form�� ������ּ���.
	�������� /ctof�̸� ���۸޼���� post�Դϴ�.
	cel�̶�� number Ÿ���� input�� �̿��� �µ��� �Է¹�����
	�����ư�� ������ �ش� �ּҿ� post������� ������ �� ������ �µ��� �����ϰ�
	�� ����� ȭ�鿡 �ѷ��ݴϴ�. -->
	<form action="/ctof" method="post">
	c �µ� : <input type="number" name="c" required><br/>
	<hr/>
	<input type="submit" value="����"><br/>
	</form>
</body>
</html>