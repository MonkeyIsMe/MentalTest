/**
 * Created by CallMeDad on 2020/02/15.
 */

var row = 1;  //页数
var count; //总记录数
var pid,pname,pbirthday,psex,pidcard,pnation,pplace,pnum;

var nations = ["汉族", "蒙古族", "回族", "藏族", "维吾尔族", "苗族", "彝族", "壮族", "布依族", "朝鲜族", "满族", "侗族", "瑶族", "白族",
	"土家族", "哈尼族", "哈萨克族", "傣族", "黎族", "傈僳族", "佤族", "畲族", "高山族", "拉祜族", "水族", "东乡族", "纳西族", "景颇族", "柯尔克孜族", "土族", 
	"达斡尔族", "仫佬族", "羌族", "布朗族", "撒拉族", "毛南族", "仡佬族", "锡伯族", "阿昌族", "普米族", "塔吉克族", "怒族", "乌孜别克族", "俄罗斯族", "鄂温克族", 
	"德昂族", "保安族", "裕固族", "京族", "塔塔尔族", "独龙族", "鄂伦春族", "赫哲族", "门巴族", "珞巴族", "基诺族"];




$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"MentalTest/CountPatient",
			{
				
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.Count;
				count = Math.ceil(sum/25);
				var total = "共" + Math.ceil(sum/25) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);

	
});


$(function(){
    $.post(
        "MentalTest/QueryPatientPageSize",
        {
            page:row,
            limit:25,
            
        },
        function(data) {
            var data = JSON.parse(data);
            //console.log(data);
                for( var i = 0; i < data.Array.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
                    var nid = data.Array[i].PatientNation;
                    nid --;
                    var nation = nations[nid];
                    
                    var sex = "男";
                    if(data.Array[i].PatientGender == 2) sex = "女";
                    
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data.Array[i].PatientId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].PatientName +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].PatientBirthday +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + sex  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].PatientIndentity +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + nation +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data.Array[i].PatientPlace  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].PatientNumber +"</td>");
			        $trTemp.append("<td>" + 
			        		'<a><span class="delete glyphicon glyphicon-minus" style="cursor:pointer;" data-toggle="modal" data-target="#del_myModal"></span></a>'
			        		 + '<a><span class="update glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:18px" data-toggle="modal" data-target="#up_Modal"></span></a>'
			        		+"</td>");
                    // $("#J_TbData").append($trTemp);
                    $trTemp.appendTo("#PatientList");
                }
        }
    );

});


function PrevPage(){	
	if(row == 1){
		alert("没有前一页了");
	}
	else{
		row--;
		$("#PatientList").html("");
	    $.post(
	            "MentalTest/QueryPatientPageSize",
	            {
	                page:row,
	                limit:25,
	                
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                //console.log(data);
	                for( var i = 0; i < data.Array.length; i++ ) {
	                    //动态创建一个tr行标签,并且转换成jQuery对象
	                    var $trTemp = $("<tr ></tr>");
	                    //往行里面追加 td单元格
	                    var nid = data.Array[i].PatientNation;
	                    nid --;
	                    var nation = nations[nid];
	                    
	                    var sex = "男";
	                    if(data.Array[i].PatientGender == 2) sex = "女";
	                    
				        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data.Array[i].PatientId +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].PatientName +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].PatientBirthday +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" + sex  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].PatientIndentity +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + nation +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" +data.Array[i].PatientPlace  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].PatientNumber +"</td>");
				        $trTemp.append("<td>" + 
				        		'<a><span class="delete glyphicon glyphicon-minus" style="cursor:pointer;" data-toggle="modal" data-target="#del_myModal"></span></a>'
				        		 + '<a><span class="update glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:18px" data-toggle="modal" data-target="#up_Modal"></span></a>'
				        		+"</td>");
	                    // $("#J_TbData").append($trTemp);
	                    $trTemp.appendTo("#PatientList");
	                }
	            }
	        );
	}
}

function NextPage(){
	if(row == count){
		alert("没有后一页了");
	}
	else{
		row ++;
		$("#PatientList").html("");
	    $.post(
	            "MentalTest/QueryPatientPageSize",
	            {
	                page:row,
	                limit:25,
	                
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                //console.log(data);
	                for( var i = 0; i < data.Array.length; i++ ) {
	                    //动态创建一个tr行标签,并且转换成jQuery对象
	                    var $trTemp = $("<tr ></tr>");
	                    //往行里面追加 td单元格
	                    var nid = data.Array[i].PatientNation;
	                    nid --;
	                    var nation = nations[nid];
	                    
	                    var sex = "男";
	                    if(data.Array[i].PatientGender == 2) sex = "女";
	                    
				        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data.Array[i].PatientId +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].PatientName +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].PatientBirthday +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" + sex  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].PatientIndentity +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + nation +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" +data.Array[i].PatientPlace  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].PatientNumber +"</td>");
				        $trTemp.append("<td>" + 
				        		'<a><span class="delete glyphicon glyphicon-minus" style="cursor:pointer;" data-toggle="modal" data-target="#del_myModal"></span></a>'
				        		 + '<a><span class="update glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:18px" data-toggle="modal" data-target="#up_Modal"></span></a>'
				        		+"</td>");
	                    // $("#J_TbData").append($trTemp);
	                    $trTemp.appendTo("#PatientList");
	                }
	            }
	        );
	}

}

$(document).ready(function(){
	
	  
	  $("#myTable").on('click','.delete',function(){
		    //获得当前行
		    var currentRow=$(this).closest("tr"); 
		    var col1 = currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    var col2 = currentRow.find("td:eq(1)").text(); //获得当前行第一个TD值
		    
		    pid = col1;
		    pname = col2;
		    $("#del_patient_info").empty();
		    $("#del_patient_info").append("是否删除名字为: "+ pname + "的病人");
		  });
	  
	  $("#myTable").on('click','.update',function(){
		    //获得当前行
		    var currentRow=$(this).closest("tr");
		    
		    var col1 = currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    
		    pid = col1;
		    
		    $.post(
					"MentalTest/QuerySinglePatient",
					{
						patient_id:pid,
					},
					function(data){
						var data = JSON.parse(data);
						//console.log(data);
					    pname = data.result.PatientName;
					    pbirthday = data.result.PatientBirthday;
					    psex = data.result.PatientGender;
					    pidcard = data.result.PatientIndentity;
					    pnation = data.result.PatientNation;
					    pplace = data.result.PatientPlace;
					    pnum = data.result.PatientNumber;
					}
					);
		    
		    $("#up_name").val(pname);
			$("#up_nation").find("option[value='"+ pnation +"']").attr("selected",true);
			$("#up_idcard").val(pidcard);
			$("#up_birthday").val(pbirthday);
			$("#up_place").val(pplace);
			$("#up_number").val(pnum);
			$("#up_gender").find("option[value='"+ psex +"']").attr("selected",true);
		    
		  });
	  
	 
	  $("#del_patient").click(function(){
		  	$.post(
					"MentalTest/DeletePatient",
					{
						patient_id:pid,
					},
					function(data){
						var data = JSON.parse(data);
						var result = data.result;
						if(result == "Success"){
							alert("删除成功！");
						    var url = "Patient.html";
						    window.location.replace(url);
						}
						else{
							alert("删除失败！");
						    var url = "Patient.html";
						    window.location.replace(url);
						}
					}
					);
	  })
	  
	  		  $("#add_patient").click(function(){
			
			var patient_name = $("#add_name").val();
			var patient_nation = $('#add_nation option:selected') .val();
			var patient_idcard = $("#add_idcard").val();
			var patient_birthday = $("#add_birthday").val();
			var patient_place = $("#add_place").val();
			var patient_number = $("#add_number").val();
			var patient_gender = $('#add_gender option:selected') .val();
			
		  	$.post(
					"MentalTest/AddPatient",
					{
						patient_name:patient_name,
						patient_birthday:patient_birthday,
						patient_gender:patient_gender,
						patient_identity:patient_idcard,
						patient_number:patient_number,
						patient_nation:patient_nation,
						patient_place:patient_place,
						user_id:1
					},
					function(data){
						var data = JSON.parse(data);
						var result = data.result;
						if(result == "Success"){
							alert("添加成功！");
						    var url = "Patient.html";
						    window.location.replace(url);
						}
						else{
							alert("添加失败！");
						    var url = "Patient.html";
						    window.location.replace(url);
						}
					}
					);
	  })
	  
		  $("#up_patient").click(function(){
			
			var patient_name = $("#up_name").val();
			var patient_nation = $('#up_nation option:selected') .val();
			var patient_idcard = $("#up_idcard").val();
			var patient_birthday = $("#up_birthday").val();
			var patient_place = $("#up_place").val();
			var patient_number = $("#up_number").val();
			var patient_gender = $('#up_gender option:selected') .val();
			
		  	$.post(
					"MentalTest/UpdatePatient",
					{
						patient_id:pid,
						patient_name:patient_name,
						patient_birthday:patient_birthday,
						patient_gender:patient_gender,
						patient_identity:patient_idcard,
						patient_number:patient_number,
						patient_nation:patient_nation,
						patient_place:patient_place,
						user_id:1
					},
					function(data){
						var data = JSON.parse(data);
						var result = data.result;
						if(result == "Success"){
							alert("更新成功！");
						    var url = "Patient.html";
						    window.location.replace(url);
						}
						else{
							alert("更新失败！");
						    var url = "Patient.html";
						    window.location.replace(url);
						}
					}
					);
	  })
	  
});

function refresh(){
	window.location.replace("Patient.html");
}