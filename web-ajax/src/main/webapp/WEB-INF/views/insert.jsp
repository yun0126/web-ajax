<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름 : <input type="text" id="name"> <br>
	나이 : <input type="text" id="age"> <br>
	주소 : <input type="text" id="address"> <br>
	<button onclick="sendObj()">추가</button>
	<script>
	function sendObj() {
		const param = {
				name : document.querySelector('#name').value,
				age : document.querySelector('#age').value,
				address : document.querySelector('#address').value
		}
		const json = JSON.stringify(param);
		
		let xhr = new XMLHttpRequest();
		xhr.open('POST','/list/insert');
		xhr.setRequestHeader('Content-Type','application/json');
		xhr.onreadystatechange = function () {
			if(xhr.readyState == 4){
				if(xhr.status == 200){
					if(xhr.responseText == 1){
						alert('insert ok');
						location.href='/views/list';
					}else{
						alert('error');
					}
				}
			}
		}
		xhr.send(json);
	}
	</script>
</body>
</html>