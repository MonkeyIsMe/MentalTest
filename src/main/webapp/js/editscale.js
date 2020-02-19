/*
 * 获取url参数
 * */
var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?ScaleId=");
var sid = argsIndex[1];

var bigClassArr = new Array();// 保存大类数据
var smallClassArr;// 保存小类数据
var bidClassId, smallClassId;
window.onload = function() {
	getBigClass(); // 获取大类接口
	var save = document.getElementById("saveBtn");
	save.onclick = function() {
		getSaveData();
	}
	
    $.post(
            "/MentalTest/QuerySingleScale",
            {
            	scale_id:sid
            },
            function(data) {
                var data = JSON.parse(data);
                //console.log(data);
            	$('#introInfo').val(data.ScaleInfo)
            	$('#guideInfo').val(data.ScaleGuide);
            	$('#tableName').val(data.ScaleName);
            }
        );
}
/**
 * 获取大类的接口
 */
function getBigClass() {
	$.ajax({
		type : "POST",
		url : "/MentalTest/QueryAllFkind",
		dataType : "json",
		data : {},
		success : function(data) {
			var Select = document.getElementById("Select1");
			var bigStr = "";
			bigClassArr = data.Array;
			//console.log(bigClassArr);
			for (var i = 0; i < bigClassArr.length; i++) {
				var bc = document.createElement("option");
				bc.setAttribute("id", bigClassArr[i].fkindId);
				bc.innerHTML = bigClassArr[i].fkindName;
				Select.appendChild(bc);
			}
			var id1 = $("#Select1 option:selected").attr("id");
			bidClassId = id1;
			getSmallClass(id1);// 获取小类初始接口数据
			$("#Select1").change(function() {// 监听列表选项的变化
				var id2 = $("#Select1 option:selected").attr("id");
				bidClassId = id2;
				getSmallClass(id2);// 获取变化后的小类接口数据
			});
		},
	});
}
/**
 * 获取小类接口
 */
function getSmallClass(fk_id) {
	$.ajax({
		type : "POST",
		url : "/MentalTest/QueryAllSkind",
		dataType : "json",
		data : {
			fk_id : fk_id
		},
		success : function(data) {
			var Select = document.getElementById("Select2");
			smallClassArr = data.Array;
			//console.log(smallClassArr);
			$("#Select2").empty(); // 创建前先清空select标签
			for (var i = 0; i < smallClassArr.length; i++) {
				var bc = document.createElement("option");
				bc.setAttribute("id", smallClassArr[i].SkindId);
				bc.innerHTML = smallClassArr[i].SkindName;
				Select.appendChild(bc);
			}
			var id1 = $("#Select2 option:selected").attr("id");
			smallClassId = id1;
			$("#Select1").change(function() {// 监听列表选项的变化
				var id2 = $("#Select2 option:selected").attr("id");
				smallClassId = id1;
			});
		},
	});
}
/**
 * 获取提交数据的接口
 */
function getSaveData() {

	var scale_info, scale_guide, scale_name;
	var $textArea1 = $('#introInfo');
	var $textArea2 = $('#guideInfo');
	var $textArea3 = $('#tableName');
	scale_info = $textArea1.val();
	scale_guide = $textArea2.val();
	scale_name = $textArea3.val();
	if (document.getElementById("tableName").value == '') {
		alert("请输入量表名称!");
		return false;
	} else {
		
		   $.post(
		            "/MentalTest/UpdateScale",
		            {
						scale_id:sid,
						scale_name : scale_name,
						scale_info : scale_info,
						scale_guide : scale_guide,
						sk_id : smallClassId,
						fk_id : bidClassId,
		            },
		            function(data) {
		                var data = JSON.parse(data);
		                //console.log(data);
		                result = data.result;
		                if(result == "Success"){
		                	alert("保存成功！");
		                	window.location.replace(url);
		                }
		                else{
		                	alert("保存失败！");
		                	window.location.replace(url);
		                }
		            }
		        );
		
/*		$.ajax({
			type : "POST",
			url : "/MentalTest/UpdateScale",
			dataType : "json",
			data : {
				scale_id:sid,
				scale_name : scale_name,
				scale_info : scale_info,
				scale_guide : scale_guide,
				sk_id : smallClassId,
				fk_id : bidClassId,
			},
			success : function(data) {
				window.location.replace(url);
				if (data != null) {
					console.log(data);
					alert("保存成功！");
				} else {
					alert("保存失败！");
				}
			},
		});*/
	}
}