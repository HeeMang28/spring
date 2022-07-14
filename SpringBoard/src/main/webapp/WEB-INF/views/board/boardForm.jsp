<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/uploadAjax.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  글쓸때, 제목 글쓴이, 본문 을 채우고 submit을 눌러야 합니다.
	vo에 적힌 명칭을 감안해서 제목, 글쓴이 본문을 쓸 수 있도록 폼태그를 완성시켜주세요. -->
	<form action="/board/insert" method="post">
	제목 : <input type="text" name="title"> <br/>
	글쓴이 : <input type="text" name="writer"> <br/>
	내용 : <textarea name="content"></textarea> <br/>
	<input type="submit" value="글쓰기">
	</form>
	<form action="/board/list" method="get">
		<input type="submit" value="목록으로">
	</form>
	
		<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>
	
	<div class="uploadResult">
		<ul>
			<!--  업로드 된 파일들이 여기 나열됨. -->
		</ul>
	</div>
	
	<button id="uploadBtn">Upload</button>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<script>
		
		let csrfHeaderName = "${_csrf.headerName}";
		let csrfTokenValue="${_csrf.token}";
		
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
				
				var cloneObj = $(".uploadDiv").clone();
				
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
						url: '/uploadFormAction',
						   beforeSend : function(xhr) {
						        xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
						   },
						processData: false,
						contentType: false,
						data: formData,
						type: 'POST',
						dataType:'json',
						success: function(result){
							console.log(result);
							
							showUploadedFile(result);
							
							$(".uploadDiv").html(cloneObj.html());
						}
					}); // ajax
				}); // uploadBtn.onclick
				
				var uploadResult = $(".uploadResult ul");
				
				function showUploadedFile(uploadResultArr) {
					var str = "";
					
					$(uploadResultArr).each(function(i, obj){
						
						if(!obj.image){
							
							var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);	
							
							str += `<li data-path='\${obj.uploadPath}' data-uuid='$\{obj.uuid}'
										data-filename='\${obj.fileName}' data-type='\${obj.fileType}'>
										<a href='/download?fileName=\${fileCallPath}'>
											<img src='/resources/pngwing.com.png'>\${obj.fileName}
										</a>
										<span data-file='\${fileCallPath}' data-type='file'> X </span>
									</li>`;
						} else {
						//str += `<li>\${obj.fileName}</li>`;
						// 수정 코드
						
						//썸네일은 display에 배치
						let fileCallPath = encodeURIComponent(
								obj.uploadPath + "/s_" 
								+ obj.uuid + "_" + obj.fileName);
						// 다운로드는 download에 배치
						let fileCallPath2 = encodeURIComponent(
								obj.uploadPath + "/"
								+ obj.uuid + "_" + obj.fileName);
						
						str += `<li><a href='download?fileName=\${fileCallPath2}'>
						<img src='/display?fileName=\${fileCallPath}'>\${obj.fileName}</a>
						<span data-file='\${fileCallPath2}' data-type='image'> X </span>
						</li>`;
						}
					});
					
					uploadResult.append(str);
				} // showUploadedFile
				
				$(".uploadResult").on("click", "span", function(e){
					// 파일 이름을 span태그 내부의 data-file에서 얻어와서 저장
					let targetFile = $(this).data("file");
					// 이미지 여부를 span태그 내부의 data-type에서 얻어와서 저장
					let type = $(this).data("type");
					
					// 클릭한 span태그와 엮여있는 li를 targetLi에 저장
					let targetLi = $(this).closest("li");
					// 클릭한 li 요소를 화면에서 삭제함(파일은 남아있음.)
					$.ajax({
						url : '/deleteFile',
						   beforeSend : function(xhr) {
						        xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
						   },
						data: {fileName: targetFile, type:type},
						dataType: 'text',
						type: 'POST',
						success: function(result){
							alert(result);
							targetLi.remove();
						}
					}); // ajax
				}); // click span
				
			}); // document ready
		</script>
</body>
</html>