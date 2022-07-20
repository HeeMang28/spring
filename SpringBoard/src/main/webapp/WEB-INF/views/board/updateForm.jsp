<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<style>
		#uploadResult {
			width:100%;
			background-color:gray;
		}
		#uploadResult ul {
			display: flex;
			flex-flow: row;
			justify-content : center;
			align-items: center;
		}
		#uploadResult ul li {
			list-style: none;
			padding: 10px;
			align-content: center;
			text-align: center;
		}
		#uploadResult ul li img {
			width:100px;
		}
	</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- insertForm페이지에서 폼양식을 가져온 다음
	얻어온 글에 대한 정보를 끼워넣어주세요. -->
	<form action="/board/update" method="post">
		<input type="hidden" name="page" value="${param.page }"/>
		<input type="hidden" name="searchType" value="${param.searchType }"/>
		<input type="hidden" name="keyword" value="${param.keyword }"/>
		<input type="text" name="title" value="${board.title }" requried/>
		<input type="text" name="writer" value="${board.writer  }" required/>
		<textarea name="content" requirer>${board.content }</textarea>
		<input type="hidden" name="bno" value="${board.bno }">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
		<input type="submit" id="submitBtn">
	</form>
	
	<div class="row">
		<h3 class="text-primary">첨부파일</h3>
		
		<div class="form-group uploadDiv">
			<input type='file' name='uploadFile' multiple>
		</div>
	<button id="uploadBtn">등록</button>
		
		<div id="uploadResult">
			<ul>
				<!-- 첨부파일이 들어갈 위치 -->
			</ul>
		</div>
	</div><!-- row -->
	
	<script>
	let csrfHeaderName = "${_csrf.headerName}"
	let csrfTokenValue="${_csrf.token}"
	// 어떤 글의 첨부파일인지 확인하기위해 bno를 선언해 받아넣어주세요.
	let bno = ${board.bno };
	
	// 정규표현식 : 예).com 끝나는 문장 등의 조건이 복잡한 문장을 컴퓨터에게 이해시키기 위한 구문
	var regex = new RegExp("(.*)\.(exe|sh|zip|alz)$");
						// 파일이름 .  exe|sh|zip|alz 인 경우를 체크함
	var maxSize =5242880; // 5Mb
	
	function checkExtension(fileName, fileSize){
		// 파일크기 초과시 종료시킴
		if(fileSize >= maxSize){
			alert("파일 사이즈 초과");
			return false;// return이 있어서 아래쪽 구문은 실행 안됨
		}
		// regex에 표현해둔 정규식과 일치하는지 여부를 체크, 일치하면 true, 아니면 false
		if(regex.test(fileName)){
			alert("해당 확장자를 가진 파일은 업로드할 수 없습니다.");
			return false;
		}
		return true;
	}
	
	
	
	
	// 익명함수 선언 및 호출
	// 우선 함수이기 때문에 호출한다는 점을 명시하기 위해 마지막에 () 를 추가로 붙여준다.
	(function(){
		$.getJSON("/board/getAttachList", {bno:bno}, function(arr){
			console.log(arr);
			
			let str = "";
			
			$(arr).each(function(i, attach){
				// 이미지파일인 경우
				if(attach.fileType){
					let fileCallPath = encodeURIComponent(attach.uploadPath + 
										"/s_" + attach.uuid + "_" + attach.fileName);
					
					str += `<li data-path='\${attach.uploadPath}' data-uuid='\${attach.uuid}'
							data-filename='\${attach.fileName}' data-type='\${attach.fileType}'>
								<div>
									<span>\${attach.fileName}</span>
									<button type='button' data-file='\${fileCallPath}' data-type='image'
										class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br/>
									<img src='/display?fileName=\${fileCallPath}'>
								</div>
							</li>`;
				} else {
					let fileCallPath = encodeURIComponent(attach.uploadPath + 
							"/" + attach.uuid + "_" + attach.fileName);
					// 이미지가 아닌 파일의 경우
					str += `<li data-path='\${attach.uploadPath}' data-uuid='\${attach.uuid}'
								data-filename='\${attach.fileName}' data-type='\${attach.fileType}'>
								<div>
									<span>\${attach.fileName}</span><br/>
									<button type='button' data-file='\${fileCallPath}' data-type='file' 
										class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br/>
									<img src='/resources/attach.png' width='100px' height='100px'>
								</div>
							</li>`;
				}
			});// .each 반복문 닫는부분
			// 위에서 str변수에 작성된 태그 형식을 화면에 끼워넣기
			$("#uploadResult ul").html(str);
		});
	})();// 익명함수 닫는부분
	
	
	$("#uploadResult").on("click", "button", function(e){
		if(confirm("선택한 파일을 삭제하시겠습니까?")){
			let targetLi = $(this).closest("li");
			targetLi.remove();
		}
	});
	

	let cloneObj = $(".uploadDiv").clone();
	
	$('#uploadBtn').on("click", function(e){
		
		var formData = new FormData();
		
		var inputFile = $("input[name='uploadFile']");
		
		var files = inputFile[0].files;
		console.log(files);
		
		// 파일 데이터를 폼에 집어넣기
		for(var i = 0; i < files.length; i++){
			if(!checkExtension(files[i].name, files[i].size)){
				return false;// 조건에 맞지않은 파일 포함시 onclick 이벤트 함수자체를 종료시켜버림
			}
			
			formData.append("uploadFile", files[i]);
		}
		console.log("--------------파일 적재 후 formData 태그 -------------");
		console.log(formData);
		
		$.ajax({
			url: '/uploadFormAction', 
			processData : false,
			contentType: false,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			data : formData,
			dataType:'json',
			type : 'POST',
			success : function(result){
				console.log(result);
				
				showUploadedFile(result);
				
				$(".uploadDiv").html(cloneObj.html());
			}
		}); // ajax
	});// uploadBtn onclick	
	
	let uploadResult = $("#uploadResult ul");
	
	function showUploadedFile(uploadResultArr){
		let str = "";
		
		$(uploadResultArr).each(function(i, obj){
			console.log(obj);
			console.log(obj.image);

			if(!obj.image){
				
				let fileCallPath = encodeURIComponent(obj.uploadPath + "/" + 
											obj.uuid + "_" + obj.fileName);
				
				str += `<li data-path='\${obj.uploadPath}' data-uuid='\${obj.uuid}'
							data-filename='\${obj.fileName}' data-type='\${obj.image}'>
							<div>
								<span>\${obj.fileName}</span><br/>
								<button type='button' data-file='\${fileCallPath}' data-type='file' 
									class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br/>
								<img src='/resources/attach.png' width='100px' height='100px'>
							</div>
						</li>`;
			} else{
				// str += `<li>\${obj.fileName}</li>`;
				// 수정 후 코드
				//썸네일은 display에 배치 						
				let fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" +
													obj.uuid + "_" + obj.fileName);
				// 실제 파일은 download 배치
				let fileCallPath2 = encodeURIComponent(obj.uploadPath +	"/" +
													obj.uuid + "_" + obj.fileName);
				console.log(fileCallPath2);
				
				str += `<li data-path='\${obj.uploadPath}' data-uuid='\${obj.uuid}'
						data-filename='\${obj.fileName}' data-type='\${obj.image}'>
							<div>
								<span>\${obj.fileName}</span>
								<button type='button' data-file='\${fileCallPath}' data-type='image'
									class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br/>
								<img src='/display?fileName=\${fileCallPath}'>
							</div>
						</li>`;
			}
		});
		uploadResult.append(str);
	}// showUploadedFile	
	
	// 제출버튼 막기
	$("#submitBtn").on("click", function(e){
		
		// 1. 버튼의 제출기능을 막습니다.
		e.preventDefault();
		
		// 2. let formObj = $("form");으로 폼태그를 가져옵니다.
		let formObj = $("form");
		
		// 3. 첨부파일과 관련된 정보를 hidden태그들로 만들어 문자로 먼저 저장합니다.
		let str = "";
	
		$("#uploadResult ul li").each(function(i, obj){

			// $(obj)에 대해서만 .data() 를 활용해 데이터를 얻어올 수 있음
			let jobj =$(obj);
			
			str += `<input type='hidden' name='attachList[\${i}].fileName' 
						value='\${jobj.data("filename")}'>
					<input type='hidden' name='attachList[\${i}].uuid' 
						value='\${jobj.data("uuid")}'>
					<input type='hidden' name='attachList[\${i}].uploadPath' 
						value='\${jobj.data("path")}'>
					<input type='hidden' name='attachList[\${i}].fileType' 
						value='\${jobj.data("type")}'>`
			
		});
		console.log(str);
		
		// 4. formObj에 append를 이용해 str을 끼워넣습니다.
		formObj.append(str);
		
		// 5. formObj.submit()을 이용해 제출기능이 실행되도록합니다.
		formObj.submit();	
	});	
	
	</script>
</body>
</html>






