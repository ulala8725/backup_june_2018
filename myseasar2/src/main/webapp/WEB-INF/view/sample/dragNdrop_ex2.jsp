<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:te="http://www.seasar.org/teeda/extension" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>drag and drop example</title>
<script type="text/javascript" src="resources/jquery-3.3.1.js"></script>
<style type="text/css">
    div>div{
        display: inline-block;
        padding: 10px;
        background-color: #aaa;
        margin: 3px;
    }
    #dropzone{
    	width: 300px;
    	height: 100px;
    	border: 1px solid;
    }
</style>
</head>
<body>
<script>
$(document).ready(function(){
	$(".f").on("dragstart", dragStart);
	$("#dropzone").on("dragover", dragOver);
	$("#dropzone").on("drop", drop);
	$(document).on("dragstart", ".fd", dragStart);
	$(document).on("dragover", ".fd", dragOver);
	$(document).on("dragleave", ".fd", dragLeave);
});

function dragStart(event){
	event.originalEvent.dataTransfer.setData("text", event.target.textContent)
	event.originalEvent.dataTransfer.effectAllowed = "copyMove";
};

function dragOver(event){
	event.preventDefault();
	event.stopPropagation();
};

function drop(event){
	event.preventDefault();
	event.stopPropagation();
	// dataTransfer.getData(): only executed in ondrop function
	var textContent = event.originalEvent.dataTransfer.getData("text/plain");
	
	// to compare with between fd's textContent and this.textContent
	for(i=0; i<$(".fd").length; i++){
		if(textContent == $(".fd")[i].textContent || textContent == ""){
			return;
		}
	}
	var addRow = "";
	addRow += "<div draggable='true' class='fd'>";
	addRow += textContent;
	addRow += "</div>";
	$("#dropzone").append(addRow);
	
// 	event.originalEvent.dataTransfer.clearData();
}

function dragLeave(event){
	event.preventDefault();
	event.stopPropagation();
	var addRow = "";
// 	var textContent = this.textContent; // $(this).text()
	var array = $(".fd");
	for(i=0; i<array.length; i++){
		if($(this).text() == array[i].textContent || $(this).text() == ""){
			$(this).removeClass();
		}  
		if(array[i].className == "fd"){
			addRow += "<div draggable='true' class='fd'>";
			addRow += array[i].textContent;
			addRow += "</div>";
		}
	}
	$("#dropzone").empty();
	$("#dropzone").append(addRow);

// 	event.originalEvent.dataTransfer.clearData();
}

</script>
<div>
	<div draggable="true" class="f">Mexico</div>
	<div draggable="true" class="f">Sweden</div>
	<div draggable="true" class="f">Korea</div>
	<div draggable="true" class="f">Germany</div>
</div>
<hr>
#F
<div id="dropzone" ></div>

</body></html>