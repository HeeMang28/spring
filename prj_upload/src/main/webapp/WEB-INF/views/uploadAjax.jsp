<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>Upload with Ajax</h1>
	
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>
	
	<button id="uploadBtn">Upload</button>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<script>
		$(document).ready(function(){
			
			var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
			var maxSize = 5242880; // 5MB
			
			function checkExtension(fileName, fileSize){
				if(fileSize >= maxSize){
					alert("파일 사이즈 초과");
					return false;
				}
				
				if(regex.test(fileName)){
					alert("해당 종류의 파일은 업로드할 수 없습니다.");
					return false;
				}
				return true;
			}
			
			$('#uploadBtn').on("click", function(e){
				
				var formData = new FormData();
				console.log("-----------빈 폼 생성 체크 ------------");
				console.log(formData);
				
				var inputFile = $("input[name='uploadFile']");
				console.log("----------보낸 파일 목록 체크----------");
				console.log(inputFile);
				
				var files = inputFile[0].files;
				
				console.log(files);
				console.log("-----------파일들만 뽑아서 체크-----------");
				console.log(files);
				
				// 파일 데이터를 폼에 집어넣기
				for(var i = 0; i < files.length; i++){
					if(!checkExtension(files[i].name, files[i].size)){
						return false; // 조건에 맞지않은 파일 포함시 onclick 이벤트 함수자체를 종료시켜버림
					}
					formData.append("uploadFile", files[i]);
				}
				
				$.ajax({
					url: '/uploadAjaxAction',
					processData: false,
					contentType: false,
					data: formData,
					type: 'POST',
					success: function(result){
						alert("Uploaded");
					}
				}); // ajax
			}); // uploadBtn.onclick
		}); // document ready
	</script>
</body>
</html>